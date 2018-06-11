package rpn;

import rpn.ConcretObserve.*;
import rpn.Event.*;
import rpn.Res.Operator;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CLI  {

    private Orchestrator orchestrator;

    public CLI(){
        this.orchestrator = new Orchestrator();

        Operation operation = new Operation();
        rpn.ConcretObserve.Token token = new Token();
        ResultDisplayer resultDisplayer = new ResultDisplayer();

        orchestrator.addObserver(operation);
        orchestrator.addObserver(token);
        orchestrator.addObserver(resultDisplayer);
    }


    public static final void main(String[] args) {

        String expression = Stream.of(args).collect(Collectors.joining(" "));
        System.out.println("About to evaluate '" + expression + "'");

        CLI cli = new CLI();
        cli.process(expression);

        double result = cli.getOrchestratorResult();
        System.out.println("> " + result);
    }

    public void process(String expression){


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

    /**
     * this method is defined for the cases where you need
     * to recover final value for unit tests
     */
    public Double getOrchestratorResult(){
        return this.orchestrator.getResult();
    }

}
