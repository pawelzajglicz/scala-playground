package lectures.part3fp

object TuplesAndMaps extends App {

  // tuples = finite ordered "lists"
  val aTuple0 = Tuple2(2, "hello, Scala") // Tuple2[Int, String] = (Int, String)
  val aTuple = (2, "hello, Scala") // Tuple2[Int, String] = (Int, String)

  println(aTuple._1)  // 2
  println(aTuple.copy(_2 = "goodbye Java"))
  println(aTuple.swap) // "hello, Scala", 2

  // Maps - keys -> values
  val aMap: Map[String, Int] = Map()

  val phonebook0 = Map(("Jim", 555), ("Daniel", 789))
  val phonebook = Map(("Jim", 555), "Daniel" -> 789).withDefaultValue(-1)
  // a -> b is sugar for (a, b)
  println(phonebook)

  // map operations
  println(phonebook.contains("Jim"))
  println(phonebook.contains("Jimmy"))
  println(phonebook("Jim"))
  // println(phonebook("Mary")) -> exception if no default value

  // add a pairing
  val newPairing = "Mary" -> 678
  val newPhonebook = phonebook + newPairing

  print(newPhonebook)

  // functionals on maps
    // map, flatMap, filter

  println(phonebook.map(pair => pair._1.toLowerCase -> pair._2))

  // filterKeys
  println(phonebook.filterKeys(x => x.startsWith("J")))
  println(phonebook.view.filterKeys(x => x.startsWith("J")))
  println(phonebook.view.filterKeys(x => x.startsWith("J")).toMap)

  // mapValues
  println(phonebook.mapValues(number => number * 10))
  println(phonebook.view.mapValues(number => number * 10))
  println(phonebook.view.mapValues(number => number * 10).toMap)

  // conversions to other collections
  println(phonebook.toList)
  println(List(("Daniel", 555)).toMap)

  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim", "Adam")
  println(names.groupBy(name => name.charAt(0)))
}
