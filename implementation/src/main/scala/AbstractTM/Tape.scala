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

  def run(): scala.collection.immutable.Vector[Vector[TapeVal]] = {
    val build = new VectorBuilder[Vector[TapeVal]]
    val startTime = System.currentTimeMillis()
    var latest = tape
    while (!finish()){
      println(latest)
      latest = step(latest)
      build.addOne(latest)
    }
    val collection = build.result()
    val endTime = System.currentTimeMillis()
    println("Elapsed time: " + (endTime - startTime) + "ms")
    collection
  }

  def step(curTape: Vector[TapeVal]): Vector[TapeVal] = {
    vectorIndex = curTape.indexWhere(x => x.index == tapeIndex)
    val transition: Transition = {
      turingMachine.transitions.find(x => (x.rule.symbol == curTape(vectorIndex).symbol && x.rule.state == tapeState)) match {
        case None => throw new Exception("No transition exists for this state and symbol")
        case Some(s) => s
      }
    }
    tapeState = transition.result.state
    val nextTape: Vector[TapeVal] = curTape.updated(vectorIndex, TapeVal(curTape(vectorIndex).index, transition.result.symbol))
    tapeIndex = nextIndex(transition)
    val finalTape = expandTape(nextTape)
    finalTape
  }

  private def finish(): Boolean = {
    val finished = if (tapeState == turingMachine.finalState) true else false
    finished
  }

  def expandTape(t: Vector[TapeVal]): Vector[TapeVal] = {
    if (t(vectorIndex) == t.head) extendTapeDown(t)
    else if (t(vectorIndex) == t.last) extendTapeUp(t)
    else t
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
