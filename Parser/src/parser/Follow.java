/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0 File: Follow.java Created: March 2017
 *
 * Description: This file provides us with the follow sets for all relevant
 * productions in our CMinusGrammar
 */
package parser;

import parser.scanner.Token.TokenType;

public class Follow {

    public static TokenType[] Expression = {TokenType.CLOSE_PAREN,
                                            TokenType.SEMICOLON,
                                            TokenType.COMMA,
                                            TokenType.CLOSE_BRACKET};

    public static TokenType[] SimpleExpressionPrime = {TokenType.CLOSE_PAREN,
                                                       TokenType.SEMICOLON,
                                                       TokenType.COMMA,
                                                       TokenType.CLOSE_BRACKET};

    public static TokenType[] AdditiveExpression = {TokenType.CLOSE_PAREN,
                                                    TokenType.SEMICOLON,
                                                    TokenType.COMMA,
                                                    TokenType.CLOSE_BRACKET};

    public static TokenType[] VarCall = {TokenType.CLOSE_PAREN,
                                         TokenType.SEMICOLON,
                                         TokenType.COMMA,
                                         TokenType.CLOSE_BRACKET,
                                         TokenType.ADD,
                                         TokenType.SUB,
                                         TokenType.MULT,
                                         TokenType.DIV,
                                         TokenType.LTE,
                                         TokenType.GTE,
                                         TokenType.LT,
                                         TokenType.GT,
                                         TokenType.EQUAL,
                                         TokenType.NOT_EQUAL};
}
