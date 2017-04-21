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

public class ExpressionStatement extends Statement {
    
    Expression expr;
    
    public ExpressionStatement(Expression ex) {
        expr = ex;
    }

	@Override
    public String print(String cur, String indent) {
        String str = "";
        if (expr != null) {
            str += expr.print(cur + indent, indent);
        }
        str += '\n' + cur + ";\n\n";
		
		return str;
    }
}
