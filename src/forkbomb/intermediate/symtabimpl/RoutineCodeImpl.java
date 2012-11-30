package forkbomb.intermediate.symtabimpl;

import forkbomb.intermediate.RoutineCode;

public enum RoutineCodeImpl implements RoutineCode
{
  // Maths
  PLUS("+"), MINUS("-"), STAR("*"), DIV("div"), MOD("mod"),

  // Boolean
  AND("and"), OR("or"), EQ("="), NEQ("!="), GT(">"), GTE(">="), LT("<"),
  LTE("<="), NOT("not"),

  // Extra
  TO_STR("to-str"),
}
