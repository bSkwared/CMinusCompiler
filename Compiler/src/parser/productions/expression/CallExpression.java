/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0 File: CallExpression.java Created: March 2017
 *
 * Description: This file provides an implemented Expression class called
 * CallExpression to use with the CMinusParser
 */
package parser.productions.expression;

import java.util.ArrayList;
import lowlevel.CodeItem;
import lowlevel.Function;
import parser.CodeGenerationException;

public class CallExpression extends Expression {

	private String id;
	private ArrayList<Expression> arguments;
	private boolean hasArguments;

	public CallExpression(String ID, ArrayList<Expression> args) {
		id = ID;

		if (args == null || args.isEmpty()) {
			arguments = null;
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
	public int genCode(Function func) throws CodeGenerationException{

		// call genCode on params in reverse
		ArrayList<CodeItem> paramItems = new ArrayList();

		for (int i = arguments.size() - 1; i >= 0; i--) {
			int regNum = arguments.get(i).genCode(func);
			
			// PASS the param by storing to memory
			
		}

		// CALL function, annotate with num_params
		// SAVE return value if integer
			
		
		return 0;
	}

}