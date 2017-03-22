/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0
 * File: Parameter.java
 * Created: March 2017
 *
 * Description: This file provides an a Parameter class
 * 				to use with the CMinusParser
 */

package parser.productions;

import parser.scanner.Token.*;

public class Parameter {
    
    
    private static final TokenType[] FIRST  = { TokenType.INT, TokenType.VOID };
    private static final TokenType[] FOLLOW = { TokenType.CLOSE_PAREN };
    
    private String id;
    private boolean isArray;
    
    public Parameter(String i, boolean isArr) {
        id = i;
        isArray = isArr;
    }

    public void print(String cur, String indent) {
        
    }
}
