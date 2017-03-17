package parser.productions;

/**
 *
 * @author Timothy Smith and Blake Lasky
 */
public class Declaration {
    
    private static final TokenType[] FIRST_SET = {};
    private static final TokenType[] FOLLOW_SET = {};

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
