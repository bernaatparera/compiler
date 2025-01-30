/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import sintactic.*;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.SymbolFactory;
import threeAddressCode.CodeGenerator;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;


// Author: Bernat Parera


public class Compiler {
    private static final String ErrorsPath = "Results/Errors.txt";
    private static final String LexicPath = "Results/LexicSymbols.txt";
    private static final String ThreeAddressCodePath = "Results/ThreeAddressCode.txt";
    private static final String ThreeAddressOptimizedPath = "Results/ThreeAddressOptimized.txt";
    private static final String ThreeAddressVarsPath = "Results/ThreeAddressVars.txt";
    private static final String assembly = "Results/assembly.x68";
    private static final String assemblyOpt = "Results/assemblyOptimized.x68";

    public static void main(String[] args) {
        JFileChooser file = new JFileChooser("./tests");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files (*.txt)", "txt");
        file.setFileFilter(filter);
        int res = file.showOpenDialog(null);
        Reader input;
        if(res == JFileChooser.APPROVE_OPTION){
            try {
                input = new FileReader(file.getSelectedFile());
                Lector scanner = new Lector(input);
                SymbolFactory sf = new ComplexSymbolFactory();
                Parser parser = new Parser(scanner, sf);
                parser.parse();
                if(parser.getFoundErrors() == 0){
                    scanner.printVars(new PrintStream(new FileOutputStream(new File(LexicPath))));
                    parser.print3AddressCode(new PrintStream(new FileOutputStream(new File(ThreeAddressCodePath))));
                    parser.print3AddressVars(new PrintStream(new FileOutputStream(new File(ThreeAddressVarsPath))));
                    parser.assemblyCode(new PrintStream(new FileOutputStream(new File(assembly))));
                    CodeGenerator c = parser.get3AC();
                    c.Optimization(new PrintStream(new FileOutputStream(new File(ThreeAddressOptimizedPath))));
                    parser.assemblyCode(new PrintStream(new FileOutputStream(new File(assemblyOpt))));
                }else{
                    parser.printErrors(new PrintStream(new FileOutputStream(new File(ErrorsPath))));
                }

            } catch (FileNotFoundException e) {
                System.err.println("File does not exist");
            } catch (IOException e) {
                System.err.println("Error processing file");
            } catch (Exception e) {
                System.err.println("Error: " + e);
                e.printStackTrace(System.err);
            }
        }
    }
    
}
