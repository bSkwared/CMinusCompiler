import java.io.*;

public class CMinusScanner implements Scanner{
	
	private PushbackReader inFile;
	private Token nextToken;
	
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
	
	public CMinusScanner(PushbackReader file){
		inFile = file;
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
		
		if(!hasNextChar()) return new Token(Token.TokenType.EOF);
		
		while(state != State.DONE){
		
			if(!hasNextChar()) return new Token(Token.TokenType.EOF);
			
			char c = viewNextChar();
			
			switch(state){
				case START:
					if(Character.isDigit(c)){
						state = State.NUM;
						tokenStr += c;
					} else if(Character.isLetter(c)){
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
					if(c == '*'){
						state = State.INCOMMENT;
						consumeNextChar();
					} else {
						state = State.DONE;
						currToken = new Token(Token.TokenType.DIV);
					}
					break;
					
				case EXCLAMATION:
					if(c == '='){
						state = State.DONE;
						currToken = new Token(Token.TokenType.NOT_EQUAL);
						consumeNextChar();
					} else {
						state = State.DONE;
						currToken = new Token(Token.TokenType.NOT_EQUAL);
					}
					break;
					
				case LESSTHAN:
					if(c == '='){
						state = State.DONE;
						currToken = new Token(Token.TokenType.LTE);
						consumeNextChar();
					} else {
						state = State.DONE;
						currToken = new Token(Token.TokenType.LT);
					}
					break;
					
				case GREATERTHAN:
					if(c == '='){
						state = State.DONE;
						currToken = new Token(Token.TokenType.GTE);
						consumeNextChar();
					} else {
						state = State.DONE;
						currToken = new Token(Token.TokenType.GT);
					}
					break;
					
				case EQUAL:
					if(c == '='){
						state = State.DONE;
						currToken = new Token(Token.TokenType.EQUAL);
						consumeNextChar();
					} else {
						state = State.DONE;
						currToken = new Token(Token.TokenType.ASSIGN);
					}
					break;
					
				case INCOMMENT:
					if(c == '*'){
						state = State.ASTERISK;
					}
					
					consumeNextChar();
					break;
					
				case ASTERISK:
					if(c == '/'){
						state = State.DONE;
						currToken = new Token(Token.TokenType.COMMENT);
					} else if(c != '*'){
						state = State.INCOMMENT;
					}
					
					consumeNextChar();
					break;
					
				case DONE:
					/* This shouldn't happen */
					System.exit(1);
					break;
			}
		}		
		
		return currToken;
	}
	
	private void consumeNextChar(){
		try {
			int i = inFile.read();
			//return (char) i;
		} catch (IOException ex) {
			System.exit(1);
		}
		
		//return '\0';
	}
	
	private char viewNextChar(){
		try {
			int i = inFile.read();
			inFile.unread(i);
			return (char)i;
		} catch (IOException ex) {
			System.exit(1);
		}		
		return '\0';
	};
	
	private boolean hasNextChar(){
		try {
			int i = inFile.read();
			if(i == -1) return false;
			
			inFile.unread(i);
			return true;
			
		} catch (IOException ex) {
			System.exit(1);
		}
		
		return false;
	}
	
	public static void main(String[] args) throws IOException{
		/* Test the program here */
		
		File f_1 = new File("test_01.cm");
		File f_2 = new File("test_02.cm");
		
		PushbackReader r_1 = new PushbackReader(new FileReader(f_1));
		PushbackReader r_2 = new PushbackReader(new FileReader(f_2));
		
		Scanner s_1 = new CMinusScanner(r_1);
		Scanner s_2 = new CMinusScanner(r_2);
		
		Token t_1 = s_1.getNextToken();
		while(t_1.getType() != Token.TokenType.EOF){
			System.out.println(t_1.getType().toString());
			t_1 = s_1.getNextToken();
		}
	}
}
