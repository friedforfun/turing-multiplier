package AbstractTM

// case class to represent individual cells on the tape.
case class TapeVal (index: Int, symbol: Char) {
  override def toString: String = symbol.toString
}