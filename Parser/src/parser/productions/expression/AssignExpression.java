/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0
 * File: AssignExpression.java
 * Created: March 2017
 *
 * Description: This file provides an implemented Expression class called
 * 				AssignExpression to use with the CMinusParser
 */

package parser.productions.expression;

import parser.scanner.Token.*;

public class AssignExpression extends Expression {
    
    
    private static final TokenType[] FIRST  = {};
    private static final TokenType[] FOLLOW = {};    
    
    private VarExpression variable;
    private Expression expr;
    
    public AssignExpression(VarExpression var, Expression ex) {
        variable = var;
        expr = ex;
    }
    
    public static boolean inFirst(TokenType type) {
        return inSet(FIRST, type);
    }

    public static boolean inFollow(TokenType type) {
        return inSet(FOLLOW, type);
    }

    @Override
    public void print(String cur, String indent) {
        System.out.print(variable.getId() + " = ");
        expr.print(cur+indent, indent);
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
