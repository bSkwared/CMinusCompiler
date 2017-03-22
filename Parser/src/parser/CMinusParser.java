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
        
        ArrayList<Declaration> decls = new ArrayList<>();
        
        Token     nextTok  = scan.getNextToken();
        TokenType nextType = nextTok.getType();
        
        while (nextType == TokenType.INT || nextType == TokenType.VOID) {
            
            Token id = scan.getNextToken();
            TokenType idType = id.getType();
            
            if (idType != TokenType.ID) {
                throw new CMinusParseException("ERROR in parse(): Next token " 
                                        + idType + " is not an indentifier.");
            }
            
            Declaration nextDecl;
            if (nextType == TokenType.VOID) {
                nextDecl = parseFunDeclaration(nextTok, id);
                
            } else {
                // type == TokenType.INT
                nextDecl = parseVarFunDeclaration(nextTok, id);
                
            }
            
            decls.add(nextDecl);
            
            nextTok = scan.getNextToken();
            nextType = nextTok.getType();
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
                retDecl = new VarDeclaration((String)id.getData());
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
        // TODO
        return null;
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
            }
            
        } else {
            throw new CMinusParseException("ERROR in parseParameters");
        }
        
        return params;
    }
    
    private Parameter parseParameter() {
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
    
    private ExpressionStatement parseExpressionStatement() 
            throws CMinusParseException {
        
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
    
    private CompoundStatement parseCompoundStatement() 
            throws CMinusParseException {
        
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
        
        // NOTE: this looks like different syntax than the rest of the inSets?
        // i switched it but left in comments
        // Blake: That was the onld syntax. Deleted.
        TokenType nextType = scan.viewNextToken().getType();
        
        if (Expression.inFirst(nextType)) {
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
    
    private ArrayList<Expression>parseArguments() throws CMinusParseException {
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
        
        return argsList;
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
