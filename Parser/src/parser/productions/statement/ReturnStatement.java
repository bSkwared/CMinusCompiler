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
    public String print(String cur, String indent) {
        
		String str = "";
		
		str += cur + "return";
		        
        if (returnExpression != null) {
            str += "\n";
            str += returnExpression.print(cur + indent, indent);
            str += "\n" + cur;
        }
        
        str += ";\n\n";
		
		return str;
    }
}
