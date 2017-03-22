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

import parser.scanner.Token.*;

public abstract class Expression {
    
    public static final TokenType[] FIRST  = { TokenType.OPEN_PAREN, 
                                                 TokenType.ID, 
                                                 TokenType.NUM};

    public static final TokenType[] FOLLOW = { TokenType.CLOSE_PAREN,
                                                 TokenType.SEMICOLON,
                                                 TokenType.CLOSE_BRACKET,
                                                 TokenType.COMMA };
    abstract public void print(String cur, String indent);
}
