package Machines
import java.io._

import AbstractTM.{Tape, TapeVal}

// Print output of tape in latex format
object TexTapePrinter {

  // function to convert Char to latex friendly style
  def symbTex(char: Char): String = {

    char match {
      case '⋀' => "\\wedge"
      case 'Φ' => "\\Phi"
      case 'Ψ' => "\\Psi"
      case 'Ω' => "\\Omega"
      case '1' => "1"
      case 'M' => "M"
      case 'Ξ' => "\\Xi"
      case '⊥' => "\\bot"
      case '=' => "="
      case 'T' => "T"
      case 'U' => "U"
      case 'V' => "V"
      case 'D' => "D"

    }
  }

  // print function, reduce tapes per page when tapes longer than windowSize are expected as output
  def tex(fileName: String, tape: Tape, tapesPerPage: Int = 7): Unit = {
    val coll = tape.tapeCollection._1
    val indexColl = tape.tapeCollection._2
    val stateColl = tape.tapeCollection._3
    val runTime = tape.runTime
    val numberOfTapes = tape.tapeCounter

    // windowSize dictates the length of tape each tabular will display
    val windowSize = 18
    val pw = new PrintWriter(new File(fileName+".tex"), "UTF-8")
    pw.write("\\documentclass{article}\n")
    pw.write("\\usepackage[margin=0.5in]{geometry}\n")
    pw.write("\\usepackage{dashrule}\n")
    pw.write("\\usepackage{float}\n")
    pw.write("\\begin{document}\n")

    pw.write("\n")
    pw.write("\\section{Tape start}\n")
    pw.write(s"Number of tapes: ${numberOfTapes.toString}\\\\\n")
    pw.write(s"Tape execution time: ${runTime.toString}ns\\\\\n")

    for (x <- 0 until coll.size) {
      if (x % tapesPerPage == 0 && x != 0) pw.write(("\\clearpage\n"))
      pw.write("\n")
      pw.write("\\begin{table}[H]\n")
      pw.write("\\centering\n")

      val window = coll(x).sliding(windowSize, windowSize).toVector
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
          if (u == indexColl(x)) pw.write("$\\uparrow$ & ")
          else pw.write(" & ")
        }
        pw.write(" \\\\\n")

        // Write state under uparrow
        pw.write("& ")
        for (u <- z.head.index to z.last.index) {
          if (u == indexColl(x)) pw.write(s"$$ ${stateColl(x)} $$ & ")
          else pw.write(" & ")
        }
        pw.write(" \\\\\n")

        pw.write("\\end{tabular}\n")
      }
      pw.write(s"\\\\\nTape number: ${x.toString}\n")
      pw.write("\\noindent\\makebox[\\linewidth]{\\hdashrule{\\textwidth}{1pt}{1pt}}")
      pw.write("\\end{table}\n")


    }

    pw.write("\n")

    pw.write("\\end{document}\n")
    pw.close()
  }
}
