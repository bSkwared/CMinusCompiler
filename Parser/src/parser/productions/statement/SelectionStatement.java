/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0
 * File: SelectionStatement.java
 * Created: March 2017
 *
 * Description: This file provides an implemented Statement class called
 * 				SelectionStatement to use with the CMinusParser
 */

package parser.productions.statement;

import parser.productions.expression.Expression;
import parser.scanner.Token.*;

public class SelectionStatement extends Statement {
    
    private Expression condition;
    private Statement thenStatement;
    private Statement elseStatement;
    
    
    public SelectionStatement(Expression cond, Statement then, Statement els) {
        condition = cond;
        thenStatement = then;
        elseStatement = els;
    }
    
    private static final TokenType[] FIRST  = { TokenType.IF };

    private static final TokenType[] FOLLOW = { TokenType.VOID,
                                                  TokenType.INT,
                                                  TokenType.EOF,
                                                  TokenType.ELSE };

	@Override
    public void print(String cur, String indent) {
        
    }
}
