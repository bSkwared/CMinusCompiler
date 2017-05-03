/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0 File: CallExpression.java Created: March 2017
 *
 * Description: This file provides an implemented Expression class called
 * CallExpression to use with the CMinusParser
 */
package parser.productions.expression;

import java.util.ArrayList;
import lowlevel.*;

public class CallExpression extends Expression {
	
	private String id;
	private ArrayList<Expression> arguments;
	private boolean hasArguments;
	
	public CallExpression(String ID, ArrayList<Expression> args) {
		id = ID;
		
		if (args == null || args.isEmpty()) {
			arguments = new ArrayList<>();
			hasArguments = false;
			
		} else {
			arguments = args;
			hasArguments = true;
		}
		
	}
	
	public CallExpression(String ID) {
		this(ID, null);
	}
	
	@Override
	public String print(String cur, String indent) {
		String str = cur + id + "(";
		
		if (hasArguments) {
			str += "\n";
			for (Expression arg : arguments) {
				str += arg.print(cur + indent, indent);
				str += "\n";
			}
			str += cur;
		}
		
		str += ")\n";
		
		return str;
	}
	
	@Override
	// TODO: Finish this -- look at project requirements
	public int genCode(Function func) throws CodeGenerationException {
		
		BasicBlock currBlock = func.getCurrBlock();
		
		for (int i = arguments.size() - 1; i >= 0; i--) {
			int regNum = arguments.get(i).genCode(func);

			// PASS the param by storing to memory
			Operation op = new Operation(Operation.OperationType.PASS, currBlock);
			Operand param = new Operand(Operand.OperandType.REGISTER, regNum);
			
			Attribute paramNum = new Attribute("PARAM_NUM", String.valueOf(i));
			
			op.setSrcOperand(0, param);
			op.addAttribute(paramNum);
			
			currBlock.appendOper(op);
		}

		// CALL function, annotate with num_params
		Operation op = new Operation(Operation.OperationType.CALL, currBlock);
		Operand funcOper = new Operand(Operand.OperandType.STRING, this.id);
		
		Attribute numParams = new Attribute("numParams", String.valueOf(arguments.size()));
		
		op.setSrcOperand(0, funcOper);
		op.addAttribute(numParams);
		
		currBlock.appendOper(op);
		
		
		// SAVE return value
		int saveRegNum = func.getNewRegNum();
		
		Operation saveOp = new Operation(Operation.OperationType.ASSIGN, currBlock);
		Operand destOper = new Operand(Operand.OperandType.REGISTER, saveRegNum);
		Operand srcOper = new Operand(Operand.OperandType.MACRO, "RetReg");
		
		saveOp.setDestOperand(0, destOper);
		saveOp.setSrcOperand(0, srcOper);
		
		currBlock.appendOper(op);
		
		return saveRegNum;
	}
	
}
