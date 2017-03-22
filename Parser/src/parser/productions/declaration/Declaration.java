/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0
 * File: VarDeclaration.java
 * Created: March 2017
 *
 * Description: This file provides an abstract Declaration class
 * 				to use with the CMinusParser
 */

package parser.productions.declaration;

import parser.scanner.Token.*;

public abstract class Declaration {
    
    private static final TokenType[] FIRST  = { TokenType.VOID, TokenType.INT };

    private static final TokenType[] FOLLOW = { TokenType.VOID,
                                                TokenType.INT,
                                                TokenType.EOF };

    public static boolean inFirst(TokenType type) {
        return inSet(FIRST, type);
    }

    public static boolean inFollow(TokenType type) {
        return inSet(FOLLOW, type);
    }
    
    abstract public void print(String cur, String indent);

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
