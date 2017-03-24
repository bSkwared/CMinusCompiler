/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0
 * File: CallExpression.java
 * Created: March 2017
 *
 * Description: This file provides an implemented Expression class called
 * 				CallExpression to use with the CMinusParser
 */

package parser.productions.expression;

import java.util.ArrayList;

public class CallExpression extends Expression {
    
    private String id;
    private ArrayList<Expression> arguments;
    private boolean hasArguments;
    
    public CallExpression(String ID, ArrayList<Expression> args) {
        id = ID;
        
        if (args == null || args.isEmpty()) {
            arguments = null;
            hasArguments = false;
            
        } else {
            arguments = args;
            hasArguments = true;
        }
        
    }
    
    public CallExpression(String ID) {
        this(ID, null);
    }

    @Override
    public String print(String cur, String indent) {
        String str = cur + id + "(";
        
        if (hasArguments) {
            str += "\n";
            for (Expression arg : arguments) {
                str += arg.print(cur + indent, indent);
                str += "\n";
            }
            str += cur;
        }
        
        str += ")\n";
		
		return str;
    }
}
