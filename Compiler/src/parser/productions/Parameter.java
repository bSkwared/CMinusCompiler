/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0 File: Parameter.java Created: March 2017
 *
 * Description: This file provides an a Parameter class to use with the
 * CMinusParser
 */
package parser.productions;

import java.util.HashMap;
import lowlevel.BasicBlock;
import lowlevel.Data;
import lowlevel.FuncParam;
import lowlevel.Function;
import lowlevel.Operand;
import lowlevel.Operation;

public class Parameter {

	private String id;
	private boolean isArray;

	public Parameter(String i, boolean isArr) {
		id = i;
		isArray = isArr;
	}

	public String getId() {
		return id;
	}

	public String print(String cur, String indent) {
		String str = "int " + id;

		if (isArray) {
			str += "[]";
		}

		return str;
	}

	public void genCode(Function func, int i) {
		HashMap<String, Integer> symTable = func.getTable();

		symTable.put(id, i);
	}
}
