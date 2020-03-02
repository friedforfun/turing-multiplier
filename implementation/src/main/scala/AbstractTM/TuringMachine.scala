package AbstractTM

class TuringMachine(val states: Vector[String], val symbols: Vector[Char], val transitions: Vector[Transition]){
  // initial state is the first state in the collection
  val initialState: String = states.head
  // final state is the last state in the collection
  val finalState = states.last
  // First state is the empty symbol (by convention)
  val emptySymbol = symbols.head

  // Check transition rules
  // Transition states must be contained in TuringMachine "states" collection
  // Transition symbols must be contained in TuringMachine "symbols" collection
  // No duplicate rules can exist
  val rules = (for (x <- transitions) yield if (checkRules(x)) x.rule)

  if (!(rules == rules.distinct)) throw new Exception ("Transition has duplicate rules")

  def checkRules(transition: Transition): Boolean = {
    if (!states.contains(transition.rule.state) || !states.contains(transition.result.state) ||
      !symbols.contains(transition.rule.symbol) || !symbols.contains(transition.result.symbol))
      throw new Exception ("Transition "+transition.toString+" contains invalid values")
    else true
  }


}
