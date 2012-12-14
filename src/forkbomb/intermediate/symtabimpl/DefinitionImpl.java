package forkbomb.intermediate.symtabimpl;

import wci.intermediate.Definition;

/**
 * <h1>DefinitionImpl</h1>
 *
 * <p>How a Lure symbol table entry is defined.</p>
 *
 * <p>Copyright (c) 2012 by Steven! Ragnarök</p>
 */
public enum DefinitionImpl implements Definition
{
  CONSTANT, GLOBAL, VARIABLE, FIELD, UNDEFINED, BUILTIN_FUNCTION, FUNCTION;

  private String text;

  /**
   * Constructor.
   */
  DefinitionImpl() {
    this.text = this.toString().toLowerCase();
  }

  /**
   * Constructor.
   * @param text the text for the definition code.
   */
  DefinitionImpl(String text) {
    this.text = text;
  }

  /**
   * Getter.
   * @return the text of the definition code.
   */
  public String getText() {
    return text;
  }
}
