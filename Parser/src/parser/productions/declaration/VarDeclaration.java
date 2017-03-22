/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0
 * File: VarDeclaration.java
 * Created: March 2017
 *
 * Description: This file provides an implemented Declaration class called
 * 				VarDeclaration to use with the CMinusParser
 */

package parser.productions.declaration;

import parser.scanner.Token.*;

public class VarDeclaration extends Declaration {
    
    
    public static final TokenType[] FIRST  = { TokenType.INT };
    public static final TokenType[] FOLLOW = { TokenType.OPEN_BRACE,
                                               TokenType.IF,
                                               TokenType.WHILE,
                                               TokenType.RETURN,
                                               TokenType.OPEN_PAREN,
                                               TokenType.NUM,
                                               TokenType.ID,
                                               TokenType.VOID,
                                               TokenType.INT,
                                               TokenType.EOF,
                                               TokenType.ELSE };
    
    private String id;
    
    private boolean isArray;
    private int arraySize;
    
    
    public VarDeclaration (String ID) {
        id = ID;
        isArray = false;
    }
    
    public VarDeclaration(String ID, int arrSize) {
        this(ID);
        arraySize = arrSize;
        isArray = true;
    }
    
    public void print(String cur, String indent) {
        String out = "int " + id;
        
        if (isArray) {
            out += " [" + arraySize + "]"; 
        }
        
        out += ";";
        
        System.out.println(cur + out);
    }
}
