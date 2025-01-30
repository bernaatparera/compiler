package sintactic.Symbols;

import java.util.ArrayList;
import java.util.Stack;

public class SymbolTCP {
    Stack<String> calls;
    public SymbolTCP(Stack<String> calls,String s){
        this.calls = calls;
        calls.push(s);
    }
    public SymbolTCP(){
        this.calls = new Stack<>();
    }
    public Stack<String> getCalls(){
        return calls;
    }
}
