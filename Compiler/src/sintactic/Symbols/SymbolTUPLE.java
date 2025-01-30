package sintactic.Symbols;

public class SymbolTUPLE {
    //it can be a tuple ( which we are assigning or defining a tuple) or a bool or int type
    private String type;
    public SymbolTUPLE(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
}
