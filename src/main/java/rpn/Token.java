package rpn;

import rpn.Event.OperationEvent;
import rpn.Event.ResultEvent;
import rpn.Event.TokenEvent;
import rpn.ConcretObserve.Orchestrator;

import java.util.ArrayList;

public class Token {

    private Orchestrator orchestrator;
    private String expression;

    public Token(String expression, Orchestrator orchestrator){
        this.orchestrator = orchestrator;
        this.expression = expression;
    }

    public static ArrayList<String>  refineExpression(String expression) {

        String [] roughExpression = expression.split("\\s+");
        ArrayList<String> refinedExpression = new ArrayList<>();

        for( String value : roughExpression ) {

            if( isDouble(value) ||
                Operator.isKnowSymbol(value) ) {
                refinedExpression.add(value);
            }
        }

        return refinedExpression;
    }


    /**
     * well ...
     * */
    public static boolean isDouble(String value ){
        try {
            Double.parseDouble(value);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }


}
