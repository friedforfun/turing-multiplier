package AbstractTM

class Transition(private val st0 : String, private val sy0 : Char, private val st1 : String, private val sy1 : Char, private val m : Char){
  private val acceptedMoves : List[Char] = List('R', 'L', '0')
  val move: Char = if (acceptedMoves contains m) m else throw new Exception("Invalid move value in: "+this.toString)
  val rule: Rule = new Rule(st0, sy0)
  val result: Result = new Result(st1, sy1, move)

  // subclasses Rule & Result to help clarify what state and what symbol is being accessed
  class Rule(private val st0: String, private val sy0: Char) {
    val state = st0
    val symbol = sy0
    override def toString: String = s"Transition rule: State = $state, Symbol = $symbol)"
  }

  class Result(private val st1: String, private val sy1: Char, private val m: Char ) {
    val state = st1
    val symbol = sy1
    val move: Char = m
    override def toString: String = s"Transition result: State = $state, Symbol = $symbol, Move = $move)"
  }

  private val _definition = (st0, sy0, st1, sy1, move)
  def definition = _definition

  override def toString: String = s"Transition: ($st0, $sy0, $st1, $sy1, $move)"
}