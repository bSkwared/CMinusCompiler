package parser.productions;

import java.util.ArrayList;
import parser.scanner.*;
import parser.scanner.Token.*;

/**
 *
 * @author Timothy Smith and Blake Lasky
 */
public class Program {
    private ArrayList<Declaration> decls;
    
    private static final TokenType[] FIRST  = { TokenType.INT, TokenType.VOID };
    private static final TokenType[] FIRSTprivate static final TokenType[] FOLLOW = {};
    
    public Program() {
        decls = new ArrayList<>();
    }
    
    public void addDeclaration(Declaration decl) {
        decls.add(decl);
    }
    
    
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
