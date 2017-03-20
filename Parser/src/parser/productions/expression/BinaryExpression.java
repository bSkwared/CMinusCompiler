package parser.productions.expression;

import parser.scanner.Token;
import parser.scanner.Token.*;

/**
 *
 * @author Timothy Smith and Blake Lasky
 */
public class BinaryExpression extends Expression {
    
    
    public static final Token.TokenType[] FIRST  = {};
    public static final Token.TokenType[] FOLLOW = {};
    
    private Expression left;
    private TokenType  operator;
    private Expression right;
    
    public BinaryExpression(Expression lef, Token opToken, Expression righ) {
        left = lef;
        operator = opToken.getType();
        right = righ;
    }
    
    public static boolean inFirst(TokenType type) {
        return inSet(FIRST, type);
    }

    public static boolean inFollow(TokenType type) {
        return inSet(FOLLOW, type);
    }

    @Override
    public void print(String cur, String indent) {
        left.print(cur+indent, indent);
        
        String op = "";
        
        switch(operator) {
            case ADD:
                op = "+";
                break;
            case SUB:
                op = "-";
                break;
            case MULT:
                op = "*";
                break;
            case DIV:
                op = "/";
                break;
            case GT:
                op = ">";
                break;
            case GTE:
                op = ">=";
                break;
            case LT:
                op = "<";
                break;
            case LTE:
                op = "<=";
                break;
            case EQUAL:
                op = "==";
                break;
            case NOT_EQUAL:
                op = "!=";
                break;
        }
        
        System.out.print(" " + op + " ");
        
        right.print(cur + indent, indent);
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
