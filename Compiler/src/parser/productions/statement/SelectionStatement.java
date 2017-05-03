/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0 File: SelectionStatement.java Created: March 2017
 *
 * Description: This file provides an implemented Statement class called
 * SelectionStatement to use with the CMinusParser
 */
package parser.productions.statement;

import lowlevel.*;
import lowlevel.Operand.OperandType;
import lowlevel.Operation.OperationType;
import parser.productions.expression.Expression;

public class SelectionStatement extends Statement {

    private Expression condition;
    private Statement thenStatement;
    private Statement elseStatement;

    public SelectionStatement(Expression cond, Statement then, Statement els) {
        condition = cond;
        thenStatement = then;
        elseStatement = els;
    }

    @Override
    public String print(String cur, String indent) {
        String str = "";
		
		str += cur + "if (";

        if (condition != null) {
            str += "\n";
            str += condition.print(cur + indent, indent);
            str += "\n" + cur;
        }

        str += ")\n";

        if (thenStatement != null) {
            str += thenStatement.print(cur + indent, indent);
        }

        if (elseStatement != null) {
            str += cur + "else\n";
            str += elseStatement.print(cur + indent, indent);
        }
		
		return str;
    }
	
		
	@Override
	public void genCode(Function func) throws CodeGenerationException {
				
		BasicBlock currBlock = func.getCurrBlock();		
		BasicBlock thenBlock = new BasicBlock(func);		
		BasicBlock elseBlock = null;				
		BasicBlock postBlock = new BasicBlock(func);
		BasicBlock branchBlock = postBlock;
		
		if (elseStatement != null) {
			elseBlock = new BasicBlock(func);
			branchBlock = elseBlock;
		}
		
		createIf(func, branchBlock, thenBlock);
	
		// genCode on thenStatement
		thenStatement.genCode(func);
		
		// append POST block
		func.appendToCurrentBlock(postBlock);
				
		// set CB and genCode ELSE block if it exists				
		if (elseBlock != null) {
                    createElseBlock(func, postBlock, elseBlock);
		}		

		// set CB to POST
		func.setCurrBlock(postBlock);
	}

    private void createIf(Function func, BasicBlock branchBlock,
            BasicBlock thenBlock) throws CodeGenerationException {

        // gen condition and branch
        BasicBlock currBlock = func.getCurrBlock();
        int regNum = condition.genCode(func);

        // gen branch
        int branchBlockNum = branchBlock.getBlockNum();
        Operation branchOp = new Operation(OperationType.BEQ, currBlock);
        Operand oper1 = new Operand(OperandType.REGISTER, regNum);
        Operand oper2 = new Operand(OperandType.INTEGER, 0);
        Operand bbOper = new Operand(OperandType.BLOCK, branchBlockNum);

        branchOp.setSrcOperand(0, oper1);
        branchOp.setSrcOperand(1, oper2);
        branchOp.setSrcOperand(2, bbOper);

        currBlock.appendOper(branchOp);

        // append THEN block and change CB
        func.appendToCurrentBlock(thenBlock);
        func.setCurrBlock(thenBlock);
    }
    
    private void createElseBlock(Function func, BasicBlock postBlock,
            BasicBlock elseBlock) throws CodeGenerationException {
        
        func.setCurrBlock(elseBlock);

        elseStatement.genCode(func);
        BasicBlock currBlock = func.getCurrBlock();

        // JUMP to POST		
        int postNum = postBlock.getBlockNum();
        Operation postJump = new Operation(OperationType.JMP, currBlock);
        Operand postOper = new Operand(OperandType.BLOCK, postNum);

        postJump.setSrcOperand(0, postOper);
        currBlock.appendOper(postJump);

        // append ELSE to the unconnected chain
        func.appendUnconnectedBlock(elseBlock);

    }
}
