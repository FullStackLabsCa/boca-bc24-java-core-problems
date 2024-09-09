package problems.junit;
import org.junit.Before;
import org.junit.Test;
import stringproblems.Calculator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class BasicCalculator {
    @Before

    @Test
    public void add(){ // test for addition
        Calculator calculator= new Calculator();
        assertEquals(8, calculator.add(4,4));
        assertEquals(8.8,calculator.add(4.2,4.2));
        assertEquals(-10,calculator.add(-14,4));
    }
   @Test
   public void subtract(){
        Calculator calculator = new Calculator();
        assertEquals(2,calculator.subtract(5,3));
       assertEquals(-4,calculator.subtract(6,10));
   }
   @Test
    public void divide(){
        Calculator calculator = new Calculator();
        assertEquals(4,calculator.divide(20,5));
       assertEquals(5,calculator.divide(10,2));
   }
   @Test
    public void multiplication(){
        Calculator calculator = new Calculator();
       assertEquals(100,calculator.multiply(20,5));
       assertEquals(-10,calculator.multiply(2,-5));
   }
   @Test
    public void dividebyZero(){
       Calculator calculator = new Calculator();
       assertThrows("Error: Second operator can't be zero", calculator.divide(10,0));
   }

}
