package lectures.part2oop

import java.sql

import playground.{PrinceCharming, Cinderella => Princess}
// import playground._

import java.util.Date
import java.sql.{Date => SqlDate}

object PackagingAndImports extends App {

  // package members are accessible by their simple name
   val writer = new Writer("Daniel", "RockTheJVM", 2018)

  // import the package
  // val princess = new Cinderella
  val princess = new Princess
  val princess1 = new playground.Cinderella // fully qualified class name

  // packages are in hierarchy
  // matching folder structure

  // package object
  sayHello
  println(SPEED_OF_LIGHT)

  // imports
  val prince = new PrinceCharming

  // val d = new Date <-- first import

  // 1. Use fully qualified name
  val date = new Date
  val sqlDate = new java.sql.Date(2018, 5, 4)

  // 2. Use aliasing
  val sqlDate1 = new SqlDate(2018, 5, 4)

  // default imports
  // java.lang - String, Object, Exception
  // scala - Int, Nothing, Function
  // scala.Predef - println, ???
}
