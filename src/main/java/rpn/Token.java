package rpn;

public class Token {


    public static String[] analyseAndTransformExpression(String expression) {

        String [] analyzedExpression = expression.split("/\\s+/g");

        for( String value : analyzedExpression ) {
            // value is double
            //value is in known operator list
        }


        return null;
    }

}
