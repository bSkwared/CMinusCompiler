/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0
 * File: VarExpression.java
 * Created: March 2017
 *
 * Description: This file provides an implemented Expression class called
 * 				VarExpression to use with the CMinusParser
 */

package parser.productions.expression;

import parser.scanner.Token.*;

public class VarExpression extends Expression {
    
    
    public static final TokenType[] FIRST  = {};
    public static final TokenType[] FOLLOW = {};
    
    private String id;
    
    private boolean isArray;
    
    private Expression arrayIndex;
    
    public VarExpression(String i, Expression index) {
        id = i;
        arrayIndex = index;
        
        isArray = index != null;
    }
    
    public VarExpression(String id){
        this(id, null);
    }

	@Override
    public void print(String cur, String indent) {
        
    }

    public String getId() {
        return id;
    }
}
