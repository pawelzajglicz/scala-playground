package lectures.part2oop

object Objects /* extends App */{

  // SCALA DOES NOT HAVE CLASS-LEVEL FUNCTIONALITY ("static")
  object Person { // type + its only instance
    // "static"/"class" - level functionality
    val N_EYES = 2
    def canFly: Boolean = false

    // factory method
    def from(mother: Person, father: Person): Person = new Person("Bobbie")
    def apply(mother: Person, father: Person): Person = new Person("Bobbie")
  }

  class Person(val name: String) {
    // instance-level functionality
  }
  // COMPANIONS

  def main(args: Array[String]): Unit = {

  println(Person.N_EYES)
  println(Person.canFly)

  // Scala object = SINGLETON INSTANCE
  val mary = Person
  val john = Person
  println(mary == john)
  println(mary == Person)

  val mary0 = new Person("Mary")
  val john0 = new Person("John")
  println(mary0 == john0)

  val bobbie = Person.from(mary0, john0)
  val bobbie0 = Person.apply(mary0, john0)
  val bobbie1 = Person(mary0, john0)

  // Scala Applications = Scala object with
  // def main(args: Array[String]): Unit

  }
}
