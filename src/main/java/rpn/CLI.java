package rpn;

import rpn.ConcretObserve.Orchestrator;
import rpn.Event.OperationEvent;
import rpn.Event.ResultEvent;
import rpn.Event.TokenEvent;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CLI {

    private String expression;
    private Orchestrator orchestrator;

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

    public void process(){

        for(String value : expression.split("\\s+")) {


            if(Operator.isKnowSymbol(value)){
                orchestrator.setEvent(new OperationEvent(value));
            }
            else {
                orchestrator.setEvent(new TokenEvent(value));
            }
        }

        orchestrator.setEvent(new ResultEvent());
    }
}
