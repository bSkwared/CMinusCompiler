package parser.productions.statement;

import parser.scanner.Token;
import parser.scanner.Token.*;

import parser.productions.expression.Expression;

/**
 *
 * @author Timothy Smith and Blake Lasky
 */
public class ReturnStatement extends Statement {
    Expression returnExpression;
    
    private static final Token.TokenType[] FIRST  = { TokenType.RETURN };
    private static final Token.TokenType[] FOLLOW = { TokenType.VOID,
                                                      TokenType.INT,
                                                      TokenType.EOF,
                                                      TokenType.ELSE };
    
    public ReturnStatement(Expression rtrn) {
        returnExpression = rtrn;
    }
    
    public static boolean inFirst(TokenType type) {
        return inSet(FIRST, type);
    }

    public static boolean inFollow(TokenType type) {
        return inSet(FOLLOW, type);
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
