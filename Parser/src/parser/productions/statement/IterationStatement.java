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

import parser.productions.expression.Expression;

public class IterationStatement extends Statement {
    Expression condition;
    Statement result;
    
    public IterationStatement(Expression cond, Statement res) {
        condition = cond;
        result = res;
    }

    @Override
    public void print(String cur, String indent) {
        System.out.println(cur + "while (");
        
        if (condition != null) {
            condition.print(cur + indent, indent);
        }
        System.out.println("");
        System.out.println(cur + ")");

        result.print(cur + indent, indent);
    }
}
