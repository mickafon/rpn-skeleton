package rpn.ConcretObserve;

import rpn.Event.IEvent;
import rpn.Event.TokenEvent;
import rpn.Observe.Observable;
import rpn.Observe.Observer;

/**
 * Token Observer
 * Token subscribe to Orchestrator, and detect its change
 *
 * If Orchestrator event is a TokenEvent, this operate a treatment on token event value
 * and complete Orchestrator stack only if value is accepted
 */
public class Token implements Observer {

    public Token() { }

    @Override
    public void observableUpdate( Observable observable ) {

        if( observable instanceof Orchestrator ) {

            Orchestrator orchestrator = (Orchestrator) observable;
            IEvent event = orchestrator.getEvent();

            if( event instanceof TokenEvent ) {

                Double value = (Double) event.getValue();

                if( value != null ) {

                    orchestrator.appendStack(value);
                }
            }
        }
    }

}
