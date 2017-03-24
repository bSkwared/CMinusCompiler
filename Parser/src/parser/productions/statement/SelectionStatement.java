/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0 File: SelectionStatement.java Created: March 2017
 *
 * Description: This file provides an implemented Statement class called
 * SelectionStatement to use with the CMinusParser
 */
package parser.productions.statement;

import parser.productions.expression.Expression;

public class SelectionStatement extends Statement {

    private Expression condition;
    private Statement thenStatement;
    private Statement elseStatement;

    public SelectionStatement(Expression cond, Statement then, Statement els) {
        condition = cond;
        thenStatement = then;
        elseStatement = els;
    }

    @Override
    public void print(String cur, String indent) {
        System.out.print(cur + "if (");

        if (condition != null) {
            System.out.println("");
            condition.print(cur + indent, indent);
        }

        System.out.println(")");

        if (thenStatement != null) {
            System.out.println("");
            thenStatement.print(cur + indent, indent);
        }

        if (elseStatement != null) {
            System.out.println("");
            System.out.println(cur + "else");
            elseStatement.print(cur + indent, indent);
        }
    }
}
