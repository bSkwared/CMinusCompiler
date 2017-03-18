package parser.productions.statement;

import java.util.ArrayList;
import parser.productions.declaration.VarDeclaration;
import parser.scanner.Token;
import parser.scanner.Token.*;

/**
 *
 * @author Timothy Smith and Blake Lasky
 */
public class CompoundStatement extends Statement {
    
    private ArrayList<VarDeclaration> varDecls;
    private ArrayList<Statement> statements;
    
    public CompoundStatement(ArrayList<VarDeclaration> decls, 
                                    ArrayList<Statement> states) {
        
        varDecls = decls;
        statements = states;
    }
    
    private static final Token.TokenType[] FIRST  = { TokenType.OPEN_BRACE };
    private static final Token.TokenType[] FOLLOW = { TokenType.VOID,
                                                      TokenType.INT,
                                                      TokenType.EOF,
                                                      TokenType.ELSE };
    
    public static boolean inFirst(TokenType type) {
        return inSet(FIRST, type);
    }

    public static boolean inFollow(TokenType type) {
        return inSet(FOLLOW, type);
    }

    @Override
    public void print(String cur, String indent) {
        
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
