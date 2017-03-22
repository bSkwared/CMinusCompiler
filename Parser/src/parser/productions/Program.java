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
    
    private static final TokenType[] FIRST  = { TokenType.INT, TokenType.VOID };
    private static final TokenType[] FOLLOW = { TokenType.EOF };
    
    private ArrayList<Declaration> decls;
    
    public Program(ArrayList<Declaration> declList) {
        decls = declList;
    }

    public void print(String cur, String indent) {
        for (Declaration d : decls) {
            d.print(cur + indent, indent);
            System.out.println();
        }
    }
}
