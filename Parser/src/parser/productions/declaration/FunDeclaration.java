package parser.productions.declaration;

import java.util.ArrayList;
import parser.productions.Parameter;
import parser.productions.statement.CompoundStatement;
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
    
    
    private Token returnType;
    private Token id;
    
    private ArrayList<Parameter> parameters;
    
    private CompoundStatement statement;
    
    
    public FunDeclaration(Token type, Token i, ArrayList<Parameter> params,
                            CompoundStatement stmt) {
        
        returnType = type;
        id = i;
        parameters = params;
        statement = stmt;
    }
    
    
    public static boolean inFirst(TokenType type) {
        return inSet(FIRST, type);
    }

    public static boolean inFollow(TokenType type) {
        return inSet(FOLLOW, type);
    }

    public void print(String cur, String indent) {
        System.out.println(cur + returnType.toString());
        System.out.println(cur + id.toString());
        System.out.println(cur + TokenType.OPEN_PAREN.toString());
        
        for (Parameter p : parameters) {
            p.print(cur + indent, indent);
        }
        
        System.out.println(cur + TokenType.CLOSE_PAREN.toString());
        
        statement.print(cur + indent, indent);
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
