package AbstractTM

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