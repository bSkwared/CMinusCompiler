/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0 File: VarExpression.java Created: March 2017
 *
 * Description: This file provides an implemented Expression class called
 * VarExpression to use with the CMinusParser
 */
package parser.productions.expression;

public class VarExpression extends Expression {

    private String id;

    private boolean isArray;

    private Expression arrayIndex;

    public VarExpression(String i, Expression index) {
        id = i;
        arrayIndex = index;

        isArray = index != null;
    }

    public VarExpression(String id) {
        this(id, null);
    }

    @Override
    public void print(String cur, String indent) {
        System.out.print(cur + id);
        if (isArray) {
            System.out.println("[");
            arrayIndex.print(cur + indent, indent);
            System.out.print(cur + "]");
        }
    }

    public String getId() {
        return id;
    }
}
