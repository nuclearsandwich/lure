package wci.intermediate;

import java.util.ArrayList;

/**
 * <h1>SymTabEntry</h1>
 *
 * <p>The interface for a symbol table entry.</p>
 *
 * <p>Copyright (c) 2008 by Ronald Mak</p>
 * <p>For instructional purposes only.  No warranties.</p>
 */
public interface SymTabEntry
{
    /**
     * Getter.
     * @return the name of the entry.
     */
    public String getName();

    /**
     * Getter.
     * @return the symbol table that contains this entry.
     */
    public SymTab getSymTab();

    /**
     * Getter.
     * @return the nesting level of the parent symbol table.
     */
    public int getNestingLevel();

    /**
     * Setter. 
     * @param index the local variable array index.
     */
    public void setIndex(int index);
    
    /**
     * Getter.
     * @return the local variable index.
     */
    public int getIndex();

    /**
     * Setter.
     * @param definition the definition to set.
     */
    public void setDefinition(Definition definition);

    /**
     * Getter.
     * @return the definition.
     */
    public Definition getDefinition();

    /**
     * Setter.
     * @param typeSpec the type specification to set.
     */
    public void setTypeSpec(TypeSpec typeSpec);

    /**
     * Getter.
     * @return the type specification.
     */
    public TypeSpec getTypeSpec();

    /**
     * Append a source line number to the entry.
     * @param lineNumber the line number to append.
     */
    public void appendLineNumber(int lineNumber);

    /**
     * Getter.
     * @return the list of source line numbers.
     */
    public ArrayList<Integer> getLineNumbers();

    /**
     * Set an attribute of the entry.
     * @param key the attribute key.
     * @param value the attribute value.
     */
    public void setAttribute(SymTabKey key, Object value);

    /**
     * Get the value of an attribute of the entry.
     * @param key the attribute key.
     * @return the attribute value.
     */
    public Object getAttribute(SymTabKey key);

    /**
     * Check if the entry is global or local
     * @return true if the entry is global and false otherwise.
     */
    public boolean isGlobal();
}
