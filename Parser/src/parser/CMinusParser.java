/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0 File: CMinusParser.java Created: March 2017
 *
 * Description: This class provides a CMinusParser. It's cool.
 */
package parser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import parser.scanner.*;
import parser.scanner.Token.*;
import parser.productions.*;
import parser.productions.declaration.*;
import parser.productions.expression.*;
import parser.productions.statement.*;

public class CMinusParser implements Parser {

	CMinusScanner scan;
	Program program;

	public CMinusParser(String filename) throws IOException {
		scan = new CMinusScanner(filename);
		program = null;
	}

	@Override
	public Program parse() throws CMinusParseException {

		program = parseProgram();
		return program;
	}

	public Program getProgram() throws CMinusParseException {
		if (program == null) {
			program = parseProgram();
		}

		return program;
	}

	private Program parseProgram() throws CMinusParseException {

		ArrayList<Declaration> decls = new ArrayList<>();

		Token nextTok = scan.viewNextToken();
		TokenType nextType = nextTok.getType();

		// Make sure we have at least one decl
		if (!nextType.inSet(First.Declaration)) {
			throw new CMinusParseException("ERROR in parseProgram(): "
				+ "there are no declarations");
		}

		while (nextType.inSet(First.Declaration)) {

			// decl-list -> decl
			Declaration nextDecl = parseDeclaration();
			decls.add(nextDecl);

			nextTok = scan.viewNextToken();
			nextType = nextTok.getType();
		}

		match(TokenType.EOF);

		return new Program(decls);

	}

	private Declaration parseDeclaration() throws CMinusParseException {

		// declaration -> VOID ID fundecl | INT ID varfundecl
		Token returnToken = scan.getNextToken();
		TokenType returnType = returnToken.getType();

		Token idToken = scan.getNextToken();
		TokenType idType = idToken.getType();

		if (idType != TokenType.ID) {
			throw new CMinusParseException("ERROR in parse(): Next token "
				+ idType + " is not an ID");
		}

		String identifier = (String) idToken.getData();

		Declaration returnDecl;
		if (returnType == TokenType.VOID) {
			// decl -> VOID ID fundecl
			returnDecl = parseFunDeclaration(returnToken, identifier);

		} else {
			// decl -> INT ID varfundecl
			returnDecl = parseVarFunDeclaration(returnToken, identifier);
		}

		return returnDecl;
	}

	private FunDeclaration parseFunDeclaration(Token retType, String identifier)
		throws CMinusParseException {

		// Get function parameters
		// fundecl -> ( params ) compound-stmt
		match(TokenType.OPEN_PAREN);
		ArrayList<Parameter> params = parseParameters();
		match(TokenType.CLOSE_PAREN);

		CompoundStatement stmt = parseCompoundStatement();

		return new FunDeclaration(retType, identifier, params, stmt);
	}

	private Declaration parseVarFunDeclaration(Token retType, String identifier)
		throws CMinusParseException {

		Token nextTok = scan.viewNextToken();
		TokenType nextType = nextTok.getType();

		Declaration retDecl;

		switch (nextType) {
			case SEMICOLON:
				// VarFunDecl -> ;
				scan.getNextToken();
				retDecl = new VarDeclaration(identifier);
				break;

			case OPEN_BRACKET:
				// VarFunDecl -> [ num ];
				scan.getNextToken();
				Token array = scan.getNextToken();

				if (array.getType() != TokenType.NUM) {
					throw new CMinusParseException("ERROR in "
						+ "parseVarFunDeclaration(): "
						+ "given array index is not a NUM");
				}

				match(TokenType.CLOSE_BRACKET);
				match(TokenType.SEMICOLON);

				retDecl = new VarDeclaration(identifier, (int) array.getData());

				break;

			case OPEN_PAREN:
				// VarFunDecl -> FunDecl
				retDecl = parseFunDeclaration(retType, identifier);
				break;

			default:
				throw new CMinusParseException("ERROR in "
					+ "parseVarFunDeclaration(): "
					+ nextType.toString() + " is not in the first set of "
					+ "VarFunDeclaration");
		}

		return retDecl;
	}

	private VarDeclaration parseVarDeclaration() throws CMinusParseException {

		// VarDecl -> int id
		match(TokenType.INT);

		Token id = scan.getNextToken();

		if (id.getType() != TokenType.ID) {
			throw new CMinusParseException("ERROR in parseVarDeclaration(): "
				+ id.getType().toString() + " is not an ID");
		}

		String identifier = (String) id.getData();

		TokenType next = scan.getNextToken().getType();

		VarDeclaration retDecl;

		if (next == TokenType.OPEN_BRACKET) {
			// Declaring an array
			// VarDecl -> int id [num]
			Token array = scan.getNextToken();

			if (array.getType() != TokenType.NUM) {
				throw new CMinusParseException("ERROR in parseVarDeclaration(): "
					+ "given array index is not a NUM");
			}

			int arraySize = (int) array.getData();

			match(TokenType.CLOSE_BRACKET);
			match(TokenType.SEMICOLON);

			retDecl = new VarDeclaration(identifier, arraySize);

		} else if (next == TokenType.SEMICOLON) {
			// Declaraing a regular variable
			retDecl = new VarDeclaration(identifier);

		} else {
			throw new CMinusParseException("ERROR in parseVarDeclaration(): "
				+ next.toString() + " is not in the first set of VarDeclaration");
		}

		return retDecl;
	}

	private ArrayList<Parameter> parseParameters() throws CMinusParseException {
		Token nextTok = scan.viewNextToken();
		TokenType nextType = nextTok.getType();

		ArrayList<Parameter> params;

		switch (nextType) {
			case VOID:
				// params -> void
				scan.getNextToken();
				params = null;

				break;

			case INT:
				// params -> param
				params = new ArrayList<>();
				params.add(parseParameter());

				nextTok = scan.viewNextToken();
				nextType = nextTok.getType();

				while (nextType == TokenType.COMMA) {
					// params -> { , param }
					scan.getNextToken();
					params.add(parseParameter());

					nextTok = scan.viewNextToken();
					nextType = nextTok.getType();
				}
				break;

			default:
				throw new CMinusParseException("ERROR in parseParameters(): "
					+ nextType.toString() + " is not a VOID or INT");
		}

		return params;
	}

	private Parameter parseParameter() throws CMinusParseException {

		// param -> int id [ [] ]
		match(TokenType.INT);

		Token id = scan.getNextToken();

		if (id.getType() != TokenType.ID) {
			throw new CMinusParseException("ERROR in parseParameter(): "
				+ id.getType().toString() + " is not an ID");
		}

		String identifier = (String) id.getData();

		Token arr = scan.viewNextToken();
		boolean isArray = arr.getType() == TokenType.OPEN_BRACKET;

		if (isArray) {
			// param -> int id []
			scan.getNextToken();
			match(TokenType.CLOSE_BRACKET);
		}

		return new Parameter(identifier, isArray);
	}

	private Statement parseStatement() throws CMinusParseException {

		TokenType nextType = scan.viewNextToken().getType();

		Statement retStatement;

		if (nextType.inSet(First.ExpressionStatement)) {
			// stmt -> ExprStmt
			retStatement = parseExpressionStatement();

		} else if (nextType.inSet(First.CompoundStatement)) {
			// stmt -> CmpdStmt
			retStatement = parseCompoundStatement();

		} else if (nextType.inSet(First.SelectionStatement)) {
			// stmt -> SlctStmt
			retStatement = parseSelectionStatement();

		} else if (nextType.inSet(First.IterationStatement)) {
			// stmt -> IterStmt
			retStatement = parseIterationStatement();

		} else if (nextType.inSet(First.ReturnStatement)) {
			// stmt -> RetStmt
			retStatement = parseReturnStatement();

		} else {
			// ERROR
			throw new CMinusParseException("ERROR in parseStatement(): "
				+ "Next token " + nextType.toString()
				+ " is not in the first set of any Statement extension");
		}

		return retStatement;
	}

	private ExpressionStatement parseExpressionStatement()
		throws CMinusParseException {

		TokenType nextType = scan.viewNextToken().getType();

		Expression expr;

		if (nextType.inSet(First.Expression)) {
			// exprstmt -> expr ;
			expr = parseExpression();

		} else if (nextType == TokenType.SEMICOLON) {
			// expr -> ;
			expr = null;

		} else {
			// ERROR
			throw new CMinusParseException("ERROR in parseExpressionStatement():"
				+ " Next token " + nextType.toString()
				+ " is not in the first set of Expression or a SEMICOLON");
		}

		match(TokenType.SEMICOLON);

		return new ExpressionStatement(expr);
	}

	private CompoundStatement parseCompoundStatement()
		throws CMinusParseException {

		match(TokenType.OPEN_BRACE);

		ArrayList<VarDeclaration> varDecls = new ArrayList<>();

		Token nextTok = scan.viewNextToken();
		TokenType nextType = nextTok.getType();

		while (nextType.inSet(First.VarDeclaration)) {
			varDecls.add(parseVarDeclaration());
			nextTok = scan.viewNextToken();
			nextType = nextTok.getType();
		}

		ArrayList<Statement> statements = new ArrayList<>();

		while (nextType.inSet(First.Statement)) {
			Statement stmt = parseStatement();
			statements.add(stmt);
			nextTok = scan.viewNextToken();
			nextType = nextTok.getType();
		}

		match(TokenType.CLOSE_BRACE);

		return new CompoundStatement(varDecls, statements);
	}

	private SelectionStatement parseSelectionStatement()
		throws CMinusParseException {

		// SelStmt -> IF ( expr ) stmt [ else stmt ]
		match(TokenType.IF);
		match(TokenType.OPEN_PAREN);

		Expression condition = parseExpression();

		match(TokenType.CLOSE_PAREN);

		Statement thenStatement = parseStatement();
		Statement elseStatement = null;

		if (scan.viewNextToken().getType() == TokenType.ELSE) {
			// SelStmt -> else stmt
			match(TokenType.ELSE);
			elseStatement = parseStatement();
		}

		return new SelectionStatement(condition, thenStatement, elseStatement);

	}

	private IterationStatement parseIterationStatement()
		throws CMinusParseException {

		// IterExpr -> WHILE ( expr ) stmt
		match(TokenType.WHILE);
		match(TokenType.OPEN_PAREN);

		Expression condition = parseExpression();

		match(TokenType.CLOSE_PAREN);

		Statement result = parseStatement();

		return new IterationStatement(condition, result);
	}

	private ReturnStatement parseReturnStatement() throws CMinusParseException {

		match(TokenType.RETURN);

		Token nextTok = scan.viewNextToken();
		TokenType nextType = nextTok.getType();

		Expression returnExpr = null;

		if (nextType.inSet(First.Expression)) {
			// RetStmt -> retern expr;
			returnExpr = parseExpression();

		}

		match(TokenType.SEMICOLON);

		return new ReturnStatement(returnExpr);
	}

	private Expression parseExpression() throws CMinusParseException {

		Token nextTok = scan.getNextToken();
		TokenType nextType = nextTok.getType();

		Expression retExpr = null;

		switch (nextType) {
			case OPEN_PAREN:
				// expr -> ( expr ) SE'
				Expression expr = parseExpression();

				match(TokenType.CLOSE_PAREN);

				retExpr = parseSimpleExpressionPrime(expr);
				break;

			case NUM:
				// expr -> NUM SE'
				int num = (int) nextTok.getData();

				Expression numExpr = new NumExpression(num);

				retExpr = parseSimpleExpressionPrime(numExpr);
				break;

			case ID:
				// expr -> id expr'
				String identifier = (String) nextTok.getData();
				retExpr = parseExpressionPrime(identifier);
				break;

			default:
				throw new CMinusParseException("ERROR in parseExpression(): "
					+ "Next token " + nextType.toString()
					+ " is not in the first set of Expression");
		}

		return retExpr;
	}

	private Expression parseExpressionPrime(String id)
		throws CMinusParseException {

		Token nextTok = scan.viewNextToken();
		TokenType nextType = nextTok.getType();

		Expression retExpr;

		if (nextType == TokenType.ASSIGN) {
			// expr' -> = expr
			scan.getNextToken();
			VarExpression varriable = new VarExpression(id);
			Expression expr = parseExpression();
			retExpr = new AssignExpression(varriable, expr);

		} else if (nextType == TokenType.OPEN_PAREN) {
			// expr' -> ( args ) SE'
			scan.getNextToken();
			ArrayList<Expression> args = parseArguments();

			match(TokenType.CLOSE_PAREN);
			CallExpression call = new CallExpression(id, args);

			retExpr = parseSimpleExpressionPrime(call);

		} else if (nextType == TokenType.OPEN_BRACKET) {
			// expr' -> [ expr ] expr''
			scan.getNextToken();

			Expression index = parseExpression();
			match(TokenType.CLOSE_BRACKET);
			VarExpression array = new VarExpression(id, index);

			retExpr = parseExpressionDoublePrime(array);

		} else if (nextType.inSet(First.SimpleExpressionPrime)) {

			// expr' -> SE'
			VarExpression variable = new VarExpression(id);
			retExpr = parseSimpleExpressionPrime(variable);

		} else if (nextType.inSet(Follow.SimpleExpressionPrime)) {
			// SE' -> epsilon
			retExpr = new VarExpression(id);

		} else {
			// ERROR
			throw new CMinusParseException("ERROR in parseExpressionPrime(): "
				+ nextType.toString()
				+ " is not in first or follow set");
		}

		return retExpr;
	}

	private Expression parseExpressionDoublePrime(VarExpression left)
		throws CMinusParseException {

		Token nextTok = scan.viewNextToken();
		TokenType nextType = nextTok.getType();

		Expression retExpr;

		if (nextType == TokenType.ASSIGN) {
			// E'' -> = E
			scan.getNextToken();
			Expression rightSide = parseExpression();
			retExpr = new AssignExpression(left, rightSide);

		} else if (nextType.inSet(First.SimpleExpressionPrime)) {
			// E'' -> SE'
			retExpr = parseSimpleExpressionPrime(left);

		} else if (nextType.inSet(Follow.SimpleExpressionPrime)) {
			// SE' -> epsilon
			retExpr = left;

		} else {
			throw new CMinusParseException("ERROR in parseExpressionDoublePrime(): "
				+ nextType.toString() + " is not in first or follow set of "
				+ "SimpleExpressionPrime");

		}

		return retExpr;
	}

	private Expression parseSimpleExpressionPrime(Expression left)
		throws CMinusParseException {

		// SE' -> AE'
		Expression expr = parseAdditiveExpression(left);

		Expression retExpr = null;

		Token nextTok = scan.viewNextToken();
		TokenType nextType = nextTok.getType();

		if (nextType.inSet(First.relop)) {

			// SE' -> [ relop AE ]
			TokenType relop = scan.getNextToken().getType();
			Expression right = parseAdditiveExpression(null);
			retExpr = new BinaryExpression(expr, relop, right);

		} else if (nextType.inSet(Follow.AdditiveExpression)) {
			// SE' -> epsilon
			retExpr = expr;

		} else {
			throw new CMinusParseException("ERROR in parseSimpleExpressionPrime()"
				+ nextType.toString() + " is not a RELOP or in the follow of "
				+ "AdditiveExpression");
		}

		return retExpr;
	}

	private Expression parseAdditiveExpression(Expression left)
		throws CMinusParseException {

		Expression retExpr = parseTerm(left);

		Token nextTok = scan.viewNextToken();
		TokenType nextType = nextTok.getType();

		// AE -> term { addop term }
		while (nextType.inSet(First.addop)) {
			TokenType addop = scan.getNextToken().getType();

			Expression right = parseTerm(null);
			retExpr = new BinaryExpression(retExpr, addop, right);

			// update lookahead
			nextTok = scan.viewNextToken();
			nextType = nextTok.getType();
		}

		return retExpr;
	}

	private Expression parseTerm(Expression left) throws CMinusParseException {

		Expression retExpr;
		if (left == null) {
			// parse whole factor
			retExpr = parseFactor();

		} else {
			// first operand removed
			retExpr = left;

		}

		Token nextTok = scan.viewNextToken();
		TokenType nextType = nextTok.getType();

		// parse { mulop factor }
		while (nextType.inSet(First.mulop)) {

			// get next mulop factor
			TokenType mulop = scan.getNextToken().getType();
			Expression nextFactor = parseFactor();

			// build on top of retExpr tree
			retExpr = new BinaryExpression(retExpr, mulop, nextFactor);

			// update token
			nextTok = scan.viewNextToken();
			nextType = nextTok.getType();
		}

		return retExpr;
	}

	private Expression parseFactor() throws CMinusParseException {

		Expression retExpr;

		Token nextTok = scan.getNextToken();
		TokenType nextType = nextTok.getType();

		switch (nextType) {
			case OPEN_PAREN:
				// factor -> ( expr )
				retExpr = parseExpression();
				match(TokenType.CLOSE_PAREN);
				break;

			case ID:
				// factor -> id
				retExpr = parseVarCall((String) nextTok.getData());
				break;

			case NUM:
				// factor -> num
				retExpr = new NumExpression((int) nextTok.getData());
				break;

			default:
				throw new CMinusParseException("ERROR in parseFactor(): "
					+ nextType.toString() + " is not in first set of Factor");
		}

		return retExpr;
	}

	private Expression parseVarCall(String id) throws CMinusParseException {

		Expression retExpr;

		Token nextTok = scan.viewNextToken();
		TokenType nextType = nextTok.getType();

		if (nextType == TokenType.OPEN_BRACKET) {
			// varcall -> [ expr ]
			scan.getNextToken();
			Expression index = parseExpression();
			match(TokenType.CLOSE_BRACKET);

			retExpr = new VarExpression(id, index);

		} else if (nextType == TokenType.OPEN_PAREN) {
			// varcall -> ( args )
			scan.getNextToken();

			ArrayList<Expression> args = parseArguments();

			retExpr = new CallExpression(id, args);

			match(TokenType.CLOSE_PAREN);

		} else if (nextType.inSet(Follow.VarCall)) {
			// varcall -> epsilon
			retExpr = new VarExpression(id);

		} else {
			throw new CMinusParseException("ERROR in parseVarCall(): "
				+ nextType.toString() + " is not in first set or follow of "
				+ "VarCall");
		}

		return retExpr;
	}

	private ArrayList<Expression> parseArguments() throws CMinusParseException {

		Token tok = scan.viewNextToken();
		TokenType nextType = tok.getType();

		ArrayList<Expression> args = null;

		if (nextType.inSet(First.Expression)) {

			// parse first argument
			args = new ArrayList<>();
			args.add(parseExpression());

			nextType = scan.viewNextToken().getType();

			// parse remaining arguments
			while (nextType == TokenType.COMMA) {

				// munch comma
				scan.getNextToken();

				// get next argument
				args.add(parseExpression());

				// update lookahead
				nextType = scan.viewNextToken().getType();
			}

		} else if (!nextType.inSet(Follow.Expression)) {
			// ERROR
			throw new CMinusParseException("ERROR in parseArguments(): "
				+ "Next token " + nextType.toString()
				+ " is not in the first set or follow set of Expression");
		}

		return args;
	}

	private void match(TokenType toMatch) throws CMinusParseException {

		Token token = scan.getNextToken();
		TokenType type = token.getType();

		if (toMatch != type) {
			throw new CMinusParseException("ERROR in match(): next token "
				+ type.toString() + " does not match " + toMatch.toString());
		}
	}

	public static void main(String[] args) throws Exception {

		String app = "test_err_";
		for (int i = 1; i <= 11; i++) {
			String fileName = (i < 10)? app + "0" + i : app + i;

			String header = "/* AST for " + fileName + ".cm */\n\n";

			try {
				CMinusParser cmp = new CMinusParser("test_cases/" + fileName + ".cm");

				Program p = cmp.parse();
				String ast = header + p.print("", "   ");
				System.out.print(ast);

				FileOutputStream f = new FileOutputStream(new File(fileName + ".ast"));
				f.write(ast.getBytes());
			} catch (Exception e) {
				System.out.println(fileName + ".cm");
				System.out.println(e.getMessage());
			}
		}
	}
}
