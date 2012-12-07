package forkbomb.intermediate;

import wci.intermediate.*;
import forkbomb.intermediate.TypeForm;
import forkbomb.intermediate.TypeKey;
import forkbomb.intermediate.TypeSpec;
import forkbomb.intermediate.symtabimpl.*;

/**
 * <h1>SymTabFactory</h1>
 *
 * <p>A factory for creating objects that implement the symbol table.</p>
 *
 * <p>Copyright (c) 2012 by Steven! Ragnarök</p>
 */
public class SymTabFactory
{
  /**
   * Create and return a symbol table stack implementation.
   * @return the symbol table implementation.
   */
  public static SymTabStack createSymTabStack()
  {
    return new SymTabStackImpl();
  }

  /**
   * Create and return a symbol table implementation.
   * @param nestingLevel the nesting level.
   * @return the symbol table implementation.
   */
  public static SymTab createSymTab(int nestingLevel)
  {
    return new SymTabImpl(nestingLevel);
  }

  /**
   * Create and return a symbol table entry implementation.
   * @param name the identifier name.
   * @param symTab the symbol table that contains this entry.
   * @return the symbol table entry implementation.
   */
  public static SymTabEntry createSymTabEntry(String name, SymTab symTab)
  {
    return new SymTabEntryImpl(name, symTab);
  }
}
