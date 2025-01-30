package threeAddressCode;

import java.util.Stack;

// Author: Bernat Parera

public class Procedure {
    private String ID;
    private int np;
    private int label;
    private Stack<Integer> parameters;

    public Procedure(String id, int np, int label, Stack<Integer> parameters){
        this.ID = id;
        this.np = np;
        this.label = label;
        this.parameters = parameters;
    }

    public int label() {
        return label;
    }

    public String getID() {
        return ID;
    }

    public Stack<Integer>getParameters(){
        Stack<Integer> returnStack = new Stack<>();
        Stack<Integer> aux = new Stack<>();
        while(!parameters.empty()){
            aux.push(parameters.pop());
        }
        while(!aux.empty()) {
            int i = aux.pop();
            parameters.push(i);
            returnStack.push(i);
        }
        return returnStack;
    }

}



