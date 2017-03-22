/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0
 * File: BinaryExpression.java
 * Created: March 2017
 *
 * Description: This file provides an implemented Expression class called
 * 				BinaryExpression to use with the CMinusParser
 */

package parser.productions.expression;

import parser.scanner.Token;
import parser.scanner.Token.*;

public class BinaryExpression extends Expression {
    
    
    public static final TokenType[] FIRST  = {};
    public static final TokenType[] FOLLOW = {};
    
    private Expression left;
    private TokenType  operator;
    private Expression right;
    
    public BinaryExpression(Expression lef, Token opToken, Expression righ) {
        left = lef;
        operator = opToken.getType();
        right = righ;
    }
	
    @Override
    public void print(String cur, String indent) {
        left.print(cur+indent, indent);
        
        String op = "";
        
        switch(operator) {
            case ADD:
                op = "+";
                break;
            case SUB:
                op = "-";
                break;
            case MULT:
                op = "*";
                break;
            case DIV:
                op = "/";
                break;
            case GT:
                op = ">";
                break;
            case GTE:
                op = ">=";
                break;
            case LT:
                op = "<";
                break;
            case LTE:
                op = "<=";
                break;
            case EQUAL:
                op = "==";
                break;
            case NOT_EQUAL:
                op = "!=";
                break;
        }
        
        System.out.print(" " + op + " ");
        
        right.print(cur + indent, indent);
    }
}
