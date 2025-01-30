package sintactic.Symbols;

public class SymbolASSIGN {
    int pre;
    int end;
    public SymbolASSIGN(int pre, int end) {
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
