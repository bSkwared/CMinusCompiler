/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0
 * File: Statement.java
 * Created: March 2017
 *
 * Description: This file provides an abstract Statement class for use
 * 				use with the CMinusParser
 */

package parser.productions.statement;

import lowlevel.Function;
import parser.CodeGenerationException;

public abstract class Statement {
    
    abstract public String print(String cur, String indent);
	
	abstract public void genCode(Function func) throws CodeGenerationException ;
}
