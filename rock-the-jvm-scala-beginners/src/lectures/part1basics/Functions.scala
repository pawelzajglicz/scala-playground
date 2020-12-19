package lectures.part1basics

object Functions extends App {

  def aFunction0(a: String, b: Int): String =
    a + " " + b

  def aFunction(a: String, b: Int)= {
    a + " " + b
  }

  println(aFunction("hello", 3))

  def aParameterlessFunction(): Int = 42
  println(aParameterlessFunction())
  println(aParameterlessFunction)

  def aRepeatedFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + aRepeatedFunction(aString, n-1)
  }

  println(aRepeatedFunction("hello",3))

  // WHEN YOU NEED LOOPS, USE RECURSION.

  def aFunctionWithSideEffects(aString: String): Unit = println(aString)

  def aBigFunction(n: Int) : Int = {

    def aSmallerFunction(a: Int, b: Int): Int = a + b

    aSmallerFunction(n, n-1)
  }

  /*
    1. A greeting function (name, age) => "Hi, my name is $name and I am $years old."
   */

  def aGreeting(name: String, age: Int): String =
    "Hi, my name is " + name + " and I am " + age + " years old."

  println(aGreeting("John", 23))

  /*
    2. Factorial function 1  * 2 * 3 * ... * n
   */

  def factorial(number: Int): Int =
    if (number <= 1) number
    else number * factorial(number - 1)

  println(factorial(4))

  /*
    3. A Fibonacci function
   */

  def fibonacci(number: Int): Int =
    if (number <= 2) 1
    else fibonacci(number - 1) + fibonacci(number - 2)

  println(fibonacci(7))

  /*
    4. Tests if a number is prime
   */

  def isPrime(number: Int): Boolean = {
    def isPrimeUntil(t: Int): Boolean =
      if (t <= 1) true
      else number % t != 0 && isPrimeUntil(t-1)

    isPrimeUntil(number / 2)
  }

  println(isPrime(37))
  println(isPrime(2003))
  println(isPrime(52))
  println(isPrime(75))
  println(isPrime(75 * 14))
}
