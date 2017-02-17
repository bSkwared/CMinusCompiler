import java.io.*;
import java.util.*;

/**
 * @author Timothy Smith and Blake Lasky
 * @version 1.0
 * File: CMinusTest.java
 * Created: Feb 2017
 *
 * Description: This class tests the jflex implementation of the Scanner
 *              against the manual state implementatino of the Scanner. Both of
 *              these implementations are for the C- language.
 */


class CMinusTest {

	public static void main(String[] args) throws IOException {

        // Make sure file names were specified
        if (args.length == 0) {
            System.out.println("USAGE: java CMinusLex input_file [input_file]");
            System.exit(1);
        }
		

        // Set to true if discrepency between manual and jflex imeplementations
        boolean hasError = false;


        // Loop through filenames in command line arguments
        for (String filename : args) {

            // STATE implementation scanning
            Scanner manual = new CMinusScanner(filename);
            ArrayList<Token> manToks = new ArrayList<>();

            // Scan until EOF or error
            Token curToken = manual.getNextToken();
            while (curToken.getType() != Token.TokenType.EOF
                    && curToken.getType() != Token.TokenType.ERROR) {
                
                manToks.add(curToken);
                curToken = manual.getNextToken();
            }


            // JFLEX implementatino scanning
            Scanner jflex  = new CMinusLex(filename);
            ArrayList<Token> lexToks = new ArrayList<>();

            // Scan until EOF or error
            curToken = jflex.getNextToken();
            while (curToken.getType() != Token.TokenType.EOF
                    && curToken.getType() != Token.TokenType.ERROR) {
                
                lexToks.add(curToken);
                curToken = jflex.getNextToken();
            }


            // Check that they both scanned the same number of tokens
            if (manToks.size() == lexToks.size()) {
                
                // Check that tokens are all the same type
                for (int i = 0; i < manToks.size(); ++i) {
                    Token man = manToks.get(i);
                    Token lex = lexToks.get(i);

                    // If they are not the same type, print error
                    if (man.getType() != lex.getType()) {
                        hasError = true;
                        System.out.println("Error in " + filename);
                        System.out.println("Mismatched token types.");
                        System.out.println("manual: " + man.getType());
                        System.out.println("jflex:  " + lex.getType() + '\n');
                    }
                }

            } else {
                // If they did not read the same number of tokens
                hasError = true;
                System.out.println("Error in " + filename);
                System.out.println("Different token counts.");
            }
        }


        // If no errors, print success
        if (!hasError) {
            System.out.println("Success! Both lexers agreed.");

        } else {
            // If errors, print error
            System.out.println("Lexing finished with errors.");
        }
	}
}
