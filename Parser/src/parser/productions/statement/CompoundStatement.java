/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0
 * File: CompoundStatement.java
 * Created: March 2017
 *
 * Description: This file provides an implemented Statement class called
 * 				CompoundStatement to use with the CMinusParser
 */

package parser.productions.statement;

import java.util.ArrayList;
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
    public void print(String cur, String indent) {
        for(VarDeclaration v : varDecls) v.print(cur, indent);
		
		for(Statement s : statements) s.print(cur, indent);
    }
}
