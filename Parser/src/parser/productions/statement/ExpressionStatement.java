/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0
 * File: ExpressionStatement.java
 * Created: March 2017
 *
 * Description: This file provides an implemented Statement class called
 * 				ExpressionStatement to use with the CMinusParser
 */

package parser.productions.statement;

import parser.productions.expression.Expression;
import parser.scanner.Token.*;

public class ExpressionStatement extends Statement {
    
    Expression expr;
    
    public ExpressionStatement(Expression ex) {
        expr = ex;
    }
    
    private static final TokenType[] FIRST  = { TokenType.OPEN_PAREN,
                                                 TokenType.NUM,
                                                 TokenType.ID /*** OR EPSILON ***/ };

    private static final TokenType[] FOLLOW = { TokenType.VOID,
                                                  TokenType.INT,
                                                  TokenType.EOF,
                                                  TokenType.ELSE };
    
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
