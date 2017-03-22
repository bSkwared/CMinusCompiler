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

public abstract class Statement {
    
    abstract public void print(String cur, String indent);
}
