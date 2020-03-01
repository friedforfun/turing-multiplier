package AbstractTM

import scala.collection.immutable.VectorBuilder
import scala.collection.mutable
import scala.math.{max, min}

class Tape(private val knownTape: Set[(Int, Char)], val turingMachine: TuringMachine, private val initialIndex: Int = 0){

  private val emptySymbol = turingMachine.emptySymbol
  private val blankIndeces = initTapeRange(knownTape).toSet.diff((for (x <- knownTape) yield x._1))
  private val tapeBlanks: Set[(Int, Char)] = for (x <- blankIndeces) yield (x, emptySymbol)

  // Set representing the initial tape contents
  private val tapeContents: Set[TapeVal] = for (x <- (knownTape union tapeBlanks)) yield TapeVal(x._1, x._2)

  val tape: Vector[TapeVal] = tapeContents.toVector.sortBy(_.index)
  private var tapeIndex = initialIndex
  private var vectorIndex = tape.indexWhere(x => x.index == tapeIndex)

  var tapeState: String = turingMachine.initialState
  def setState(state: String): Unit = tapeState = state

  // given a transition returns the next tape index
  private def nextIndex(transition: Transition): Int = {
    val newIndex = transition.move match {
      case '0' => tapeIndex
      case 'R' => tapeIndex+1
      case 'L' => tapeIndex-1
    }
    newIndex
  }

  // Collection of all tapes produced by the turing machine running the input tape
  val tapeCollection = this.run()

  // tapes meta data
  val runTime: Long = tapeCollection._4
  val tapeCounter = tapeCollection._5



  def run(): (scala.collection.immutable.Vector[Vector[TapeVal]], Vector[Int], Vector[String], Long, Int) = {
    val states = new VectorBuilder[String]
    val indexes = new VectorBuilder[Int]
    val tapes = new VectorBuilder[Vector[TapeVal]]
    var latest = tape
    states.addOne(tapeState)
    indexes.addOne(tapeIndex)
    tapes.addOne(latest)

    val startTime = System.currentTimeMillis()

    println(latest)
    while (!finish()){

      latest = step(latest)
      println(latest)
      states.addOne(tapeState)
      indexes.addOne(tapeIndex)
      tapes.addOne(latest)
    }
    val endTime = System.currentTimeMillis()

    // gather data
    val indexCollection = indexes.result()
    val stateCollection = states.result()
    val tapeCollection = tapes.result()

    val numberOfTapes = tapeCollection.size
    val runTime = endTime - startTime

    println(s"Number of tapes: ${numberOfTapes}")
    println("Elapsed time: " + (runTime) + "ms")

    // reset tape to run again if desired
    tapeState = turingMachine.initialState
    tapeIndex = initialIndex
    vectorIndex = tape.indexWhere(x => x.index == tapeIndex)

    // return collection data
    (tapeCollection, indexCollection, stateCollection, runTime, numberOfTapes)
  }

  // One step on the tape, returns the next tape
  def step(curTape: Vector[TapeVal]): Vector[TapeVal] = {
    vectorIndex = curTape.indexWhere(x => x.index == tapeIndex)
    print("Index: "+vectorIndex.toString+" ")
    val transition: Transition = {
      turingMachine.transitions.find(x => (x.rule.symbol == curTape(vectorIndex).symbol && x.rule.state == tapeState)) match {
        case None => throw new Exception(s"No transition exists for this state: ${tapeState} and symbol: ${curTape(vectorIndex).symbol.toString}")
        case Some(s) => s
      }
    }
    tapeState = transition.result.state
    val nextTape: Vector[TapeVal] = curTape.updated(vectorIndex, TapeVal(curTape(vectorIndex).index, transition.result.symbol))
    tapeIndex = nextIndex(transition)
    val finalTape = expandTape(nextTape)
    finalTape
  }

  // Checks if tape is in the final state
  private def finish(): Boolean = {
    val finished = if (tapeState == turingMachine.finalState) true else false
    finished
  }

  // extends the tape with blanks
  private def expandTape(t: Vector[TapeVal]): Vector[TapeVal] = {
    if (t(vectorIndex) == t.head) extendTapeDown(t)
    else if (t(vectorIndex) == t.last) extendTapeUp(t)
    else t
  }

  private def extendTapeUp(t: Vector[TapeVal]): Vector[TapeVal] = {
    val top = t.last.index + 1
    val newTape = t :++ Vector(TapeVal(top, emptySymbol), TapeVal(top+1, emptySymbol))
    newTape
  }

  private def extendTapeDown(t: Vector[TapeVal]): Vector[TapeVal] = {
    val bottom = t.head.index - 1
    val newTape = Vector(TapeVal(bottom-1, emptySymbol), TapeVal(bottom, emptySymbol)) ++: t
    newTape
  }

  // initialise the tape with extra blank symbols
  private def initTapeRange(set: Set[(Int, Char)]): Range.Inclusive = {
    val intList: Vector[Int] = set.toVector.collect(x => x._1)
    intList.reduceLeft(min)-1 to intList.reduceLeft(max)+5
  }

  override def toString: String = {
    val str = new StringBuilder
    str.append("| ")
    for (x <- tape) str.append(x.toString+" | ")
    str.toString()
  }

}
