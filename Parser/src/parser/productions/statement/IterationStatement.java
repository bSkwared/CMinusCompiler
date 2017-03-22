/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0
 * File: IterationStatement.java
 * Created: March 2017
 *
 * Description: This file provides an implemented Statement class called
 * 				IterationStatement to use with the CMinusParser
 */

package parser.productions.statement;

import parser.scanner.Token.*;
import parser.productions.expression.Expression;

public class IterationStatement extends Statement {
    Expression condition;
    Statement result;
    
    private static final TokenType[] FIRST  = { TokenType.WHILE };

    private static final TokenType[] FOLLOW = { TokenType.VOID,
                                                  TokenType.INT,
                                                  TokenType.EOF,
                                                  TokenType.ELSE };
    
    public IterationStatement(Expression cond, Statement res) {
        condition = cond;
        result = res;
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
