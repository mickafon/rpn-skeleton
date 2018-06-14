package rpn.Res;

import rpn.ConcretObserve.*;
import rpn.Event.*;

public class Tokenizer {

    private String expression;
    private Orchestrator orchestrator;

    public Tokenizer(String expression){

        this.expression = expression;
        this.orchestrator = new Orchestrator();

        this.init();
    }

    private void init(){

        Operation operation = new Operation();
        Token token = new Token();
        ResultDisplayer resultDisplayer = new ResultDisplayer();

        this.orchestrator.addObserver(operation);
        this.orchestrator.addObserver(token);
        this.orchestrator.addObserver(resultDisplayer);

    }

    public Double process(){
        for(String value : expression.split("\\s+")) {


            if(Operator.isKnowSymbol(value)){
                this.orchestrator.setEvent(new OperationEvent(value));
            }
            else {
                this.orchestrator.setEvent(new TokenEvent(value));
            }
        }

        this.orchestrator.setEvent(new ResultEvent());

        return this.orchestrator.getResult();
    }
}
