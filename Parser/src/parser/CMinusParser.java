package parser;

import java.io.IOException;
import parser.productions.Program;
import parser.scanner.*;

/**
 *
 * @author Timothy Smith and Blake Lasky
 */
public class CMinusParser implements Parser {

    CMinusScanner scan;
    
    public CMinusParser(String filename) throws IOException {
        scan = new CMinusScanner(filename);
    }
    
    @Override
    public Program parse() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public static void main(String[] args) {
        System.out.println("Hey");
    }
    
}
