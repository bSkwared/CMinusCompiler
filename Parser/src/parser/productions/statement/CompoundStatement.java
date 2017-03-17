package parser.productions.statement;

/**
 *
 * @author Timothy Smith and Blake Lasky
 */
public class CompoundStatement extends Statement {
    
    
    private static final Token.TokenType[] FIRST  = { TokenType.OPEN_BRACE };
    private static final Token.TokenType[] FOLLOW = {};
    
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
