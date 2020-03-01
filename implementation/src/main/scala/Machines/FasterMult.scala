package Machines

import AbstractTM.{Tape, Transition, TuringMachine}

import scala.collection.immutable.VectorBuilder

class FasterMult {

  val wedge = 0x22C0.toChar
  //val times = 0x2A09.toChar
  val times = 0x03A8.toChar
  val bot = 0x22A5.toChar
  val d = 0x0044.toChar
  val t = 0x0054.toChar
  val u = 0x0055.toChar
  val v = 0x0056.toChar
  val equals = 0x003D.toChar
  val one = 0x0031.toChar

  val transitionBuilder = new VectorBuilder[Transition]

  transitionBuilder.addOne(new Transition("q0", one, "q1", bot, 'R'))
  transitionBuilder.addOne(new Transition("q0", times, "q13", times, 'L'))
  transitionBuilder.addOne(new Transition("q1", one, "q2", t, 'R'))
  transitionBuilder.addOne(new Transition("q1", times, "q11", times, 'R'))
  transitionBuilder.addOne(new Transition("q2", one, "q2", one, 'R'))
  transitionBuilder.addOne(new Transition("q2", times, "q3", times, 'R'))
  transitionBuilder.addOne(new Transition("q3", one, "q4", v, 'R'))
  transitionBuilder.addOne(new Transition("q3", equals, "q8", equals, 'L'))
  transitionBuilder.addOne(new Transition("q4", one, "q4", one, 'R'))
  transitionBuilder.addOne(new Transition("q4", wedge, "q5", equals, 'R'))
  transitionBuilder.addOne(new Transition("q4", equals, "q5", equals, 'R'))
  transitionBuilder.addOne(new Transition("q5", one, "q5", one, 'R'))
  transitionBuilder.addOne(new Transition("q5", wedge, "q6", one, 'R'))
  transitionBuilder.addOne(new Transition("q6", one, "q6", one, 'R'))
  transitionBuilder.addOne(new Transition("q6", wedge, "q7", one, 'L'))
  transitionBuilder.addOne(new Transition("q7", one, "q7", one, 'L'))
  transitionBuilder.addOne(new Transition("q7", equals, "q7", equals, 'L'))
  transitionBuilder.addOne(new Transition("q7", v, "q3", one, 'R'))
  transitionBuilder.addOne(new Transition("q7", u, "q12", one, 'R'))
  transitionBuilder.addOne(new Transition("q8", one, "q8", one, 'L'))
  transitionBuilder.addOne(new Transition("q8", times, "q8", times, 'L'))
  transitionBuilder.addOne(new Transition("q8", t, "q9", d, 'L'))
  transitionBuilder.addOne(new Transition("q8", bot, "q0", one, 'R'))
  transitionBuilder.addOne(new Transition("q9", bot, "q10", one, 'R'))
  transitionBuilder.addOne(new Transition("q10", d, "q0", one, 'R'))
  transitionBuilder.addOne(new Transition("q11", one, "q12", u, 'R'))
  transitionBuilder.addOne(new Transition("q11", equals, "q8", equals, 'L'))
  transitionBuilder.addOne(new Transition("q12", one, "q12", one, 'R'))
  transitionBuilder.addOne(new Transition("q12", wedge, "q6", equals, 'R'))
  transitionBuilder.addOne(new Transition("q12", equals, "q6", equals, 'R'))
  transitionBuilder.addOne(new Transition("q13", one, "q13", one, 'L'))
  transitionBuilder.addOne(new Transition("q13", wedge, "q14", wedge, 'R'))

  val states = Vector("q0", "q1", "q2", "q3", "q4", "q5", "q6", "q7", "q8", "q9", "q10", "q11", "q12", "q13", "q14")
  val symbols = Vector(wedge, one, times, equals, bot, t, u, v, d)
  val transitions = transitionBuilder.result()
  val fasterMult = new TuringMachine(states, symbols, transitions)


  def newTape(x: Int, y: Int): Tape = {
    val nextTape = new Tape(TapeBuilder.buildTape(x, y), fasterMult)
    nextTape
  }
}
