package parser.productions;

/**
 *
 * @author Timothy Smith and Blake Lasky
 */
public class Parameter {
    
    
    private static final TokenType[] FIRST  = { TokenType.INT, TokenType.VOID };
    private static final TokenType[] FOLLOW = { TokenType.CLOSE_PAREN };
    
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
