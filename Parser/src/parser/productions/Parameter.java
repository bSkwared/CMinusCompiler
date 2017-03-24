/**
 * @author Blake Lasky and Timothy Smith
 * @version 1.0
 * File: Parameter.java
 * Created: March 2017
 *
 * Description: This file provides an a Parameter class
 * 				to use with the CMinusParser
 */

package parser.productions;

public class Parameter {
    
    private String id;
    private boolean isArray;
    
    public Parameter(String i, boolean isArr) {
        id = i;
        isArray = isArr;
    }

    public void print(String cur, String indent) {
        System.out.print("int " + id);
		
	if(isArray) {
            System.out.print("[]");
        }
    }
}
