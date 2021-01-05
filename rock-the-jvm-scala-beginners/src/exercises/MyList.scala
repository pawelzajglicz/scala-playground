package exercises

abstract class MyList[+A] {

  /*
    head = first element of the list
    tail = remainder of the list
    isEmpty = is this list empty
    add(int) => new list with this element added
    toString => a string representation of the list
   */

  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String
  override def toString: String = "[" + printElements + "]"

  def map[B](transformer: A => B): MyList[B]
  def flatMap[B](transformer: A => MyList[B]): MyList[B]
  def filter(predicate: A => Boolean): MyList[A]

  // concatenation
  def ++[B >: A](list: MyList[B]): MyList[B]
}

case object Empty extends MyList[Nothing] {

  override def head: Nothing = throw new NoSuchElementException
  override def tail: MyList[Nothing] = throw new NoSuchElementException
  override def isEmpty: Boolean = true
  override def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
  override def printElements: String = ""

  // higher-order functions
  override def map[B](transformer: Nothing => B): MyList[B] = Empty
  override def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty
  override def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty
  override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
}


case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {

  override def head: A = h
  override def tail: MyList[A] = t
  override def isEmpty: Boolean = false
  override def add[B >: A](element: B): MyList[B] = new Cons(element, this)
  override def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements

  override def filter(predicate: A => Boolean): MyList[A] =
    if (predicate(h)) Cons(h, t.filter(predicate))
    else t.filter(predicate)

  override def map[B](transformer: A => B): MyList[B] =
    new Cons(transformer(h), t.map(transformer))

  override def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)

  override def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)
}

object ListTest extends App {
  /* val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
  println(list.head)
  println(list.tail.head)
  println(list.add(4).head)

  println(list.toString)
  println(list.add(4)) */
  val listOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val cloneListOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val listOfStrings: MyList[String] = new Cons("Hello", new Cons("Scala", Empty))
  val listOfNumberStrings: MyList[String] = new Cons("1", new Cons("2", Empty))

  println(listOfIntegers)
  println(listOfStrings)

  println(listOfIntegers.map(new Function1[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }))
  println(listOfIntegers.map((element: Int) => element * 2))
  println(listOfIntegers.map(element => element * 2))
  println(listOfIntegers.map(_ * 2))


  println(listOfNumberStrings.head.getClass)
 // println(listOfNumberStrings.map(StringToIntTransformer))
 // println(listOfNumberStrings.map(StringToIntTransformer).head.getClass)


 // println(listOfIntegers.filter(EvenPredicate))
  println(listOfIntegers.filter(new Function1[Int, Boolean] {
    override def apply(testable: Int): Boolean = testable % 2 != 0
  }))
  println(listOfIntegers.filter((testable: Int) => testable % 2 != 0))
  println(listOfIntegers.filter(testable => testable % 2 != 0))
  println(listOfIntegers.filter(_ % 2 != 0))


  val listOfIntegers1: MyList[Int] = new Cons(1, new Cons(4, new Cons(7, Empty)))
  val listOfIntegers2: MyList[Int] = new Cons(1, new Cons(2, Empty))

  println(listOfIntegers ++ listOfIntegers1)
  println(listOfIntegers1)

  println(listOfIntegers1.flatMap((element: Int) => new Cons[Int](element, new Cons[Int](element + 1, Empty))))
  println(listOfIntegers1.flatMap(element => new Cons[Int](element, new Cons[Int](element + 1, Empty))))
  println(listOfIntegers1.flatMap(new Function1[Int, MyList[Int]] {
    override def apply(element: Int): MyList[Int] = new Cons[Int](element, new Cons[Int](element + 1, Empty))
  }))

  println(listOfIntegers == cloneListOfIntegers)
}

/*
    1. Generic trait MyPredicate[-T] with a little method test(T) => Boolean
    2. Generic trait MyTransformer[-A, B] with a method transform(A) => B
    3. MyList:
        - map(transformer) => MyList
        - filter(predicate) => MyList
        - flatMap(transformer from A to MyList[B]) => MyList[B]

        class EvenPredicate extends MyPredicate[Int]
        class StringToIntTransformer extends MyTransformer[String, Int]

        pseudocode
        [1,2,3].map(n * 2) = [2,3,6]
        [1,2,3,4].filter(n % 2) = [2,4]
        [1,2,3].flatMap(n => [n, n+1]) => [1,2,2,3,3,4]
   */
/*
trait MyTransformer[-A, B] { // A => B
  def transform(element: A): B
}

object StringToIntTransformer extends MyTransformer[String, Int] {
  override def transform(text: String): Int = text.toInt
}

trait MyPredicate[-T] { // T => Boolean
  def test(testable: T): Boolean
}

object EvenPredicate extends MyPredicate[Int] {
  override def test(testable: Int): Boolean = testable % 2 == 0
}
*/
