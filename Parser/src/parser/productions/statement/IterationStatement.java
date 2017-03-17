package parser.productions.statement;

import parser.productions.expression.Expression;

/**
 *
 * @author Timothy Smith and Blake Lasky
 */
public class IterationStatement extends Statement {
    Expression condition;
    Statement result;
    
    private static final Token.TokenType[] FIRST  = { TokenType.WHILE };

    private static final Token.TokenType[] FOLLOW = { TokenType.VOID,
                                                      TokenType.INT,
                                                      TokenType.EOF,
                                                      TokenType.ELSE };
    
    public IterationStatement(Expression cond, Statement res) {
        condition = cond;
        result = res;
    }
    
    public static boolean inFirst(TokenType type) {
        return inSet(FIRST_SET, type);
    }

    public static boolean inFollow(TokenType type) {
        return inSet(FOLLOW_SET, type);
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
