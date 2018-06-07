package rpn.ConcretObserve;

import rpn.Event.IEvent;
import rpn.Event.OperationEvent;
import rpn.Observe.Observable;
import rpn.Observe.Observer;
import rpn.Res.Operator;

import java.util.Stack;


/**
 * Operation is an Observer
 * Operation subscribe to Orchestrator, and detect its change
 *
 * If Orchestrator event is a OperationEvent, this calculate from stack and from operation event value
 * and complete Orchestrator stack
 */
public class Operation implements Observer {

    private Stack<Double> stack;
    private Operator operator;

    @Override
    public void observableUpdate( Observable observable ) {

        if( observable instanceof Orchestrator ) {

            Orchestrator orchestrator = (Orchestrator) observable;
            IEvent event = orchestrator.getEvent();

            if( event instanceof OperationEvent ) {

                this.stack = orchestrator.getStack();
                this.operator = (Operator) event.getValue();

                operate();
                orchestrator.setStack(stack);
            }
        }
    }

    private void operate() {

        if( this.stack.size() > 1 ) {

            double right = stack.peek();
            stack.pop();

            double left = stack.peek();
            stack.pop();

            double step = operator.operate(left, right);

            stack.push(step);

        }
    }
}
