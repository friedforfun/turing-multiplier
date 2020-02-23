package AbstractTM
import Array._

class TuringMachine() {
  private var _state: Array[String] = Array()
  private var _symbol: Array[Symbol] = Array()

  def state = _state
  def state_=(newState: Array[String]): Unit ={
    _state = concat(_state, newState)
  }

  def symbol = _symbol
  def symbol_=(newSymbol: Array[Symbol]): Unit = {
    _symbol = concat(_symbol, newSymbol)
  }

  def addState{}

  def storeTransition(st0 : String, sy0 : Char, st1 : String, sy1 : Char, move : Char){}


class Symbol (x: Char){
  private val _sym = x
  def symbol= _sym
}

class State(x: String){
  private val _state = x
  def state = _state
}

object Move extends Enumeration {
  type Move = Value
  val R, L, O = Value
}

class Transition(st0 : State, sy0 : Symbol, st1 : State, sy1 : Symbol, m : Move){

}

}
