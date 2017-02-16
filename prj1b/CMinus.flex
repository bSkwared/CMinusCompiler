/* User code */
import java.io.*;

%%

/* Options and declarations */
%class CMinusLex

%implements Scanner

%type Token

%function scanToken

%{
    
	public CMinusLex(BufferedReader file){
		this.zzReader = file;
		try{
            nextToken = scanToken();
        } catch (IOException e){
            nextToken = new Token(Token.TokenType.ERROR);
        }
	}
    
    


    private Token nextToken;

    public Token getNextToken() {
        Token returnToken = nextToken;

        if (nextToken.getType() != Token.TokenType.EOF) {
            try {
                nextToken = scanToken();
            } catch (IOException ioe) {
                nextToken = new Token(Token.TokenType.ERROR);
            }
        }

        return returnToken;
    }

    public Token viewNextToken() {
        return nextToken;
    }


	public static void main(String[] args) throws IOException{
		/* Test the program here */		
        if(args.length != 1){
            System.out.println("USAGE: java CMinusLex input_file");
            System.exit(1);
        }
		File f = new File(args[0]);
		
		BufferedReader r = new BufferedReader(new FileReader(f));
		
		CMinusLex s = new CMinusLex(r);
		
		System.out.println("Testing File (" + args[0] + ")");
		Token t = s.getNextToken();
		while(t.getType() != Token.TokenType.EOF && t.getType() != Token.TokenType.ERROR){
			System.out.println(t.toString());
			t = s.getNextToken();
		}
        System.out.println(t.toString());
	}
%}

Identifier = [:jletter:]+
Integer = 0 | [1-9][0-9]*
WhiteSpace = \r|\n|\r\n|[ \t\f]

NumLetErr = [:digit:]+[:jletter:]+
LetNumErr = [:jletter:]+[:digit:]+

LexError = {NumLetErr} | {LetNumErr}

%state COMMENT


%%

<COMMENT> {
    <<EOF>> {return new Token(Token.TokenType.ERROR);}
    "*/"    {yybegin(YYINITIAL); return new Token(Token.TokenType.COMMENT, yytext());}
   .|\n       { }
}

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


    "/*" {yybegin(COMMENT);}

    {LexError} {return new Token(Token.TokenType.ERROR, "illegal identifier");}


	{Integer} {return new Token(Token.TokenType.NUM, Integer.parseInt(yytext()));}
	{Identifier} {return new Token(Token.TokenType.ID, yytext());}
	{WhiteSpace} {}
}

.|\n    { return new Token(Token.TokenType.ERROR); }
<<EOF>> { return new Token(Token.TokenType.EOF  ); }
