package exercises

import com.sun.xml.internal.ws.client.sei.ResponseBuilder.Header

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
}

object Empty extends MyList[Nothing] {

override def head: Nothing = throw new NoSuchElementException
override def tail: MyList[Nothing] = throw new NoSuchElementException
override def isEmpty: Boolean = true
override def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
override def printElements: String = ""

}


class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {

  override def head: A = h
  override def tail: MyList[A] = t
  override def isEmpty: Boolean = false
  override def add[B >: A](element: B): MyList[B] = new Cons(element, this)
  override def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements

}

object  ListTest extends App {
  /* val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
  println(list.head)
  println(list.tail.head)
  println(list.add(4).head)

  println(list.toString)
  println(list.add(4)) */
  val listOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val listOfStrings: MyList[String] = new Cons("Hello", new Cons("Scala", Empty))

  println(listOfIntegers)
  println(listOfStrings)
}