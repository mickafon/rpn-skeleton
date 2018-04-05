package rpn;

public enum Operators {

    ONE("+"),
    TWO("-"),
    MULTIPLY("*"),
    DIVIDE("/");

    private final String value;

    Operators( String value ) {
        this.value = value;
    }

    public static Operators findByValue(String value){
        for(Operators constants : values()){
            if(constants.value.equals(value)){
                return constants;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public boolean valueIsInOperators( String c){
        return false;
    }
}
