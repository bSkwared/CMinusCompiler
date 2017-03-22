/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0
 * File: NumExpression.java
 * Created: March 2017
 *
 * Description: This file provides an implemented Expression class called
 * 				NumExpression to use with the CMinusParser
 */

package parser.productions.expression;

import parser.scanner.Token.*;

public class NumExpression extends Expression {
    
    
    public static final TokenType[] FIRST  = {};
    public static final TokenType[] FOLLOW = {};
    
    public static boolean inFirst(TokenType type) {
        return inSet(FIRST, type);
    }

    public static boolean inFollow(TokenType type) {
        return inSet(FOLLOW, type);
    }

    @Override
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
