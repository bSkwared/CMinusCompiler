/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import parser.scanner.Token.TokenType;

/**
 *
 * @author Timothy Smith and Blake Lasky
 */
public class First {
    public static TokenType[] rogram = { TokenType.VOID, 
                                          TokenType.INT };
    
    public static TokenType[] relop = { TokenType.GT,
                                        TokenType.GTE,
                                        TokenType.LT,
                                        TokenType.LTE,
                                        TokenType.EQUAL,
                                        TokenType.NOT_EQUAL};
    
    
    
    public static TokenType[] addop = { TokenType.ADD,
                                        TokenType.SUB };
    
    public static TokenType[] compoundStatement = { TokenType.OPEN_BRACE };
    
    public static TokenType[] statement = { TokenType.OPEN_BRACE,
                                            TokenType.IF,
                                            TokenType.WHILE,
                                            TokenType.RETURN,
                                            TokenType.OPEN_PAREN,
                                            TokenType.NUM,
                                            TokenType.ID };
    
    public static TokenType[] expression = { TokenType.OPEN_PAREN,
                                             TokenType.NUM,
                                             TokenType.ID };
    
    public static TokenType[] expressionPrime = { TokenType.EQUAL,
                                                  TokenType.NUM,
                                                  TokenType.ID };
    
    
    public static TokenType[]  = {  };
    
    public static TokenType[]  = {  };
    
    public static TokenType[]  = {  };
    public static TokenType[]  = {  };
    public static TokenType[]  = {  };
    public static TokenType[]  = {  };
    public static TokenType[]  = {  };
    public static TokenType[]  = {  };
}
