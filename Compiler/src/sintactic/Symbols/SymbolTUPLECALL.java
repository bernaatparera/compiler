package sintactic.Symbols;


public class SymbolTUPLECALL {
    //it can be a tuple ( which we are assigning or defining a tuple) or a bool or int type
    private String type;
    private int r;
    public SymbolTUPLECALL(String type,int r) {
        this.type = type;
        this.r = r;
    }
    public String getType() {
        return type;
    }

    public int getR() {
        return r;
    }
}
