package threeAddressCode;

import sintactic.*;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

// Author: Bernat Parera

public class CodeGenerator {
    private int labels;
    private int varCounter;
    private ArrayList<Code> code;
    //variable table
    private ArrayList<Variable> vt;
    //procedure table
    private HashMap<String, Procedure> pt;
    private HashMap<Integer,String> procedureLabels;
    private SymbolTable st;
    private Stack<Integer> parameters;
    //optimization structures
    private HashMap<Integer,Code> skip;
    private HashMap<Integer,Boolean> existBranch;

    public CodeGenerator(SymbolTable st){
        skip = new HashMap<>();
        existBranch = new HashMap<>();
        parameters = new Stack<>();
        this.st = st;
        varCounter = 0;
        labels =0;
        code = new ArrayList<>();
        vt = new ArrayList<>();
        procedureLabels = new HashMap<>();
        pt = new HashMap<>();
    }
    public int nextLabel(){
        return ++labels;
    }
    public String nextVar(){
        return "t"+varCounter++;
    }

    public int declare(String ID,String Type, boolean t){
        vt.add(new Variable(ID,Type,st.getLevel()));
        if(!t)
            st.consult(ID).setIndexVT(vt.size()-1);
        return vt.size()-1;
    }


    //Assignations
    public void assignation(int procendence,int destination){
        Code c = new Code(Operation.getID("copy"),procendence,0,destination);
        code.add(c);
    }
    public void assignationVal(int val,int destination){
        code.add(new Code(Operation.getID("copyval"),val,0,destination));
    }

    //Arithmetics
    public void arithmetic( String mnemonic, int op1, int op2, int destination){
        //it means it is a -id
        if(op1 == -1){
            String s = nextVar();
            vt.add(new Variable(s,Description.TypeInt,st.getLevel()));
            assignationVal(0,vt.size()-1);
            code.add(new Code(Operation.getID(mnemonic),vt.size()-1,op2,destination));
        }else
            code.add(new Code(Operation.getID(mnemonic),op1,op2,destination));
    }

    //Logic
    public void logic(String mnemonic, int op1, int op2, int destination){
        code.add(new Code(Operation.getID(mnemonic),op1,op2,destination));
    }
    //Logic
    public void relational(String mnemonic, int op1, int op2, int destination) {
        code.add(new Code(Operation.getID(mnemonic),op1,op2,destination));
    }




    public void label(int e){
        Code c =new Code(Operation.getID("skip"),0,0,e);
        code.add(c);
        skip.put(e,c);
    }
    public void goTo(int e){
        code.add(new Code(Operation.getID("goto"),0,0,e));
        existBranch.put(e,true);
    }
    //generate a conditional--> if bool is true, skip, if false, goto e
    public void conditional(int bool,int e){
        code.add(new Code(Operation.getID("IF"),bool,0,e));
        existBranch.put(e,true);
    }
    public void rtn(int src){
        code.add(new Code(Operation.getID("rtn"),0,0,src));
    }
    public void rtn(){
        code.add(new Code(Operation.getID("rtn"),0,0,-1));
    }
    public int pmb(String id){
        int goTo = nextLabel();
        goTo(goTo);
        int label = nextLabel();
        pt.put(id,new Procedure(id,st.getLevel(),label,getParameters()));
        procedureLabels.put(label,id);

        label(label);
        //add code pmb with function's label in first operand and np destination
        code.add(new Code(Operation.getID("pmb"),label,0,st.getLevel()));
        return goTo;
    }
    public void call(int destination,String id){
        code.add(new Code(Operation.getID("assigcall"),0,pt.get(id).label(),destination));
        existBranch.put(pt.get(id).label(),true);
    }
    public void call(String id){
        code.add(new Code(Operation.getID("call"),0,0,pt.get(id).label()));
        existBranch.put(pt.get(id).label(),true);
    }
    public void param(int r){
        code.add(new Code(Operation.getID("param_s"),0,0,r));
    }
    public void print(int r){
        code.add(new Code(Operation.getID("print"),0,0,r));
    }
    public void println(int r){
        code.add(new Code(Operation.getID("println"),0,0,r));
    }
    public void input(int r){
        code.add(new Code(Operation.getID("input"),0,0,r));
    }
    public int functionParameter(String Type){
        String id = nextVar();
        vt.add(new Variable(id,Type,st.getLevel()));
        parameters.push(vt.size()-1);
        return vt.size()-1;
    }
    public Stack<Integer> getParameters(){
        Stack<Integer> aux = new Stack<>();
        aux.addAll(parameters);
        parameters = new Stack<>();
        return aux;
    }
    public ArrayList<Code> getCode(){
        return code;
    }
    public ArrayList<Variable> getVt(){
        return vt;
    }
    public HashMap<String,Procedure> getProcedures(){
        return pt;
    }
    public HashMap<Integer,String> getPl(){
        return procedureLabels;
    }




    public void print3AddressCode(PrintStream out){
        if(code.isEmpty()){
            out.println("There is no 3 address code");
        } else {
            String fmt = "%s";
            for(int i =0;i<code.size();i++){
                out.println(String.format(fmt,code.get(i).threeAddressToString()));
            }
        }
    }
    public void print3AddressVars(PrintStream out){
        if(vt.isEmpty()){
            out.println("There is no 3 address variables");
        } else {
            String fmt = "%s";
            for(int i =0;i<vt.size();i++){
                out.println(String.format(fmt,vt.get(i).toString()));
            }
        }
    }

    public void Optimization(PrintStream out){
        boolean change = true;
        while(change){
            change = false;
            //optimization 1
            ArrayList<Integer> removes = new ArrayList<>();
            for (Integer i : skip.keySet()) {
                if(existBranch.get(i) == null ||!existBranch.get(i)){
                    removes.add(i);
                    change = true;
                }
            }
            for(Integer i : removes){
                code.remove(skip.get(i));
                skip.remove(i);
            }
            //optimization 2
            for(Code c : code){
                if(c.getOperation().equals("goto")){
                    int label = c.destinationValue();
                    Code aux = skip.get(label);
                    int i = code.indexOf(aux);
                    if(i+1<code.size() && code.get(i+1).getOperation().equals("goto")){
                        c.setDestination(code.get(i+1).destinationValue());
                        change = true;
                    }
                }
            }
            refreshBranch();
        }
        //optimization 5
        noAccessCode();
        print3AddressCode(out);
    }
    public void refreshBranch(){
        existBranch = new HashMap<>();
        for(Code c : code){
            //first
            String op = c.getOperation();
             if(op.equals("IF")||op.equals("call")||op.equals("goto")){
                existBranch.put(c.destinationValue(),true);
            }else if(op.equals("assigcall")){
                 existBranch.put(c.operand2Val(),true);
             }
        }
    }
    public void noAccessCode(){
        for (int i = 0; i < code.size(); i++) {
            Code c = code.get(i);
            String op = c.getOperation();
            if(op.equals("goto")||op.equals("rtn")){
                removeUntilSkip(i+1);
            }
        }

    }
    public void removeUntilSkip(int i){
        for (int j = i; j < code.size(); j++) {
            if(code.get(j).getOperation().equals("skip")){
                break;
            }else{
                code.remove(i);
                j--;
            }
        }
    }

}
