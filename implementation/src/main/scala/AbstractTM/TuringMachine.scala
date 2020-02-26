package AbstractTM

class TuringMachine(val states: List[String], val symbols: List[Char], val transitions: List[Transition]){
  var initialState: String = states.head
  var finalState = states.tail

}
