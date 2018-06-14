package rpn;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CLITest {
    
    @Test
    public void should_evaluate_single_digit_constant() {
        String expression = "5";
        assertThat(CLI.evaluate(expression)).isEqualTo(5.0);
    }

    @Test
    public void should_evaluate_multiple_digits_constant() {
        String expression = "17";
        assertThat(CLI.evaluate(expression)).isEqualTo(17.0);
    }

    @Test
    public void should_evaluate_simple_addition() {
        String expression = "17 5 +";
        assertThat(CLI.evaluate(expression)).isEqualTo(22.0);
    }

    @Test
    public void should_evaluate_more_complex_addition() {
        String expression = "2 3 5 + +";
        assertThat(CLI.evaluate(expression)).isEqualTo(10.0);
    }

    @Test
    public void should_evaluate_simple_substraction() {
        String expression = "17 5 -";
        assertThat(CLI.evaluate(expression)).isEqualTo(12.0);
    }

    @Test
    public void should_evaluate_more_complex_substraction() {
        String expression = "10 2 7 + -";
        assertThat(CLI.evaluate(expression)).isEqualTo(1.0);
    }

    @Test
    public void should_evaluate_simple_multiplication() {
        String expression = "17 5 *";
        assertThat(CLI.evaluate(expression)).isEqualTo(85.0);
    }

    @Test
    public void should_evaluate_more_complex_multiplication() {
        String expression = "10 2 7 * *";
        assertThat(CLI.evaluate(expression)).isEqualTo(140.0);
    }

    @Test
    public void should_evaluate_simple_division() {
        String expression = "100 5 /";
        assertThat(CLI.evaluate(expression)).isEqualTo(20.0);
    }

    @Test
    public void should_evaluate_more_complex_division() {
        String expression = "10 2 5 / /";
        assertThat(CLI.evaluate(expression)).isEqualTo(25.0);
    }

    @Test(expected = ArithmeticException.class)
    public void should_not_evaluate_division_by_zero() {
        CLI.evaluate("4 0 /");
    }


    @Test
    public void should_evaluate_operation_with_decimal(){
        String expression = "2.1 1.9 +";
        assertThat(CLI.evaluate(expression)).isEqualTo(4.0);
    }

    @Test(expected = ArithmeticException.class)
    public void should_concat_numbers_when_no_operande(){
        CLI.evaluate("7 2 - 3 4");
    }

    @Test
    public void should_evaluate_operation_with_negatives(){
        String expression = "10 -5 +";
        assertThat(CLI.evaluate(expression)).isEqualTo(5.0);
    }

    @Test
    public void should_evaluate_operation_with_negatives_and_decimals(){
        String expression = "10.3 -5 +";
        assertThat(CLI.evaluate(expression)).isEqualTo(5.3);
    }

    @Test
    public void should_ignore_when_operators_exceed(){
        String expression = "10.3 -5 + -";
        assertThat(CLI.evaluate(expression)).isEqualTo(5.3);
    }

    @Test
    public void should_clean_and_calculate_when_fancy_expression(){
        String expression = "10.3 -5 + aaaz    2 +";
        assertThat(CLI.evaluate(expression)).isEqualTo(7.3);
    }

}