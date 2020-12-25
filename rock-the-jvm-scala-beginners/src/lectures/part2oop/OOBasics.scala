package lectures.part2oop

object OOBasics extends App {

  val person = new Person("John", 26)
  println(person.age)
  println(person.x)
  person.greet("Daniel")
  person.greet()
  person.greet

  val writer1 = new Writer("Miroslav", "Zamboch", 1972)
  val writer2 = new Writer("Miroslav", "Zamboch", 1972)
  println(writer1.fullName)
  val novel1 = new Novel("Na ostrzu noża", 2007, writer1)
  println(novel1.authorAge())
  println(novel1.isWrittenBy(writer1))
  println(novel1.isWrittenBy(writer2))
  val novel2 = novel1.copy(2005)

  val counter0 = new Counter
  // val counter0 = new Counter()
  counter0.print
  val counter = new Counter(5)
  /*println(counter.value)
  println(counter.increment.value)
  println(counter.decrement(10).value)*/
  counter.print
  counter.increment.print
  counter.increment.increment.increment.increment.print
  counter.decrement(10).print
}

// constructor
class Person(name: String, val age: Int = 0) {
  // body
  val x = 2

  println(1 + 3)

  // method
  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")

  // overloading
  def greet(): Unit = println(s"Hi, I am $name")

  // multiple constructors
  def this(name: String) = this(name, 0)
  def this() = this("John Doe")
}

// class parameters are NOT FIELDS

/*
  Novel and a Writer

  Writer: first name, surname, year
    - method fullname

  Novel: name, year of release, author
    - authorAge
    - isWrittenBy(author)
    - copy (new year of release = new instance of Novel)
 */

class Writer(name: String, surname: String, val year: Int) {

  def fullName(): String = s"$name $surname"
}

class Novel(name: String, yearOfRelease: Int, author: Writer) {

  def authorAge(): Int = yearOfRelease - author.year
  def isWrittenBy(author: Writer) = author == this.author
  def copy(newYear: Int): Novel = new Novel(name, newYear, author)

}

/*
  Counter class
    - receives an int value
    - method current count
    - method to increment/decrement => new Counter
    - overload inc/dec to receive an amount
 */

class Counter(val value: Int = 0) {

  // def currentCount: Int = value

  // def increment = new Counter(value + 1) // immutability
  /*def increment() = new Counter(value + 1) // immutability
  def decrement() = new Counter(value - 1)
  def increment(amount: Int) = new Counter(value + amount)
  def decrement(amount: Int) = new Counter(value - amount)*/

  def increment: Counter = {
    println("incrementing")
    new Counter(value + 1)
  }

  def decrement: Counter = {
    println("decrementing")
    new Counter(value - 1)
  }

  def increment(number: Int): Counter = {
    if (number <= 0) this
    else increment.increment(number - 1)
  }

  def decrement(number: Int): Counter = {
    if (number <= 0) this
    else decrement.decrement(number - 1)
  }

  def print = println(value)
}