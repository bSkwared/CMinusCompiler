public class Token {
	
	public enum TokenType{
		
		/* Keyword Tokens */
		INT_TOKEN,
		VOID_TOKEN,
		WHILE_TOKEN,
		IF_TOKEN,
		ELSE_TOKEN,
		RETURN_TOKEN,
		
		/* Character Tokens */
		ADD_TOKEN,
		SUB_TOKEN,
		MULT_TOKEN,
		DIV_TOKEN,
		LESSTHAN_TOKEN,
		LESSTHANEQUAL_TOKEN,
		GREATERTHAN_TOKEN,
		GREATERTHANEQUAL_TOKEN,
		EQUAL_TOKEN,
		NOTEQUAL_TOKEN,
		ASSIGN_TOKEN,
		SEMICOLON_TOKEN,
		COMMA_TOKEN,
		LEFTPAREN_TOKEN,
		RIGHTPAREN_TOKEN,
		LEFTBRACKET_TOKEN,
		RIGHTBRACKET_TOKEN,
		LEFTBRACE_TOKEN,
		RIGHTBRACE_TOKEN,
		STARTCOMMENT_TOKEN,
		ENDCOMMENT_TOKEN,
		
		/* Other Tokens */
		ID_TOKEN,
		NUM_TOKEN,
		
		WHITESPACE_TOKEN,
		EOF_TOKEN
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
}
