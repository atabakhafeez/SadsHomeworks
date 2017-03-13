package language

/**
  * Created by atabakh on 28/02/2017.
  */
object Main {
  def main(args: Array[String]) {
    val cmd = scala.io.StdIn.readLine();
    val parsed = new oldParser(cmd).parseCommand
  }
}
