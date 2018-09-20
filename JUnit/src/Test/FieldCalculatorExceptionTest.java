package Test;

import Calculator.BasicCalculator;
import Calculator.FieldCalculator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)

public class FieldCalculatorExceptionTest {

    private FieldCalculator fieldCalculatorTester;
    double x;

    @Before
    public void executedBeforeEachTest() {
        fieldCalculatorTester = new FieldCalculator();
    }


    @After
    public void executedAfterEachTest(){
        fieldCalculatorTester=null;
    }

    public FieldCalculatorExceptionTest(double x)
    {
        this.x=x;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {-2},
                {-4},
                {-8}
        });
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void radiusLowerThanZeroCalculateCircleShouldThrowIllegalArgumentException() {
        fieldCalculatorTester.calculateCircle(x);
    }

}
