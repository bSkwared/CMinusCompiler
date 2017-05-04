/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0 File: BinaryExpression.java Created: March 2017
 *
 * Description: This file provides an implemented Expression class called
 * BinaryExpression to use with the CMinusParser
 */
package parser.productions.expression;

import lowlevel.*;
import lowlevel.Operand.OperandType;
import lowlevel.Operation.OperationType;
import parser.scanner.Token.*;

public class BinaryExpression extends Expression {

	private enum OpType {
		ADD, SUB, MULT, DIV, GT, GTE, LT, LTE, EQUAL, NOT_EQUAL
	}

	private Expression left;
	private OpType operator;
	private Expression right;

	public BinaryExpression(Expression lef, TokenType op, Expression righ) {
		left = lef;

		switch (op) {
			case ADD:
				operator = OpType.ADD;
				break;
			case SUB:
				operator = OpType.SUB;
				break;
			case MULT:
				operator = OpType.MULT;
				break;
			case DIV:
				operator = OpType.DIV;
				break;
			case GT:
				operator = OpType.GT;
				break;
			case GTE:
				operator = OpType.GTE;
				break;
			case LT:
				operator = OpType.LT;
				break;
			case LTE:
				operator = OpType.LTE;
				break;
			case EQUAL:
				operator = OpType.EQUAL;
				break;
			case NOT_EQUAL:
				operator = OpType.ADD;
				break;
		}
		right = righ;
	}

	@Override
	public String print(String cur, String indent) {
		String str = "";

		String op = "";

		switch (operator) {
			case ADD:
				op = "+";
				break;
			case SUB:
				op = "-";
				break;
			case MULT:
				op = "*";
				break;
			case DIV:
				op = "/";
				break;
			case GT:
				op = ">";
				break;
			case GTE:
				op = ">=";
				break;
			case LT:
				op = "<";
				break;
			case LTE:
				op = "<=";
				break;
			case EQUAL:
				op = "==";
				break;
			case NOT_EQUAL:
				op = "!=";
				break;
		}

		str += cur + op + "\n";

		str += left.print(cur + "|" + indent, indent);
		str += "\n";
		str += right.print(cur + "|" + indent, indent);

		return str;
	}

	@Override
	public int genCode(Function func) throws CodeGenerationException{

		BasicBlock currBlock = func.getCurrBlock();
		
		// genCode on left and right and get register numbers
		int leftRegNum = left.genCode(func);
		int rightRegNum = right.genCode(func);

		// get new register to store result in
		int newRegNum = func.getNewRegNum();
		
		OperationType opType = null;
		
		// new assign operation
		// convert OpType to the Operation.OperationType
                opType = opTypeToOperationType(operator);

		
		Operation op = new Operation(opType, currBlock);
		
		Operand destOper = new Operand(OperandType.REGISTER, newRegNum);
		Operand leftOper = new Operand(OperandType.REGISTER, leftRegNum);
		Operand rightOper = new Operand(OperandType.REGISTER, rightRegNum);
		
		// set the src/dest registers
		op.setDestOperand(0, destOper);
		op.setSrcOperand(0, leftOper);
		op.setSrcOperand(1, rightOper);
		
		// append to current block
		currBlock.appendOper(op);
		
		// return the register number of the new operation
		return newRegNum;		
	}
        
    public OperationType opTypeToOperationType(OpType op) {
        OperationType opType = null;
        switch (operator) {
            case ADD:
                opType = OperationType.ADD_I;
                break;
            case SUB:
                opType = OperationType.SUB_I;
                break;
            case MULT:
                opType = OperationType.MUL_I;
                break;
            case DIV:
                opType = OperationType.DIV_I;
                break;
            case GT:
                opType = OperationType.GT;
                break;
            case GTE:
                opType = OperationType.GTE;
                break;
            case LT:
                opType = OperationType.LT;
                break;
            case LTE:
                opType = OperationType.LTE;
                break;
            case EQUAL:
                opType = OperationType.EQUAL;
                break;
            case NOT_EQUAL:
                opType = OperationType.NOT_EQUAL;
                break;
        }
        return opType;
    }
}
