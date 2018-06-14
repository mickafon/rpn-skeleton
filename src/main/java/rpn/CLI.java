package rpn;

import rpn.ConcretObserve.*;
import rpn.Event.*;
import rpn.Res.Operator;
import rpn.Res.Tokenizer;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CLI  {

    public CLI(){ }


    public static final void main(String[] args) {

        String expression = Stream.of(args).collect(Collectors.joining(" "));
        System.out.println("About to evaluate '" + expression + "'");

        double result = CLI.evaluate(expression);
        System.out.println("> " + result);
    }

    static double evaluate(String expression){

        Tokenizer tokenizer = new Tokenizer(expression);
        return tokenizer.process();
    }

}
