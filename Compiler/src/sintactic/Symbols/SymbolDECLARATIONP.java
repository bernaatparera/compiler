package sintactic.Symbols;

public class SymbolDECLARATIONP{
    private String Type;
    private String ID;
    private int value;
    private int r;
    private String function;

    public SymbolDECLARATIONP(String Type, String ID, int value) {
        this.Type = Type;
        this.ID = ID;
        this.value = value;
    }
    public SymbolDECLARATIONP(String ID,String Type,int value,int r,String function) {
        this.Type = Type;
        this.ID = ID;
        this.value = value;
        this.r =r;
        this.function = function;
    }
    public SymbolDECLARATIONP(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }
    public String getType() {
        return Type;
    }
    public int getValue() {
        return value;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public void setType(String Type) {
        this.Type = Type;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public int getR() {
        return r;
    }

    public String function() {
        return function;
    }
}
