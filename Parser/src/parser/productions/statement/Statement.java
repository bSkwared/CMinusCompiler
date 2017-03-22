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

import parser.scanner.Token.*;

public abstract class Statement {
    
    
    private static final TokenType[] FIRST  = { TokenType.OPEN_BRACE,
                                                  TokenType.IF,
                                                  TokenType.WHILE,
                                                  TokenType.RETURN,
                                                  TokenType.OPEN_PAREN,
                                                  TokenType.NUM,
                                                  TokenType.ID /*** OR EPSILON***/};
    
    private static final TokenType[] FOLLOW = { TokenType.VOID,
                                                  TokenType.INT,
                                                  TokenType.EOF,
                                                  TokenType.ELSE };

    
    abstract public void print(String cur, String indent);
}
