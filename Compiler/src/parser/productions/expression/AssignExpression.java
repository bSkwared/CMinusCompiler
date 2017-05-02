/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0 File: AssignExpression.java Created: March 2017
 *
 * Description: This file provides an implemented Expression class called
 * AssignExpression to use with the CMinusParser
 */
package parser.productions.expression;

import lowlevel.BasicBlock;
import lowlevel.Function;
import lowlevel.Operand;
import lowlevel.Operation;
import parser.CodeGenerationException;

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
		
		int destRegNum = variable.genCode(func);
		int srcRegNum = expr.genCode(func);

		// new assign operation
		Operation op = new Operation(Operation.OperationType.ASSIGN, currBlock);
		
		Operand destOper = new Operand(Operand.OperandType.REGISTER, destRegNum);
		Operand srcOper = new Operand(Operand.OperandType.REGISTER, srcRegNum);
		
		op.setDestOperand(0, destOper);
		op.setSrcOperand(0, srcOper);
		
		currBlock.appendOper(op);
		
		// return the register number of the variable saved into
		return destRegNum;
	}
}
