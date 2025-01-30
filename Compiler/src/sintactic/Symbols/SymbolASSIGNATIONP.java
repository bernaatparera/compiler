package sintactic.Symbols;

public class SymbolASSIGNATIONP {
    private String Type;
    private String ID;
    private int value;
    private int r;
    private boolean function;

    public SymbolASSIGNATIONP(String ID,String Type,int value,int r) {
        this.Type = Type;
        this.ID = ID;
        this.value = value;
        this.r =r;
        function= false;
    }
    public SymbolASSIGNATIONP(String Type) {
        this.Type = Type;
        function= true;
    }
    public SymbolASSIGNATIONP(String Type,String id) {
        this.Type = Type;
        this.ID = id;
        function= true;
    }

    public SymbolASSIGNATIONP(int value) {
        this.value = value;
    }
    public String getType() {
        return Type;
    }
    public String getID() {
        return ID;
    }
    public int getValue() {
        return value;
    }
    public int getR() {
        return r;
    }

    public boolean isFunction() {
        return function;
    }
}
