package sintactic.Symbols;

public class SymbolDECLARATION {
    private int td;
    private String id;
    public SymbolDECLARATION(String id,int td){
        this.td = td;
        this.id = id;
    }

    public int gettd() {
        return td;
    }

    public String getId() {
        return id;
    }
}
