package rpn.Event;

/**
 * Token Event
 * define an event behaviour for Token
 */
public class TokenEvent implements IEvent {

    public Double value;

    public TokenEvent( String value ) {

        try {
            this.value = Double.parseDouble( value );
        }
        catch (Exception exception){
            this.value = null;
        }
    }

    @Override
    public void setValue( String value ) {
        this.value = Double.parseDouble(value);
    }

    @Override
    public Double getValue() {
        return this.value;
    }


}
