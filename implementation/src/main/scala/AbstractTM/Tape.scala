package AbstractTM
import scala.math.{max, min}

class Tape(val turingMachine : TuringMachine, private val knownTape: Set[(Int, Char)], val initialIndex: Int = 0){

  case class TapeVal (index: Int, symbol: Char, state: String)
  // wedge: 0x22C0 | \wedge
  // 1:     0x0031| 1
  // Phi:   0x03A6 | \Phi
  // Psi:   0x03A8 | \Psi
  // Omega: 0x03A9 | \Omega
  // M:     0x004D | M
  // Xi:    0x039E | \Xi
  // Set((0, 0x03A6.toChar), (1, 0x0031.toChar), (2, 0x0031.toChar), (3, 0x03A8.toChar), (4, 0x0031.toChar), (5, 0x0031.toChar))

  // Set of blank symbols on the tape
  private val blankIndeces = (for (x <- knownTape) yield x._1).diff(initTapeRange(knownTape).toSet)
  private val tapeBlanks: Set[(Int, Char)] = for (x <- blankIndeces) yield (x, turingMachine.symbols.head)

  // Set representing the initial tape contents
  private val tapeContents: Set[(Int, Char)] = knownTape union tapeBlanks
  private var curState = tm.initialState
  private var tapeIndex = initialIndex
  


  private def initTapeRange(set: Set[(Int, Char)]): Range.Inclusive = {
    val intList = set.toList.collect(x => x._1)
    intList.reduceLeft(min)-1 to intList.reduceLeft(max)+1
  }
}
