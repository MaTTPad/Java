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

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)

public class FieldCalculatorTest {
    private FieldCalculator fieldCalculatorTester;
    static BufferedWriter out;
    double expected=0;
    double x,y,z;

    @BeforeClass
    public static void onceExecutedBeforeAll() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        String fileName = "logFileField" + timeStamp +".txt";
        File newFile = new File(fileName);
        out=new BufferedWriter(new FileWriter(newFile));
        System.out.println("@BeforeClass: Test field started.");

    }

    @Rule
    public TestWatcher watchman= new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            try {
                out.write("Test failed: ");
                out.write(String.valueOf(description));
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
        System.out.println("@AfterClass: Test field ended.");

    }


    @Before
    public void executedBeforeEachTest() {
        fieldCalculatorTester = new FieldCalculator();
    }

    @After
    public void executedAfterEachTest(){
        fieldCalculatorTester=null;
        expected=0;
    }

    @Test
    public void squareFieldShouldBeCorrect()
    {
        assertEquals("Square side 10, field must be 100.",100, fieldCalculatorTester.calculateSquare(10),0.00);
        assertEquals("Square side 12, field must be 144.",144, fieldCalculatorTester.calculateSquare(12),0.00);
        expected=40*40;
        assertEquals("Square side 40, field must be 1600.",expected, fieldCalculatorTester.calculateSquare(40),0.00);
    }


    @Test
    public void circleFieldShouldBeCorrect()
    {
        assertEquals("Circle radius 10, field should be ~314.",314, fieldCalculatorTester.calculateCircle(10),0.2);
        assertEquals("Circle radius 1, field should be ~3.14.",3.14, fieldCalculatorTester.calculateCircle(1),0.2);
        expected=Math.PI*5*5;
        assertEquals("Circle radius 5, field should be ~78.5.",expected, fieldCalculatorTester.calculateCircle(5),0.2);
    }

    @Test
    public void triangleFieldShouldBeCorrect()
    {
        assertEquals("Triangle side 10 and height 10, field should be 50.",50, fieldCalculatorTester.calculateTriangle(10,10),0.00);
        expected=1*10/2;
        assertEquals("Triangle side 1 and height 10, field should be 5.",expected, fieldCalculatorTester.calculateTriangle(1,10),0.00);
        assertEquals("Triangle side 31 and height 13, field should be 201.5.",201.5, fieldCalculatorTester.calculateTriangle(31,13),0.00);
    }

    @Test
    public void rectangleFieldShouldBeCorrect()
    {
        FieldCalculator calc=new FieldCalculator();
        double result=calc.calculateRectangle(x,y);
        assertEquals(z,result,0.0);
    }


    @Test(expected = java.lang.IllegalArgumentException.class)
    public void radiusFirstLowerThanZeroCalculateCircleShouldThrowIllegalArgumentException() {
    fieldCalculatorTester.calculateCircle(-2);
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void radiusSecondLowerThanZeroCalculateCircleShouldThrowIllegalArgumentException() {
        fieldCalculatorTester.calculateCircle(-3);
    }


    @Test(expected = java.lang.IllegalArgumentException.class)
    public void radiusThirdLowerThanZeroCalculateCircleShouldThrowIllegalArgumentException() {
        fieldCalculatorTester.calculateCircle(-5);
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void FirstsideAndHeightLowerThanZeroCalculateTriangleShouldThrowIllegalArgumentException() {
        fieldCalculatorTester.calculateTriangle(-1,10);
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void SecondsideAndHeightLowerThanZeroCalculateTriangleShouldThrowIllegalArgumentException() {
        fieldCalculatorTester.calculateTriangle(-32,-221);
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void ThirdsideAndHeightLowerThanZeroCalculateTriangleShouldThrowIllegalArgumentException() {
        fieldCalculatorTester.calculateTriangle(1,-312);
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void FirstsidessLowerThanZeroCalculateRectangleShouldThrowIllegalArgumentException() {
        fieldCalculatorTester.calculateTriangle(-1,10);
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void SecondsidessLowerThanZeroCalculateRectangleShouldThrowIllegalArgumentException() {
        fieldCalculatorTester.calculateTriangle(-32,-221);
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void ThirdsidessLowerThanZeroCalculateRectangleShouldThrowIllegalArgumentException() {
        fieldCalculatorTester.calculateTriangle(1,-312);
    }


    @Test(expected = java.lang.IllegalArgumentException.class)
    public void sideFirstLowerThanZeroCalculateSquareShouldThrowIllegalArgumentException() {
        fieldCalculatorTester.calculateSquare(-2);

    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void sideSecondLowerThanZeroCalculateSquareShouldThrowIllegalArgumentException() {
        fieldCalculatorTester.calculateSquare(-3);
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void sideThirdLowerThanZeroCalculateSquareShouldThrowIllegalArgumentException() {
        fieldCalculatorTester.calculateSquare(-5);
    }

    public FieldCalculatorTest(double x, double y, double z)
    {
        this.x=x;
        this.y=y;
        this.z=z;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {2, 4, 8},
                {2, 2, 4},
                {8, 2, 16}
        });
    }
}
