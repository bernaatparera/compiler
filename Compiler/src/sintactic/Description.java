package sintactic;

// Author: Bernat Parera

public class Description {

    // this values are used for td
    public static final int dType = 0;
    public static final int dVariable = 1;
    public static final int dConstant = 2;
    public static final int dFunction = 3;
    public static final int dProcedure = 4;
    public static final int dArg = 5;

    public static final String TypeInt = "int";
    public static final String TypeBool = "bool";
    public static final String TypeTuple = "tuple";



    private String ID;
    // contains the level at which the variable was declared
    private int np;
    // pointer which points has 2 porpuse:
    // 1. in td: points to the first parameter (functions and tuples)
    // 2. in te: pints to the next parameter (functions and tuples)
    private int pointer;

    private int td;
    // it my has a value (argument of tuple and function, constant or type)
    private int value;

    /*
     * type = 0: int
     * type = 1: bool
     * type = 2: tuple
     */
    private String type;

    private int min, max;
    //represents the index of the TV of the id or description
    private int indexVT;
    private String tupleRef;

    // all
    public Description(String ID, int np, int pointer, int DescriptionType, int value, String type) {
        this.ID = ID;
        this.np = np;
        this.pointer = pointer;
        this.td = DescriptionType;
        this.value = value;
        this.type = type;
    }

    // no pointer
    public Description(String ID, int np, int DescriptionType, int value, String type) {
        this.ID = ID;
        this.np = np;
        this.td = DescriptionType;
        this.value = value;
        this.type = type;
    }

    // type inicialitation
    public Description(String ID, int np, int DescriptionType, int value, String type, int min, int max) {
        this.ID = ID;
        this.np = np;
        this.pointer = -1;
        this.td = DescriptionType;
        this.value = value;
        this.type = type;
        this.min = min;
        this.max = max;
    }

    public Description(String ID, int DescriptionType, String type) {
        this.ID = ID;
        this.td = DescriptionType;
        this.type = type;
    }
    public Description(String ID, int DescriptionType, String type,int indexVT) {
        this.ID = ID;
        this.td = DescriptionType;
        this.type = type;
        this.indexVT = indexVT;
    }

    public Description(String ID, int DescriptionType) {
        this.ID = ID;
        this.td = DescriptionType;
    }

    // we only want to put the td (functions and procedure declaration)
    public Description(int DescriptionType) {
        this.td = DescriptionType;
    }
    public Description(String ID, int DescriptionType, String type,String tuple){
        this.ID = ID;
        this.td = DescriptionType;
        this.type = type;
        this.tupleRef = tuple;
    }
    public Description(String ID, int DescriptionType, String type,String tuple,int indexVT){
        this.ID = ID;
        this.td = DescriptionType;
        this.type = type;
        this.tupleRef = tuple;
        this.indexVT = indexVT;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getNp() {
        return np;
    }

    public void setNp(int np) {
        this.np = np;
    }

    public int getPointer() {
        return pointer;
    }

    public void setPointer(int pointer) {
        this.pointer = pointer;
    }

    public int gettd() {
        return td;
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getIndexVT() {
        return indexVT;
    }

    public void setIndexVT(int indexVT) {
        this.indexVT = indexVT;
    }
    public String getTupleRef(){
        return tupleRef;
    }
}