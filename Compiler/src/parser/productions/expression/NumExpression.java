/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0 File: NumExpression.java Created: March 2017
 *
 * Description: This file provides an implemented Expression class called
 * NumExpression to use with the CMinusParser
 */
package parser.productions.expression;

import lowlevel.BasicBlock;
import lowlevel.Function;
import lowlevel.Operand;
import lowlevel.Operation;

public class NumExpression extends Expression {

	private int value;

	public NumExpression(int number) {
		value = number;
	}

	@Override
	public String print(String cur, String indent) {
		return cur + value;
	}

	@Override
	// TODO: check Timothy's code
	public int genCode(Function func) {
		
		// get current block
		BasicBlock currBlock = func.getCurrBlock();

		// get a new register number for the saved value and create operands
		int newRegNum = func.getNewRegNum();		
		Operand regOper = new Operand(Operand.OperandType.REGISTER, newRegNum);
		Operand numOper = new Operand(Operand.OperandType.INTEGER, this.value);
		
		// create operations and set the src and dest operands
		Operation op = new Operation(Operation.OperationType.ASSIGN,currBlock);
		op.setSrcOperand(0, numOper);
		op.setDestOperand(0, regOper);
		
		// append the new operation to the current block
		currBlock.appendOper(op);		
		
		return newRegNum;
	}
}
