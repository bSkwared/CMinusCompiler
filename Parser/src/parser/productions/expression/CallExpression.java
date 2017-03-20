package parser.productions.expression;

import java.util.ArrayList;
import parser.scanner.Token;
import parser.scanner.Token.*;

/**
 *
 * @author Timothy Smith and Blake Lasky
 */
public class CallExpression extends Expression {
    
    
    public static final Token.TokenType[] FIRST  = {};
    public static final Token.TokenType[] FOLLOW = {};
    
    private String id;
    private ArrayList<Expression> arguments;
    private boolean hasArguments;
    
    private CallExpression(String ID, ArrayList<Expression> args) {
        id = ID;
        
        if (args == null) {
            arguments = null;
            hasArguments = false;
            
        } else {
            arguments = args;
            hasArguments = true;
        }
        
    }
    
    private CallExpression(String ID) {
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
