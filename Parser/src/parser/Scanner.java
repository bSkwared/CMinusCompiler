package parser;

/**
 * @author Timothy Smith and Blake Lasky
 * @version 1.0
 * File: Scanner.java
 * Created: Jan 2017
 *
 * Description: This class provide an interface for a lexer. This scans a
 *              source file and returns Tokens to be used by the parser.
 */

public interface Scanner {
    // Returns and consumes next Token
	public Token getNextToken();

    // Returns next Token, but does NOT consume
	public Token viewNextToken();
}
