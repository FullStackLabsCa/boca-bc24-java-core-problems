package problems.junit;
import mathematicsproblem.Calculator.Calculator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


public class ParmetrizedCalculator {
    Calculator calculator = new Calculator();
    //additon
    @ParameterizedTest
    @CsvSource({
            "2, 3, 5",
            "2, -3, -1"
    })

    public void add(int operator1, int operator2, int result){
        assertEquals(result,calculator.add(operator1,operator2));
    }
    // subtraction
    @ParameterizedTest
    @CsvSource({
            "2, 3, -1",
            "12, 3, 9"
    })

    public void subtract(int operator1, int operator2, int result){
        assertEquals(result,calculator.subtract(operator1,operator2));
    }
    // multiplication
    @ParameterizedTest
    @CsvSource({
            "2, 3, 6",
            "8, -3, -24"
    })

    public void multiply(int operator1, int operator2, int result){
        assertEquals(result,calculator.multiply(operator1,operator2));
    }

    //division
    @ParameterizedTest
    @CsvSource({
            "12, 3, 4",
            "2, 1, 2"
    })
    public void divide(int operator1, int operator2, int result){
        assertEquals(result,calculator.divison(operator1,operator2));
    }
    ArithmeticException e = assertThrows(ArithmeticException.class, () -> {
        calculator.divide(10, 0);
        assertEquals("error cant divided by zero",e.getMessage());
    });


}
