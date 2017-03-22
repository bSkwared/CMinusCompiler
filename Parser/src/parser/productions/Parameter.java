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
    
    public static boolean inFirst(TokenType type) {
        return inSet(FIRST, type);
    }

    public static boolean inFollow(TokenType type) {
        return inSet(FOLLOW, type);
    }

    public void print(String cur, String indent) {
        
    }
    
    private static boolean inSet(TokenType[] set, TokenType tok) {
        boolean result = false;

        for (TokenType t : set) {
            if (tok == t) {
                result = true;
                break;
            }
        }

        return result;
    }

}
