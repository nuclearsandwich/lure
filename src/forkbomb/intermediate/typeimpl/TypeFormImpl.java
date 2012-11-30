package forkbomb.intermediate.typeimpl;

import forkbomb.intermediate.TypeForm;

/**
 * <h1>TypeFormImpl</h1>
 *
 * <p>Type forms for a Pascal type specification.</p>
 *
 * <p>Copyright (c) 2008 by Ronald Mak</p>
 * <p>For instructional purposes only.  No warranties.</p>
 */
public enum TypeFormImpl implements TypeForm
{
    SCALAR;

    public String toString()
    {
        return super.toString().toLowerCase();
    }
}
