import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import au.com.bytecode.opencsv.CSVReader;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.CsvMapWriter;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.io.ICsvMapWriter;
import org.supercsv.prefs.CsvPreference;

public class Main {
    public static void main(String []args)
    {
        ScatterSystem system = new ScatterSystem();
        ExtendedSystemCache esc=new ExtendedSystemCache();
//        double[] input1={1.0,2.0,3.0};
//        double[] input2={4.0,4.5,5.0,4.5};
//        double result;
//        result=system.add(input1);
//        esc.put(input1,result);
//        result=system.add(input2);
//        esc.put(input2,result);


        //na 4.0
//
//        DataOutputStream a=new DataOutputStream(new FileOutputStream("sciezka"));
//        a.writeDouble(input1[0]);
//        //1
//        //2
//
//        DataInputStream b=new DataInputStream(new FileInputStream("tasamacciszka"))
//                b.readDouble()
//                        b.readDouble()
//

        try {
            //esc.save("C:\\TestFolder\\data.bin");
           esc.load("C:\\TestFolder\\data.bin");

            /*File f=new File("C:\\TestFolder\\csvFile2.csv");
            esc.importCSV(f); //import file
            */

           /* File file = new File("C:\\TestFolder\\csvFile.csv");
            InputStream stream = new DataInputStream(new FileInputStream(file));
            esc.importCSV(stream); //inputstream import
*/

            esc.exportCSV("C:\\TestFolder\\csvFilepobinie.csv"); // export path
           // esc.importCSV("C:\\TestFolder\\csvFile.csv"); // import  path


/*
            File f=new File("C:\\TestFolder\\DEBUG.csv");
            esc.exportCSV(f);

            //export file
*/
 /*
          File file2 = new File("C:\\TestFolder\\csvFile22.csv");
            file2.createNewFile();
            OutputStream stream2 = new DataOutputStream(new FileOutputStream(file2));
            esc.exportCSV(stream2); //export outputstream
*/
            //esc.serialize("Obj.ser");
           // esc.deserialize("Obj.ser");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
