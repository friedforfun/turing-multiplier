package AbstractTM

case class TapeVal (index: Int, symbol: Char) {
  override def toString: String = symbol.toString
}