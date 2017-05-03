 /**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0
 * File: VarDeclaration.java
 * Created: March 2017
 *
 * Description: This file provides an implemented Declaration class called
 * 				VarDeclaration to use with the CMinusParser
 */

package parser.productions.declaration;

import compiler.CMinusCompiler;
import lowlevel.*;

public class VarDeclaration extends Declaration {
    
    private boolean isArray;
    private int arraySize;
    
    
    public VarDeclaration (String ID) {
        id = ID;
        isArray = false;
    }
    
    public VarDeclaration(String ID, int arrSize) {
        this(ID);
        arraySize = arrSize;
        isArray = true;
    }
    
	@Override
    public String print(String cur, String indent) {
        String out = "int " + id;
        
        if (isArray) {
            out += "[" + arraySize + "]"; 
        }
        
        out += ";";
        
        String str = cur + out + "\n";
		return str;
    }

    public CodeItem genCode() {
        Data globalDecl = new Data(Data.TYPE_INT, id);
		            
		// add to symbol table if it is a global variable
		CMinusCompiler.globalHash.put(id, id);
		
        return globalDecl;
    }
}
