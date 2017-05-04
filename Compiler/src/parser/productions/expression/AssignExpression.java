/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0 File: AssignExpression.java Created: March 2017
 *
 * Description: This file provides an implemented Expression class called
 * AssignExpression to use with the CMinusParser
 */
package parser.productions.expression;

import compiler.CMinusCompiler;
import java.util.HashMap;
import lowlevel.*;
import lowlevel.Operand.OperandType;
import lowlevel.Operation.OperationType;

public class AssignExpression extends Expression {

	private VarExpression variable;
	private Expression expr;

	public AssignExpression(VarExpression var, Expression ex) {
		variable = var;
		expr = ex;
	}

	@Override
	public String print(String cur, String indent) {
		String str = "";
		str += cur + '=' + "\n";
		str += cur + indent + variable.getId() + "\n";
		str += expr.print(cur + indent, indent);

		return str;
	}

	@Override
	public int genCode(Function func) throws CodeGenerationException {

		// get current block
		BasicBlock currBlock = func.getCurrBlock();
		
		HashMap<String, Integer> symTable = func.getTable();
		HashMap<String, Object> global = CMinusCompiler.globalHash;
		
		String varId = variable.getId();
		
		Integer regNum = symTable.get(varId);				
		int srcRegNum = expr.genCode(func);
		
		// check for global
		if(regNum == null){
			
			boolean exists = global.containsKey(varId);
			
			// if global, store in memory
			if(exists){
				
				Operation op = new Operation(OperationType.STORE_I, currBlock);
				Operand regOper = new Operand(OperandType.REGISTER, srcRegNum);
				Operand strOper = new Operand(OperandType.STRING, varId);

				op.setSrcOperand(0, regOper);
				op.setSrcOperand(1, strOper);

				currBlock.appendOper(op);
				
			} else{
				throw new CodeGenerationException("Variable " + varId + " has not been declared.");
			}
			
		}
		// if local, assign to register
		else{
			
			Operand destOper = new Operand(OperandType.REGISTER, regNum);
			Operation op = new Operation(OperationType.ASSIGN, currBlock);
		
			Operand srcOper = new Operand(OperandType.REGISTER, srcRegNum);

			op.setDestOperand(0, destOper);
			op.setSrcOperand(0, srcOper);

			currBlock.appendOper(op);
		}

		
		// return the register number of the variable saved into (right hand side
		// if the left hand side is memory)
		if(regNum == null){
			return srcRegNum;
		} else{
			return regNum;
		}
	}
}
