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
import lowlevel.CodeItem;
import parser.productions.declaration.*;

public class Program {
	
    private ArrayList<Declaration> decls;
    
    public Program(ArrayList<Declaration> declList) {
        decls = declList;
    }

    public CodeItem genCode() {
        return null;
    }
    
    public String print(String cur, String indent) {
        String str = "";
		
		for (Declaration d : decls) {
            str += d.print(cur + indent, indent);
            str += "\n";
        }
		
		return str;
    }
}
