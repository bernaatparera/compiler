package sintactic;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
// Author: Bernat Parera

//Every level has its own TE
public class SymbolTable {
    // Indicates the level at which we are adding data.
    int level;
    // ta
    // scope table: contains the id of the last variable declared in the current
    // level
    private final ArrayList<Integer> ta;
    // td
    // description table: contains the id of the visible variables from the current
    // level
    private final HashMap<String, Description> td;

    private final HashMap<Integer, Description> te;

    private static final String errorPath = "Results/Errors.txt";

    public SymbolTable() {
        level = 0;
        ta = new ArrayList<>();
        ta.add(level, 0);
        te = new HashMap<>();
        td = new HashMap<>();
        insertConstants();
    }

    public void insertConstants() {

        // Add boolean type
        Description d = new Description(Description.TypeBool, 0, Description.dType, -1, "", -1, 0);
        td.put(Description.TypeBool, d);

        // Add true constant
        d = new Description("TRUE", 0, Description.dConstant, -1, Description.TypeBool);
        td.put("TRUE", d);

        // Add false constant
        d = new Description("FALSE", 0, Description.dConstant, 0, Description.TypeBool);
        td.put("FALSE", d);
        // Add integer type
        d = new Description(Description.TypeInt, 0, Description.dType, -1, "", Integer.MIN_VALUE, Integer.MAX_VALUE);
        td.put(Description.TypeInt, d);
        d = new Description(Description.TypeTuple, 0, Description.dType, -1, "");
        td.put(Description.TypeTuple, d);

    }

    public void empty() {
        ta.clear();
        td.clear();
        te.clear();
        level = 0;
    }

    public void put(String ID, Description d) {
        Description aux = td.get(ID);
        if (aux != null) {
            if (aux.getNp() == level) {

                printError("ERROR: variable " + ID + " already declared in this level");
                System.exit(0);
            } else {
                int idxe = ta.get(level);
                idxe++;
                ta.set(level, idxe);
                te.put(idxe, aux);
            }
        }
        d.setNp(level);
        td.put(ID, d);
    }
    public void remove(String id){
        td.remove(id);
    }

    public void enterBlock() {
        level++;
        // if we have already been in this level we don't have to add, we have to set
        if (ta.size() <= level)
            ta.add(level, ta.get(level - 1));
        else
            ta.set(level, ta.get(level - 1));
    }

    // returns null if ID is not in the symbols table
    // return the description of the ID if exists
    public Description consult(String ID) {
        return td.get(ID);
    }

    public void addField(String idr, String idc, Description dcamp) {
        Description d = td.get(idr);
        if (d == null) {
            printError("Error: tuple " + idr + " not declared");
            return;

        }
        if (!d.getType().equals(Description.TypeTuple)) {
            printError("Error: " + idr + " is not a record");
            return;
        }
        int i = td.get(idr).getPointer();
        while (i != 0 && !te.get(i).getID().equals(idc)){
           i = te.get(i).getPointer();
        }
        if(i != 0){
            printError("Error: "+idc+" already exists in tuple");
            return;
        }
        int idxe = ta.get(level);
        idxe++;
        ta.set(level, idxe);
        dcamp.setNp(-1);
        dcamp.setID(idc);
        dcamp.setPointer(d.getPointer());
        te.put(idxe, dcamp);
        d.setPointer(idxe);
    }


    public Description checkField(String idr, String idc) {
        Description d = td.get(idr);
        if (d == null || !d.getType().equals(Description.TypeTuple)) {
            printError("Error: " + idr + " is not a record or not declared");
            return null;
        }
        int i = d.getPointer();
        while (i != 0 && !idc.equals(te.get(i).getID())) {
                i = te.get(i).getPointer();
        }
        if(i == 0){
            printError("Error: field does not exist in tuple");
            return null;
        }else
            return te.get(i);
    }
    public void exitBlock() throws FileNotFoundException {
        printVars(new PrintStream(new FileOutputStream(new File("Results/SymbolTable.txt"))));
        int idxi = ta.get(level);
        level--;
        int idxf = ta.get(level);
        while (idxi > idxf) {
            Description d = te.get(idxi);
            if (d.getNp() != -1) {
                td.put(d.getID(), d);
            }
            idxi--;
        }
        // we remove the variables that are above the current level
        iterateTd();

    }

    public void iterateTd() {
        Set<String> keys = td.keySet();
        keys.removeIf(key -> td.get(key).getNp() > level);
    }

    public void addParameter(String idpr, String idparam, Description d) {
        Description desc = td.get(idpr);
        // if it's null (idpr doesn't exist ) or if the td of d is not a function or a
        // procedure
        if (desc == null || (desc.gettd() != Description.dFunction && desc.gettd() != Description.dProcedure)) {
            printError("Error: " + idpr + " is not a subprogram or not declared");
            System.exit(0);
        }

        int idxe = desc.getPointer();
        int idxep = 0;
        while (idxe != 0 && !te.get(idxe).getID().equals(idparam)) {
            idxep = idxe;
            idxe = te.get(idxe).getPointer();
        }

        if (idxe != 0) {
            printError("ERROR: parameter " + idparam + " already declared in subprogram " + idpr);
            System.exit(0);
        }

        idxe = ta.get(level);
        idxe++;
        ta.set(level, idxe);
        d.setNp(-1);
        d.setID(idparam);
        d.setPointer(0);
        te.put(idxe, d);

        // if idxep is 0 it means that the parameter is the first one, the function or
        // procedure will point to the first parameter
        if (idxep == 0) {
            desc.setPointer(idxe);
        } else {
            // else the previous parameter will point to the new one
            te.get(idxep).setPointer(idxe);
        }
    }
    public int getLevel() {
        return level;
    }
    public String getType(String id){
        Description d = td.get(id);
        if(d == null){
            printError("ERROR: " + id + " not declared");
            System.exit(0);
        }
        return d.getType();
    }
    //return the size in bytes for a string type
    public static int getSizeType(String type){
        switch (type) {
            case Description.TypeBool:
                return 1;

            case Description.TypeInt:
                return 4;
            default:
                printError("ERROR: not int or bool in getSizeType");

        }
        return 0;
        }
        public int getFuncSize(String id){
            Description d = td.get(id);
        if(d==null || (d.gettd() != Description.dFunction && d.gettd() != Description.dProcedure)){
            printError("ERROR: get func size not getting function");
            return -1;
        }else{
            int idxe = d.getPointer();
            int n = 0;
            while (idxe != 0 ) {
                idxe = te.get(idxe).getPointer();
                n++;
            }
            return n;
        }
        }
        public Description getParam(String id,int i){
            Description d = td.get(id);
            if(d==null || (d.gettd() != Description.dFunction && d.gettd() != Description.dProcedure)){
                printError("ERROR: get func size not getting function");
                return null;
            }
            int idxe = d.getPointer();
            int n = 0;
            int pre = -1;
            while (idxe != 0 && n<i) {
                pre = idxe;
                idxe = te.get(idxe).getPointer();
                n++;
            }
            if(n<i){
                printError("ERROR: more parameters than declared");
                return null;
            }

            return te.get(pre);
        }

        public void setParams(String id){
            int n = getFuncSize(id);
            for (int i = 1; i <= n ; i++) {
                Description d = getParam(id,i);
                put(d.getID(),new Description(d.getID(),d.gettd(),d.getType(),d.getIndexVT()));

            }
        }
        //return true if the function exists, false if not
        public boolean existsFunction(String id){
            return td.get(id) != null;
        }

    public void printVars(PrintStream out) {
        if (!td.isEmpty()) {
            td.forEach((String k,Description d) -> {
                switch (d.gettd()) {

                    case Description.dType -> out.println("\tType "+k+",level: "+level);
                    case 1 -> out.println("\tVariable "+k+",type:"+d.getType()+",level: "+level);
                    case 2 -> out.println("\tConstant "+k+",type:"+d.getType()+",level: "+level);
                    case 3 -> printFunction("Function",out, d);
                    case 4 -> printFunction("Procedure",out, d);
                    case 5 -> out.println("\tArgument "+k+",type:"+d.getType()+",level: "+level);
                    default -> out.println("\t"+k+",type: NULL,level: "+level);
                }
            });
        }
    }
    private void printFunction(String td,PrintStream out,Description d){
        out.println("\t"+td+" "+d.getID()+",type: "+d.getType()+",level: "+level);
        int n = getFuncSize(d.getID());
        for (int i = 1; i <= n ; i++) {
            Description desc = getParam(d.getID(),i);
            out.println("\tArgument "+desc.getID()+",type: "+desc.getType()+",level: "+level);

        }
    }

    private static void printError(String m){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(errorPath,true))) {
            System.err.println(m);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}