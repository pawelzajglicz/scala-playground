package lectures.part3fp

object AnonymousFunctions extends App {

  val doubler0 = new Function1[Int, Int] {
    override def apply(x: Int) = x * 2
  }

  // anonymous function (LAMBDA)
  val doubler1 = (x: Int) => x * 2

  val doubler: Int => Int = x => x * 2

  // multiple params in a lambda
  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b

  // no params
  val justDoSomething: () => Int = () => 3

  // careful
  println(justDoSomething) // function itself
  println(justDoSomething()) // call

  // curly braces with lambdas
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // MOAR syntactic sugar
  val niceIncrementer0: Int => Int = (x: Int) => x + 1
  val niceIncrementer: Int => Int = _ + 1 // equivalent to x => x + 1

  val niceAdder0: (Int, Int) => Int = (a, b) => a + b
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a, b) => a + b // every underscore different parameter

  /*
    1. MyList replace all FunctionX calls with lambdas
    2. Rewrite the "special" adder as anonymous function
   */

}
