package forkbomb.intermediate.typeimpl;

import java.util.HashMap;

import wci.intermediate.*;
import forkbomb.intermediate.symtabimpl.Predefined;

import forkbomb.intermediate.typeimpl.TypeFormImpl;
import forkbomb.intermediate.typeimpl.TypeKeyImpl;

/**
 * <h1>TypeSpecImpl</h1>
 *
 * <p>A Lure type specification implementation.</p>
 *
 * <p>Copyright (c) 2012 by Steven! Ragnarök</p>
 */
public class TypeSpecImpl
  extends HashMap<TypeKey, Object>
  implements TypeSpec
{
  private TypeForm form;           // type form
  private SymTabEntry identifier;  // type identifier

  /**
   * Constructor.
   * @param form the type form.
   */
  public TypeSpecImpl(TypeForm form)
  {
    this.form = form;
    this.identifier = null;
  }

  /**
   * Constructor.
   * @param value a string value.
   */
  public TypeSpecImpl(String value)
  {
    this.form = TypeFormImpl.STRING;
  }

  /**
   * Getter
   * @return the type form.
   */
  public TypeForm getForm()
  {
    return form;
  }

  /**
   * Setter.
   * @param identifier the type identifier (symbol table entry).
   */
  public void setIdentifier(SymTabEntry identifier)
  {
    this.identifier = identifier;
  }

  /**
   * Getter.
   * @return the type identifier (symbol table entry).
   */
  public SymTabEntry getIdentifier()
  {
    return identifier;
  }

  /**
   * Set an attribute of the specification.
   * @param key the attribute key.
   * @param value the attribute value.
   */
  public void setAttribute(TypeKey key, Object value)
  {
    this.put(key, value);
  }

  /**
   * Get the value of an attribute of the specification.
   * @param key the attribute key.
   * @return the attribute value.
   */
  public Object getAttribute(TypeKey key)
  {
    return this.get(key);
  }

  public TypeSpec baseType() {
    return this;
  }
}
