package rpn.ConcretObserve;

import rpn.Event.IEvent;
import rpn.Event.OperationEvent;
import rpn.Observe.Observable;
import rpn.Observe.Observer;
import rpn.Operator;

import java.util.Stack;

import static java.lang.Math.round;

public class Operation implements Observer {

    private Stack<Double> stack;
    private Operator operator;

    @Override
    public void observableUpdate(Observable observable) {
        if(observable instanceof Orchestrator){
            Orchestrator orchestrator = (Orchestrator) observable;
            IEvent event = orchestrator.getEvent();

            if(event instanceof OperationEvent){
                this.stack = orchestrator.getStack();
                this.operator = ((OperationEvent) event).getValue();

                operate();
                orchestrator.setStack(stack);
            }
        }
    }

    private void operate(){

        double result = 0d;

        if(this.stack.size() > 1) {

            while(stack.size() > 0){

                double right = stack.peek();
                stack.pop();

                double left = stack.peek();
                stack.pop();

                double step = operator.operate(left, right);

                stack.push(step);

            }
        }

        //return round( result * 100.0 ) / 100.0 ;
    }
}
