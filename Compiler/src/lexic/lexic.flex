
import java.io.*;


import java.util.ArrayList;
import java.util.Iterator;
import sintactic.*;
import java_cup.runtime.*;
import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;
import java_cup.runtime.ComplexSymbolFactory.Location;

%%
%cup
%public
%class Lector

%line
%column

%eofval{
  return symbol(ParserSym.EOF);
%eofval}

%{
    private ArrayList<String> symbols = new ArrayList<String>();

    public void printVars(PrintStream out) {
        if (symbols.isEmpty()) {
            out.println("No symbols scanned");
            } else {
                String fmt = "Symbol -> %s";
                for(int i =0;i<symbols.size();i++){
                    out.println(String.format(fmt, symbols.get(i)));
                }
        }
    }

    private ComplexSymbol symbol(int type) {

        Location left = new Location(yyline+1, yycolumn+1);
        Location right = new Location(yyline+1, yycolumn+yytext().length()+1);

        return new ComplexSymbol(ParserSym.terminalNames[type], type, left, right);
    }

    private Symbol symbol(int type, Object value) {

        Location left = new Location(yyline+1, yycolumn+1);
        Location right = new Location(yyline+1, yycolumn+yytext().length()+1);

        return new ComplexSymbol(ParserSym.terminalNames[type], type, left, right, value);
    }
%}


digit = [0-9]
noZeroDigit = [1-9]
digits = 0 | ({noZeroDigit}{digit}*)
noDigit = 0{digit}+
letter = [A-Za-z]
space = [ ,\t,\r,\n]
id = {letter}({letter}|{digit}|_)*


//Reserved keywords
functionKey = function
printlnKey = println
returnKey = return
ifKey = if
elseKey = else
forKey = for
whileKey = while
constKey = const
mainKey = main
trueKey = TRUE
falseKey = FALSE
inputKey = input
printKey = print
dot =  \.


//Types
integerKey = int
booleanKey = bool
tupleKey = tuple


//Assignation
equalKey = \=

//Operations
sumOp = \+
minOp = \-
mulOp = \*
divOp = \/

//Relational
equalRel = \=\=
notEqualRel = \!\=
greaterRel = \>
lessRel = \<
greaterEqRel = \>\=
lessEqRel = \<\=

//Logic 
andLogic = AND
orLogic = OR
notLogic = NOT
xorLogic = XOR
nandLogic = NAND
norLogic = NOR
xnorLogic = XNOR

//End of line character and comma
eol = ;
comma = ,


//Blocks
openParenthesis = \(
closeParenthesis = \)
openBracket = \{
closeBracket = \}



//Comments
comment = \/\/[^\n]*
blockComment = \/ \* ([^\*]|\*[^\/])* \*\/


%%



//Reserved keywords

{functionKey}           {
                            symbols.add("KeyWord: function");
                            return symbol(ParserSym.function);

                        }
{printKey}              {
                            symbols.add("KeyWord: print");
                            return symbol(ParserSym.print);
                        }
{printlnKey}            {
                            symbols.add("KeyWord: println");
                            return symbol(ParserSym.println);
                        }
{returnKey}             {
                            symbols.add("KeyWord: return");
                            return symbol(ParserSym.returnt);
                        }
{ifKey}                 {
                            symbols.add("KeyWord: if");
                            return symbol(ParserSym.ift);
                        }
{elseKey}                 {
                            symbols.add("KeyWord: else");
                            return symbol(ParserSym.elset);
                        }

{forKey}                {
                            symbols.add("KeyWord: for");
                            return symbol(ParserSym.fort);
                            
                        }
{whileKey}              {
                            symbols.add("KeyWord: while");
                            return symbol(ParserSym.whilet);
                            
                        }
{constKey}              {
                            symbols.add("KeyWord: const");
                            return symbol(ParserSym.constt);
                            
                        }
{mainKey}              {
                            symbols.add("KeyWord: main");
                            return symbol(ParserSym.main);
                            
                        }
{trueKey}               {
                            symbols.add("KeyWord: TRUE");
                            return symbol(ParserSym.truet);
                            
                        }
{falseKey}              {
                            symbols.add("KeyWord: FALSE");
                            return symbol(ParserSym.falset);
                            
                        }

//Types
{integerKey}            {
                            symbols.add("KeyWord: int");
                            return symbol(ParserSym.integer);
                            
                        }
{booleanKey}            {
                            symbols.add("KeyWord: bool");
                            return symbol(ParserSym.bool);
                            
                        }
{tupleKey}              {
                            symbols.add("KeyWord: tuple");
                            return symbol(ParserSym.tuple);
                            
                        }
{inputKey}              {
                            symbols.add("KeyWord: input");
                            return symbol(ParserSym.input);

                        }

//Assignation

{equalKey}              {
                            symbols.add("KeyWord: =");
                            return symbol(ParserSym.equal);
                            
                        }

//Operations

{sumOp}                 {
                            symbols.add("KeyWord: +");
                            return symbol(ParserSym.sumop);
                            
                        }
{minOp}                 {
                            symbols.add("KeyWord: -");
                            return symbol(ParserSym.minop);
                            
                        }
{mulOp}                 {
                            symbols.add("KeyWord: *");
                            return symbol(ParserSym.mulop);
                            
                        }
{divOp}                 {
                            symbols.add("KeyWord: /");
                            return symbol(ParserSym.divop);
                            
                        }


//Relational

{equalRel}              {
                            symbols.add("KeyWord: ==");
                            return symbol(ParserSym.equalrel);
                            
                        }
{notEqualRel}           {
                            symbols.add("KeyWord: !=");
                            return symbol(ParserSym.notequalrel);
                            
                        }
{greaterRel}            {
                            symbols.add("KeyWord: >");
                            return symbol(ParserSym.greaterrel);
                            
                        }
{lessRel}               {
                            symbols.add("KeyWord: <");
                            return symbol(ParserSym.lessrel);
                            
                        }
{greaterEqRel}          {
                            symbols.add("KeyWord: >=");
                            return symbol(ParserSym.greatereqrel);
                            
                        }
{lessEqRel}             {
                            symbols.add("KeyWord: <=");
                            return symbol(ParserSym.lesseqrel);
                            
                        }

//Logic
{andLogic}              {
                            symbols.add("KeyWord: AND");
                            return symbol(ParserSym.and);
                            
                        }
{orLogic}               {
                            symbols.add("KeyWord: OR");
                            return symbol(ParserSym.or);
                        }
{notLogic}              {
                            symbols.add("KeyWord: NOT");
                            return symbol(ParserSym.not);
                            
                        }
{xorLogic}              {
                            symbols.add("KeyWord: XOR");
                            return symbol(ParserSym.xor);
                            
                        }
{nandLogic}             {
                            symbols.add("KeyWord: NAND");
                            return symbol(ParserSym.nand);
                            
                        }
{norLogic}              {
                            symbols.add("KeyWord: NOR");
                            return symbol(ParserSym.nor);
                            
                        }
{xnorLogic}             {
                            symbols.add("KeyWord: XNOR");
                            return symbol(ParserSym.xnor);
                            
                        }
{id}                    { symbols.add("Identifier: " +yytext());
                           return(symbol(ParserSym.id, this.yytext()));
                         }
{digits}                {
                            symbols.add("Number: "+yytext());
                            return symbol(ParserSym.number, Integer.parseInt(yytext()));
                        }


//End of line and comma

{eol}                   {
                            symbols.add("KeyWord: ;");
                            return symbol(ParserSym.semicolon);
                            
                        }
{comma}                 {
                            symbols.add("KeyWord: ,");
                            return symbol(ParserSym.comma);
                            
                        }


//Blocks

{openParenthesis}      {
                            symbols.add("KeyWord: (");
                            return symbol(ParserSym.openp);
                            
                        }
{closeParenthesis}     {
                            symbols.add("KeyWord: )");
                            return symbol(ParserSym.closep);
                            
                        }
{openBracket}           {
                            symbols.add("KeyWord: {");
                            return symbol(ParserSym.openb);
                            
                        }
{closeBracket}          {
                            symbols.add("KeyWord: }");
                            return symbol(ParserSym.closeb);
                            
                        }

//Comments

{comment}               {
                            /*Ignore*/
                            symbols.add("Comment");

                        }
{blockComment}          {
                            /*Ignore*/
                            symbols.add("blockComment");
                        }
{space}                 {
                        //empty
                        }


{noDigit}               {
                             return symbol(ParserSym.error);
                        }

{dot}                   {
                              symbols.add("KeyWord: .");
                             return symbol(ParserSym.dot);
                        }


[^]                     { 
                                        symbols.add("Unknown: ");
                                        return symbol(ParserSym.error);
                        }