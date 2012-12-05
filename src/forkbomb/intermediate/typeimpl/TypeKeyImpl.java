package forkbomb.intermediate.typeimpl;

import wci.intermediate.TypeKey;

/**
 * <h1>TypeKeyImpl</h1>
 *
 * <p>Attribute keys for a Lure type specification.</p>
 *
 * <p>Copyright (c) 2012 by Steven! Ragnarök</p>
 */
public enum TypeKeyImpl implements TypeKey
{
    // Enumeration
    ENUMERATION_CONSTANTS,

    // Subrange
    SUBRANGE_BASE_TYPE, SUBRANGE_MIN_VALUE, SUBRANGE_MAX_VALUE,

    // Array
    ARRAY_INDEX_TYPE, ARRAY_ELEMENT_TYPE, ARRAY_ELEMENT_COUNT,

    // Record
    RECORD_SYMTAB
}
