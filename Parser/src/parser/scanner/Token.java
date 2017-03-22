package parser.scanner;

/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.1
 * File: Token.java
 * Created: January 2017
 *
 * Description: This class provides Tokens for the C- language. Every element
 *              in a valid C- program will be one of the given TokenTypes.
 */
public class Token {
	
	public enum TokenType{
		
		/* Keyword Tokens */
		INT,
		VOID,
		WHILE,
		IF,
		ELSE,
		RETURN,
		
		/* Character Tokens */
		ADD,
		SUB,
		MULT,
		DIV,
		LT,
		LTE,
		GT,
		GTE,
		EQUAL,
		NOT_EQUAL,
		ASSIGN,
		SEMICOLON,
		COMMA,
		OPEN_PAREN,
		CLOSE_PAREN,
		OPEN_BRACKET,
		CLOSE_BRACKET,
		OPEN_BRACE,
		CLOSE_BRACE,
		COMMENT,
		
		/* Other Tokens */
		ID,
		NUM,
		EOF,
		ERROR
	}	
	
	private TokenType tokenType;
	private Object tokenData;
	
	public Token(TokenType type){
		this(type, null);
	}
	
	public Token(TokenType type, Object data){
		tokenType = type;
		tokenData = data;
	}
	
	public Object getData() {
		if(tokenData != null) return tokenData;
		
		return tokenType.toString();
		
	}
	public TokenType getType() {return tokenType;}
	
	public String toString(){
		return tokenType.toString() + ((tokenData != null)? ":\t" + tokenData.toString() : "");	
	}
}
