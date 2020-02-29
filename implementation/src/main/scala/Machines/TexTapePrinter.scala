package Machines
import java.io._
import AbstractTM.TapeVal

object TexTapePrinter {


  def symbTex(char: Char): String = {
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
    char match {
      case '⋀' => "\\wedge"
      case 'Φ' => "\\Phi"
      case 'Ψ' => "\\Psi"
      case 'Ω' => "\\Omega"
      case '1' => "1"
      case 'M' => "M"
      case 'Ξ' => "\\Xi"
    }
  }

  def tex(fileName: String, data: (Vector[Vector[TapeVal]], Vector[Int], Vector[String])): Unit = {
    val coll = data._1
    val indexColl = data._2
    val stateColl = data._3

    val windowSize = 15
    val pw = new PrintWriter(new File(fileName+".tex"), "UTF-8")
    pw.write("\\documentclass{article}\n")
    pw.write("\\begin{document}\n")

    pw.write("\n")
    pw.write("\\section{Tape start}")

    for (x <- coll) {

      pw.write("\n")
      pw.write("\\begin{table}[h]\n")
      pw.write("\\centering\n")

      val window = x.sliding(windowSize, windowSize).toVector
      for (z <- window){
        pw.write("\\begin{tabular}{l")
        for (y <- z.head.index to z.last.index) pw.write("l")
        pw.write("l}\n")

        // Write index
        pw.write(" & ")
        for (i <- z){
          pw.write(i.index.toString+" & ")
        }
        pw.write("\\\\\n")

        // Write symbols
        pw.write("\\hline\n")
        pw.write("$...$ & ")
        for (s <- z) pw.write(s"\\multicolumn{1}{|l|}{$$${symbTex(s.symbol)}$$} & ")
        pw.write("$...$\\\\\n")
        pw.write("\\hline\n")

        // Write uparrow
        pw.write("& ")
        for (u <- z.head.index to z.last.index) {
          if (u == indexColl(coll.indexOf(x))) pw.write("$\\uparrow$ & ")
          else pw.write(" & ")
        }
        pw.write(" \\\\\n")

        // Write state under uparrow
        pw.write("& ")
        for (u <- z.head.index to z.last.index) {
          if (u == indexColl(coll.indexOf(x))) pw.write(s"$$ ${stateColl(coll.indexOf(x))} $$ & ")
          else pw.write(" & ")
        }
        pw.write(" \\\\\n")

        pw.write("\\end{tabular}\n")
      }
      pw.write(s"Tape number: ${coll.indexOf(x).toString}\n") // matches tape appearance not specific instance of x
      pw.write("\\end{table}\n")

      if (coll.indexOf(x) % 15 == 1) pw.write(("\\clearpage\n"))
    }

    pw.write("\n")

    pw.write("\\end{document}\n")
    pw.close()
  }
}
