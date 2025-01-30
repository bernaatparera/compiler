package sintactic.Symbols;

public class SymbolCONDITION {
    private int pre,end;
    public SymbolCONDITION(int pre, int end) {
        this.pre = pre;
        this.end = end;
    }
    public int getPre() {
        return pre;
    }
    public int getEnd() {
        return end;
    }
}