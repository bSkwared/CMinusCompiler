/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0
 * File: CompoundStatement.java
 * Created: March 2017
 *
 * Description: This file provides an implemented Statement class called
 * 				CompoundStatement to use with the CMinusParser
 */

package parser.productions.statement;

import java.util.ArrayList;
import parser.productions.declaration.VarDeclaration;
import parser.scanner.Token.*;

public class CompoundStatement extends Statement {
    
    private ArrayList<VarDeclaration> varDecls;
    private ArrayList<Statement> statements;
    
    public CompoundStatement(ArrayList<VarDeclaration> decls, 
                                    ArrayList<Statement> states) {
        
        varDecls = decls;
        statements = states;
    }
    
    private static final TokenType[] FIRST  = { TokenType.OPEN_BRACE };
    private static final TokenType[] FOLLOW = { TokenType.VOID,
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
