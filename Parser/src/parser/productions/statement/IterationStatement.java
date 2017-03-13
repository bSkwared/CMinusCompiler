package parser.productions.statement;

import parser.productions.expression.Expression;

/**
 *
 * @author Timothy Smith and Blake Lasky
 */
public class IterationStatement extends Statement {
    Expression condition;
    Statement result;
    
    public IterationStatement(Expression cond, Statement res) {
        condition = cond;
        result = res;
    }
}
