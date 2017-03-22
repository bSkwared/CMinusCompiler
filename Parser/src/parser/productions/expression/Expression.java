/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0
 * File: Expression.java
 * Created: March 2017
 *
 * Description: This file provides an abstract Expression class for
 * 				use with the CMinusParser
 */

package parser.productions.expression;

public abstract class Expression {
    
    abstract public void print(String cur, String indent);
}
