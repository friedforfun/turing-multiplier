package AbstractTM
import scala.math.{max, min}

class Tape(private val knownTape: Set[(Int, Char)], private val initialIndex: Int = 0, private val emptySymbol: Char = 0x22C0.toChar){

  case class TapeVal (index: Int, symbol: Char) {
    override def toString: String = symbol.toString
  }

  // Set of blank symbols on the tape
  private val blankIndeces = (for (x <- knownTape) yield x._1).diff(initTapeRange(knownTape).toSet)
  private val tapeBlanks: Set[(Int, Char)] = for (x <- blankIndeces) yield (x, turingMachine.symbols.head)

  // Set representing the initial tape contents
  private val tapeContents: Set[(Int, Char)] = knownTape union tapeBlanks

  // Actual tape: List[TapeVal]
  var tape = for (x <- tapeContents.toVector.sorted) yield
    if (x._1 == initialIndex) TapeVal(x._1, x._2, turingMachine.initialState) else TapeVal(x._1, x._2, "")

  private def initTapeRange(set: Set[(Int, Char)]): Range.Inclusive = {
    val intList = set.toVector.collect(x => x._1)
    intList.reduceLeft(min)-1 to intList.reduceLeft(max)+1
  }

  private var tapeIndex = tape.indexWhere(x => x.state != "")
  def run(): Unit ={
    val transition: Transition = turingMachine.transitions(turingMachine.transitions.indexWhere(x =>
      x.rule.state == tape(tapeIndex).state && x.rule.symbol == tape(tapeIndex).symbol))


  }

  private def expandTape(): Vector[TapeVal] = {
    if (tape(tapeIndex) == tape.head) extendTapedown(tape)
    else if (tape(tapeIndex) == tape.last) extendTapeUp(tape)
    else tape
  }

  private def nextIndex(transition: Transition): Int = {
    val newIndex = transition.move match {
      case '0' => tapeIndex
      case 'R' => tapeIndex+1
      case 'L' => tapeIndex-1
    }

    newIndex
  }

  private def extendTapeUp(t: Vector[TapeVal]): Vector[TapeVal] = {
    val top = t.last.index + 1
    val newTape = tape :+ TapeVal(top, turingMachine.symbols.head, "")
    newTape
  }

  private def extendTapedown(t: Vector[TapeVal]): Vector[TapeVal] = {
    val bottom = t.head.index - 1
    val newTape = TapeVal(bottom, turingMachine.symbols.head, "") +: tape
    newTape
  }
}
