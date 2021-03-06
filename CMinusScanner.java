import java.io.*;

/**
 * @author Timothy Smith and Blake Lasky
 * @version 1.0
 * File: CMinusScanner.java
 * Created: Jan 2017
 *
 * Description: This class provides a Scanner implementation which works for
 *              the C- language.
 */

public class CMinusScanner implements Scanner{
	
	private BufferedReader inFile;
	private Token nextToken;

    private char c;
	
	private enum State{
		START,		
		NUM,		
		ID,		
		INCOMMENT,		
		FORWARD_SLASH,		
		EXCLAMATION,
		ASTERISK,
		LESSTHAN,		
		GREATERTHAN,		
		EQUAL,		
		DONE
	}
	
	public CMinusScanner(BufferedReader file){
		inFile = file;
        consumeNextChar();
		nextToken = scanToken();
	}
	
	public Token getNextToken(){
		Token returnToken = nextToken;
		if(nextToken.getType() != Token.TokenType.EOF){
			nextToken = scanToken();
		}
		return returnToken;
	}
	public Token viewNextToken(){
		return nextToken;
	}
	
	private Token scanToken(){
		/* this is where our code goes */
		Token currToken = new Token(Token.TokenType.ERROR);
		String tokenStr = "";
		State state = State.START;
		
		// return EOF if there is no more input
		if(c == '\0') return new Token(Token.TokenType.EOF);
		
		while(state != State.DONE){
			
			// loop through characters until a token is found
			switch(state){
				case START:
					// Digits
					if(Character.isDigit(c)){
						state = State.NUM;
						tokenStr += c;
					} 
					// Letters
					else if(Character.isLetter(c)){
						state = State.ID;
						tokenStr += c;
					}
					// Whitespace
					else if(Character.isWhitespace(c)){
						/* do nothing */
					}
					// Special Characters
					else{
						switch(c){
							case '+':
								state = State.DONE;
								currToken = new Token(Token.TokenType.ADD);
								break;
							case '-':
								state = State.DONE;
								currToken = new Token(Token.TokenType.SUB);
								break;
							case ';':
								state = State.DONE;
								currToken = new Token(Token.TokenType.SEMICOLON);
								break;
							case ',':
								state = State.DONE;
								currToken = new Token(Token.TokenType.COMMA);
								break;
							case '(':
								state = State.DONE;
								currToken = new Token(Token.TokenType.OPEN_PAREN);
								break;
							case ')':
								state = State.DONE;
								currToken = new Token(Token.TokenType.CLOSE_PAREN);
								break;
							case '[':
								state = State.DONE;
								currToken = new Token(Token.TokenType.OPEN_BRACKET);
								break;
							case ']':
								state = State.DONE;
								currToken = new Token(Token.TokenType.CLOSE_BRACKET);
								break;
							case '{':
								state = State.DONE;
								currToken = new Token(Token.TokenType.OPEN_BRACE);
								break;
							case '}':
								state = State.DONE;
								currToken = new Token(Token.TokenType.CLOSE_BRACE);
								break;
							case '*':
								state = State.DONE;
								currToken = new Token(Token.TokenType.MULT);
								break;
							case '>':
								state = State.GREATERTHAN;
								break;
							case '<':
								state = State.LESSTHAN;
								break;
							case '/':
								state = State.FORWARD_SLASH;
								break;
							case '!':
								state = State.EXCLAMATION;
								break;
							case '=':
								state = State.EQUAL;
								break;
							case '\0':
								state = State.DONE;
								currToken = new Token(Token.TokenType.EOF);
								break;
							default:
								state = State.DONE;
								currToken = new Token(Token.TokenType.ERROR);
								break;
						}
					}
					
					// eat that delicious character
					consumeNextChar();
					break;
					
				case NUM:
					if(Character.isDigit(c)){
						// keep the same state
						tokenStr += c;
						consumeNextChar();
					} else if(Character.isLetter(c)){
						// ERROR
						state = State.DONE;
						currToken = new Token(Token.TokenType.ERROR);
						consumeNextChar();
					} else{
						state = State.DONE;
						currToken = new Token(Token.TokenType.NUM, Integer.valueOf(tokenStr));
					}
					
					break;
					
				case ID:
					if(Character.isLetter(c)){
						// keep the same state
						tokenStr += c;
						consumeNextChar();
					} else if(Character.isDigit(c)){
						// ERROR
						state = State.DONE;
						currToken = new Token(Token.TokenType.ERROR);
						consumeNextChar();
					} else{
						state = State.DONE;
						// look to see if we match a keyword
						switch(tokenStr){
							case "else":
								currToken = new Token(Token.TokenType.ELSE);
								break;
							case "if":
								currToken = new Token(Token.TokenType.IF);
								break;
							case "int":
								currToken = new Token(Token.TokenType.INT);
								break;
							case "return":
								currToken = new Token(Token.TokenType.RETURN);
								break;
							case "void":
								currToken = new Token(Token.TokenType.VOID);
								break;
							case "while":
								currToken = new Token(Token.TokenType.WHILE);
								break;
							default:
								currToken = new Token(Token.TokenType.ID, tokenStr);
								break;
						}						
					}					
					break;
				
				case FORWARD_SLASH:
					// /* is a comment
					if(c == '*'){
						state = State.INCOMMENT;
						consumeNextChar();
					}
					// / is division
					else {
						state = State.DONE;
						currToken = new Token(Token.TokenType.DIV);
					}
					break;
					
				case EXCLAMATION:
					// != is NOT EQUAl
					if(c == '='){
						state = State.DONE;
						currToken = new Token(Token.TokenType.NOT_EQUAL);
						consumeNextChar();
					}
					// ! is error
					else {
						state = State.DONE;
						currToken = new Token(Token.TokenType.ERROR);
					}
					break;
					
				case LESSTHAN:
					// <= 
					if(c == '='){
						state = State.DONE;
						currToken = new Token(Token.TokenType.LTE);
						consumeNextChar();
					}
					// <
					else {
						state = State.DONE;
						currToken = new Token(Token.TokenType.LT);
					}
					break;
					
				case GREATERTHAN:
					// >=
					if(c == '='){
						state = State.DONE;
						currToken = new Token(Token.TokenType.GTE);
						consumeNextChar();
					}
					// >
					else {
						state = State.DONE;
						currToken = new Token(Token.TokenType.GT);
					}
					break;
					
				case EQUAL:
					// ==
					if(c == '='){
						state = State.DONE;
						currToken = new Token(Token.TokenType.EQUAL);
						consumeNextChar();
					}
					// =
					else {
						state = State.DONE;
						currToken = new Token(Token.TokenType.ASSIGN);
					}
					break;
					
				case INCOMMENT:
					// if we get a * the next character could end comment
					if(c == '*'){
						state = State.ASTERISK;
					}
					// EOF
					else if(c == '\0'){
						state = State.DONE;
						currToken = new Token(Token.TokenType.ERROR);
					}
					
					consumeNextChar();
					break;
					
				case ASTERISK:
					// end comment
					if(c == '/'){
						state = State.DONE;
						currToken = new Token(Token.TokenType.COMMENT);
					}
					// EOF
					else if(c == '\0'){
						state = State.DONE;
						currToken = new Token(Token.TokenType.ERROR);
					}
					// we are back in regular comment-zone
					else if(c != '*'){
						state = State.INCOMMENT;
					} 
					
					consumeNextChar();
					break;
					
				case DONE:
					/* This shouldn't happen */
					System.exit(666);
					break;
			}
		}		
		
		return currToken;
	}
	
	private void consumeNextChar(){
		try {
			c = (char) inFile.read();
            if (c == (char) -1) {
                c = '\0';
            }
		} catch (IOException ex) {
			System.exit(1);
		}
	}
	
	public static void main(String[] args) throws IOException{
		/* Test the program here */
		
		File f_1 = new File("test_01.cm");
		File f_2 = new File("test_02.cm");
		
		BufferedReader r_1 = new BufferedReader(new FileReader(f_1));
		BufferedReader r_2 = new BufferedReader(new FileReader(f_2));
		
		Scanner s_1 = new CMinusScanner(r_1);
		Scanner s_2 = new CMinusScanner(r_2);
		
		System.out.println("Testing File #1 (test_01.cm)");
		Token t_1 = s_1.getNextToken();
		while(t_1.getType() != Token.TokenType.EOF){
			System.out.println(t_1.toString());
			t_1 = s_1.getNextToken();
		}
		
		System.out.println("\nTesting File #2 (test_02.cm)");
		Token t_2 = s_2.getNextToken();
		while(t_2.getType() != Token.TokenType.EOF){
			System.out.println(t_2.toString());
			t_2 = s_2.getNextToken();
		}
	}
}
