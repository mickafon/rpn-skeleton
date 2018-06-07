package rpn.ConcretObserve;

import rpn.Event.*;
import rpn.Observe.*;

import java.util.Stack;
import static java.lang.Math.round;

/**
 * ResultDisplayer Observer
 * ResultDisplayer subscribe to Orchestrator, and detect its change
 *
 * If Orchestrator event is a ResultEvent, this means that operation is terminated
 * and ResultDisplayer verify Orchestrator content and update/format Orchestrator result
 */
public class ResultDisplayer implements Observer {

    private Double result;
    private Stack<Double> stack;

    public ResultDisplayer() {

        this.result = 0d;
    }

    @Override
    public void observableUpdate( Observable observable ) {

        if( observable instanceof Orchestrator ) {

            Orchestrator orchestrator = (Orchestrator) observable;
            IEvent event = orchestrator.getEvent();

            if( event instanceof ResultEvent ) {

                stack = orchestrator.getStack();

                if( stack.size() == 1 ) {

                    result = round( stack.peek() * 100.0 ) / 100.0 ;
                    stack.pop();

                    /**
                     * in this part you define the behaviour when operation is terminated
                     */
                    orchestrator.setResult(result );
                }
                else {

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

}
