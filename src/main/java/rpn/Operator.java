package rpn;

import java.util.HashMap;
import java.util.Map;

public enum Operator {

    /**
     * this complex enumeration class define values and override
     * method operate for each one
     * */

    PLUS("+") {

        @Override
        public double operate( double left, double right){
            return left + right;
        }
    },
    MINUS("-") {

        @Override
        public double operate( double left, double right){
            return left - right;
        }
    },
    TIMES("*") {

        @Override
        public double operate( double left, double right){
            return left * right;
        }
    },
    DIVIDE("/") {

        @Override
        public double operate( double left, double right){
            if( right != 0)
                return left / right;
            else throw new ArithmeticException("You CANNOT divide by zero mofo");
        }
    };

    final String symbol;
    static final Map symbolsMap = new HashMap();

    Operator(String symbol ) {
        this.symbol = symbol;
    }


    public abstract double operate( double right, double left );

    private String getSymbol(){
        return this.symbol;
    }

    /** self generated map, which contains Operators instantiations
     *  with corresponding symbol. This permit to reach a known operator by symbol
     *  and use its operate method
     * */
    static {
        for (Operator operator : values()){
            symbolsMap.put(operator.getSymbol(), operator);
        }
    }

    /**
     * this return value from enumeration list corresponding to the
     * symbol in parameter
     * */
    public static Operator fromSymbol(String symbol ){
        return (Operator) symbolsMap.get( symbol);
    }

    /**
     * verify if symbol in parameter is known in this list or not
     * */
    public static boolean isKnowSymbol(String symbol){
        if(fromSymbol(symbol) != null) return true;
        return false;
    }

    public static void main(String[] args) {
        Operator operator = Operator.fromSymbol("-");

        operator.operate(1.1,2.2);
    }
}
