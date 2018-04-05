package rpn;

import java.util.Stack;

public class Operation {

    public static long calculateFromExpressionAnalyzed(String[] content) {

        Stack stack = new Stack();
        int increment;

        for(increment = 0; increment < content.length; increment++ ) {

            if( (Double) Double.parseDouble(content[increment]) instanceof Double ){
                Double number = Double.parseDouble(content[increment]);
                stack.push(number);
            }
            else{

            }
        }

        return 0l;
    }

}
