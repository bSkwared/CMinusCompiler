/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0
 * File: CMinusParser.java
 * Created: March 2017
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
    //TODO 
    public Program parse() throws CMinusParseException {
        
        Program prog = new Program();
        
        return prog;
    }
    
    private VarDeclaration parseVarDeclaration() throws CMinusParseException {
        // TODO
        return null;
    }
    
    private Statement parseStatement() throws CMinusParseException {
        
        TokenType nextType = scan.viewNextToken().getType();
        
        Statement retStatement;
        
        if (ExpressionStatement.inFirst(nextType)) {
            retStatement = parseExpressionStatement();
            
        } else if (CompoundStatement.inFirst(nextType)) {
            retStatement = parseCompoundStatement();
            
        } else if (SelectionStatement.inFirst(nextType)) {
            retStatement = parseSelectionStatement();
            
        } else if (IterationStatement.inFirst(nextType)) {
            retStatement = parseIterationStatement();
            
        } else if (ReturnStatement.inFirst(nextType)) {
            retStatement = parseReturnStatement();
            
        } else {
            // ERROR
            throw new CMinusParseException("ERROR in parseStatement(): Next token " + nextType.toString() +  " is not in the first set of any Statement extension");
        }
        
        return retStatement;
    }
    
    private Statement parseExpressionStatement() throws CMinusParseException {
        
        TokenType nextType = scan.viewNextToken().getType();
        
        Expression expr;
        
        if (Expression.inFirst(nextType)) {
            expr = parseExpression();
            
        } else if (nextType == TokenType.SEMICOLON) {
            expr = null;
            
        } else {
            // ERROR
            throw new CMinusParseException("ERROR in parseExpressionStatement(): Next token " + nextType.toString() +  " is not in the first set of Expression or a SEMICOLON");
        }
        
        match (TokenType.SEMICOLON);
        
        return new ExpressionStatement(expr);
    }
    
    private Statement parseCompoundStatement() throws CMinusParseException {
        
        match(TokenType.OPEN_BRACE);
        
        
        TokenType nextType = scan.viewNextToken().getType();
        ArrayList<VarDeclaration> varDecls = new ArrayList<>();
        
        
        // NOTE do we need to check follow if we dont do any VarDecls?
        // Timothy: I would guess no because that will be handled by the 
        // match and Statement.inFirst()
        while (VarDeclaration.inFirst(nextType)) {
            
            varDecls.add(parseVarDeclaration());
            
            nextType = scan.viewNextToken().getType();
        }
        
        ArrayList<Statement> statements = new ArrayList<>();
        
        // NOTE same as above
        // Timothy: WE got that match right there that should take care of it
        // adding another if would be repetitive and wouldn't tell us anything since
        // the match(CLOSEBRACE) is even more restrictive
        while (Statement.inFirst(nextType)) {
            
            statements.add(parseStatement());
            
            nextType = scan.viewNextToken().getType();
        }
        
                
        match(TokenType.CLOSE_BRACE);
        
        return new CompoundStatement(varDecls, statements);
    }
    
    private Statement parseSelectionStatement() throws CMinusParseException {
        
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
    
    private Statement parseIterationStatement() throws CMinusParseException {
        
        match(TokenType.WHILE);
        match(TokenType.OPEN_PAREN);
        
        Expression condition = parseExpression();
        
        match(TokenType.CLOSE_PAREN);
        
        Statement result = parseStatement();        
        
        return new IterationStatement(condition, result);
    }
    
    private Statement parseReturnStatement() throws CMinusParseException {
        match(TokenType.RETURN);
        
        Expression returnExpr = null;
        
        // NOTE: this looks like different syntax than the rest of the inSets?
        // i switched it but left in comments
        TokenType nextType = scan.viewNextToken().getType();
        
        if (Expression.inFirst(nextType) /*inSet(Expression.FIRST)*/ ) {
            returnExpr = parseExpression();
            
        }
        
        match(TokenType.SEMICOLON);
        
        return new ReturnStatement(returnExpr);
    }
    
    //TODO This function
    private Expression parseExpression() throws CMinusParseException {
        
        TokenType nextType = scan.viewNextToken().getType();
        
        Expression expr = null;
        
        if (nextType == TokenType.OPEN_PAREN) {
            
        	match(TokenType.OPEN_PAREN);
        	//TODO EVERYTHING
        	
        } else if (nextType == TokenType.NUM) {
        	
        	//match(TokenType.NUM);
        	//TODO EVERYTHING
        	
            
        } else if (nextType == TokenType.ID) {
        	
        	//match(TokenType.ID);
            
        } else {
            // ERROR
            throw new CMinusParseException("ERROR in parseExpression(): Next token " + nextType.toString() +  " is not in the first set of Expression");
        }
        
        return null;
    }
    
    private Expression[] parseArguments() throws CMinusParseException {
        ArrayList<Expression> argsList = new ArrayList<>();
        
        TokenType curTokenType = scan.viewNextToken().getType();
        final TokenType CLOSE_PAREN = TokenType.CLOSE_PAREN;
        final TokenType COMMA = TokenType.COMMA;
        
        while (curTokenType != CLOSE_PAREN) {
            Expression nextExpression = parseExpression();
            argsList.add(nextExpression);
            
            curTokenType = scan.viewNextToken().getType();
            if (curTokenType == COMMA) {
                match(COMMA);
            }
        }
        
        match(CLOSE_PAREN);
                
        Expression[] argsArray = new Expression[argsList.size()];
        return argsList.toArray(argsArray);
    }
    
    private ArrayList<Expression> parseArgs() throws CMinusParseException {
        
        TokenType nextType = scan.viewNextToken().getType();
        
        ArrayList<Expression> args = new ArrayList<>();
        
        if (Expression.inFirst(nextType)) {
            args.add(parseExpression());
            nextType = scan.viewNextToken().getType();
            
            while (nextType == TokenType.COMMA) {
                match(TokenType.COMMA);
                args.add(parseExpression());
                nextType = scan.viewNextToken().getType();
            }
            
        } else if (!Expression.inFollow(nextType)) {
            // ERROR
            throw new CMinusParseException("ERROR in parseArgs(): Next token " + nextType.toString() +  " is not in the first set or follow set of Expression");
        }
        
        return args;
    }
    
    private boolean match(TokenType toMatch) throws CMinusParseException {
        //NOTE: do we want this to munch the Token on an mismatch?
    	    	
    	Token token = scan.viewNextToken();
    	TokenType type = token.getType();
    	if(toMatch == type){
    		scan.getNextToken(); // advance token
    		
    	} else{
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
    
    public static void main(String[] args) {
        System.out.println("HEY HEY YOU YOU I DONT LIKE YOUR GIRLFRIEND NO WAY NO WAY I THINK YOU NEED A NEW ONE");
    }
    
}
