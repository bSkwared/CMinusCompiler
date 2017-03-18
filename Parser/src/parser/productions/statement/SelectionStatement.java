package parser.productions.statement;
import parser.productions.expression.Expression;
import parser.scanner.Token;
import parser.scanner.Token.*;

/**
 *
 * @author Timothy Smith and Blake Lasky
 */
public class SelectionStatement extends Statement {
    
    private Expression condition;
    private Statement thenStatement;
    private Statement elseStatement;
    
    
    public SelectionStatement(Expression cond, Statement then, Statement els) {
        condition = cond;
        thenStatement = then;
        elseStatement = els;
    }
    
    private static final Token.TokenType[] FIRST  = { TokenType.IF };

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
