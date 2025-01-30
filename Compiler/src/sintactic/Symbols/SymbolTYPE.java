package sintactic.Symbols;

public class SymbolTYPE {
    public static final String  TypeInt = "int",
                                TypeBool = "bool",
                                TypeTuple = "tuple";
    private String Type;

    public SymbolTYPE(String Type) {
        this.Type = Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }
    public String getType() {
        return Type;
    }
}
