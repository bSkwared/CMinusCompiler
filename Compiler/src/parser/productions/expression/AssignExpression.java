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
	// TODO: check Timothy's code
	public int genCode(Function func) throws CodeGenerationException {

		// get current block
		BasicBlock currBlock = func.getCurrBlock();
		
		HashMap<String, Integer> symTable = func.getTable();
		
		String varId = variable.getId();
		
		Integer regNum = symTable.get(varId);				
		Operand destOper;
		Operation op;
		
		// check for global
		if(regNum == null){
			
			boolean exists = (CMinusCompiler.globalHash.get(varId) != null);
			
			// if global, store in memory
			if(exists){
				destOper = new Operand(Operand.OperandType.STRING, varId);
				op = new Operation(Operation.OperationType.STORE_I, currBlock);
				
			} else{
				throw new CodeGenerationException("Variable " + varId + " has not been declared.");
			}
			
		}
		// if local, assign to register
		else{
			
			destOper = new Operand(Operand.OperandType.REGISTER, regNum);
			op = new Operation(Operation.OperationType.ASSIGN, currBlock);
		}
		
		int srcRegNum = expr.genCode(func);
		
		Operand srcOper = new Operand(Operand.OperandType.REGISTER, srcRegNum);
		
		op.setDestOperand(0, destOper);
		op.setSrcOperand(0, srcOper);
		
		currBlock.appendOper(op);
		
		// return the register number of the variable saved into (right hand side
		// if the left hand side is memory)
		if(regNum == null){
			return srcRegNum;
		} else{
			return regNum;
		}
	}
}
