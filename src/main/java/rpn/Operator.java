package rpn;

public class Operator {

    private String operator;

    public Operator( String _operator ) {
        this.operator = _operator;
    }

    public long calculate( long left, long right ){

        switch ( operator ) {
            case "+" :
                return this.add(left,right);
            case "-":
                return this.substract(left,right);
            case "*" :
                return this.multiply(left,right);
            case "/" :
                return this.divide(left,right);
        }
        return 0l;
    }

    private long add( long left, long right ){
        return left + right;
    }

    private long substract( long left, long right ){
        return left - right;
    }

    private long multiply( long left, long right ){
        return left * right;
    }

    private long divide( long left, long right ) {

        if( right != 0 )
            return left / right;

        else throw  new ArithmeticException("You CANNOT divide by 0 mf");
    }

}
