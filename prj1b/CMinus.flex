/* User code */
import java.io.*;

%%

/* Options and declarations */
%class CMinusLex

%type Token

%{
	public static void main(String[] args) throws IOException{
		/* Test the program here */		
        if(args.length != 1){
            System.out.println("USAGE: java CMinusLex input_file");
            System.exit(1);
        }
		File f = new File(args[0]);
		
		BufferedReader r = new BufferedReader(new FileReader(f));
		
		Scanner s = new CMinusScanner(r);
		
		System.out.println("Testing File (" + args[0] + ")");
		Token t = s.getNextToken();
		while(t.getType() != Token.TokenType.EOF){
			System.out.println(t.toString());
			t = s.getNextToken();
		}
	}
%}

Identifier = [:jletter:][:jletter:]*
Integer = [0-9][0-9]*
WhiteSpace = \r|\n|\r\n|[ \t\f]

Comment = "/*" [^*] ~"*/" | "/*" "*"+ "/"

%%

/* Lexical rules */

/* yytext() returns text matched by current rule */
<YYINITIAL> {
	"int"    {return new Token(Token.TokenType.INT);}
	"void"   {return new Token(Token.TokenType.VOID);}
	"while"  {return new Token(Token.TokenType.WHILE);}
	"if"     {return new Token(Token.TokenType.IF);}
	"else"   {return new Token(Token.TokenType.ELSE);}
	"return" {return new Token(Token.TokenType.RETURN);}

	"+"  {return new Token(Token.TokenType.ADD       );}
	"-"  {return new Token(Token.TokenType.SUB       );}
	"*"  {return new Token(Token.TokenType.MULT      );}
	"/"  {return new Token(Token.TokenType.DIV       );}
	"<"  {return new Token(Token.TokenType.LT        );}
	"<=" {return new Token(Token.TokenType.LTE       );}
	">"  {return new Token(Token.TokenType.GT        );}
	">=" {return new Token(Token.TokenType.GTE       );}
	"==" {return new Token(Token.TokenType.EQUAL     );}
	"!=" {return new Token(Token.TokenType.NOT_EQUAL );}
	"="  {return new Token(Token.TokenType.ASSIGN    );}
	";"  {return new Token(Token.TokenType.SEMICOLON );}
	","  {return new Token(Token.TokenType.COMMA     );}

	"("  {return new Token(Token.TokenType.OPEN_PAREN    );}
	")"  {return new Token(Token.TokenType.CLOSE_PAREN   );}
	"["  {return new Token(Token.TokenType.OPEN_BRACKET  );}
	"]"  {return new Token(Token.TokenType.CLOSE_BRACKET );}
	"{"  {return new Token(Token.TokenType.OPEN_BRACE    );}
	"}"  {return new Token(Token.TokenType.CLOSE_BRACE   );}

	{Comment} {return new Token(Token.TokenType.COMMENT);}

	{Integer} {return new Token(Token.TokenType.NUM, Integer.parseInt(yytext()));}
	{Identifier} {return new Token(Token.TokenType.ID, yytext());}
	{WhiteSpace} {}
}
