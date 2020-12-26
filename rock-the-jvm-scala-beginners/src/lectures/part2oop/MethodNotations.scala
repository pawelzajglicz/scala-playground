package lectures.part2oop

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String, val age: Int = 20) {

    def likes(movie: String): Boolean = movie == favoriteMovie
    def hangOutWith(person: Person): String = s"$name is hanging out with ${person.name}"
    def +(person: Person): String = s"$name is hanging out with ${person.name}"
    def unary_! : String = s"$name, what the heck?!"
    def isAlive: Boolean = true
    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"
    def +(nickname: String): Person = new Person(s"$name ($nickname)", favoriteMovie)
    def unary_+ : Person = new Person(name, favoriteMovie, age + 1)
    def learns(subject: String) = s"$name is learning $subject"
    def learnsScala = this learns "Scala"
    def apply(number: Int): String = s"$name watched $favoriteMovie $number times"
  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println( mary likes "Inception")  // equivalent
  // infix notation + operator notation --> works only with methods with single parameter
  // syntactic sugar

  // "operators" in Scala
  val tom = new Person("Tom", "Fight Club")
  println(mary hangOutWith tom)
  println(mary + tom)
  println(mary.+(tom))
  println(1 + 2)
  println(1.+(2))

  // ALL OPERATORS ARE METHODS.
  // Akka actors have ! ?

  // prefix notation
  // unary operators
  val x = -1
  val y = 1.unary_-
  // unary_ prefix only works with - + ~ !
  println(!mary)
  println(mary.unary_!)

  /* // postfix notation
  println(mary.isAlive)
  println(mary isAlive)*/ // not turn on default now

  // apply
  println(mary.apply())
  println(mary()) // equivalent

  /*
    1. Overload the + operator
       mary + "the rockstar" => new Person "Mary (the rockstar)"
  */
    val maryTheRockstar = mary + "the Rockstar"
    println(maryTheRockstar.name)
    println(maryTheRockstar())

  /*
    2. Add an age to the Person class
       Add a unary + operator => new person with the age + 1
       +mary => mary with the age incrementer

  */

  println(mary.age)
  println((+mary).age)

  /*

    3. Add a "learns" method in the Person class => "Mary learns Scala"
       Add a learnScala method, calls learns method with "Scala"
       Use it in postfix notation

  */

  println(mary learns "cooking")
  // println(mary learnsScala)
  println(mary.learnsScala)

  /*

    4. Overload the apply method
       mary.apply(2) => "Mary watched Inception 2 times"
   */

  println(mary(2))
  println(mary.apply(3))

}
