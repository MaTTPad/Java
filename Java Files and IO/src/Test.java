import org.junit.*;

import java.io.*;

import static org.junit.Assert.assertEquals;


public class Test {
    static ExtendedSystemCache esc,esc2;
    static ScatterSystem system;
    static double[] input1;
    static double[] input2;
    static double[] input3;
    static double[] input4;
    static double[] input5;

    @BeforeClass
    public static void onceExecutedBeforeAll() throws IOException {
        ScatterSystem system = new ScatterSystem();
        esc=new ExtendedSystemCache();
        esc2=new ExtendedSystemCache();
        input1= new double[]{1.0, 2.0, 3.0};
        input2= new double[]{4.0, 4.5, 5.0};
        input3= new double[]{3.0, 1.5, 1.0};
        input4= new double[]{43.0, 4.5, 3.0};
        input5= new double[]{31.0, 4.3, 5.0, 1.0};

        double result;
        result=system.add(input1);
        esc.put(input1,result);
        result=system.add(input2);
        esc.put(input2,result);
        result=system.add(input3);
        esc.put(input3,result);
        result=system.add(input4);
        esc.put(input4,result);
        result=system.add(input5);
        esc.put(input5,result);
    }

    @AfterClass
    public static void onceExecutedAfterAll(){
        esc=null;
        esc2=null;
    }

    @org.junit.Test
    public void exportAndImportPathMustWorkCorrectly() throws IOException {
        esc.exportCSV("C:\\TestFolder\\csvFileTest.csv");
        esc2.importCSV("C:\\TestFolder\\csvFileTest.csv");

        assertEquals(6,esc2.get(input1),0.00);
        assertEquals(13.5,esc2.get(input2),0.00);
        assertEquals(5.5,esc2.get(input3),0.00);
        assertEquals(50.5,esc2.get(input4),0.00);
        assertEquals(41.3,esc2.get(input5),0.00);

    }

    @org.junit.Test
    public void exportAndImportFileMustWorkCorrectly() throws IOException {
        File f=new File("C:\\TestFolder\\csvFileTest.csv");

        esc.exportCSV(f);
        esc2.importCSV(f);

        assertEquals(6,esc2.get(input1),0.00);
        assertEquals(13.5,esc2.get(input2),0.00);
        assertEquals(5.5,esc2.get(input3),0.00);
        assertEquals(50.5,esc2.get(input4),0.00);
        assertEquals(41.3,esc2.get(input5),0.00);

    }

    @org.junit.Test
    public void exportAndImportStreamMustWorkCorrectly() throws IOException {

        File f=new File("C:\\TestFolder\\csvFileTest.csv");
        OutputStream outputStream = new DataOutputStream(new FileOutputStream(f));
        InputStream inputStream = new DataInputStream(new FileInputStream(f));

        esc.exportCSV(outputStream);
        esc2.importCSV(inputStream);

        assertEquals(6,esc2.get(input1),0.00);
        assertEquals(13.5,esc2.get(input2),0.00);
        assertEquals(5.5,esc2.get(input3),0.00);
        assertEquals(50.5,esc2.get(input4),0.00);
        assertEquals(41.3,esc2.get(input5),0.00);

    }



    @org.junit.Test
    public void saveAndLoadPathMustWorkCorrectly() throws IOException {

        esc.save("C:\\TestFolder\\csvFileBinTest.bin");
        esc2.load("C:\\TestFolder\\csvFileBinTest.bin");

        assertEquals(6,esc2.get(input1),0.00);
        assertEquals(13.5,esc2.get(input2),0.00);
        assertEquals(5.5,esc2.get(input3),0.00);
        assertEquals(50.5,esc2.get(input4),0.00);
        assertEquals(41.3,esc2.get(input5),0.00);

    }


    @org.junit.Test
    public void saveAndLoadFileMustWorkCorrectly() throws IOException {

        File f=new File("C:\\TestFolder\\csvFileBinTest.bin");

        esc.save(f);
        esc2.load(f);

        assertEquals(6,esc2.get(input1),0.00);
        assertEquals(13.5,esc2.get(input2),0.00);
        assertEquals(5.5,esc2.get(input3),0.00);
        assertEquals(50.5,esc2.get(input4),0.00);
        assertEquals(41.3,esc2.get(input5),0.00);

    }
    @org.junit.Test
    public void saveAndLoadStreamMustWorkCorrectly() throws IOException {

        File f=new File("C:\\TestFolder\\csvFileBinTest.bin");
        OutputStream outputStream = new DataOutputStream(new FileOutputStream(f));
        InputStream inputStream = new DataInputStream(new FileInputStream(f));

        esc.save(outputStream);
        esc2.load(inputStream);

        assertEquals(6,esc2.get(input1),0.00);
        assertEquals(13.5,esc2.get(input2),0.00);
        assertEquals(5.5,esc2.get(input3),0.00);
        assertEquals(50.5,esc2.get(input4),0.00);
        assertEquals(41.3,esc2.get(input5),0.00);

    }
}
