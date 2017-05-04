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
import lowlevel.*;
import lowlevel.Operand.OperandType;
import lowlevel.Operation.OperationType;

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
	public int genCode(Function func) throws CodeGenerationException{
		
		HashMap<String, Integer> symTable = func.getTable();
		HashMap<String, Object> global = CMinusCompiler.globalHash;
		
		Integer regNum = symTable.get(this.id);
		// if it isn't in the local table check the gloabl table
		if(regNum == null){
			
			boolean exists = global.containsKey(this.id);
			// if it isn't the global table either, we have a problem
			if(exists){	
				// put global variable in a register				
				BasicBlock currBlock = func.getCurrBlock();
				
				regNum = func.getNewRegNum();
				
				Operation op = new Operation(OperationType.LOAD_I, currBlock);
		
				Operand destOper = new Operand(OperandType.REGISTER, regNum);
				Operand srcOper = new Operand(OperandType.STRING, this.id);

				op.setDestOperand(0, destOper);
				op.setSrcOperand(0, srcOper);

				currBlock.appendOper(op);
			} else{
				throw new CodeGenerationException("Variable " + this.id + " has not been declared.");
			}			
		}
		
		return regNum;
	}
}
