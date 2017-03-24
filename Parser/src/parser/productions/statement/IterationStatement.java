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
    public String print(String cur, String indent) {
        String str = "";
		str += cur + "while (\n";
        
        if (condition != null) {
            str += condition.print(cur + indent, indent);
        }
        str += "\n";
        str += cur + ")\n";

        str += result.print(cur + indent, indent);
		
		return str;
    }
}
