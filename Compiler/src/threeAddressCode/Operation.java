package threeAddressCode;

// Author: Bernat Parera

public enum Operation {
    // Operaciones aritméticas
    COPY(0, "copy"),
    ADD(1, "add"),
    SUB(2, "sub"),
    PROD(3, "prod"),
    DIV(4, "div"),
    COPYVAL(5, "copyval"),

    // Operaciones lógicas
    AND(6, "and"),
    OR(7, "or"),
    NOT(8, "not"),
    XOR(9, "xor"),
    NAND(10, "nand"),
    XNOR(11, "xnor"),
    NOR(12, "nor"),


    // Operaciones de control de flujo
    SKIP(13, "skip"),
    GOTO(14, "goto"),
    LT(15, "LT"),
    LE(16, "LE"),
    EQ(17, "EQ"),
    NE(18, "NE"),
    GE(19, "GE"),
    GT(20, "GT"),
    IF(21,"IF"),

    // Operaciones de procedimientos
    PMB(22, "pmb"),
    CALL(23, "call"),
    ASSGINATIONCALL(24, "assigcall"),
    RTN(25, "rtn"),
    PARAM_S(26, "param_s"),
    //input and output
    INPUT(27, "input"),
    PRINTLN(28, "println"),
    PRINT(29, "print");

    private final int code;
    private final String mnemonic;

    // Constructor del enum
    Operation(int code, String mnemonic) {
        this.code = code;
        this.mnemonic = mnemonic;
    }

    public int getCode() {
        return code;
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public static int getID(String name) {
        for (Operation op : Operation.values()) {
            if (op.getMnemonic().equalsIgnoreCase(name)) {
                return op.getCode();
            }
        }
        return -1;
    }

    public static String getMnemonicByCode(int code) {
        for (Operation op : Operation.values()) {
            if (op.getCode() == code) {
                return op.getMnemonic();
            }
        }
        throw new IllegalArgumentException("No operation found for code: " + code);
    }
}
