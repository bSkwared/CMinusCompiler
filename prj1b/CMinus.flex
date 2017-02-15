/* User code */
import java.io.*;

%%

/* Options and declarations */
%class CMinusLex

%type Token

%{
	public static void main(String[] args) throws IOException{
		/* Test the program here */		
		File f_1 = new File("test_01.cm");
		File f_2 = new File("test_02.cm");
		
		BufferedReader r_1 = new BufferedReader(new FileReader(f_1));
		BufferedReader r_2 = new BufferedReader(new FileReader(f_2));
		
		Scanner s_1 = new CMinusScanner(r_1);
		Scanner s_2 = new CMinusScanner(r_2);
		
		System.out.println("Testing File #1 (test_01.cm)");
		Token t_1 = s_1.getNextToken();
		while(t_1.getType() != Token.TokenType.EOF){
			System.out.println(t_1.toString());
			t_1 = s_1.getNextToken();
		}
		
		System.out.println("\nTesting File #2 (test_02.cm)");
		Token t_2 = s_2.getNextToken();
		while(t_2.getType() != Token.TokenType.EOF){
			System.out.println(t_2.toString());
			t_2 = s_2.getNextToken();
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
