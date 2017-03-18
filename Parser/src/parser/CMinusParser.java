package parser;

import java.io.IOException;
import java.util.ArrayList;
import parser.scanner.*;
import parser.scanner.Token.*;
import parser.productions.*;
import parser.productions.expression.*;
import parser.productions.statement.*;

/**
 *
 * @author Timothy Smith and Blake Lasky
 */
public class CMinusParser implements Parser {

    CMinusScanner scan;
    
    public CMinusParser(String filename) throws IOException {
        scan = new CMinusScanner(filename);
    }
    
    @Override
    public Program parse() {
        
        
        
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private Statement parseStatement() {
        return null;
    }
    
    private Statement parseIterationStatement() {
        
        match(Token.TokenType.WHILE);
        match(Token.TokenType.OPEN_PAREN);
        Expression condition = parseExpression();
        match(Token.TokenType.CLOSE_PAREN);
        Statement result = parseStatement();
        
        
        return new IterationStatement(condition, result);
    }
    
    private Statement parseReturnStatement() throws Exception {
        match(Token.TokenType.RETURN);
        Expression returnExpr;
        
        if (inSet(Expression.FIRST)) {
            returnExpr = parseExpression();
            
        } else if (match(Token.TokenType.SEMICOLON)) {
            returnExpr = null;
            
        } else {
            // error
            throw new Exception("yeah");
        }
        
        Statement returnStatement = new ReturnStatement(returnExpr);
        return returnStatement;
    }
    
    private Expression parseExpression() {
        
        TokenType nextType = scan.viewNextToken().getType();
        
        if (nextType == Token.TokenType.OPEN_PAREN) {
            
        } else if (nextType == Token.TokenType.NUM) {
            
        } else if (nextType == Token.TokenType.ID) {
            
        } else {
            // error
        }
        
        return null;
    }
    
    private Expression[] parseArguments() {
        ArrayList<Expression> argsList = new ArrayList<>();
        
        TokenType curTokenType = scan.viewNextToken().getType();
        final TokenType CLOSE_PAREN = Token.TokenType.CLOSE_PAREN;
        final TokenType COMMA = Token.TokenType.COMMA;
        
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
    
    private ArrayList<Expression> parseArgs() {
        
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
            
        } else if (Expression.inFollow(nextType)) {
            
            
        } else {
            // ERRO
        }
        
        return args;
    }
    
    private boolean match(Token.TokenType toMatch) {
        //TODO
        return true;
    }
    
    private boolean inSet(Token.TokenType[] set) {
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
        System.out.println("Hey");
    }
    
}
