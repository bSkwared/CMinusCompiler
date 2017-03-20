package parser.productions.expression;

import parser.scanner.Token;
import parser.scanner.Token.*;

/**
 *
 * @author Timothy Smith and Blake Lasky
 */
public class AssignExpression extends Expression {
    
    
    private static final Token.TokenType[] FIRST  = {};
    private static final Token.TokenType[] FOLLOW = {};
    
    
    private String id;
    private Expression expr;
    
    public AssignExpression(String ID, Expression ex) {
        id = ID;
        expr = ex;
    }
    
    public static boolean inFirst(TokenType type) {
        return inSet(FIRST, type);
    }

    public static boolean inFollow(TokenType type) {
        return inSet(FOLLOW, type);
    }

    @Override
    public void print(String cur, String indent) {
        System.out.print(id + " = ");
        expr.print(cur+indent, indent);
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
