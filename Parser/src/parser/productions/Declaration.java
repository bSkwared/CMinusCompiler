package parser.productions;

/**
 *
 * @author Timothy Smith and Blake Lasky
 */
public class Declaration {
    
    private static final TokenType[] FIRST  = { TokenType.VOID, TokenType.INT };

    private static final TokenType[] FOLLOW = {};

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
