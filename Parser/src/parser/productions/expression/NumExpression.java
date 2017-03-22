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

import parser.scanner.Token.*;

public class NumExpression extends Expression {
    
    
    public static final TokenType[] FIRST  = {};
    public static final TokenType[] FOLLOW = {};
    
    private int value;
    
    public NumExpression(int number) {
        value = number;
    }
    
    @Override
    public void print(String cur, String indent) {
        
    }
}
