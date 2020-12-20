package lectures.part1basics

import lectures.part1basics.Functions.isPrime

import scala.annotation.tailrec

object Recursion extends App {

  def factorial(number: BigInt): BigInt =
    if (number <= 1) number
    else number * factorial(number - 1)

  def anotherFactorial(number: Int): BigInt = {

    @tailrec
    def factorialHelper(x: Int, accumulator: BigInt): BigInt =
      if (x <= 1) accumulator
      else factorialHelper(x - 1, x * accumulator) // TAIL RECURSION = use recursive call as the LAST expression

    factorialHelper(number, 1)
  }


  // println(factorial(5000)) // StackOverflowError
  println(anotherFactorial(5000))

  // WHEN YOU NEED LOOPS, USE TAIL RECURSION

  /*
    1. Concatenate a string n times
    2. IsPrime function tail recursive
    3. Fibonacci function tail recursive
   */

  def concatenate(text: String, number: Int) = {

    @tailrec
    def concatenateHelper(txt: String, n: Int, accumulator: String): String =
      if (n <= 1) accumulator
      else concatenateHelper(txt, n-1, txt + accumulator)

    concatenateHelper(text, number, text)
  }

  println(concatenate("test ", 5))

  def concatenate2(aString: String, n: Int, accumulator: String): String =
    if (n <= 0) accumulator
    else concatenate2(aString, n-1, aString + accumulator)

  println(concatenate2("hello", 3, ""))

  def isPrime(number: Int): Boolean = {

    @tailrec
    def isPrimeHelper(divider: Int): Boolean =
      if (divider <= 1) true
      else if (number % divider == 0) false
      else isPrimeHelper(divider-1)

    isPrimeHelper(number / 2)
  }

  println(isPrime(37))
  println(isPrime(2003))
  println(isPrime(52))
  println(isPrime(75))
  println(isPrime(75 * 14))
  println(isPrime(3))

  def isPrime2(n: Int): Boolean = {

    @tailrec
    def isPrimeHelper(t: Int, isStillPrime: Boolean): Boolean =
      if (!isStillPrime) false
      else if (t <= 1 ) true
      else isPrimeHelper(t - 1, n% t != 0 && isStillPrime)

      isPrimeHelper(n / 2, true)
  }

    println("***")
    println(isPrime2(37))
    println(isPrime2(2003))
    println(isPrime2(52))
    println(isPrime2(75))
    println(isPrime2(75 * 14))
    println(isPrime2(3))

  def fibonacci(number: Int): Int = {

    @tailrec
    def fibonacciHelper(i: Int, last: Int, nextToLast: Int): Int =
      if (i >= number) last
      else fibonacciHelper(i + 1, last + nextToLast, last)

      if (number <= 2) 1
      else fibonacciHelper(2, 1, 1)
  }

  println(fibonacci(8))

}
