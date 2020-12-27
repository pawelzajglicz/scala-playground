package lectures.part2oop

object Exceptions extends App {

  val x: String = null
  // println(x.length) --> this will crash with NPE

  // 1. throwing exceptions
  // val aWeirdValue = throw new NullPointerException

  // throwable classes extends the Throwable class.
  // Exception and Error are the major Throwable subtypes

  // 2. how to catch exceptions
  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int for you!")
    else 42

  val potentialFail = try {
    // code that might throw
    getInt(false)
  } catch {
    case e: RuntimeException => println("caught a Runtime exception")
   // case e: RuntimeException => 43
  } finally {
    // code that will get executed NO MATTER WHAT
    // optional
    // does not influence the return type of this expression
    // use finally only for side effects
    println("finally")
  }

  println(potentialFail)

  // 3. how to define your own exceptions
  class MyException extends Exception
  val exception = new MyException
  // throw exception

  /*
    1. Crash your program with an OutOfMemoryError
    2. Crash with SOError
    3. PocketCalculator
      - add(x,y)
      - subtract(x,y)
      - multiply(x,y)
      - divide(x,y)

      Throw
        - OverflowException if add(x,y) exceeds Int.MAX_VALUE
        - UnderflowException if subtract(x,y) exceeds Int.MIN_VALUE
        - MathCalculationException for division by 0
   */

  // 1. OOM
    // println("O" * Int.MaxValue)
    // val array = Array.ofDim(Int.MaxValue)

  // 2. SO
    // def so(text: String): String = so(text) + "crash"
    // so("StackOverflow")
    // def infinite: Int = 1 + infinite
    // val noLimit = infinite

  // 3.

  class OverflowException extends RuntimeException
  class UnderflowException extends RuntimeException
  class MathCalculationException(message: String) extends RuntimeException(message)

  object PocketCalculator {
    def add(x: Int, y: Int): Int = {
      val result = x + y
      // if (Int.MaxValue - x < y || Int.MaxValue - y < x) throw new OverflowException
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }

    def subtract(x: Int, y: Int): Int = {
      val result = x - y
      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def multiply(x: Int, y: Int) = {
      val result = x * y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException
      else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def divide(x: Int, y: Int) = {
      if (y == 0) throw new MathCalculationException("Division by 0 not allowed!")
      else x / y
    }
  }

  println(PocketCalculator.add(10, 3))
  // println(PocketCalculator.add(Int.MaxValue, 3))
  // println(PocketCalculator.divide(3, 0))

}
