package parser.productions.expression;

import parser.scanner.Token;

/**
 *
 * @author Timothy Smith and Blake Lasky
 */
public class Expression {
    
    public static final Token.TokenType[] FIRST  = { TokenType.OPEN_PARAM, 
                                                     TokenType.ID, 
                                                     TokenType.NUM};

    public static final Token.TokenType[] FOLLOW = { TokenType.CLOSE_PAREN,
                                                     TokenType.SEMICOLON,
                                                     TokenType.CLOSE_BRACKET,
                                                     TokenType.COMMA };
    
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
