/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0 File: CompoundStatement.java Created: March 2017
 *
 * Description: This file provides an implemented Statement class called
 * CompoundStatement to use with the CMinusParser
 */
package parser.productions.statement;

import java.util.ArrayList;
import java.util.HashMap;
import lowlevel.CodeGenerationException;
import lowlevel.CodeItem;
import lowlevel.Data;
import lowlevel.Function;
import parser.productions.declaration.VarDeclaration;

public class CompoundStatement extends Statement {

	private ArrayList<VarDeclaration> varDecls;
	private ArrayList<Statement> statements;

	public CompoundStatement(ArrayList<VarDeclaration> decls,
		ArrayList<Statement> states) {

		varDecls = decls;
		statements = states;
	}

	@Override
	public String print(String cur, String indent) {

		String str = "";

		str += cur + "{\n";
		for (VarDeclaration v : varDecls) {
			str += v.print(cur + indent, indent);
		}

		for (Statement s : statements) {
			str += s.print(cur + indent, indent);
		}
		str += cur + "}\n\n";

		return str;
	}	
	
	@Override
	// TODO: Check Timothy's Code	
	public void genCode(Function func) throws CodeGenerationException{
		
		HashMap<String, Integer> symTable = func.getTable();
		
		// loop over the local var decls and put them in the symbol table
		for(VarDeclaration v : varDecls){			
			int regNum = func.getNewRegNum();
			symTable.put(v.getId(), regNum);
		}
		
		// loop over the local statements
		for(Statement s : statements){
			s.genCode(func);
		}
		
	}
}
