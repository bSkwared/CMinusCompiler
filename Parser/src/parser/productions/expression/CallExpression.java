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
    public void print(String cur, String indent) {
        System.out.print(cur + id + "(");
        
        if (hasArguments) {
            System.out.println("");
            boolean firstArg = true;
            for (Expression arg : arguments) {
                /*if (firstArg) {
                    firstArg = false;
                } else {
                    System.out.print(", ");
                }*/

                arg.print(cur + indent, indent);
            }
            System.out.print(cur);
        }
        
        System.out.println(")");
    }
}
