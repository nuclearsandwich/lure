package forkbomb.intermediate.typeimpl;

import forkbomb.intermediate.TypeForm;

/**
 * <h1>TypeFormImpl</h1>
 *
 * <p>Type forms for a Lure type specification.</p>
 *
 * <p>Copyright (c) 2012 by Steven! Ragnarök</p>
 */
public enum TypeFormImpl implements TypeForm {
  STRING, NUMBER, BOOLEAN, RECORD, NIL;

  public String toString() {
    return super.toString().toLowerCase();
  }
}
