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

import parser.scanner.Token.*;

public class BinaryExpression extends Expression {
    
    private enum OpType {
        ADD, SUB, MULT, DIV, GT, GTE, LT, LTE, EQUAL, NOT_EQUAL
    }
    
    private Expression left;
    private OpType  operator;
    private Expression right;
    
    public BinaryExpression(Expression lef, TokenType op, Expression righ){
        left = lef;
        
        switch(op) {
            case ADD:
                operator = OpType.ADD;
                break;
            case SUB:
                operator = OpType.SUB;
                break;
            case MULT:
                operator = OpType.MULT;
                break;
            case DIV:
                operator = OpType.DIV;
                break;
            case GT:
                operator = OpType.GT;
                break;
            case GTE:
                operator = OpType.GTE;
                break;
            case LT:
                operator = OpType.LT;
                break;
            case LTE:
                operator = OpType.LTE;
                break;
            case EQUAL:
                operator = OpType.EQUAL;
                break;
            case NOT_EQUAL:
                operator = OpType.ADD;
                break;
        }
        right = righ;
    }
	
    @Override
    public String print(String cur, String indent) {
        String str = "";
		
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
        
        str += cur + op + "\n";
        
        str += left.print(cur +"|"+ indent, indent);
        str += "\n";
        str += right.print(cur +"|"+ indent, indent);
		
		return str;
    }
}
