package rpn.ConcretObserve;

import rpn.Event.IEvent;
import rpn.Event.TokenEvent;
import rpn.Observe.Observable;
import rpn.Observe.Observer;

public class Token implements Observer {

    public Token(){ }

    @Override
    public void observableUpdate(Observable observable) {
        if(observable instanceof Orchestrator){
            Orchestrator orchestrator = (Orchestrator) observable;
            IEvent event = orchestrator.getEvent();

            if(event instanceof TokenEvent){
                if(this.isDouble((String) event.getValue())){
                    orchestrator.appendStack(Double.parseDouble((String) event.getValue()));
                }
            }
        }
    }

    private boolean isDouble(String value){
        try {
            Double.parseDouble(value);
            return true;
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
            return false;
        }
    }
}
