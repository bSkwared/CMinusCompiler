/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0
 * File: IterationStatement.java
 * Created: March 2017
 *
 * Description: This file provides an implemented Statement class called
 * 				IterationStatement to use with the CMinusParser
 */

package parser.productions.statement;

import lowlevel.BasicBlock;
import lowlevel.Function;
import lowlevel.Operand;
import lowlevel.Operation;
import lowlevel.CodeGenerationException;
import parser.productions.expression.Expression;

public class IterationStatement extends Statement {
    Expression condition;
    Statement result;
    
    public IterationStatement(Expression cond, Statement res) {
        condition = cond;
        result = res;
    }

    @Override
    public String print(String cur, String indent) {
        String str = "";
		str += cur + "while (\n";
        
        if (condition != null) {
            str += condition.print(cur + indent, indent);
        }
        str += "\n";
        str += cur + ")\n";

        str += result.print(cur + indent, indent);
		
		return str;
    }
	
	@Override
	public void genCode(Function func) throws CodeGenerationException{
		
		BasicBlock condBlock = new BasicBlock(func);
		BasicBlock thenBlock = new BasicBlock(func);
		BasicBlock postBlock = new BasicBlock(func);
		
		// append condition block and generate branch
		func.appendToCurrentBlock(condBlock);		
		func.setCurrBlock(condBlock);
		
		BasicBlock currBlock = func.getCurrBlock();
		
		int condReg = condition.genCode(func);
		
		Operation branchOp = new Operation(Operation.OperationType.BEQ, currBlock);
		Operand condOp = new Operand(Operand.OperandType.REGISTER, condReg);
		Operand zeroOp = new Operand(Operand.OperandType.INTEGER, 0);
		Operand bbOper = new Operand(Operand.OperandType.BLOCK, postBlock);
		
		branchOp.setSrcOperand(0, condOp);
		branchOp.setSrcOperand(1, zeroOp);
		branchOp.setSrcOperand(2, bbOper);
		
		currBlock.appendOper(branchOp);
		
		// append then block and generate the loop
		func.appendToCurrentBlock(thenBlock);
		currBlock = func.getCurrBlock();
		result.genCode(func);
		
		// generate jump to condition
		Operation jmpOp = new Operation(Operation.OperationType.JMP, currBlock);
		Operand jmpOper = new Operand(Operand.OperandType.BLOCK, condBlock);
		
		branchOp.setSrcOperand(0, jmpOper);
		
		currBlock.appendOper(branchOp);
		
		// append post block
		func.appendToCurrentBlock(postBlock);
		func.setCurrBlock(postBlock);		
	}
}
