/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0 File: ReturnStatement.java Created: March 2017
 *
 * Description: This file provides an implemented Statement class called
 * ReturnStatement to use with the CMinusParser
 */
package parser.productions.statement;

import lowlevel.*;
import lowlevel.Operand.OperandType;
import lowlevel.Operation.OperationType;
import parser.productions.expression.Expression;

public class ReturnStatement extends Statement {

	Expression returnExpression;

	public ReturnStatement(Expression rtrn) {
		returnExpression = rtrn;
	}

	@Override
	public String print(String cur, String indent) {

		String str = "";

		str += cur + "return";

		if (returnExpression != null) {
			str += "\n";
			str += returnExpression.print(cur + indent, indent);
			str += "\n" + cur;
		}

		str += ";\n\n";

		return str;
	}

	@Override
	public void genCode(Function func) throws CodeGenerationException {

		BasicBlock currBlock = func.getCurrBlock();

		int regNum;
		// genCode on Expression
		if (returnExpression != null) {
			regNum = returnExpression.genCode(func);

			// move expression info to RetReg
			Operation op = new Operation(OperationType.ASSIGN, currBlock);
			Operand retOper = new Operand(OperandType.MACRO, "RetReg");
			Operand expOper = new Operand(OperandType.REGISTER, regNum);

			op.setSrcOperand(0, expOper);
			op.setDestOperand(0, retOper);

			currBlock.appendOper(op);
		}

		// add JMP operation to ExitBlock
		int returnBlockNum = func.getReturnBlock().getBlockNum();
		Operation jmpOp = new Operation(OperationType.JMP, currBlock);
		Operand exitOper = new Operand(OperandType.BLOCK, returnBlockNum);

		jmpOp.setSrcOperand(0, exitOper);

		currBlock.appendOper(jmpOp);
	}
}
