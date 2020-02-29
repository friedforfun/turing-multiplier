package Machines
import AbstractTM.{Tape, Transition, TuringMachine}

class AddOne {
  // addOne turing machine from lectures
  val addStates = Vector("q0", "q1", "q2")
  val addSymbols = Vector(0x22C0.toChar, '1')
  val t0 = new Transition("q0", '1', "q0", '1', 'R')
  val t1 = new Transition("q0", 0x22C0.toChar, "q1", '1', 'L')
  val t2 = new Transition("q1", '1', "q1", '1', 'L')
  val t3 = new Transition("q1", 0x22C0.toChar, "q2", 0x22C0.toChar, 'R')
  val addTransitions = Vector(t0, t1, t2, t3)
  val addOne = new TuringMachine(addStates, addSymbols, addTransitions)
  val tapeContents = Set((0, '1'), (1, '1'))
  val otherTapeContets = Set((0, '1'), (1, '1'), (3,'1'))
  val tape = new Tape(tapeContents, addOne)
  val otherTape = new Tape(otherTapeContets, addOne)

}
