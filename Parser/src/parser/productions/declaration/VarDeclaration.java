package parser.productions;

/**
 *
 * @author Timothy Smith and Blake Lasky
 */
public class VarDeclaration extends Declaration {
    
    
    public static final Token.TokenType[] FIRST  = { TokenType.INT };
    public static final Token.TokenType[] FOLLOW = { TokenType.OPEN_BRACE,
                                                     TokenType.IF,
                                                     TokenType.WHILE,
                                                     TokenType.RETURN,
                                                     TokenType.OPEN_PAREN,
                                                     TokenType.NUM,
                                                     TokenType.ID,
                                                     TokenType.VOID,
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
