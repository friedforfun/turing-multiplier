package AbstractTM

import scala.math.{max, min}

class Tape(private val knownTape: Set[(Int, Char)], private val initialIndex: Int = 0, private val emptySymbol: Char = 0x22C0.toChar){

  case class TapeVal (index: Int, symbol: Char) {
    override def toString: String = symbol.toString
  }

  private val blankIndeces = initTapeRange(knownTape).toSet.diff((for (x <- knownTape) yield x._1))
  private val tapeBlanks: Set[(Int, Char)] = for (x <- blankIndeces) yield (x, emptySymbol)

  // Set representing the initial tape contents
  private val tapeContents: Set[TapeVal] = for (x <- (knownTape union tapeBlanks)) yield TapeVal(x._1, x._2)

  private def initTapeRange(set: Set[(Int, Char)]): Range.Inclusive = {
    val intList: Vector[Int] = set.toVector.collect(x => x._1)
    intList.reduceLeft(min)-1 to intList.reduceLeft(max)+1
  }

  val tape: Vector[TapeVal] = tapeContents.toVector.sortBy(_.index)
  private var tapeIndex = initialIndex

  def expandTape(): Vector[TapeVal] = {
    if (tape(tapeIndex) == tape.head) extendTapeDown(tape)
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
    val newTape = tape :+ TapeVal(top, emptySymbol) :+ TapeVal(top+1, emptySymbol)
    newTape
  }

  private def extendTapeDown(t: Vector[TapeVal]): Vector[TapeVal] = {
    val bottom = t.head.index - 1
    val newTape = TapeVal(bottom-1, emptySymbol) +: TapeVal(bottom, emptySymbol) +: tape
    newTape
  }


  override def toString: String = {
    val str = new StringBuilder
    str.append("| ")
    for (x <- tape) str.append(x.toString+" | ")
    str.toString()
  }

}
