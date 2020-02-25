package AbstractTM

class TuringMachine {
  class Transition(st0 : String, sy0 : Char, st1 : String, sy1 : Char, m : Char){
    private val acceptedMoves : List[Char] = List('R', 'L', '0')
    private val _state0 = st0
    private val _symbol0 = sy0
    private val _state1 = st1
    private val _symbol1 = sy1
    private val _move = if (acceptedMoves contains m) m else println("Invalid move value in: "+this.toString)

    private val _rule = (state0, symbol0)
    private val _result = (state1, symbol1, move)
    private val _definition = (state0, symbol0, state1, symbol1, move)

    override def toString: String = s"($state0, $symbol0, $state1, $symbol1, $move)"
    def state0 = _state0
    def symbol0 = _symbol0
    def state1 = _state1
    def symbol1 = _symbol1
    def move = _move

    def rule = _rule
    def result = _result
    def definition = _definition
  }
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
