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

import parser.productions.expression.Expression;

public class ReturnStatement extends Statement {
    Expression returnExpression;
    
    public ReturnStatement(Expression rtrn) {
        returnExpression = rtrn;
    }

    @Override
    public void print(String cur, String indent) {
        System.out.print(cur + "return");
        
        if (returnExpression != null) {
            System.out.println("");
            returnExpression.print(cur + indent, indent);
            System.out.print("\n" + cur);
        }
        
        System.out.println(";\n");
    }
}
