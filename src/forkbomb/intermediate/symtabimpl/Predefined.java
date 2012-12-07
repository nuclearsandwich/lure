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

  /**
   * Initialize a symbol table stack with predefined identifiers.
   * @param symTab the symbol table stack to initialize.
   */
  public static void initialize(SymTabStack symTabStack) {
    initializeConstants(symTabStack);
    initializeStandardRoutines(symTabStack);
  }

  /**
   * Initialize the predefined constant.
   * @param symTabStack the symbol table stack to initialize.
   */
  private static void initializeConstants(SymTabStack symTabStack) {
    // Constant value false.
    falseId = symTabStack.enterLocal("false");
    falseId.setDefinition(DefinitionImpl.VARIABLE);
    falseId.setTypeSpec(new TypeSpecImpl(TypeFormImpl.BOOLEAN));
    falseId.setAttribute(CONSTANT_VALUE, false);

    // Constant value true.
    trueId = symTabStack.enterLocal("true");
    trueId.setDefinition(DefinitionImpl.VARIABLE);
    trueId.setTypeSpec(new TypeSpecImpl(TypeFormImpl.BOOLEAN));
    trueId.setAttribute(CONSTANT_VALUE, true);

    // Constant value nil.
    nilId = symTabStack.enterLocal("nil");
    nilId.setDefinition(DefinitionImpl.VARIABLE);
    nilId.setTypeSpec(new TypeSpecImpl(TypeFormImpl.NIL));
    nilId.setAttribute(CONSTANT_VALUE, null);
  }

  /**
   * Initialize the standard procedures and functions.
   * @param symTabStack the symbol table stack to initialize.
   */
  private static void initializeStandardRoutines(SymTabStack symTabStack) {
  }

  /**
   * Enter a standard procedure or function into the symbol table stack.
   * @param symTabStack the symbol table stack to initialize.
   * @param defn either PROCEDURE or FUNCTION.
   * @param name the procedure or function name.
   */
  private static SymTabEntry enterStandard(SymTabStack symTabStack,
      Definition defn, String name,
      RoutineCode routineCode)
  {
    SymTabEntry procId = symTabStack.enterLocal(name);
    procId.setDefinition(defn);
    procId.setAttribute(ROUTINE_CODE, routineCode);

    return procId;
  }
}
