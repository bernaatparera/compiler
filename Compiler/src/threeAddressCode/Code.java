package threeAddressCode;

// Author: Bernat Parera

public class Code {
    private final int length = 4;
    private int [] code;

    public Code(int operation,int firstOperand,int secondOperand,int destination){
        code = new int[4];
        code[0]=operation;
        code[1]=firstOperand;
        code[2]=secondOperand;
        code[3]=destination;
    }

    public String threeAddressToString(){
        return "Operation: "+ Operation.getMnemonicByCode(code[0])+" , "+code[1]+" , "+code[2]+" Dest: "+code[3];
    }
    public String getOperation(){
        return Operation.getMnemonicByCode(code[0]);
    }
    public String operand1(){
        return "V"+code[1];
    }
    public int operand1Val(){
        return code[1];
    }
    public int operand2Val(){
        return code[2];
    }
    public String operand2(){
        return "V"+code[2];
    }
    public String destinationVar(){
        return "V"+code[3];
    }
    public String destinationLabel(){
        return "L"+code[3];
    }
    public int destinationValue(){
        return code[3];
    }
    public void setDestination(int destination){
        code[3] = destination;
    }
}

