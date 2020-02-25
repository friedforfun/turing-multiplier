package AbstractTM
import scala.math.{max, min}

case class TapeVal (index: Int, symbol: Char, state: String)

class Tape(tm : TuringMachine, defTape: Set[Tuple2[Int, Char]]) {
  // wedge: 0x22C0 | \wedge
  // 1:     0x0031| 1
  // Phi:   0x03A6 | \Phi
  // Psi:   0x03A8 | \Psi
  // Omega: 0x03A9 | \Omega
  // M:     0x004D | M
  // Xi:    0x039E | \Xi

  // the smallest tape that will be visually represented
  val tapeWindowSize = 15

  // Set((0, 0x03A6.toChar), (1, 0x0031.toChar), (2, 0x0031.toChar), (3, 0x03A8.toChar), (4, 0x0031.toChar), (5, 0x0031.toChar))

  // Set of defined symbols on the tape
  val tapeSymbols = defTape
  // Set of blank symbols on the tape
  val tapeBlanks = (for (x <- initTapeRange(tapeSymbols)) yield if(!tapeSymbols.exists{case (i, c) =>
                                                          i == x}) (x, tm.symbols(0))).toSet[Tuple2[Int, Char]]
  // Set representing the initial tape contents
  val startTapeContents = tapeSymbols union tapeBlanks


  def initTapeRange(set: Set[(Int, Char)]): Range.Inclusive = {
    val intList = set.toList.collect(x => x._1)
    intList.reduceLeft(min)-1 to intList.reduceLeft(max)+1
  }
}
