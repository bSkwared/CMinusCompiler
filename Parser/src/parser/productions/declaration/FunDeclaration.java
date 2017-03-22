/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0
 * File: FunDeclaration.java
 * Created: March 2017
 *
 * Description: This file provides an implemented Declaration class called
 * 				FunDeclaration to use with the CMinusParser
 */

package parser.productions.declaration;

import java.util.ArrayList;
import parser.productions.Parameter;
import parser.productions.statement.CompoundStatement;
import parser.scanner.Token;
import parser.scanner.Token.*;

public class FunDeclaration extends Declaration {
    
    
    private static final TokenType[] FIRST  = { TokenType.OPEN_PAREN };

    private static final TokenType[] FOLLOW = { TokenType.VOID,
                                                TokenType.INT,
                                                TokenType.EOF };
    
    
    private boolean returnsVoid;
    private String returnType;
    
    private boolean hasParameters;
    
    private String id;
    
    private ArrayList<Parameter> parameters;
    
    private CompoundStatement statement;
    
    
    public FunDeclaration(Token type, String i, ArrayList<Parameter> params,
                            CompoundStatement stmt) {
        
        TokenType returnTokenType = type.getType();
        
        if (returnTokenType == TokenType.VOID) {
            returnsVoid = true;
            returnType = "void";
            
        } else {
            // returnType == INT
            returnsVoid = false;
            returnType = "int";
        }
        
        id = i;
        
        if (params == null || params.isEmpty()) {
            parameters = null;
            hasParameters = false;
        } else {
            parameters = params;
            hasParameters = true;
        }
        
        statement = stmt;
    }
    
    public FunDeclaration(Token type, String i, CompoundStatement stmt) {
        this(type, i, null, stmt);
    }
    
    public static boolean inFirst(TokenType type) {
        return inSet(FIRST, type);
    }

    public static boolean inFollow(TokenType type) {
        return inSet(FOLLOW, type);
    }

    public void print(String cur, String indent) {
        
        String functionHeader = returnType + " " + id + "(";
        System.out.print(cur + functionHeader);
        
        if (hasParameters) {
            System.out.println();
            for (Parameter p : parameters) {
                p.print(cur + indent, indent);
            }
        
            System.out.println(cur + ")");
            
        } else {
            System.out.println("void)");
        }
        
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
