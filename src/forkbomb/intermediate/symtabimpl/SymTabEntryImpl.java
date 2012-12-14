package forkbomb.intermediate.symtabimpl;

import java.util.ArrayList;
import java.util.HashMap;

import wci.intermediate.*;
import forkbomb.intermediate.TypeForm;
import forkbomb.intermediate.TypeKey;
import forkbomb.intermediate.TypeSpec;


/**
 * <h1>SymTabEntryImpl</h1>
 *
 * <p>An implementation of a symbol table entry.</p>
 *
 * <p>Copyright (c) 2012 by Steven! Ragnarök</p>
 */
public class SymTabEntryImpl
  extends HashMap<SymTabKey, Object>
  implements SymTabEntry
{
  private String name;                     // entry name
  private SymTab symTab;                   // parent symbol table
  private int index;                       // local variable array index
  private Definition definition;           // how the identifier is defined
  private TypeSpec typeSpec;               // type specification
  private ArrayList<Integer> lineNumbers;  // source line numbers

  /**
   * Constructor.
   * @param name the name of the entry.
   * @param symTab the symbol table that contains this entry.
   */
  public SymTabEntryImpl(String name, SymTab symTab)
  {
    this.name = name;
    this.symTab = symTab;
    this.lineNumbers = new ArrayList<Integer>();
  }

  /**
   * Getter.
   * @return the name of the entry.
   */
  public String getName()
  {
    return name;
  }

  /**
   * Getter.
   * @return the symbol table that contains this entry.
   */
  public SymTab getSymTab()
  {
    return symTab;
  }

  /**
   * Getter.
   * @return the nesting level of the entry's parent symbol table.
   */
  public int getNestingLevel() {
    return getSymTab().getNestingLevel();
  }

  /**
   * Setter. 
   * @param index the local variable array index.
   */
  public void setIndex(int index)
  {
    this.index = index;
  }

  /**
   * Getter.
   * @return the local variable index.
   */
  public int getIndex()
  {
    return index;
  }

  /**
   * Setter.
   * @param definition the definition to set.
   */
  public void setDefinition(Definition definition)
  {
    this.definition = definition;
  }

  /**
   * Getter.
   * @return the definition.
   */
  public Definition getDefinition()
  {
    return definition;
  }

  /**
   * Setter.
   * @param typeSpec the type specification to set.
   */
  public void setTypeSpec(TypeSpec typeSpec)
  {
    this.typeSpec = typeSpec;
  }

  /**
   * Getter.
   * @return the type specification.
   */
  public TypeSpec getTypeSpec()
  {
    return typeSpec;
  }

  /**
   * Append a source line number to the entry.
   * @param lineNumber the line number to append.
   */
  public void appendLineNumber(int lineNumber)
  {
    lineNumbers.add(lineNumber);
  }

  /**
   * Getter.
   * @return the list of source line numbers for the entry.
   */
  public ArrayList<Integer> getLineNumbers()
  {
    return lineNumbers;
  }

  /**
   * Set an attribute of the entry.
   * @param key the attribute key.
   * @param value the attribute value.
   */
  public void setAttribute(SymTabKey key, Object value)
  {
    put(key, value);
  }

  /**
   * Get the value of an attribute of the entry.
   * @param key the attribute key.
   * @return the attribute value.
   */
  public Object getAttribute(SymTabKey key)
  {
    return get(key);
  }

  public boolean isGlobal() {
    return getNestingLevel() == 0;
  }
}
