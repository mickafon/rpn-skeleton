package rpn.Event;

public class TokenEvent implements IEvent {

    public Double value;

    public TokenEvent(String value){
        this.value = Double.parseDouble(value);
    }

    @Override
    public void setValue(String value) {
        this.value = Double.parseDouble(value);
    }

    @Override
    public Double getValue() {
        return this.value;
    }
}
