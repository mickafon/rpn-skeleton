package rpn;

import java.util.List;

public class Token {


    public static List<String>  refineExpression(String expression) {

        String [] roughExpression = expression.split("/\\s+/g");
        List<String> refinedExpression = null;


        for( String value : roughExpression ) {

            if( isDouble(value) ||
                Operator.isKnowSymbol(value) ){

                refinedExpression.add(value);
            }
        }

        return refinedExpression;
    }

    /** vely vely ougly code */
    public static boolean isDouble(String value ){
        try {
            Double.parseDouble(value);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

}
