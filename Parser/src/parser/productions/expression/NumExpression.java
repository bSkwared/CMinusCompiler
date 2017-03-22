/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0
 * File: NumExpression.java
 * Created: March 2017
 *
 * Description: This file provides an implemented Expression class called
 * 				NumExpression to use with the CMinusParser
 */

package parser.productions.expression;

public class NumExpression extends Expression {
	
    private int value;
    
    public NumExpression(int number) {
        value = number;
    }
    
    @Override
    public void print(String cur, String indent) {
        System.out.print(value);
    }
}
