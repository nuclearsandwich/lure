package forkbomb.intermediate.symtabimpl;

import java.util.ArrayList;

import forkbomb.intermediate.*;
import forkbomb.intermediate.symtabimpl.*;

import static forkbomb.intermediate.symtabimpl.DefinitionImpl.*;
import static forkbomb.intermediate.symtabimpl.SymTabKeyImpl.*;
import static forkbomb.intermediate.symtabimpl.RoutineCodeImpl.*;
import static forkbomb.intermediate.typeimpl.TypeFormImpl.*;
import static forkbomb.intermediate.typeimpl.TypeKeyImpl.*;

/**
 * <h1>Predefined</h1>
 *
 * <p>Enter the predefined Pascal types, identifiers, and constants
 * into the symbol table.</p>
 *
 * <p>Copyright (c) 2008 by Ronald Mak</p>
 * <p>For instructional purposes only.  No warranties.</p>
 */
public class Predefined
{
    // Predefined types.
    public static TypeSpec integerType;
    // TODO Support Reals
    // public static TypeSpec realType;
    public static TypeSpec booleanType;
    public static TypeSpec stringType;
    public static TypeSpec undefinedType;

    // Predefined identifiers.
    public static SymTabEntry falseId;
    public static SymTabEntry trueId;
    public static SymTabEntry nilId;
    public static SymTabEntry plusId;
    public static SymTabEntry minusId;
    public static SymTabEntry starId;
    public static SymTabEntry divId;
    public static SymTabEntry modId;
    public static SymTabEntry eqId;
    public static SymTabEntry neqId;
    public static SymTabEntry gtId;
    public static SymTabEntry gteId;
    public static SymTabEntry ltId;
    public static SymTabEntry lteId;
    public static SymTabEntry andId;
    public static SymTabEntry orId;
    public static SymTabEntry toStrId;

    /**
     * Initialize a symbol table stack with predefined identifiers.
     * @param symTab the symbol table stack to initialize.
     */
    public static void initialize(SymTabStack symTabStack)
    {
        initializeTypes(symTabStack);
        initializeConstants(symTabStack);
        initializeStandardRoutines(symTabStack);
    }

    /**
     * Initialize the predefined types.
     * @param symTabStack the symbol table stack to initialize.
     */
    private static void initializeTypes(SymTabStack symTabStack)
    {
        // Type integer.
        integerId = symTabStack.enterLocal("integer");
        integerType = TypeFactory.createType(SCALAR);
        integerType.setIdentifier(integerId);
        integerId.setDefinition(DefinitionImpl.TYPE);
        integerId.setTypeSpec(integerType);

        // Type real.
        realId = symTabStack.enterLocal("real");
        realType = TypeFactory.createType(SCALAR);
        realType.setIdentifier(realId);
        realId.setDefinition(DefinitionImpl.TYPE);
        realId.setTypeSpec(realType);

        // Type boolean.
        booleanId = symTabStack.enterLocal("boolean");
        booleanType = TypeFactory.createType(ENUMERATION);
        booleanType.setIdentifier(booleanId);
        booleanId.setDefinition(DefinitionImpl.TYPE);
        booleanId.setTypeSpec(booleanType);

        // Type char.
        charId = symTabStack.enterLocal("char");
        charType = TypeFactory.createType(SCALAR);
        charType.setIdentifier(charId);
        charId.setDefinition(DefinitionImpl.TYPE);
        charId.setTypeSpec(charType);

        // Undefined type.
        undefinedType = TypeFactory.createType(SCALAR);
    }

    /**
     * Initialize the predefined constant.
     * @param symTabStack the symbol table stack to initialize.
     */
    private static void initializeConstants(SymTabStack symTabStack)
    {
        // Boolean enumeration constant false.
        falseId = symTabStack.enterLocal("false");
        falseId.setDefinition(DefinitionImpl.ENUMERATION_CONSTANT);
        falseId.setTypeSpec(booleanType);
        falseId.setAttribute(CONSTANT_VALUE, new Integer(0));

        // Boolean enumeration constant true.
        trueId = symTabStack.enterLocal("true");
        trueId.setDefinition(DefinitionImpl.ENUMERATION_CONSTANT);
        trueId.setTypeSpec(booleanType);
        trueId.setAttribute(CONSTANT_VALUE, new Integer(1));

        // Add false and true to the boolean enumeration type.
        ArrayList<SymTabEntry> constants = new ArrayList<SymTabEntry>();
        constants.add(falseId);
        constants.add(trueId);
        booleanType.setAttribute(ENUMERATION_CONSTANTS, constants);
    }

    /**
     * Initialize the standard procedures and functions.
     * @param symTabStack the symbol table stack to initialize.
     */
    private static void initializeStandardRoutines(SymTabStack symTabStack)
    {
        readId    = enterStandard(symTabStack, PROCEDURE, "read",    READ);
        readlnId  = enterStandard(symTabStack, PROCEDURE, "readln",  READLN);
        writeId   = enterStandard(symTabStack, PROCEDURE, "write",   WRITE);
        writelnId = enterStandard(symTabStack, PROCEDURE, "writeln", WRITELN);

        absId    = enterStandard(symTabStack, FUNCTION, "abs",    ABS);
        arctanId = enterStandard(symTabStack, FUNCTION, "arctan", ARCTAN);
        chrId    = enterStandard(symTabStack, FUNCTION, "chr",    CHR);
        cosId    = enterStandard(symTabStack, FUNCTION, "cos",    COS);
        eofId    = enterStandard(symTabStack, FUNCTION, "eof",    EOF);
        eolnId   = enterStandard(symTabStack, FUNCTION, "eoln",   EOLN);
        expId    = enterStandard(symTabStack, FUNCTION, "exp",    EXP);
        lnId     = enterStandard(symTabStack, FUNCTION, "ln",     LN);
        oddId    = enterStandard(symTabStack, FUNCTION, "odd",    ODD);
        ordId    = enterStandard(symTabStack, FUNCTION, "ord",    ORD);
        predId   = enterStandard(symTabStack, FUNCTION, "pred",   PRED);
        roundId  = enterStandard(symTabStack, FUNCTION, "round",  ROUND);
        sinId    = enterStandard(symTabStack, FUNCTION, "sin",    SIN);
        sqrId    = enterStandard(symTabStack, FUNCTION, "sqr",    SQR);
        sqrtId   = enterStandard(symTabStack, FUNCTION, "sqrt",   SQRT);
        succId   = enterStandard(symTabStack, FUNCTION, "succ",   SUCC);
        truncId  = enterStandard(symTabStack, FUNCTION, "trunc",  TRUNC);
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
