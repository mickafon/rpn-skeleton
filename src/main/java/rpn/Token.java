package rpn;

import java.util.ArrayList;

public class Token {


    public static ArrayList<String>  refineExpression(String expression) {

        String [] roughExpression = expression.split("\\s+");
        ArrayList<String> refinedExpression = new ArrayList<>();

        for( String value : roughExpression ) {

            if( isDouble(value) ||
                Operator.isKnowSymbol(value) ) {

                refinedExpression.add(value);
            }
        }

        return refinedExpression;
    }

    /**
     * a bit ouglyyy
     * */
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
