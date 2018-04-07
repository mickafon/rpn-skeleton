package rpn;

import java.util.List;
import java.util.Stack;

public class Operation {

    /** at this level, expression content is refined and clean
     *  it mays only contain double values or known operators
     */

    public static double calculateFromRefinedExpression( List<String> refinedExpression ) {

        Stack<Double> stack = new Stack<>();
        double result = 0d;

        for(String value : refinedExpression) {

            if(Token.isDouble(value)){

                stack.push(Double.parseDouble(value));
            }
            else if(stack.size() > 1) {

                Operator operator = Operator.fromSymbol(value);

                double left = stack.peek();
                stack.pop();

                double right = stack.peek();
                stack.pop();

                double step = operator.operate(left, right);

                stack.push(step);
            }
        }

        /**
         * normal situation, operand and operator number balanced
         * */
        if( stack.size() == 1 ) {
            result = stack.peek();
        }

        /**
         * unnatural situation, operand and operator number not balanced
         * */
        else if( stack.size() > 1 ) {
            String exception = "";

            while( stack.size() > 0 ) {

                exception = stack.peek().toString();
                stack.pop();
            }

            throw new ArithmeticException(exception);
        }

        return result;
    }

}
