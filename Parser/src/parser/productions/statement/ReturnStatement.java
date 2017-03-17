/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser.productions.statement;

import parser.productions.expression.Expression;

/**
 *
 * @author Timothy Smith and Blake Lasky
 */
public class ReturnStatement extends Statement {
    Expression returnExpression;
    
    public static final Token.TokenType[] FIRST  = {};
    public static final Token.TokenType[] FOLLOW = {};
    
    public ReturnStatement(Expression rtrn) {
        returnExpression = rtrn;
    }
    
    public static boolean inFirst(TokenType type) {
        return inSet(FIRST_SET, type);
    }

    public static boolean inFollow(TokenType type) {
        return inSet(FOLLOW_SET, type);
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
