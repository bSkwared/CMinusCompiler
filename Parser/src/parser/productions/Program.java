/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0
 * File: Program.java
 * Created: March 2017
 *
 * Description: This file provides a Program class derivation
 * 				to use with the CMinusParser
 */

package parser.productions;

import java.util.ArrayList;
import parser.productions.declaration.*;
import parser.scanner.Token.*;

public class Program {
    private ArrayList<Declaration> decls;
    
    private static final TokenType[] FIRST  = { TokenType.INT, TokenType.VOID };
    private static final TokenType[] FOLLOW = { TokenType.EOF };
    
    public Program() {
        decls = new ArrayList<>();
    }
    
    public void addDeclaration(Declaration decl) {
        decls.add(decl);
    }
    
    public void print(String cur, String indent) {
        for (Declaration d : decls) {
            d.print(cur + indent, indent);
            System.out.println();
        }
    }
    
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
