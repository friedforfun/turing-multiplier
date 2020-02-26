package Machines
import AbstractTM.{Tape, Transition, TuringMachine}

class AddOne {
  // wedge: 0x22C0 | \wedge
  // 1:     0x0031| 1
  // Phi:   0x03A6 | \Phi
  // Psi:   0x03A8 | \Psi
  // Omega: 0x03A9 | \Omega
  // M:     0x004D | M
  // Xi:    0x039E | \Xi
  // Set((0, 0x03A6.toChar), (1, 0x0031.toChar), (2, 0x0031.toChar), (3, 0x03A8.toChar), (4, 0x0031.toChar), (5, 0x0031.toChar))

  val addStates = List("q0", "q1", "q2")
  val addSymbols = List(0x22C0.toChar, '1')
  val t0 = new Transition("q0", '1', "q0", '1', 'R')
  val t1 = new Transition("q0", 0x22C0.toChar, "q1", '1', 'L')
  val t2 = new Transition("q1", '1', "q1", '1', 'L')
  val t3 = new Transition("q1", 0x22C0.toChar, "q2", 0x22C0.toChar, 'R')
  val addTransitions = List(t0, t1, t2, t3)
  val addOne = new TuringMachine(addStates, addSymbols, addTransitions)
  val tapeContents = Set((0, '1'), (1, '1'))
  val tape = new Tape(addOne, tapeContents)


}
