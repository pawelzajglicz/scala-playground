package exercises

import com.sun.xml.internal.ws.client.sei.ResponseBuilder.Header

abstract class MyList {

  /*
    head = first element of the list
    tail = remainder of the list
    isEmpty = is this list empty
    add(int) => new list with this element added
    toString => a string representation of the list
   */

  def head: Int
  def tail: MyList
  def isEmpty: Boolean
  def add(number: Int): MyList
  def printElements: String
  override def toString: String = "[" + printElements + "]"
}

object Empty extends MyList {

override def head: Int = throw new NoSuchElementException
override def tail: MyList = throw new NoSuchElementException
override def isEmpty: Boolean = true
override def add(number: Int): MyList = new Cons(number, Empty)
override def printElements: String = ""

}


class Cons(h: Int, t: MyList) extends MyList {

  override def head: Int = h
  override def tail: MyList = t
  override def isEmpty: Boolean = false
  override def add(number: Int): MyList = new Cons(number, this)
  override def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements

}

object  ListTest extends App {
  val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
  println(list.head)
  println(list.tail.head)
  println(list.add(4).head)

  println(list.toString)
  println(list.add(4))
}