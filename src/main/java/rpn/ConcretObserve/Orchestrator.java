package rpn.ConcretObserve;

import rpn.Event.IEvent;
import rpn.Observe.Observable;
import rpn.Observe.Observer;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Orchestrator is an Observable
 * Each time the state of this object change, every observers subscribed are notified
 */
public class Orchestrator implements Observable {

    private ArrayList<Observer> observers;
    private IEvent event;

    private Stack<Double> stack;
    private Double result;


    public Orchestrator() {

        this.observers = new ArrayList<>();
        this.stack = new Stack<>();
        this.result = 0d;
    }

    @Override
    public void addObserver( Observer observer ) {

        this.observers.add(observer);
    }

    @Override
    public void removeObserver( Observer observer ) {

        this.observers.remove(observer);
    }

    @Override
    public void notifyObserver() {

        for( Observer observer : this.observers ) {
            observer.observableUpdate(this);
        }

    }

    public void setEvent( IEvent event ) {

        this.event = event;
        notifyObserver();
    }

    public IEvent getEvent() {
        return event;
    }

    public void appendStack( Double value ) {
        stack.push(value);
    }

    public Stack<Double> getStack() {
        return stack;
    }

    public void setStack( Stack<Double> stack ) {
        this.stack = stack;
    }

    /**
     * these methods are defined when you need
     * to recover concrete value for unit tests for example
     */

    public Double getResult() {
        return result;
    }

    public void setResult( Double result ) {
        this.result = result;
    }
}
