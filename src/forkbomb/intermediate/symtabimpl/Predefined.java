package forkbomb.intermediate.symtabimpl;

import java.util.ArrayList;

import wci.intermediate.*;
import forkbomb.intermediate.typeimpl.TypeFormImpl;
import forkbomb.intermediate.typeimpl.TypeKeyImpl;
import forkbomb.intermediate.typeimpl.TypeSpecImpl;

import static forkbomb.intermediate.symtabimpl.DefinitionImpl.*;
import static forkbomb.intermediate.symtabimpl.SymTabKeyImpl.*;
import static forkbomb.intermediate.symtabimpl.RoutineCodeImpl.*;
import static forkbomb.intermediate.typeimpl.TypeFormImpl.*;
import static forkbomb.intermediate.typeimpl.TypeKeyImpl.*;

/**
 * <h1>Predefined</h1>
 *
 * <p>Enter the predefined Lure values and functions into
 * the symbol table.</p>
 *
 * <p>Copyright (c) 2012 by Steven! Ragnarök</p>
 */
public class Predefined {
  // Predefined identifiers.
  public static SymTabEntry falseId;
  public static SymTabEntry trueId;
  public static SymTabEntry nilId;

  // Builtin global functions.
  public static SymTabEntry putsId;
  public static SymTabEntry plusId;
  public static SymTabEntry minusId;
  public static SymTabEntry starId;
  public static SymTabEntry slashId;
  public static SymTabEntry equalsId;
  public static SymTabEntry nequalsId;
  /* XXX PENDING */
  public static SymTabEntry gtId;
  public static SymTabEntry ltId;
  public static SymTabEntry gteId;
  public static SymTabEntry lteId;

  /**
   * Initialize a symbol table stack with predefined identifiers.
   * @param symTab the symbol table stack to initialize.
   */
  public static void initialize(SymTabStack symTabStack) {
    putsId = enterBuiltin(symTabStack, "puts", "lure/lang/Globals/puts");
    plusId = enterBuiltin(symTabStack, "+", "lure/lang/Globals/plus");
    minusId = enterBuiltin(symTabStack, "-", "lure/lang/Globals/minus");
    starId = enterBuiltin(symTabStack, "*", "lure/lang/Globals/star");
    slashId = enterBuiltin(symTabStack, "/", "lure/lang/Globals/slash");
    equalsId = enterBuiltin(symTabStack, "=", "lure/lang/Globals/equals");
    nequalsId = enterBuiltin(symTabStack, "!=", "lure/lang/Globals/nequals");
    gtId = enterBuiltin(symTabStack, ">", "lure/lang/Globals/gt");
    gteId = enterBuiltin(symTabStack, ">=", "lure/lang/Globals/gte");
    ltId = enterBuiltin(symTabStack, "<", "lure/lang/Globals/lt");
    lteId = enterBuiltin(symTabStack, "<=", "lure/lang/Globals/lte");

    trueId = enterBuiltin(symTabStack, "true", "lure/lang/Globals/_true");
    trueId = enterBuiltin(symTabStack, "false", "lure/lang/Globals/_false");
    trueId = enterBuiltin(symTabStack, "nil", "lure/lang/Globals/_null");
  }

  private static SymTabEntry enterBuiltin(SymTabStack stack,
      String name, String fieldSpec)
  {
    SymTabEntry e = stack.enterLocal(name);
    e.setAttribute(GLOBAL_FIELD_SPEC, fieldSpec);
    return e;
  }
}
