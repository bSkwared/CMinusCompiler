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

import compiler.CMinusCompiler;
import java.util.ArrayList;
import lowlevel.CodeGenerationException;
import lowlevel.CodeItem;
import parser.productions.declaration.*;

public class Program {
	
    private ArrayList<Declaration> decls;
    
    public Program(ArrayList<Declaration> declList) {
        decls = declList;
    }

    public CodeItem genCode() throws CodeGenerationException{
        
        CodeItem firstItem = null;
        CodeItem lastItem = null;
        
        for (Declaration decl : decls) {
            
            CodeItem nextItem = decl.genCode();

			
            if (firstItem == null) {
                firstItem = nextItem;
            } else {
                lastItem.setNextItem(nextItem);
            }
            
            
            // We should do this so we can just grab the last block instead of
            // walking through the list. However, lastUnconnectedBlock is not
            // publically accesssibble.
            /*if (nextItem instanceof Function) {
                Function nextFunc = (Function) nextItem;
                lastItem = nextFunc.getLast
                
            } else {
                // instance of Data
            }*/
            
            lastItem = getLastItem(nextItem);
        }
        
        return firstItem;
    }
    
    public String print(String cur, String indent) {
        String str = "";
		
		for (Declaration d : decls) {
            str += d.print(cur + indent, indent);
            str += "\n";
        }
		
		return str;
    }
    
    private CodeItem getLastItem(CodeItem head) {
        CodeItem retItem = head;
        
        CodeItem walker = head.getNextItem();
        
        // walk list
        while (walker != null) {
            retItem = walker;
            walker = walker.getNextItem();
        }
        
        return retItem;
    }
}
