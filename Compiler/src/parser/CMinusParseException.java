/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0
 * File: CMinusParserException.java
 * Created: March 2017
 *
 * Description: This file provides the Exception to use with our 
 * 				CMinusParser
 */

package parser;

public class CMinusParseException extends Exception {
    
    private int lineNumber;
    
    public CMinusParseException(String msg, int line) {
        super(msg);
        lineNumber = line;
    }
    
    public int getLineNumber() {
        return lineNumber;
    }
}
