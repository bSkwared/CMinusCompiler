package parser.productions.declaration;

import parser.scanner.Token;
import parser.scanner.Token.*;


/**
 *
 * @author Timothy Smith and Blake Lasky
 */
public class FunDeclaration extends Declaration {
    
    
    private static final TokenType[] FIRST  = { TokenType.OPEN_PAREN };

    private static final TokenType[] FOLLOW = { TokenType.VOID,
                                                TokenType.INT,
                                                TokenType.EOF };
    
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
