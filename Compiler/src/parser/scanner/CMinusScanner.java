package parser.scanner;

import java.io.*;

/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.1 File: CMinusScanner.java Created: January 2017
 *
 * Description: This class provides a Scanner implementation which works for the
 * C- language.
 */
public class CMinusScanner implements Scanner {

	private BufferedReader inFile;
	private Token nextToken;
	private int lineNum;

	private char c;

	private enum State {
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

	public CMinusScanner(String filename) throws IOException {

		inFile = new BufferedReader(new FileReader(filename));
		
		lineNum = 1;
		
		consumeNextChar();
		nextToken = scanToken();
	}

	public Token getNextToken() {
		Token returnToken = nextToken;
		if (nextToken.getType() != Token.TokenType.EOF) {
			nextToken = scanToken();
		}
		return returnToken;
	}

	public Token viewNextToken() {
		return nextToken;
	}

	private Token scanToken() {
		/* this is where our code goes */
		Token currToken = new Token(Token.TokenType.ERROR, lineNum);
		String tokenStr = "";
		State state = State.START;

		// return EOF if there is no more input
		if (c == '\0') {
			return new Token(Token.TokenType.EOF, lineNum);
		}

		while (state != State.DONE) {
			if (c == '\n') {
				lineNum++;
			}
			// loop through characters until a token is found
			switch (state) {
				case START:
					// Digits
					if (Character.isDigit(c)) {
						state = State.NUM;
						tokenStr += c;
					} // Letters
					else if (Character.isLetter(c)) {
						state = State.ID;
						tokenStr += c;
					} // Whitespace
					else if (Character.isWhitespace(c)) {

					} // Special Characters
					else {
						switch (c) {
							case '+':
								state = State.DONE;
								currToken = new Token(Token.TokenType.ADD, lineNum);
								break;
							case '-':
								state = State.DONE;
								currToken = new Token(Token.TokenType.SUB, lineNum);
								break;
							case ';':
								state = State.DONE;
								currToken = new Token(Token.TokenType.SEMICOLON, lineNum);
								break;
							case ',':
								state = State.DONE;
								currToken = new Token(Token.TokenType.COMMA, lineNum);
								break;
							case '(':
								state = State.DONE;
								currToken = new Token(Token.TokenType.OPEN_PAREN, lineNum);
								break;
							case ')':
								state = State.DONE;
								currToken = new Token(Token.TokenType.CLOSE_PAREN, lineNum);
								break;
							case '[':
								state = State.DONE;
								currToken = new Token(Token.TokenType.OPEN_BRACKET, lineNum);
								break;
							case ']':
								state = State.DONE;
								currToken = new Token(Token.TokenType.CLOSE_BRACKET, lineNum);
								break;
							case '{':
								state = State.DONE;
								currToken = new Token(Token.TokenType.OPEN_BRACE, lineNum);
								break;
							case '}':
								state = State.DONE;
								currToken = new Token(Token.TokenType.CLOSE_BRACE, lineNum);
								break;
							case '*':
								state = State.DONE;
								currToken = new Token(Token.TokenType.MULT, lineNum);
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
								currToken = new Token(Token.TokenType.EOF, lineNum);
								break;
							default:
								state = State.DONE;
								currToken = new Token(Token.TokenType.ERROR, "invalid character " + c, lineNum);
								break;
						}
					}

					// eat that delicious character
					consumeNextChar();
					break;

				case NUM:
					if (Character.isDigit(c)) {
						// keep the same state
						tokenStr += c;
						consumeNextChar();
					} else if (Character.isLetter(c)) {
						// ERROR
						state = State.DONE;
						currToken = new Token(Token.TokenType.ERROR, "invalid character " + c + " in NUM", lineNum);
						consumeNextChar();
					} else {
						state = State.DONE;
						currToken = new Token(Token.TokenType.NUM, Integer.valueOf(tokenStr), lineNum);
					}

					break;

				case ID:
					if (Character.isLetter(c)) {
						// keep the same state
						tokenStr += c;
						consumeNextChar();
					} else if (Character.isDigit(c)) {
						// ERROR
						state = State.DONE;
						currToken = new Token(Token.TokenType.ERROR, "invalid digit " + c + " in ID", lineNum);
						consumeNextChar();
					} else {
						state = State.DONE;
						// look to see if we match a keyword
						switch (tokenStr) {
							case "else":
								currToken = new Token(Token.TokenType.ELSE, lineNum);
								break;
							case "if":
								currToken = new Token(Token.TokenType.IF, lineNum);
								break;
							case "int":
								currToken = new Token(Token.TokenType.INT, lineNum);
								break;
							case "return":
								currToken = new Token(Token.TokenType.RETURN, lineNum);
								break;
							case "void":
								currToken = new Token(Token.TokenType.VOID, lineNum);
								break;
							case "while":
								currToken = new Token(Token.TokenType.WHILE, lineNum);
								break;
							default:
								currToken = new Token(Token.TokenType.ID, tokenStr, lineNum);
								break;
						}
					}
					break;

				case FORWARD_SLASH:
					// /* is a comment
					if (c == '*') {
						state = State.INCOMMENT;
						consumeNextChar();
					} // / is division
					else {
						state = State.DONE;
						currToken = new Token(Token.TokenType.DIV, lineNum);
					}
					break;

				case EXCLAMATION:
					// != is NOT EQUAl
					if (c == '=') {
						state = State.DONE;
						currToken = new Token(Token.TokenType.NOT_EQUAL, lineNum);
						consumeNextChar();
					} // ! is error
					else {
						state = State.DONE;
						currToken = new Token(Token.TokenType.ERROR, "invalid isolated character !", lineNum);
					}
					break;

				case LESSTHAN:
					// <= 
					if (c == '=') {
						state = State.DONE;
						currToken = new Token(Token.TokenType.LTE, lineNum);
						consumeNextChar();
					} // <
					else {
						state = State.DONE;
						currToken = new Token(Token.TokenType.LT, lineNum);
					}
					break;

				case GREATERTHAN:
					// >=
					if (c == '=') {
						state = State.DONE;
						currToken = new Token(Token.TokenType.GTE, lineNum);
						consumeNextChar();
					} // >
					else {
						state = State.DONE;
						currToken = new Token(Token.TokenType.GT, lineNum);
					}
					break;

				case EQUAL:
					// ==
					if (c == '=') {
						state = State.DONE;
						currToken = new Token(Token.TokenType.EQUAL, lineNum);
						consumeNextChar();
					} // =
					else {
						state = State.DONE;
						currToken = new Token(Token.TokenType.ASSIGN, lineNum);
					}
					break;

				case INCOMMENT:
					// if we get a * the next character could end comment
					if (c == '*') {
						state = State.ASTERISK;
					} // EOF
					else if (c == '\0') {
						state = State.DONE;
						currToken = new Token(Token.TokenType.ERROR, "unclosed comment", lineNum);
					}

					consumeNextChar();
					break;

				case ASTERISK:
					// end comment
					if (c == '/') {
						state = State.START;
					} // EOF
					else if (c == '\0') {
						state = State.DONE;
						currToken = new Token(Token.TokenType.ERROR, "unclosed comment", lineNum);
					} // we are back in regular comment-zone
					else if (c != '*') {
						state = State.INCOMMENT;
					}

					consumeNextChar();
					break;
			}
		}

		return currToken;
	}

	private void consumeNextChar() {
		try {
			c = (char) inFile.read();
			if (c == (char) -1) {
				c = '\0';
			}
		} catch (IOException ex) {
			System.exit(1);
		}
	}

	public static void main(String[] args) throws IOException {
		/* Test the program here */
		if (args.length != 1) {
			System.out.println("USAGE: java CMinusLex input_file");
			System.exit(1);
		}

		Scanner s = new CMinusScanner(args[0]);

		System.out.println("Testing File (" + args[0] + ")");
		Token t = s.getNextToken();
		while (t.getType() != Token.TokenType.EOF && t.getType() != Token.TokenType.ERROR) {
			System.out.println(t.toString());
			t = s.getNextToken();
		}
		System.out.println(t.toString());
	}
}
