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
    
    public static final TokenType[] firstSet  = {};
    public static final TokenType[] followSet = {};
    
    public Program() {
        decls = new ArrayList<>();
    }
    
    public void addDeclaration(Declaration decl) {
        decls.add(decl);
    }
    
    
}
