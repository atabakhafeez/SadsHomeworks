package language

/**
  * Created by atabakh on 28/02/2017.
  */

/** Commands */
sealed abstract class Comm {
  var pos = 0
  var len = 0
}
case class fun(funName: Name, argName: Name, body: Comm) extends Comm
case class run(runName: Name, body: List[Expr]) extends Comm
case class funCall(funName: Name, argName: Expr) extends Comm
case class Comms(Comm1: Comm, Comm2: Comm) extends Comm

/** Expressions */
sealed abstract class Expr
case class variable(varName: Name) extends Expr
case class string(str: String) extends Expr

/** Names */
case class Name(name: String)

