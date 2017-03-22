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

public class VarExpression extends Expression {
	
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
        System.out.print(id);
		if(isArray){
			System.out.print("[");
			arrayIndex.print(cur, indent);
			System.out.print("]");
		}
    }

    public String getId() {
        return id;
    }
}
