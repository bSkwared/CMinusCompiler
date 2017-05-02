/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0 File: SelectionStatement.java Created: March 2017
 *
 * Description: This file provides an implemented Statement class called
 * SelectionStatement to use with the CMinusParser
 */
package parser.productions.statement;

import lowlevel.CodeItem;
import lowlevel.Function;
import parser.productions.expression.Expression;

public class SelectionStatement extends Statement {

    private Expression condition;
    private Statement thenStatement;
    private Statement elseStatement;

    public SelectionStatement(Expression cond, Statement then, Statement els) {
        condition = cond;
        thenStatement = then;
        elseStatement = els;
    }

    @Override
    public String print(String cur, String indent) {
        String str = "";
		
		str += cur + "if (";

        if (condition != null) {
            str += "\n";
            str += condition.print(cur + indent, indent);
            str += "\n" + cur;
        }

        str += ")\n";

        if (thenStatement != null) {
            str += thenStatement.print(cur + indent, indent);
        }

        if (elseStatement != null) {
            str += cur + "else\n";
            str += elseStatement.print(cur + indent, indent);
        }
		
		return str;
    }
	
		
	public void genCode(Function func){
		
	}
}
