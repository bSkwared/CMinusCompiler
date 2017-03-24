package parser.scanner;

/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.2 File: Token.java Created: January 2017
 *
 * Description: This class provides Tokens for the C- language. Every element in
 * a valid C- program will be one of the given TokenTypes. 
 * Update: added inSet to see if the given Token is contained in an array
 */
public class Token {

    public enum TokenType {

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
        ERROR;

        public boolean inSet(TokenType[] set) {

            boolean foundType = false;

            for (TokenType firstType : set) {
                if (this == firstType) {
                    foundType = true;
                    break;
                }
            }

            return foundType;
        }
    }

    private TokenType tokenType;
    private Object tokenData;
	private int lineNum;

    public Token(TokenType type, int ln) {
        this(type, null, ln);
    }

    public Token(TokenType type, Object data, int ln) {
        tokenType = type;
        tokenData = data;
		lineNum = ln;
    }

    public Object getData() {
        if (tokenData != null) {
            return tokenData;
        }

        return tokenType.toString();

    }

    public TokenType getType() {
        return tokenType;
    }
	
	public int getLineNum(){
		return lineNum;
	}

    public String toString() {
        return tokenType.toString() + ((tokenData != null) ? ":\t" + tokenData.toString() : "");
    }
}
