/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0
 * File: Parser.java
 * Created: March 2017
 *
 * Description: This class provides an interface Parser
 * 				to be implement by CMinusParser
 */

package parser;

import parser.productions.*;

public interface Parser {
    public Program parse() throws Exception;
}
