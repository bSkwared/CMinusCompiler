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
	
	public Object getData() {return tokenData;}
	public TokenType getType() {return tokenType;}
	
	public String toString(){
		return tokenType.toString() + ((tokenData != null)? ":\t" + tokenData.toString() : "");	
	}
}
