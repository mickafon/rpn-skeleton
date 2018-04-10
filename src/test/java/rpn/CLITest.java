package rpn;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static rpn.CLI.evaluate;

public class CLITest {

    @Test
    public void should_evaluate_single_digit_constant() {
        assertThat(evaluate("5")).isEqualTo(5.0);
    }

    @Test
    public void should_evaluate_multiple_digits_constant() {
        assertThat(evaluate("17")).isEqualTo(17.0);
    }

    @Test
    public void should_evaluate_simple_addition() {
        assertThat(evaluate("17 5 +")).isEqualTo(22.0);
    }

    @Test
    public void should_evaluate_more_complex_addition() {
        assertThat(evaluate("2 3 5 + +")).isEqualTo(10.0);
    }

    @Test
    public void should_evaluate_simple_soustraction() {
        assertThat(evaluate("17 5 -")).isEqualTo(12.0);
    }

    @Test
    public void should_evaluate_more_complex_soustraction() {
        assertThat(evaluate("10 2 7 + -")).isEqualTo(1.0);
    }

    @Test
    public void should_evaluate_simple_multiplication() {
        assertThat(evaluate("17 5 *")).isEqualTo(85.0);
    }

    @Test
    public void should_evaluate_more_complex_multiplication() {
        assertThat(evaluate("10 2 7 * *")).isEqualTo(140.0);
    }

    @Test
    public void should_evaluate_simple_division() {
        assertThat(evaluate("100 5 /")).isEqualTo(20.0);
    }

    @Test
    public void should_evaluate_more_complex_division() {
        assertThat(evaluate("10 2 5 / /")).isEqualTo(25.0);
    }

    @Test(expected = ArithmeticException.class)
    public void should_not_evaluate_division_by_zero() {
        evaluate("4 0 /");
    }


    @Test
    public void should_evaluate_operation_with_decimal(){
        assertThat(evaluate("2.1 1.9 +")).isEqualTo(4.0);
    }

    @Test(expected = ArithmeticException.class)
    public void should_concat_numbers_when_no_operande(){
        evaluate("7 2 - 3 4");
    }

    @Test
    public void should_evaluate_operation_with_negatives(){
        assertThat(evaluate("10 -5 +")).isEqualTo(5.0);
    }

    @Test
    public void should_evaluate_operation_with_negatives_and_decimals(){
        assertThat(evaluate("10.3 -5 +")).isEqualTo(5.3);
    }

    @Test
    public void should_ignore_when_operators_exceed(){
        assertThat(evaluate("10.3 -5 + -")).isEqualTo(5.3);
    }

    @Test
    public void should_clean_and_calculate_when_fancy_expression(){
        assertThat(evaluate("10.3 -5 + aaaz    2 +")).isEqualTo(7.3);
    }
}