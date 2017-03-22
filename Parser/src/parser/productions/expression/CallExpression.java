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
import parser.scanner.Token.*;

public class CallExpression extends Expression {
    
    
    public static final TokenType[] FIRST  = {};
    public static final TokenType[] FOLLOW = {};
    
    private String id;
    private ArrayList<Expression> arguments;
    private boolean hasArguments;
    
    public CallExpression(String ID, ArrayList<Expression> args) {
        id = ID;
        
        if (args == null) {
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
    
    public static boolean inFirst(TokenType type) {
        return inSet(FIRST, type);
    }

    public static boolean inFollow(TokenType type) {
        return inSet(FOLLOW, type);
    }

    @Override
    public void print(String cur, String indent) {
        System.out.print(cur + id + "(");
        
        boolean firstArg = true;
        for (Expression arg : arguments) {
            if (firstArg) {
                firstArg = false;
            } else {
                System.out.print(", ");
            }
            
            arg.print(cur + indent, indent);
        }
        
        System.out.println(");");
    }

    private static boolean inSet(TokenType[] set, TokenType tok) {
        boolean result = false;

        for (TokenType t : set) {
            if (tok == t) {
                result = true;
                break;
            }
        }

        return result;
    }

}
