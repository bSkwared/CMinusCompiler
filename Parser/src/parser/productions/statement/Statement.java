package parser.productions.statement;

/**
 *
 * @author Timothy Smith and Blake Lasky
 */
public class Statement {
    
    
    private static final Token.TokenType[] FIRST  = { TokenType.OPEN_BRACE.
                                                      TokenType.IF,
                                                      TokenType.WHILE,
                                                      TokenType.RETURN,
                                                      TokenType.OPEN_PAREN,
                                                      TokenType.NUM,
                                                      TokenType.ID /*** OR EPSILON***/};
    
    private static final Token.TokenType[] FOLLOW = { TokenType.VOID,
                                                      TokenType.INT,
                                                      TokenType.EOF,
                                                      TokenType.ELSE };

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
