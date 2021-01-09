package lectures.part3fp

import scala.util.Random

object Options extends App {

  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  println(myFirstOption)
  println(noOption)

  // Work with unsafe APIs
  def unsafeMethod(): String = null
  // val result = Some(unsafeMethod())
  // val result = Some(null)  // WRONG
  val result = Option(unsafeMethod()) // Some or None
  println(result)

  // chained methods
  def backupMethod(): String = "A valid result"
  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))

  // Design unsafe APIs
  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("A valid result")

  val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()

  // functions on Options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) // UNSAFE
  //println(noOption.get) // UNSAFE

  // map, flatMap, filter
  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(x => x > 10))
  println(myFirstOption.filter(x => x < 10))
  println(myFirstOption.flatMap(x => Option(x * 10)))


  /*
    Exercise
   */

  val config: Map[String, String] = Map(
    // fetched from elsewhere
    "host" -> "176.45.36.1",
    "port" -> "80"
  )

  class Connection {
    def connect = "Connected" // connect to some server
  }
  object Connection {
    val random = new Random(System.nanoTime())
    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }

  // try to establish a connection if so - print the connect method
  val host = config.get("host")
  val port = config.get("port")

  val connection = host.flatMap(h => port.flatMap(p => Connection.apply(h, p)))
  /*
    if (h != null)
      if (p != null)
        return Connection.apply(h, p)

    return null
   */

  val connectionStatus = connection.map(c => c.connect)
  /*
    if (c != null)
      return c.connect

    return null
   */
  println(connectionStatus)
  connectionStatus.foreach(println)

  config.get("host")
    .flatMap(host => config.get("port")
    .flatMap(port => Connection(host, port))
      .map(connection => connection.connect))
    .foreach(println)


  // for-comprehensions
  println("for-comprehensions")

  val forConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect

  forConnectionStatus.foreach(println)
}
