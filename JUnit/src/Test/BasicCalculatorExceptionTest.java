package Test;

import Calculator.BasicCalculator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)

public class BasicCalculatorExceptionTest {
    double x,y;
    private BasicCalculator basicCalculatorTester;


    @Before
    public void executedBeforeEachTest() {
        basicCalculatorTester = new BasicCalculator();
    }


    @After
    public void executedAfterEachTest(){
        basicCalculatorTester=null;
    }

    public BasicCalculatorExceptionTest(double x, double y)
    {
        this.x=x;
        this.y=y;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {1, 0},
                {2, 0},
                {8, 0}
        });
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void divisionOfZeroShouldThrowIllegalArgumentException() {

      basicCalculatorTester.calculateDivision(x,y);
    }

}
