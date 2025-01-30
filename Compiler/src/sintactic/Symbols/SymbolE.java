package sintactic.Symbols;

import threeAddressCode.Code;

import java.util.ArrayList;

public class SymbolE {
    private String id;
    private String type;
    private boolean negative;
    private int value;
    private int r;
    private ArrayList<Code> code;


    public SymbolE(String type, boolean negative) {
        this.type = type;
        this.negative = negative;
    }
    public SymbolE(String type, boolean negative,int value) {
        this.type = type;
        this.negative = negative;
        this.value = value;
    }
    public SymbolE(String type, boolean negative,int value,int r) {
        this.type = type;
        this.negative = negative;
        this.value = value;
        this.r = r;
    }
    public SymbolE(String id, String type) {
        this.type = type;
        this.id = id;
    }
    public String getID(){
        return id;
    }
    public String getType() {
        return type;
    }
    public boolean getNegative() {
        return negative;
    }
    public int getValue() {
        return value;
    }

    public int getR() {
        return r;
    }
    public void setR(int r){ this.r=r;}
    public ArrayList<Code> getCode() {
        return code;
    }
}
