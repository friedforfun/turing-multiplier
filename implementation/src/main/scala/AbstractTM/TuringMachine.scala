package AbstractTM

class TuringMachine(val states: Vector[String], val symbols: Vector[Char], val transitions: Vector[Transition]){
  val initialState: String = states.head
  val finalState = states.last
  val emptySymbol = symbols.head

}
