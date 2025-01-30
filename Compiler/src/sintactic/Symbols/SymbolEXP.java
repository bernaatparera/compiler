package sintactic.Symbols;


public class SymbolEXP {
    public SymbolEXP() {
    }

    //An expression can have 3 modes:
    public enum MODE{
        VARMODE,
        CONSTMODE,
        RESULTMODE
    }

    private String Type;
    private String Mode;

    public SymbolEXP(String type,MODE mode){
        this.Type = type;
        this.Mode = mode.name();
    }
    public SymbolEXP(MODE mode){
        this.Mode = mode.name();
    }
    public String getType(){
        return Type;
    }
}
