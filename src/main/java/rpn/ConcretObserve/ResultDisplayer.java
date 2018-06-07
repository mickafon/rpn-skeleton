package rpn.ConcretObserve;

import rpn.Event.IEvent;
import rpn.Event.ResultEvent;
import rpn.Observe.Observable;
import rpn.Observe.Observer;

import java.util.Stack;

import static java.lang.Math.round;

public class ResultDisplayer implements Observer {

    private Double result;
    private Stack<Double> stack;

    public ResultDisplayer(){
        this.result = 0d;
    }

    @Override
    public void observableUpdate(Observable observable) {

        if(observable instanceof Orchestrator){
            Orchestrator orchestrator = (Orchestrator) observable;
            IEvent event = orchestrator.getEvent();

            if(event instanceof ResultEvent){
                stack = orchestrator.getStack();

                if(stack.size() == 1){
                    result = round( stack.peek() * 100.0 ) / 100.0 ;
                    stack.pop();
                }
                else{

                    String exception = "";

                    while( stack.size() > 0 ) {

                        exception = stack.peek().toString() + " " + exception;
                        stack.pop();
                    }

                    throw new ArithmeticException(exception);

                }
            }
        }

    }

    public Double getResult() {
        return result;
    }
}
