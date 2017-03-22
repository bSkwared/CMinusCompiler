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

public abstract class Declaration {
    
    abstract public void print(String cur, String indent);
}
