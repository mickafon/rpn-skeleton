package rpn;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CLI {

    public static final void main(String[] args) {

        String expression = Stream.of(args).collect(Collectors.joining(" "));
        System.out.println("About to evaluate '" + expression + "'");
        double result = evaluate(expression);
        System.out.println("> " + result);
    }

    static double evaluate(String input){

        if( ( input instanceof String ) &&
            ( !input.isEmpty() ) ) {

            ArrayList<String> content = Token.refineExpression( input );

            if( content.size() > 0 ) {

                return Operation.calculateFromRefinedExpression( content );
            }
        }

        return 0d;
    }
}
