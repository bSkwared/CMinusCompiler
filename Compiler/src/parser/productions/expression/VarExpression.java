/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0 File: VarExpression.java Created: March 2017
 *
 * Description: This file provides an implemented Expression class called
 * VarExpression to use with the CMinusParser
 */
package parser.productions.expression;

import compiler.CMinusCompiler;
import java.util.HashMap;
import lowlevel.Function;
import parser.CodeGenerationException;

public class VarExpression extends Expression {

    private String id;

    private boolean isArray;

    private Expression arrayIndex;

    public VarExpression(String i, Expression index) {
        id = i;
        arrayIndex = index;

        isArray = index != null;
    }

    public VarExpression(String id) {
        this(id, null);
    }

    @Override
    public String print(String cur, String indent) {
        String str = cur + id;
        if (isArray) {
            str += "[\n";
            str += arrayIndex.print(cur + indent, indent);
            str += cur + "]\n";
        }
		
		return str;
    }

    public String getId() {
        return id;
    }	
	
	@Override
	// TODO: Check Timothy's Code
	// this function just returns the reigster number
	public int genCode(Function func) throws CodeGenerationException{
		
		HashMap<String, Integer> symTable = func.getTable();
		
		Integer regNum = symTable.get(this.id);
		// if it isn't in the local table check the gloabl table
		if(regNum == null){
			
			regNum = CMinusCompiler.globalHash.get(this.id);
			// if it isn't the global table either, we have a problem
			if(regNum == null){
				
				throw new CodeGenerationException("Variable " + this.id + " has not been declared.");				
			}			
		}
		
		return regNum;
	}
}
