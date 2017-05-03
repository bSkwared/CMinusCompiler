/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0 File: Parameter.java Created: March 2017
 *
 * Description: This file provides an a Parameter class to use with the
 * CMinusParser
 */
package parser.productions;

import java.util.HashMap;
import lowlevel.BasicBlock;
import lowlevel.Data;
import lowlevel.FuncParam;
import lowlevel.Function;
import lowlevel.Operand;
import lowlevel.Operation;

public class Parameter {

	private String id;
	private boolean isArray;

	public Parameter(String i, boolean isArr) {
		id = i;
		isArray = isArr;
	}
        
        public String getId() {
            return id;
        }

	public String print(String cur, String indent) {
		String str = "int " + id;

		if (isArray) {
			str += "[]";
		}
		
		return str;
	}
	
	public FuncParam genCode(Function func){
                HashMap<String, Integer> symTable = func.getTable();
                
                BasicBlock currBlock = func.getCurrBlock();
                int regNum = func.getNewRegNum();
                
		Operand destOper = new Operand(Operand.OperandType.REGISTER, regNum);
		Operand srcOper  = new Operand(Operand.OperandType.STRING, id);
		Operation op = new Operation(Operation.OperationType.ASSIGN, currBlock);
                
                op.setSrcOperand(0, srcOper);
                op.setDestOperand(0, destOper);
                
                currBlock.appendOper(op);
                
                symTable.put(id, regNum);
                
		return null;
	}	
}
