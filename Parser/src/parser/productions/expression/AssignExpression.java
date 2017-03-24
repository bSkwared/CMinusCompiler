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

public class AssignExpression extends Expression {
        
    private VarExpression variable;
    private Expression expr;
    
    public AssignExpression(VarExpression var, Expression ex) {
        variable = var;
        expr = ex;
    }
	
    @Override
    public String print(String cur, String indent) {
        String str = "";
		str += cur + '=' + "\n";
        str += cur + indent + variable.getId() + "\n";
        str += expr.print(cur + indent, indent);
		
		return str;
    }
}
