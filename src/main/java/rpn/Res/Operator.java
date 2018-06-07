package rpn.Res;

import java.util.HashMap;
import java.util.Map;

/**
 * Operator Tool
 */
public enum Operator {

    /**
     * this complex enumeration class define values and override
     * method operate for each one
     * */

    PLUS("+"/*, new Stack<>()*/) {

        @Override
        public double operate( double left, double right){
            return left + right;
        }
    },
    MINUS("-"/*, new Stack<>()*/) {

        @Override
        public double operate( double left, double right){
            return left - right;
        }
    },
    TIMES("*"/*, new Stack<>()*/) {

        @Override
        public double operate( double left, double right){
            return left * right;
        }
    },
    DIVIDE("/"/*, new Stack<>()*/) {

        @Override
        public double operate( double left, double right){
            if( right != 0)
                return left / right;
            else throw new ArithmeticException("You CANNOT divide by zero mofo");
        }
    };

    final String symbol;
    //final Stack<Double> stack;

    static final Map symbolsMap = new HashMap();

    Operator(String symbol/*, Stack<Double> income*/) {
        this.symbol = symbol;
        //this.stack = income;
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

}
