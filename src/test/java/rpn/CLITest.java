package rpn;

import com.sun.scenario.effect.Offset;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static rpn.CLI.evaluate;

public class CLITest {

    @Test
    public void should_evaluate_single_digit_constant() {
        String expected = String.valueOf(5);
        assertThat(evaluate("5")).isEqualTo(expected);
    }

    @Test
    public void should_evaluate_multiple_digits_constant() {
        String expected = String.valueOf(17);
        assertThat(evaluate("17")).isEqualTo(expected);
    }

    @Test
    public void should_evaluate_simple_addition() {
        String expected = String.valueOf(22.0);
        assertThat(evaluate("17 5 +")).isEqualTo(expected);
    }

    @Test
    public void should_evaluate_more_complex_addition() {
        String expected = String.valueOf(10.0);
        assertThat(evaluate("2 3 5 + +")).isEqualTo(expected);
    }

    @Test
    public void should_evaluate_simple_soustraction() {
        String expected = String.valueOf(12.0);
        assertThat(evaluate("17 5 -")).isEqualTo(expected);
    }

    @Test
    public void should_evaluate_more_complex_soustraction() {
        String expected = String.valueOf(1.0);
        assertThat(evaluate("10 2 7 + -")).isEqualTo(expected);
    }

    @Test
    public void should_evaluate_simple_multiplication() {
        String expected = String.valueOf(85.0);
        assertThat(evaluate("17 5 *")).isEqualTo(expected);
    }

    @Test
    public void should_evaluate_more_complex_multiplication() {
        String expected = String.valueOf(140.0);
        assertThat(evaluate("10 2 7 * *")).isEqualTo(expected);
    }

    @Test
    public void should_evaluate_simple_division() {
        String expected = String.valueOf(200.0);
        assertThat(evaluate("100 5 /")).isEqualTo(expected);
    }

    @Test
    public void should_evaluate_more_complex_division() {
        String expected = String.valueOf(25.0);
        assertThat(evaluate("10 2 5 / /")).isEqualTo(expected);
    }

    @Test(expected = ArithmeticException.class)
    public void should_not_evaluate_division_by_zero() {
        evaluate("4 0 /");
    }


    @Test
    public void should_evaluate_operation_with_decimal(){
        String expected = String.valueOf(4.0);
        assertThat(evaluate("2.1 1.9 +")).isEqualTo(expected);
    }

    @Test
    public void should_concat_numbers_when_no_operande(){
        assertThat(evaluate("7 2 - 3 4")).isEqualTo("5.0 3.0 4.0 ");
    }
}