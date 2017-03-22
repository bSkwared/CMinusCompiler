/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0
 * File: ReturnStatement.java
 * Created: March 2017
 *
 * Description: This file provides an implemented Statement class called
 * 				ReturnStatement to use with the CMinusParser
 */

package parser.productions.statement;

import parser.scanner.Token.*;

import parser.productions.expression.Expression;

public class ReturnStatement extends Statement {
    Expression returnExpression;
    
    private static final TokenType[] FIRST  = { TokenType.RETURN };
    private static final TokenType[] FOLLOW = { TokenType.VOID,
                                                  TokenType.INT,
                                                  TokenType.EOF,
                                                  TokenType.ELSE };
    
    public ReturnStatement(Expression rtrn) {
        returnExpression = rtrn;
    }
    
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
