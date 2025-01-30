package assemblyCode;

import threeAddressCode.Code;
import threeAddressCode.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

// Author: Bernat Parera

public class AssemblyGenerator {
    private static PrintStream out;
    private static int labelsAux;
    //variable to store the stack pointer
    private static int sp;
    private static HashMap<String,Procedure> pt;
    private static HashMap<Integer,String> pl;
    private static boolean first = true;
    public static void generateAssembly(PrintStream file, ArrayList<Code> code, ArrayList<Variable> vt,
         HashMap<String,Procedure> procedures,HashMap<Integer,String> procedureLabels) throws FileNotFoundException, IOException {
        //Incializamos el documento easy
        out = file;
        pl = procedureLabels;
        pt = procedures;
        out.println("\tORG\t$1000\t;Starting address");
        out.println("START:");
        for(Code c : code){
            switch(c.getOperation()){
                case "copy" :
                        //copy doesn't need a method
                        pre(c);
                        post(c);
                        break;
                case "add" :
                        pre(c);
                        add(c);
                        post(c);
                        break;
                case "sub" :
                        pre(c);
                        sub(c);
                        post(c);
                        break;
                case "prod":
                        pre(c);
                        prod(c);
                        post(c);
                        break;
                case "div" :
                        pre(c);
                        div(c);
                        post(c);
                        break;


                case "copyval" :
                    copyval(c);
                    break;

                case "and" :
                        pre(c);
                        and(c);
                        post(c);
                        break;
                case "or" :
                        pre(c);
                        or(c);
                        post(c);
                        break;
                case "not" :
                        pre(c);
                        not(c);
                        post(c);
                        break;
                case "xor" :
                        pre(c);
                        xor(c);
                        post(c);
                        break;
                case "nand" :
                        pre(c);
                        nand(c);
                        post(c);
                        break;
                case "xnor" :
                        pre(c);
                        xnor(c);
                        post(c);
                        break;
                case "nor" :
                        pre(c);
                        nor(c);
                        post(c);
                        break;
                case "skip" :
                        skip(c);
                        break;
                case "goto" :
                        goTo(c);
                        break;
                case "LT" :
                        pre(c);
                        LT();
                        post(c);
                        break;
                case "LE" :
                        pre(c);
                        LE();
                        post(c);
                        break;
                case "EQ" :
                        pre(c);
                        EQ();
                        post(c);
                        break;
                case "NE" :
                        pre(c);
                        NE();
                        post(c);
                        break;
                case "GE" :
                        pre(c);
                        GE();
                        post(c);
                        break;
                case "GT" :
                        pre(c);
                        GT();
                        post(c);
                        break;
                case "IF" :
                        IF(c);
                        break;
                case "pmb" :
                        pmb(c);

                        break;
                case "call" :
                        call(c);
                        break;
                case "assigcall" :
                        assigcall(c);
                        break;
                case "rtn":
                        rtn(c);
                    break;
                case "param_s":
                        param(c);
                        break;
                case "input":
                        input(c);
                        break;
                case "println":
                        println(c);
                        break;
                case "print":
                        print(c);
                        break;
            }

        }
        out.println("\tSIMHALT\t;Stop execution");
        out.println(";VARS");
        for(int i = 0; i < vt.size();i++){
            out.println("V"+i+":\tDS.L 1");
        }
        out.println("\tEND START\t\t;-- END --");
        out.close();
    }


    private static void add(Code c){
        out.println("\tADD.L\tD1,D0\t;ADD of values of D1 and D0, result in D0");
    }

    private static void sub(Code c){
        out.println("\tSUB.L\tD1,D0\t;SUB of values of D1 and D0, result in D0");
    }

    private static void prod(Code c){
        out.println("\tMULU\tD1,D0\t;Product of values of D1 and D0, result in D0");
    }

    private static void div(Code c){
        out.println("\tDIVU\tD1,D0\t;Divition of values of D1 and D0, result in D0");
    }


    private static void and(Code c){
        out.println("\tAND.L\tD1,D0\t;AND of values of D1 and D0, result in D0");
    }

    private static void or(Code c){
        out.println("\tOR.L\tD1,D0\t;AND of values of D1 and D0, result in D0");
    }

    private static void not(Code c){
        out.println("\tMOVE.L\t#-1,D1\t;Negation of D1 through mask");
        out.println("\tEOR.L\tD1,D0\t;XOR of D1 and D0");
    }

    private static void xor(Code c){
        out.println("\tEOR.L\tD1,D0\t;AND of values of D1 and D0, result in D0");
    }

    private static void nand(Code c){
        and(c);
        not(c);
    }

    private static void nor(Code c){
        or(c);
        not(c);
    }

    private static void xnor(Code c){
        xor(c);
        not(c);
    }
    private static void conditional(int label){
        out.println("\nFALSE"+label+":");
        out.println("\tMOVE.L\t #0,D0\t;Insert FALSE into D0");
        out.println("\tBRA\tAUX"+label);
        out.println("\nTRUE"+label+":");
        out.println("\tMOVE.L\t #-1,D0\t;Insert TRUE into D0");
        out.println("\tBRA\tAUX"+label);
    }
    private static void LT(){
        int aux = labelsAux++;
        out.println("\tCMP.L\tD1,D0\t;Comparamos D0 y D1");
        out.println("\tBLT\tTRUE"+aux+"\t;if D0<D1 TRUE");
        out.println("\tBRA\tFALSE"+aux);
        conditional(aux);
        out.println("\nAUX"+aux+":");
    }
    private static void NE(){
        int aux = labelsAux++;
        out.println("\tCMP.L\tD1,D0\t;Comparamos D0 y D1");
        out.println("\tBNE\tTRUE"+aux+"\t;if D0<D1 TRUE");
        //else false
        out.println("\tBRA\tFALSE"+aux);
        conditional(aux);
        out.println("\nAUX"+aux+":");
    }

    private static void GE(){
        int aux = labelsAux++;
        out.println("\tCMP.L\tD1,D0\t;Comparamos D0 y D1");
        out.println("\tBGE\tTRUE"+aux+"\t;if D0<D1 TRUE");
        //else false
        out.println("\tBRA\tFALSE"+aux);
        conditional(aux);
        out.println("\nAUX"+aux+":");
    }

    private static void GT(){
        int aux = labelsAux++;
        out.println("\tCMP.L\tD1,D0\t;Comparamos D0 y D1");
        out.println("\tBGT\tTRUE"+aux+"\t;if D0<D1 TRUE");
        //else false
        out.println("\tBRA\tFALSE"+aux);
        conditional(aux);
        out.println("\nAUX"+aux+":");
    }

    private static void LE(){
        int aux = labelsAux++;
        out.println("\tCMP.L\tD1,D0\t;Comparamos D0 y D1");
        out.println("\tBLE\tTRUE"+aux+"\t;if D0<D1 TRUE");
        //else false
        out.println("\tBRA\tFALSE"+aux);
        conditional(aux);
        out.println("\nAUX"+aux+":");
    }
    private static void EQ(){
        int aux = labelsAux++;
        out.println("\tCMP.L\tD1,D0\t;Comparamos D0 y D1");
        out.println("\tBGE\tTRUE"+aux+"\t;if D0<D1 TRUE");
        //else false
        out.println("\tBEQ\tFALSE"+aux);
        conditional(aux);
        out.println("\nAUX"+aux+":");
    }


    private static void copyval(Code c){
        out.println("\tMOVE.L\t #"+c.operand1Val()+",D0\t;Insert value into D0");
        out.println("\tMOVE.L\tD0,"+c.destinationVar()+"\t;Save value into destination");
    }

    private static void skip(Code c){
        out.println("\n"+c.destinationLabel()+":");
    }
    private static void goTo(Code c){
        out.println("\tBRA\t"+c.destinationLabel()+"\t");
    }
    private static void IF(Code c){
        out.println("\tMOVE.L\t"+c.operand1()+",D0\t;Insert boolean into D0");
        out.println("\tTST\tD0\t;check if D0 is equal to 0");
        out.println("\tBEQ\t"+c.destinationLabel()+"\t;if D0 is 0 we go to the destination");
    }

    private static void input(Code c){
        out.println("\tMOVE.L\t#4, D0\t;Preparation for keyboard input");
        out.println("\tTRAP\t#15\t");
        out.println("\tMOVE.L\tD1,"+c.destinationVar()+"\t;Move input into destination");
    }

    private static void param(Code c){
        sp ++;
        if(first){
            out.println("\tSUB.L\t#4,A7");
            first = false;
        }
        out.println("\tMOVE.L\t"+c.destinationVar()+",-(A7)\t;add parameter to stack");
    }
    private static void rtn(Code c){
        if(c.destinationValue() == -1){
            out.println("\tMOVE.L\t#0,-(A7)\t;Add return value to the stack");
            out.println("\tADD.L\t#4,A7 ; return the sp to the original value");
            out.println("\tRTS\t\t;Return without return line");
        }else{
            out.println("\tMOVE.L\t"+c.destinationVar()+",-(A7)\t;Add return value to the stack");
            out.println("\tADD.L\t#4,A7 ; return the sp to the original value");
            out.println("\tRTS\t\t; Return with value");
        }
    }
    private static void assigcall(Code c){
        first = true;
        //if sp is 0 (no parameters) we don't have to add
        if(sp > 0)
            out.println("\tADD.L\t#"+4*++sp+",A7");
        out.println("\tJSR L"+c.operand2Val()+"\t\t;jump to subroutine");
        sp = 0;
        out.println("\tSUB.L\t#4,A7");
        out.println("\tMOVE.L\t-(A7),V"+ c.destinationValue()+"\t");
        out.println("\tADD.L\t#8,A7");

    }
    private static void call(Code c){
        first = true;
        //if sp is 0 (no parameters) we don't have to add
        if(sp > 0)
            out.println("\tADD.L\t#"+4*++sp+",A7");
        out.println("\tJSR "+c.destinationLabel()+"\t\t;jump to subroutine");
        sp = 0;

    }
    private static void pmb(Code c){
        Stack<Integer> params = pt.get(pl.get(c.operand1Val())).getParameters();
        Integer [] array = params.toArray(new Integer[0]);
        for(int i =0;i < array.length;i++){
            int aux= array[i];
            sp++;
            out.println("\tMOVE.L\t-(A7),V"+aux+"\t");
        }
        out.println("\tADD.L\t#"+4*sp+",A7");
        sp = 0;
    }

    private static void println(Code c){
        out.println("\tMOVE.L\t"+c.destinationVar()+",D1\t;Message to D1");
        out.println("\tEXT.L\tD1\t;Message to D1");
        out.println("\tMOVE.L\t#3,D0\t;Preparation");
        out.println("\tTRAP\t#15\t");
        out.println("\tMOVE.L\t#11,D0\t;");
        out.println("\tMOVE.W\t#$00FF,D1\t;");
        out.println("\tTRAP\t#15\t");
        out.println("\tADD.W\t#1,D1\t;");
        out.println("\tAND.W\t#$00FF,D1\t;");
        out.println("\tTRAP\t#15\t");
    }
    private static void print(Code c){
        out.println("\tMOVE.L\t"+c.destinationVar()+",D1\t;Message to D1");
        out.println("\tEXT.L\tD1\t;Preparation");
        out.println("\tMOVE.L\t#3,D0\t;Preparation");
        out.println("\tTRAP\t#15\t");
    }


    private static void pre(Code c){
        out.println("\tMOVE.L\t"+c.operand1()+",D0\t;Insert value into D0");
        out.println("\tMOVE.L\t"+c.operand2()+",D1\t;Insert value into D1");
    }

    private static void post(Code c){
        out.println("\tMOVE.L\tD0,"+c.destinationVar()+"\t;Save value into destination");

    }

}