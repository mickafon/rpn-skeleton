package rpn;

import java.util.ArrayList;
import java.util.Stack;

import static java.lang.Math.round;

public class Operation {

    /** at this level, expression content is refined and clean
     *  it mays only contain double values or known operators
     */

    public static double calculateFromRefinedExpression( ArrayList<String> refinedExpression ) {

        if(refinedExpression.size() == 1)
            return Double.parseDouble(refinedExpression.get(0));

        Stack<Double> stack = new Stack<>();
        double result = 0d;


        for(String value : refinedExpression) {

            if(Token.isDouble(value)){
                stack.push(Double.parseDouble(value));
            }
            else if(stack.size() > 1) {

                Operator operator = Operator.fromSymbol(value);

                double right = stack.peek();
                stack.pop();

                double left = stack.peek();
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

                exception = stack.peek().toString() + " " + exception;
                stack.pop();
            }

            throw new ArithmeticException(exception);
        }

        return round( result * 100.0 ) / 100.0 ;
    }

}
