package Machines

import scala.collection.immutable.VectorBuilder
import scala.collection.mutable

object TapeBuilder {
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

  var counter: Int = -1
  def index: Int = {
    val index = counter
    counter = counter + 1
    index
  }
  def buildTape(x: Int, y: Int): Set[(Int, Char)]={
    val setBuild = new VectorBuilder[(Int, Char)]

    setBuild.addOne(index, wedge)

    if (x < 0) setBuild.addOne((index, phi))

    for (z <- 1 to Math.abs(x)) setBuild.addOne(index, '1')

    setBuild.addOne((index, psi))

    if (y < 0) setBuild.addOne((index, phi))

    for (z <- 1 to Math.abs(y)) setBuild.addOne(index, '1')

    setBuild.addOne(index, wedge)

    setBuild.result().toSet
  }
}
