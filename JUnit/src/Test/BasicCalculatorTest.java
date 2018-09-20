package Test;
import Calculator.BasicCalculator;
import Calculator.FieldCalculator;
import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)

public class BasicCalculatorTest {

    double x,y,z;
    private BasicCalculator basicCalculatorTester;
    static BufferedWriter out;
    double expected=0;


    @BeforeClass
    public static void onceExecutedBeforeAll() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        String fileName = "logFileBasic" + timeStamp +".txt";
        File newFile = new File(fileName);
        out=new BufferedWriter(new FileWriter(newFile));
        System.out.println("@BeforeClass: Test basic started.");

    }

    @Rule
    public TestWatcher watchman= new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            try {
                out.write("Test failed: ");
                out.write(String.valueOf(description));
                out.write(String.valueOf(e));
                out.newLine();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        @Override
        protected void succeeded(Description description) {
            try {
                out.write("Test passed: ");
                out.write(String.valueOf(description));
                out.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };


    @AfterClass
    public static void onceExecutedAfterAll() throws IOException {
        out.write("Test ended.");
        out.close();
        System.out.println("@AfterClass: Test basic ended.");

    }

    @Before
    public void executedBeforeEachTest() {
        basicCalculatorTester = new BasicCalculator();
    }


    @After
    public void executedAfterEachTest(){
        basicCalculatorTester=null;
        expected=0;
    }

    @Test
    public void sumShouldBeCorrect()
    {
        BasicCalculator calc=new BasicCalculator();
        double result=calc.calculateSum(x,y);
        assertEquals(z,result,0.0);

    }

    @Test
    public void differenceShouldBeCorrect()
    {
        assertEquals("10-1 must be 9",9, basicCalculatorTester.calculateDifference(10, 1),0.00);
        assertEquals("101-1 must be 100",100, basicCalculatorTester.calculateDifference(101, 1),0.00);
        expected=9999-9999;
        assertEquals("9999-9999 must be 0",expected, basicCalculatorTester.calculateDifference(9999, 9999),0.00);
    }

    @Test
    public void divisionShouldBeCorrect()
    {
        expected=10/2;
        assertEquals("10/2 must be 5",expected, basicCalculatorTester.calculateDivision(10, 2),0.00);
        assertEquals("100/100 must be 1",1, basicCalculatorTester.calculateDivision(100,100),0.00);
        assertEquals("12/3 must be 4",4, basicCalculatorTester.calculateDivision(12, 3),0.00);
    }

    @Test
    public void multiplicationShouldBeCorrect()
    {
        assertEquals("10 x 2 must be 20",20, basicCalculatorTester.calculateMultiplication(10, 2),0.00);
        expected=100*100;
        assertEquals("100 x 100 must be 10000",expected, basicCalculatorTester.calculateMultiplication(100,100),0.00);
        assertEquals("12 x 3 must be 36",36, basicCalculatorTester.calculateMultiplication(12, 3),0.00);
    }

    @Test
    public void multiplicationOfZeroShouldReturnZero() {
        assertEquals("10 x 0 must be 0",0, basicCalculatorTester.calculateMultiplication(10, 0),0.00);
        assertEquals("0 x 5 must be 0",0, basicCalculatorTester.calculateMultiplication(0, 5),0.00);
        assertEquals("0 x 0 must be 0",0, basicCalculatorTester.calculateMultiplication(0, 0),0.00);
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void divisionFirstOfZeroShouldThrowIllegalArgumentException() {
        basicCalculatorTester.calculateDivision(1,0);
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void divisionSecondOfZeroShouldThrowIllegalArgumentException() {
        basicCalculatorTester.calculateDivision(3,0);

    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void divisionThirdOfZeroShouldThrowIllegalArgumentException() {
        basicCalculatorTester.calculateDivision(4,0);
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void sqrtFirstOfNegativeNumberShouldThrowIllegalArgumentException()
    {
        basicCalculatorTester.calculateSqrt(-2);
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void sqrtSecondOfNegativeNumberShouldThrowIllegalArgumentException()
    {
        basicCalculatorTester.calculateSqrt(-12);

    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void sqrtOfThirdNumberShouldThrowIllegalArgumentException()
    {

        basicCalculatorTester.calculateSqrt(-1312);
    }

    @Test
    public void sqrtShouldBeCorrect() {
        assertEquals("sqrt 4 must be 2",2, basicCalculatorTester.calculateSqrt(4),0.00);
        expected=Math.sqrt(9);
        assertEquals("sqrt 9 must be 3",expected, basicCalculatorTester.calculateSqrt(9),0.00);
        assertEquals("sqrt 3 must be 1.73",1.73, basicCalculatorTester.calculateSqrt(3),0.01);
    }

    @Test
    public void powShouldBeCorrect() {
        assertEquals("3^2 must be 9",9, basicCalculatorTester.calculatePow(3,2),0.00);
        expected=Math.pow(10,3);
        assertEquals("10^3 must be 1000",expected, basicCalculatorTester.calculatePow(10,3),0.00);
        assertEquals("9^(1/2) must be 3",3, basicCalculatorTester.calculatePow(9,0.5),0.01);
    }

    public BasicCalculatorTest(double x, double y, double z)
    {
        this.x=x;
        this.y=y;
        this.z=z;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {1, 1, 2},
                {2, 2, 4},
                {8, 2, 10}
        });
    }

}
