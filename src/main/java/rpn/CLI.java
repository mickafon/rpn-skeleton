package rpn;

import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CLI {

    public static final void main(String[] args) {

        String expression = Stream.of(args).collect(Collectors.joining(" "));
        System.out.println("About to evaluate '" + expression + "'");
        String result = old_evaluate(expression); //modify this line to choose correct function
        System.out.println("> " + result);
    }

    static long evaluate(String input){

        if( ( input instanceof String ) &&
            ( !input.isEmpty() ) ) {

            String [] content = ( new Token() ).analyseAndTransformExpression( input );

            if( content.length > 0 ) {

                long result = (new Operation()).calculateFromExpressionAnalyzed(content);

                return result;
            }
        }

        return 0l;

        // token on input : split by space \s+ in array

        // array check content : only int / long AND known operators : delete others

        // array : treat expression stack and operator list
    }



    static String old_evaluate(String expression) {

        if (    expression == null
             || expression == "" ){

            return "";
        }

        Stack<String> stack = new Stack();

        String tmp   = "";
        int posPoint = 0;

        if( isNumeric( expression ) ) { // token expression

            return expression;
        }

        for( int i = 0; i < expression.length(); i++ ) { // treat expression

            char carac = expression.charAt(i);

            if( carac == '.' || carac == ',' ) {

                posPoint = i;
                continue;
            }
            if( carac == ' ' ) {

                if( tmp != "" ) {

                    stack.push(tmp);
                }
                tmp      = "";
                posPoint = 0;
            }
            else{
                if( stack.size() > 1 ) {

                    if( carac == '+'
                        || carac == '-'
                        || carac == '*'
                        || carac == '/' ) {

                        tmp = stack.peek();
                        stack.pop();

                        tmp = calculate( stack.peek(), tmp, carac );

                        stack.pop();
                        stack.push(tmp);
                        tmp = "";

                        continue;
                    }
                }
                if( tmp.equals("") ) {

                    tmp = "0";
                }
                if( posPoint > 0 ) {

                    double value = Double.parseDouble( tmp ) + Character.getNumericValue( carac ) * Math.pow( 10, ( i - posPoint ) * -1);
                    tmp = String.valueOf(value);
                }
                else {

                    double value = Double.parseDouble( tmp ) * Math.pow( 10, i ) + Character.getNumericValue( carac );
                    tmp = String.valueOf( value );
                }
            }
        }
        if( stack.size() == 1 ) {

            return stack.peek();
        }

        stack.push( tmp );
        StringBuffer buf = new StringBuffer();

        for( int i = 0; i < stack.size(); i++ ) {

            buf.append( stack.elementAt( i ) );
            buf.append(" ");
        }

        return buf.toString();
    }

    static String calculate( String str1, String str2, char operation ) {

        double nb1 = Double.parseDouble( str1 );
        double nb2 = Double.parseDouble( str2 );

        switch( operation ) {
            case '+':
                return String.valueOf( nb1 + nb2 );
            case '-':
                return String.valueOf( nb1 - nb2 );
            case '*':
                return String.valueOf( nb1 * nb2 );
            case '/':
                if( nb2 == 0 ) {

                    throw new ArithmeticException( "Cannot divise to 0!!" );
                }
                return String.valueOf( nb1 / nb2 );
        }
        return String.valueOf( 0 );
    }

    private static boolean isNumeric( String expression ) {
        try
        {
            double num = Double.parseDouble( expression );
        }
        catch( NumberFormatException nfe )
        {
            return false;
        }
        return true;
    }
}
