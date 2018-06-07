package rpn;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CLITest {

    private CLI cli = new CLI();


    @Test
    public void should_evaluate_single_digit_constant() {
        cli.process("5");
        assertThat(cli.getOrchestratorResult()).isEqualTo(5.0);
    }

    @Test
    public void should_evaluate_multiple_digits_constant() {
        cli.process("17");
        assertThat(cli.getOrchestratorResult()).isEqualTo(17.0);
    }

    @Test
    public void should_evaluate_simple_addition() {
        cli.process("17 5 +");
        assertThat(cli.getOrchestratorResult()).isEqualTo(22.0);
    }

    @Test
    public void should_evaluate_more_complex_addition() {
        cli.process("2 3 5 + +");
        assertThat(cli.getOrchestratorResult()).isEqualTo(10.0);
    }

    @Test
    public void should_evaluate_simple_substraction() {
        cli.process("17 5 -");
        assertThat(cli.getOrchestratorResult()).isEqualTo(12.0);
    }

    @Test
    public void should_evaluate_more_complex_substraction() {
        cli.process("10 2 7 + -");
        assertThat(cli.getOrchestratorResult()).isEqualTo(1.0);
    }

    @Test
    public void should_evaluate_simple_multiplication() {
        cli.process("17 5 *");
        assertThat(cli.getOrchestratorResult()).isEqualTo(85.0);
    }

    @Test
    public void should_evaluate_more_complex_multiplication() {
        cli.process("10 2 7 * *");
        assertThat(cli.getOrchestratorResult()).isEqualTo(140.0);
    }

    @Test
    public void should_evaluate_simple_division() {
        cli.process("100 5 /");
        assertThat(cli.getOrchestratorResult()).isEqualTo(20.0);
    }

    @Test
    public void should_evaluate_more_complex_division() {
        cli.process("10 2 5 / /");
        assertThat(cli.getOrchestratorResult()).isEqualTo(25.0);
    }

    @Test(expected = ArithmeticException.class)
    public void should_not_evaluate_division_by_zero() {
        cli.process("4 0 /");
    }


    @Test
    public void should_evaluate_operation_with_decimal(){
        cli.process("2.1 1.9 +");
        assertThat(cli.getOrchestratorResult()).isEqualTo(4.0);
    }

    @Test(expected = ArithmeticException.class)
    public void should_concat_numbers_when_no_operande(){
        cli.process("7 2 - 3 4");
    }

    @Test
    public void should_evaluate_operation_with_negatives(){
        cli.process("10 -5 +");
        assertThat(cli.getOrchestratorResult()).isEqualTo(5.0);
    }

    @Test
    public void should_evaluate_operation_with_negatives_and_decimals(){
        cli.process("10.3 -5 +");
        assertThat(cli.getOrchestratorResult()).isEqualTo(5.3);
    }

    @Test
    public void should_ignore_when_operators_exceed(){
        cli.process("10.3 -5 + -");
        assertThat(cli.getOrchestratorResult()).isEqualTo(5.3);
    }

    @Test
    public void should_clean_and_calculate_when_fancy_expression(){
        cli.process("10.3 -5 + aaaz    2 +");
        assertThat(cli.getOrchestratorResult()).isEqualTo(7.3);
    }

}