package language

import scala.annotation.tailrec
import scala.util.matching.Regex


/**
  * Created by atabakh on 28/02/2017.
  */

class oldParser(input: String) {

  private var pos = 0
  private var len = 0
  private val length = input.length

  case class Error(msg: String) extends java.lang.Exception(msg + " at position " + pos + ", found " + input.substring(pos))


    private def startsWith(s: String) = pos+s.length <= length && input.substring(pos, pos+s.length) == s

    private def parseTerminal(t: String) = {
      if (startsWith(t)) {
        println(t)
        println(input)
        pos += t.length
      } else {
        throw Error("expected " + t)
      }
    }
    // parse and drop initial whitespace (and throw it away)
    private def parseWhitespace {
      while (pos < length && input(pos).isWhitespace && input(pos) != '\n') {
        pos += 1
      }
    }

  def parseCommand : Comm = {
    if (input.substring(pos, len) contains ";"){
      /**NOTE: This part causes an error */
//      len = input.substring(pos, len) indexOf ";"
//      val comm1 = parseCommand
//      len = length
//      parseTerminal(";")
//      val comm2 = parseCommand
//      Comms(comm1, comm2)
    } else if (startsWith("fun")) {
      parseTerminal("fun")
      parseWhitespace
      val funName = parseName
      parseTerminal("(")
      val argName = parseName
      parseTerminal("){")
      val comm_ = parseCommand
      parseTerminal("}")
      fun(funName, argName, comm_)
    } else if (startsWith("run")) {
      parseTerminal("run")
      parseWhitespace
      val runName = parseName
      val commRemaining = input.substring(pos)
      var commList : List[Expr] = Nil
      while(input.length != pos) {
        parseWhitespace
        val newCommList = parseExpr :: commList
        commList = newCommList
        if(input.length == pos) {
          run(runName, newCommList)
        }
      }
      run(runName, Nil)
    } else {
      val callName = parseName
      parseTerminal("(")
      val callExpr = parseExpr
      parseTerminal(")")
      funCall(callName, callExpr)
    }
  }

  private def parseString(str: String) : Expr = {
    pos += str.length
    string(str)
  }

  def parseExpr: Expr = {
    if (startsWith("\"")) {
      val pattern = new Regex("[\\\"]([^\"])+[\\\"]");
      val subStr = input.substring(pos, length)
      val name = pattern findFirstIn subStr
      val thisExpr = name.get
      parseString(thisExpr)
    } else {
      val name = parseName
      variable(name)
    }
  }

  // parse some characters (as long as they satisfy a condition) and return them
  private def parseCertainCharacters(input: String, take: Char => Boolean): String = {
//    parseWhitespace
    val p = pos
    while (pos < input.length && take(input(pos))) {
      println(input)
      println(pos)
      pos += 1
    }
    val n = input.substring(p, pos)
    n
  }

  // this defines which characters are allowed in names
  private def parseNameCharacters(input: String) = parseCertainCharacters(input, c => c.isLetterOrDigit)

  def parseName : Name = {
    val n = parseNameCharacters(input)
    println(n)
    if (n == "")
      throw Error("empty name")
    Name(n)
  }

}
