/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0
 * File: VarDeclaration.java
 * Created: March 2017
 *
 * Description: This file provides an abstract Declaration class
 * 				to use with the CMinusParser
 */

package parser.productions.declaration;

import lowlevel.*;

public abstract class Declaration {

    protected String id;
    
    abstract public String print(String cur, String indent);
    
    abstract public CodeItem genCode() throws CodeGenerationException;
	
	public String getId(){
		return id;
	}
}
