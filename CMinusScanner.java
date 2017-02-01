import java.io.*;
import java.util.*;

public class CMinusScanner implements Scanner{
	
	private BufferedReader inFile;
	private Token nextToken;
	
	public CMinusScanner(BufferedReader file){
		inFile = file;
		nextToken = scanToken();		
	}
	
	public Token getNextToken(){
		Token returnToken = nextToken;
		if(nextToken.getType() != Token.TokenType.EOF_TOKEN){
			nextToken = scanToken();
		}
		return returnToken;
	}
	public Token viewNextToken(){
		return nextToken;
	}
	
	private Token scanToken(){
		/* this is where our code goes */
		
		return new Token(Token.TokenType.EOF_TOKEN);
	}
	
	public static void main(String[] args){
		/* Test the program here */
		
		File f_1 = "test_01.cm";
		File f_2 = "test_02.cm";
		
		Scanner s_1 = new CMinusScanner(f_1);
		Scanner s_2 = new CMinusScanner(f_2);
	}
}
