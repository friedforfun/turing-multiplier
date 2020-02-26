package AbstractTM
import scala.math.{max, min}

class Tape(val turingMachine : TuringMachine, private val knownTape: Set[(Int, Char)], val initialIndex: Int = 0){

  case class TapeVal (index: Int, symbol: Char, state: String)

  // Set of blank symbols on the tape
  private val blankIndeces = (for (x <- knownTape) yield x._1).diff(initTapeRange(knownTape).toSet)
  private val tapeBlanks: Set[(Int, Char)] = for (x <- blankIndeces) yield (x, turingMachine.symbols.head)

  // Set representing the initial tape contents
  private val tapeContents: Set[(Int, Char)] = knownTape union tapeBlanks

  // Actual tape: List[TapeVal]
  val tape = for (x <- tapeContents.toVector.sorted) yield
    if (x._1 == initialIndex) TapeVal(x._1, x._2, turingMachine.initialState) else TapeVal(x._1, x._2, "")

  private def initTapeRange(set: Set[(Int, Char)]): Range.Inclusive = {
    val intList = set.toVector.collect(x => x._1)
    intList.reduceLeft(min)-1 to intList.reduceLeft(max)+1
  }
}
