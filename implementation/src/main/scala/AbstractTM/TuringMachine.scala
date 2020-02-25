package AbstractTM

class TuringMachine {

  // Turing machine fields
  private var _states: List[String] = List()
  private var _symbols: List[Char] = List()
  private var _transitions: List[Transition] = List()

  // Auxillary constructor
  // parameter is list of states and symbols
  def this(states: List[String], symbols: List[Char]){
    this()
    this._states = states
    this._symbols = symbols
  }

  def states = _states
  def states_=(newStates: List[String]): Unit ={
    _states = newStates
  }
  def addState(s : String): Unit = {
    this.states = this.states :+ s
  }

  def symbols = _symbols
  def symbols_=(newSymbols: List[Char]): Unit = {
    _symbols = newSymbols
  }

  def addSymbol(sy : Char): Unit = {
    this.symbols = this.symbols :+ sy
  }

  def transitions = _transitions
  def transitions_=(newTransitions: List[Transition]): Unit ={
    _transitions = newTransitions
  }
  def addTransition(st0 : String, sy0 : Char, st1 : String, sy1 : Char, m : Char): Unit = {
    val t : Transition = new Transition(st0, sy0, st1, sy1, m)
    this.transitions :+ t
  }



}
