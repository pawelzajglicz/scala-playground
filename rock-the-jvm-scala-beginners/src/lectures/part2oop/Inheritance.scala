package lectures.part2oop

object Inheritance extends App {

  // single class inheritance
  sealed class Animal {
    val creatureType = "wild"
    final def eat0 = println("nomnom")
    /*protected */def eat = println("nomnom")
  }

  class Cat extends Animal {
    def crunch = {
      eat
      println("crunch crunch")
    }
  }

  val cat = new Cat
  cat.eat0
  cat.crunch

  // constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }
  // class Adult(name: String, age: Int, idCard: String) extends Person -- wrong
  class Adult0(name: String, age: Int, idCard: String) extends Person(name, age)
  class Adult(name: String, age: Int, idCard: String) extends Person(name)

  // overriding
  class Dog(override val creatureType: String = "domestic") extends Animal {
    //override val creatureType: String = "domestic"
    override def eat: Unit = {
      super.eat
      println("crunch, crunch")
    }
  }

  final class Dog0(dogType: String) extends Animal {
    override val creatureType: String = dogType
  }

  // val dog = new Dog
  val dog = new Dog("K9")
  dog.eat
  println(cat.creatureType)
  println(dog.creatureType)

  // type substitution (broad: polymorphism)
  val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.eat

  // super

  // preventing overrides
  // 1 - use final on member
  // 2 - use final on the entire class
  // 3 - seal the class = extend classes in THIS FILE, prevent extends in other files
}
