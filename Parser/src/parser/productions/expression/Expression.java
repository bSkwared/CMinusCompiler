package parser.productions.expression;

import parser.scanner.Token;

/**
 *
 * @author Timothy Smith and Blake Lasky
 */
public class Expression {
    
    public static final Token.TokenType[] FIRST  = {};
    public static final Token.TokenType[] FOLLOW = {};
    
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
