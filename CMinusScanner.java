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
}
