package rpn.Event;


import rpn.Operator;


public class OperationEvent implements IEvent {

    private Operator operator;

    public OperationEvent(String value){
        this.operator = Operator.fromSymbol(value);
    }

    @Override
    public void setValue(String value) {
        this.operator = Operator.fromSymbol(value);
    }

    @Override
    public Operator getValue() {
        return this.operator;
    }

}
