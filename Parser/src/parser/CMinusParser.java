/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0 File: CMinusParser.java Created: March 2017
 *
 * Description: This class provides a CMinusParser
 */
package parser;

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

	public CMinusParser(String filename) throws IOException {
		scan = new CMinusScanner(filename);
	}

	@Override
	public Program parse() throws CMinusParseException {

		ArrayList<Declaration> decls = new ArrayList<>();

		while (inSet(First.Program)) {			
			Token nextTok = scan.getNextToken();
			TokenType nextType = nextTok.getType();

			Token id = scan.getNextToken();
			TokenType idType = id.getType();

			if (idType != TokenType.ID) {
				throw new CMinusParseException("ERROR in parse(): Next token "
					+ idType + " is not an identifier.");
			}

			Declaration nextDecl;
			if (nextType == TokenType.VOID) {
				nextDecl = parseFunDeclaration(nextTok, id);

			} else {
				// type == TokenType.INT
				nextDecl = parseVarFunDeclaration(nextTok, id);

			}

			decls.add(nextDecl);
		}

		match(TokenType.EOF);

		return new Program(decls);
	}

	private FunDeclaration parseFunDeclaration(Token retType, Token id)
		throws CMinusParseException {

		match(TokenType.OPEN_PAREN);

		ArrayList<Parameter> params = parseParameters();

		match(TokenType.CLOSE_PAREN);

		CompoundStatement stmt = parseCompoundStatement();

		String identifier = (String) id.getData();

		return new FunDeclaration(retType, identifier, params, stmt);
	}

	private Declaration parseVarFunDeclaration(Token retType, Token id)
		throws CMinusParseException {

		Token nextTok = scan.viewNextToken();
		TokenType nextType = nextTok.getType();

		String identifier = (String) id.getData();

		Declaration retDecl;

		switch (nextType) {
			case SEMICOLON:
				scan.getNextToken();
				retDecl = new VarDeclaration((String) id.getData());
				break;

			case OPEN_BRACKET:
				scan.getNextToken();
				Token array = scan.getNextToken();

				if (array.getType() != TokenType.NUM) {
					throw new CMinusParseException("ERROR");
				}

				match(TokenType.CLOSE_BRACKET);
				match(TokenType.SEMICOLON);

				retDecl = new VarDeclaration(identifier, (int) array.getData());

				break;

			case OPEN_PAREN:
				retDecl = parseFunDeclaration(retType, id);
				break;

			default:
				throw new CMinusParseException("ERROR");
		}

		return retDecl;
	}

	private VarDeclaration parseVarDeclaration() throws CMinusParseException {

		match(TokenType.INT);

		Token id = scan.getNextToken();

		if (id.getType() != TokenType.ID) {
			throw new CMinusParseException("ERROR in parseVarDecl");
		}

		String identifier = (String) id.getData();

		TokenType next = scan.getNextToken().getType();

		VarDeclaration retDecl;

		if (next == TokenType.OPEN_BRACKET) {
			// Declaring an array
			Token array = scan.getNextToken();

			if (array.getType() != TokenType.NUM) {
				throw new CMinusParseException("ERROR in parseVarDecl");
			}

			int arraySize = (int) array.getData();

			match(TokenType.CLOSE_BRACKET);
			match(TokenType.SEMICOLON);

			retDecl = new VarDeclaration(identifier, arraySize);

		} else if (next == TokenType.SEMICOLON) {
			// Declaraing a regular variable
			retDecl = new VarDeclaration(identifier);

		} else {
			throw new CMinusParseException("ERROR in parseVarDecl");
		}

		return retDecl;
	}

	private ArrayList<Parameter> parseParameters() throws CMinusParseException {
		Token nextTok = scan.viewNextToken();
		TokenType nextType = nextTok.getType();

		ArrayList<Parameter> params;

		if (nextType == TokenType.VOID) {
			scan.getNextToken();
			params = null;

		} else if (nextType == TokenType.INT) {
			params = new ArrayList<>();
			params.add(parseParameter());

			while (nextType == TokenType.COMMA) {
				params.add(parseParameter());

				nextTok = scan.viewNextToken();
				nextType = nextTok.getType();
			}

		} else {
			throw new CMinusParseException("ERROR in parseParameters");
		}

		return params;
	}

	private Parameter parseParameter() throws CMinusParseException {
		match(TokenType.INT);

		Token id = scan.getNextToken();

		if (id.getType() != TokenType.ID) {
			throw new CMinusParseException("ERROR in parseParameter");
		}

		Token arr = scan.viewNextToken();
		boolean isArray = arr.getType() == TokenType.OPEN_BRACKET;

		if (isArray) {
			scan.getNextToken();
			match(TokenType.CLOSE_BRACKET);
		}

		return new Parameter((String) id.getData(), isArray);
	}

	private Statement parseStatement() throws CMinusParseException {

		TokenType nextType = scan.viewNextToken().getType();

		Statement retStatement;

		if (inSet(First.ExpressionStatement)) {
			retStatement = parseExpressionStatement();

		} else if (inSet(First.CompoundStatement)) {
			retStatement = parseCompoundStatement();

		} else if (inSet(First.SelectionStatement)) {
			retStatement = parseSelectionStatement();

		} else if (inSet(First.IterationStatement)) {
			retStatement = parseIterationStatement();

		} else if (inSet(First.ReturnStatement)) {
			retStatement = parseReturnStatement();

		} else {
			// ERROR
			throw new CMinusParseException("ERROR in parseStatement(): Next token " + nextType.toString() + " is not in the first set of any Statement extension");
		}

		return retStatement;
	}

	private ExpressionStatement parseExpressionStatement()
		throws CMinusParseException {

		TokenType nextType = scan.viewNextToken().getType();

		Expression expr;

		if (inSet(First.Expression)) {
			expr = parseExpression();

		} else if (nextType == TokenType.SEMICOLON) {
			expr = null;

		} else {
			// ERROR
			throw new CMinusParseException("ERROR in parseExpressionStatement(): "
				+ "Next token " + nextType.toString()
				+ " is not in the first set of Expression or a SEMICOLON");
		}

		match(TokenType.SEMICOLON);

		return new ExpressionStatement(expr);
	}

	private CompoundStatement parseCompoundStatement()
		throws CMinusParseException {

		match(TokenType.OPEN_BRACE);

		ArrayList<VarDeclaration> varDecls = new ArrayList<>();

		// NOTE do we need to check follow if we dont do any VarDecls?
		// Timothy: I would guess no because that will be handled by the 
		// match and Statement.inFirst()
		while (inSet(First.VarDeclaration)) {
			varDecls.add(parseVarDeclaration());
		}

		ArrayList<Statement> statements = new ArrayList<>();

		// NOTE same as above
		// Timothy: WE got that match right there that should take care of it
		// adding another if would be repetitive and wouldn't tell us anything since
		// the match(CLOSEBRACE) is even more restrictive
		while (inSet(First.Statement)) {
			statements.add(parseStatement());
		}

		match(TokenType.CLOSE_BRACE);

		return new CompoundStatement(varDecls, statements);
	}

	private SelectionStatement parseSelectionStatement()
		throws CMinusParseException {

		match(TokenType.IF);
		match(TokenType.OPEN_PAREN);

		Expression condition = parseExpression();

		match(TokenType.CLOSE_PAREN);

		Statement thenStatement = parseStatement();
		Statement elseStatement = null;

		if (scan.viewNextToken().getType() == TokenType.ELSE) {
			match(TokenType.ELSE);
			elseStatement = parseStatement();
		}

		return new SelectionStatement(condition, thenStatement, elseStatement);

	}

	private IterationStatement parseIterationStatement()
		throws CMinusParseException {

		match(TokenType.WHILE);
		match(TokenType.OPEN_PAREN);

		Expression condition = parseExpression();

		match(TokenType.CLOSE_PAREN);

		Statement result = parseStatement();

		return new IterationStatement(condition, result);
	}

	private ReturnStatement parseReturnStatement() throws CMinusParseException {
		match(TokenType.RETURN);

		Expression returnExpr = null;

		if (inSet(First.Expression)) {
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

				Expression expr = parseExpression();

				match(TokenType.CLOSE_PAREN);

				retExpr = parseSimpleExpressionPrime(expr);
				break;

			case NUM:

				int num = (int) nextTok.getData();

				Expression numExpr = new NumExpression(num);

				retExpr = parseSimpleExpressionPrime(numExpr);
				break;

			case ID:

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

		switch (nextType) {
			case ASSIGN:
				scan.getNextToken();
				VarExpression varriable = new VarExpression(id);
				Expression expr = parseExpression();
				retExpr = new AssignExpression(varriable, expr);
				break;

			case OPEN_PAREN:
				scan.getNextToken();
				ArrayList<Expression> args = parseArguments();

				match(TokenType.CLOSE_PAREN);

				CallExpression call = new CallExpression(id, args);

				retExpr = parseSimpleExpressionPrime(call);
				break;

			case OPEN_BRACKET:
				scan.getNextToken();

				Expression index = parseExpression();

				match(TokenType.CLOSE_BRACKET);

				VarExpression array = new VarExpression(id, index);

				retExpr = parseExpressionDoublePrime(array);
				break;

			// NOTE TODO ERROR BROKEN need to check first of SE'
			// TIMOTHY HAS ATTEMPTED TO FIX IT
			default:

				if (inSet(First.SimpleExpressionPrime) || inSet(Follow.SimpleExpressionPrime)) {
					VarExpression variable = new VarExpression(id);
					retExpr = parseSimpleExpressionPrime(variable);
					break;
				} else {
					throw new CMinusParseException("ERROR in parseExpressionPrime() : ");
				}

		}

		return retExpr;
	}

	private Expression parseExpressionDoublePrime(VarExpression left)
		throws CMinusParseException {

		Token nextTok = scan.viewNextToken();
		TokenType nextType = nextTok.getType();

		Expression retExpr;

		switch (nextType) {
			case ASSIGN:
				scan.getNextToken();
				Expression rightSide = parseExpression();
				retExpr = new AssignExpression(left, rightSide);
				break;

			// TODO ERROR BROKEN need to check first of SE'
			// TIMOTHY HAS ATTEMPTED TO FIX
			default:

				if (inSet(First.SimpleExpressionPrime) || inSet(Follow.SimpleExpressionPrime)) {
					retExpr = parseSimpleExpressionPrime(left);
					break;
				} else {
					throw new CMinusParseException("ERROR in parseExpressionDoublePrime()");
				}
		}

		return retExpr;
	}

	private Expression parseSimpleExpressionPrime(Expression left) throws CMinusParseException {

		Expression expr = parseAdditiveExpression(left);

		Expression retExpr = null;
		if (inSet(First.relop)) {
			Token relop = scan.getNextToken();
			Expression right = parseAdditiveExpression(null);
			retExpr = new BinaryExpression(expr, relop, right);
		} // TODO else if in follow
		// Timothy has assumed Follow.AdditiveExpression
		// is this really necessary though, or will it be handled in the next parse?
		else if (inSet(Follow.AdditiveExpression)) {
			retExpr = expr;
		} else {
			throw new CMinusParseException("ERROR in parseSimpleExpressionPrime()");
		}

		// NOTE: should this be new BinaryExpression()?
		return retExpr;
	}

	private Expression parseAdditiveExpression(Expression left) throws CMinusParseException {

		Expression retExpr = parseTerm(left);

		while (inSet(First.addop)) {			
			Token addop = scan.getNextToken();

			retExpr = new BinaryExpression(retExpr, addop, parseTerm(null));
		}

		return retExpr;
	}

	private Expression parseTerm(Expression left) throws CMinusParseException {

		Expression retExpr;

		if (left == null) {
			retExpr = parseFactor();

		} else {
			retExpr = left;

		}

		while (inSet(First.mulop)) {
			
			Token mulop = scan.getNextToken();

			retExpr = new BinaryExpression(retExpr, mulop, parseFactor());
		}

		return retExpr;
	}

	private Expression parseFactor() throws CMinusParseException {
		
		Token nextTok = scan.getNextToken();
		TokenType nextType = nextTok.getType();

		Expression retExpr;

		switch (nextType) {
			case OPEN_PAREN:
				retExpr = parseExpression();
				match(TokenType.CLOSE_PAREN);
				break;

			case ID:
				retExpr = parseVarCall((String) nextTok.getData());
				break;

			case NUM:
				// TODO check follow
				// TIMOTHY HAS ATTEMPTED TO FIX BY NOT DOING ANYTHING
				retExpr = new NumExpression((int) nextTok.getData());
				break;

			default:
				throw new CMinusParseException("ERROR in parseFactor()");
		}

		return retExpr;
	}

	private Expression parseVarCall(String id) throws CMinusParseException {
		Token nextTok = scan.viewNextToken();
		TokenType nextType = nextTok.getType();

		Expression retExpr;

		if (nextType == TokenType.OPEN_BRACKET) {
			scan.getNextToken();
			Expression index = parseExpression();
			match(TokenType.CLOSE_BRACKET);

			retExpr = new VarExpression(id, index);

		} else if (nextType == TokenType.OPEN_PAREN) {
			scan.getNextToken();

			ArrayList<Expression> args = parseArguments();

			retExpr = new CallExpression(id, args);

			match(TokenType.CLOSE_PAREN);

		} // TODO check follow
		// TIMOTHY HAS ATTEMPTED TO FIX
		else if (inSet(Follow.VarCall)) {
			retExpr = new VarExpression(id);
		} else {
			throw new CMinusParseException("ERROR in parseVarCall()");
		}

		return retExpr;
	}

	private ArrayList<Expression> parseArguments() throws CMinusParseException {

		Token tok = scan.viewNextToken();
		TokenType nextType = tok.getType();

		ArrayList<Expression> args = new ArrayList<>();

		if (inSet(First.Expression)) {
			args.add(parseExpression());
			nextType = scan.viewNextToken().getType();

			while (nextType == TokenType.COMMA) {

				scan.getNextToken();

				args.add(parseExpression());

				nextType = scan.viewNextToken().getType();
			}

		} else if (!inSet(Follow.Expression)) {
			// ERROR
			throw new CMinusParseException("ERROR in parseArguments(): Next token " + nextType.toString() + " is not in the first set or follow set of Expression");
		}

		return args;
	}

	private boolean match(TokenType toMatch) throws CMinusParseException {

		Token token = scan.viewNextToken();
		TokenType type = token.getType();
		if (toMatch == type) {
			scan.getNextToken(); // advance token

		} else {
			throw new CMinusParseException("ERROR in match(): Next token " + type.toString() + " does not match " + toMatch.toString());

		}

		return true;
	}

	private boolean inSet(TokenType[] set) throws CMinusParseException {
		Token token = scan.viewNextToken();
		TokenType type = token.getType();

		boolean foundType = false;

		for (TokenType firstType : set) {
			if (type == firstType) {
				foundType = true;
				break;
			}
		}

		return foundType;
	}

	public static void main(String[] args) throws Exception {
		CMinusParser cmp = new CMinusParser("../test_01.cm");
		Program p = cmp.parse();
	}

}
