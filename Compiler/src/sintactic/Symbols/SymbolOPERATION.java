package sintactic.Symbols;

public class SymbolOPERATION {
    private String type;
    private boolean negative;
    public SymbolOPERATION( String type,boolean negative) {
        this.type = type;
        this.negative = negative;
    }
    public String getType() {
        return type;
    }
    public boolean getNegative(){return negative;}
}
