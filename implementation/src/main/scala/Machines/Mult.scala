package Machines

import AbstractTM.{Tape, Transition, TuringMachine}

class Mult {
  // wedge: 0x22C0 | \wedge
  val wedge = 0x22C0.toChar
  // Phi:   0x03A6 | \Phi
  val phi = 0x03A6.toChar
  // Psi:   0x03A8 | \Psi
  val psi = 0x03A8.toChar
  // Omega: 0x03A9 | \Omega
  val omega = 0x03A9.toChar
  // 1:     0x0031| 1
  // M:     0x004D | M
  val m = 0x004D.toChar
  // Xi:    0x039E | \Xi
  val xi = 0x039E.toChar

  val states = Vector("q0", "q1", "q2", "q3", "q4", "q5", "q6", "q7", "q8", "q9", "q10", "q11", "q12", "q13", "q14")
  val symbols = Vector(wedge, phi, psi, omega, '1', m, xi)
  val t0 = new Transition("q0", wedge, "q14", wedge, '0')
  val t1 = new Transition("q0", '1', "q0", '1', 'R')
  val t2 = new Transition("q0", psi, "q13", psi, 'R')
  val t3 = new Transition("q0", phi, "q2", phi, 'R')
  val t4 = new Transition("q1", '1', "q1", '1', 'L')
  val t5 = new Transition("q1", psi, "q1", psi, 'L')
  val t6 = new Transition("q1", wedge, "q5", wedge, 'R')
  val t7 = new Transition("q13", phi, "q2", phi, 'R')
  val t8 = new Transition("q2", '1', "q2", '1', 'R')
  val t9 = new Transition("q2", psi, "q2", psi, 'R')
  val t10 = new Transition("q2", wedge, "q3", omega, 'R')
  val t11 = new Transition("q2", omega, "q3", omega, 'R')
  val t12 = new Transition("q2", phi, "q4", phi, 'L')
  val t13 = new Transition("q3", wedge, "q4", phi, 'L')
  val t14 = new Transition("q4", omega, "q4", omega, 'L')
  val t15 = new Transition("q4", '1', "q4", '1', 'L')
  val t16 = new Transition("q4", phi, "q4", phi, 'L')
  val t17 = new Transition("q4", psi, "q4", psi, 'L')
  val t18 = new Transition("q4", wedge, "q5", wedge, 'R')
  val t19 = new Transition("q5", phi, "q5", phi, 'R')
  val t20 = new Transition("q5", '1', "q6", m, 'R')
  val t21 = new Transition("q5", psi, "q11", psi, 'L')
  val t22 = new Transition("q6", '1', "q6", '1', 'R')
  val t23 = new Transition("q6", psi, "q12", psi, 'R')
  val t24 = new Transition("q6", phi, "q6", phi, 'R')
  val t25 = new Transition("q12", omega, "q10", omega, 'L')
  val t26 = new Transition("q7", omega, "q8", omega, 'R')
  val t27 = new Transition("q7", wedge, "q8", omega, 'R')
  val t28 = new Transition("q7", '1', "q7", '1', 'R')
  val t29 = new Transition("q8", wedge, "q9", '1', 'L')
  val t30 = new Transition("q8", '1', "q8", '1', 'R')
  val t31 = new Transition("q8", phi, "q8", phi, 'R')
  val t32 = new Transition("q9", '1', "q9", '1', 'L')
  val t33 = new Transition("q9", phi, "q9", phi, 'L')
  val t34 = new Transition("q9", omega, "q9", omega, 'L')
  val t35 = new Transition("q9", xi, "q12", '1', 'R')
  val t36 = new Transition("q10", '1', "q10", '1', 'L')
  val t37 = new Transition("q10", phi, "q10", phi, 'L')
  val t38 = new Transition("q10", psi, "q10", psi, 'L')
  val t39 = new Transition("q10", m, "q5", '1', 'R')
  val t40 = new Transition("q11", '1', "q11", '1', 'L')
  val t41 = new Transition("q11", phi, "q11", phi, 'L')
  val t42 = new Transition("q11", wedge, "q14", wedge, 'R')
  val t43 = new Transition("q12", '1', "q7", xi, 'R')
  val t44 = new Transition("q13", '1', "q1", '1', 'L')
  val t45 = new Transition("q13", wedge, "q11", wedge, 'L')
  val t46 = new Transition("q11", psi, "q11", psi, 'L')


  val transitions = Vector(t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29, t30, t31, t32, t33, t34, t35, t36, t37, t38, t39, t40, t41, t42, t43, t44, t45, t46)
  val mult = new TuringMachine(states, symbols, transitions)
  val tapeContents = Set((-1, wedge), (0, '1'), (1, '1'), (2, psi), (3, '1'), (4, '1'), (5, wedge))
  val otherTapeContents = TapeBuilder.buildTape(100, 100)
  val tape = new Tape(tapeContents, mult)
  val otherTape = new Tape(otherTapeContents, mult)

  def newTape(x: Int, y: Int): Tape = {
    val nextTape = new Tape(TapeBuilder.buildTape(x, y), mult)
    nextTape
  }
}
