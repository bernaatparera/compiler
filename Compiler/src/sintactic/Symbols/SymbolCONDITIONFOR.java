package sintactic.Symbols;

public class SymbolCONDITIONFOR extends SymbolCONDITION{
    private int start;
    public SymbolCONDITIONFOR(SymbolCONDITION cond,int goTo) {
        super(cond.getPre(),cond.getEnd());
        this.start = goTo;
    }
    public int getStart() {
        return start;
    }

}
