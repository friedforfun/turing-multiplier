package AbstractTM
import scala.math.{max, min}

class Tape(tm : TuringMachine, tape: Set[Tuple2[Int, Char]]) {
  // wedge: 0x22C0 | \wedge
  // 1:     0x0031| 1
  // Phi:   0x03A6 | \Phi
  // Psi:   0x03A8 | \Psi
  // Omega: 0x03A9 | \Omega
  // M:     0x004D | M
  // Xi:    0x039E | \Xi

  val tapeWindowSize = 10

  // Set((0, 0x03A6.toChar), (1, 0x0031.toChar), (2, 0x0031.toChar), (3, 0x03A8.toChar), (4, 0x0031.toChar), (5, 0x0031.toChar))
  val tapeSymbols = tape
  val tapeBlanks = -1;

  def minMax(set: Set[(Int, Char)]): (Int, Int) = {
    val intList = set.toList.collect(x => x._1)
    (intList.reduceLeft(min), intList.reduceLeft(max))
  }
}
