package rpn.Event;

/**
 * Result Event
 * define an event behaviour for Result
 */
public class ResultEvent implements IEvent {

    private String value;

    public ResultEvent() {
        this.value = "";
    }

    @Override
    public void setValue( String value ) {
        this.value = value;
    }

    @Override
    public Object getValue() {
        return this.value;
    }
}
