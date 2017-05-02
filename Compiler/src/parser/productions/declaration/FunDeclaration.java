/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0 File: FunDeclaration.java Created: March 2017
 *
 * Description: This file provides an implemented Declaration class called
 * FunDeclaration to use with the CMinusParser
 */
package parser.productions.declaration;

import java.util.ArrayList;
import lowlevel.CodeItem;
import lowlevel.Data;
import lowlevel.FuncParam;
import lowlevel.Function;
import parser.productions.Parameter;
import parser.productions.statement.CompoundStatement;
import parser.scanner.Token;
import parser.scanner.Token.*;

public class FunDeclaration extends Declaration {

    private boolean returnsVoid;
    private String returnType;

    private boolean hasParameters;

    private ArrayList<Parameter> parameters;

    private CompoundStatement statement;

    public FunDeclaration(Token type, String i, ArrayList<Parameter> params,
            CompoundStatement stmt) {

        TokenType returnTokenType = type.getType();

        if (returnTokenType == TokenType.VOID) {
            returnsVoid = true;
            returnType = "void";

        } else {
            // returnType == INT
            returnsVoid = false;
            returnType = "int";
        }

        id = i;

        if (params == null || params.isEmpty()) {
            parameters = null;
            hasParameters = false;
        } else {
            parameters = params;
            hasParameters = true;
        }

        statement = stmt;
    }

    public FunDeclaration(Token type, String i, CompoundStatement stmt) {
        this(type, i, null, stmt);
    }

	@Override
    public String print(String cur, String indent) {

		String str = "";
		
        String functionHeader = returnType + " " + id + "(";
        str += cur + functionHeader;

        if (hasParameters) {
            
            for (int i = 0; i < parameters.size(); i++) {
                Parameter p = parameters.get(i);
                str += p.print(cur + indent, indent);
                if (i < parameters.size() - 1) {
                    str += ", ";
                }
            }
            str += ")\n";

        } else {
            str += "void)\n";
        }

        str += statement.print(cur + indent, indent);
		
		return str;
    }

	@Override
    public CodeItem genCode() {
        
        CodeItem retItem;
        FuncParam firstParam = null;
        
        if (hasParameters) {
            FuncParam lastParam = null;
            for (Parameter p : parameters) {
				// changed this line to call Parameter.genCode() instead of
				// making the FuncParam right here
                FuncParam nextParam = p.genCode();
                        
                if (firstParam == null) {
                    firstParam = nextParam;
                    lastParam = firstParam;
                }
                
                lastParam.setNextParam(nextParam);
                lastParam = nextParam;
            }   
        }
        
        int retType;
        if (returnsVoid) {
            retType = Data.TYPE_VOID;
        } else {
            retType = Data.TYPE_INT;
        }

        retItem = new Function(retType, id, firstParam);
        
        return retItem;
    }
}
