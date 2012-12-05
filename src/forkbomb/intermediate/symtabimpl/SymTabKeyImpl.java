package forkbomb.intermediate.symtabimpl;

import wci.intermediate.SymTabKey;

/**
 * <h1>SymTabKeyImpl</h1>
 *
 * <p>Attribute keys for a symbol table entry.</p>
 *
 * <p>Copyright (c) 2012 by Steven! Ragnarök</p>
 */
public enum SymTabKeyImpl implements SymTabKey
{
  // Constant.
  CONSTANT_VALUE,

// Procedure or function.
  ROUTINE_CODE, ROUTINE_SYMTAB, ROUTINE_ICODE,
  ROUTINE_PARAMS, ROUTINE_LOCALS_COUNT,

  // Variable or record field value.
  DATA_VALUE
}
