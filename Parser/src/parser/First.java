/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0 File: First.java Created: March 2017
 *
 * Description: This file provides us with the first sets for all relevant
 * productions in our CMinusGrammar
 */
package parser;

import parser.scanner.Token.TokenType;

public class First {

    public static TokenType[] Program = {TokenType.VOID,
                                         TokenType.INT};

    public static TokenType[] relop = {TokenType.GT,
                                       TokenType.GTE,
                                       TokenType.LT,
                                       TokenType.LTE,
                                       TokenType.EQUAL,
                                       TokenType.NOT_EQUAL};

    public static TokenType[] addop = {TokenType.ADD,
                                       TokenType.SUB};

    public static TokenType[] mulop = {TokenType.MULT,
                                       TokenType.DIV};

    public static TokenType[] Statement = {TokenType.OPEN_BRACE,
                                           TokenType.IF,
                                           TokenType.WHILE,
                                           TokenType.RETURN,
                                           TokenType.OPEN_PAREN,
                                           TokenType.NUM,
                                           TokenType.ID};

    public static TokenType[] ExpressionStatement = {TokenType.OPEN_PAREN,
                                                     TokenType.NUM,
                                                     TokenType.ID};

    public static TokenType[] CompoundStatement = {  TokenType.OPEN_BRACE};

    public static TokenType[] SelectionStatement = { TokenType.IF};

    public static TokenType[] IterationStatement = { TokenType.WHILE};

    public static TokenType[] ReturnStatement = {    TokenType.RETURN};

    public static TokenType[] Expression = {         TokenType.OPEN_PAREN,
                                                     TokenType.NUM,
                                                     TokenType.ID};

    public static TokenType[] ExpressionPrime = {    TokenType.EQUAL,
                                                     TokenType.NUM,
                                                     TokenType.ID};

    public static TokenType[] SimpleExpressionPrime = {TokenType.ADD,
                                                       TokenType.SUB,
                                                       TokenType.MULT,
                                                       TokenType.DIV,
                                                       TokenType.LTE,
                                                       TokenType.GTE,
                                                       TokenType.LT,
                                                       TokenType.GT,
                                                       TokenType.EQUAL,
                                                       TokenType.NOT_EQUAL};

    public static TokenType[] VarDeclaration = {TokenType.INT};
}
