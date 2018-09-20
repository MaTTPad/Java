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




public class ExtendedSystemCache extends SystemCache {

    public ExtendedSystemCache() {
    }


    void exportCSV(String path) throws IOException {
        this.exportCSV(new File(path));
        //final String[] header = new String[]{"Parameters", "Result"};

//        ICsvListWriter listWriter = new CsvListWriter(new FileWriter(path), CsvPreference.STANDARD_PREFERENCE);
//        // listWriter.write(header);
//        for (Map.Entry<Parameter, Double> entry : getCache().entrySet()) {
//            double[] tempTab = new double[entry.getKey().getValues().length];
//            String toWrite = new String();
//            for (int i = 0; i < entry.getKey().getValues().length; i++) {
//                toWrite = toWrite + " " + entry.getKey().getValues()[i];
//                //toWrite = toWrite + String.valueOf(entry.getKey().getValues()[i]);
//                //toWrite = toWrite + " ";
//            }
//            listWriter.write(toWrite, entry.getValue());
//        }
//        listWriter.close();
    }

    void exportCSV(File file) throws IOException {

        try (OutputStream stream = new FileOutputStream(file)) {
            this.exportCSV(stream);
        }

//        final String[] header = new String[]{"Parameters", "Result"};
//
//        ICsvListWriter listWriter = new CsvListWriter(new FileWriter(file), CsvPreference.STANDARD_PREFERENCE);
//        try{
//
//            listWriter.write(header);
//            for (Map.Entry<Parameter, Double> entry : getCache().entrySet()) {
//                double[] tempTab = new double[entry.getKey().getValues().length];
//                String toWrite = new String();
//                for (int i = 0; i < entry.getKey().getValues().length; i++) {
//                    toWrite = toWrite + " " + entry.getKey().getValues()[i];
//                    //toWrite = toWrite + String.valueOf(entry.getKey().getValues()[i]);
//                    //toWrite = toWrite + " ";
//                }
//                listWriter.write(toWrite, entry.getValue());
//            }
//        }
//        finally
//        {        listWriter.close();
//        }

    }

    void exportCSV(OutputStream stream) throws IOException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(stream, "UTF-8"));

        try {
            for (Map.Entry<Parameter, Double> entry : getCache().entrySet()) {
                double[] tempTab = new double[entry.getKey().getValues().length];
                String toWrite = new String();
                for (int i = 0; i < entry.getKey().getValues().length; i++) {
                    toWrite = toWrite + " " + entry.getKey().getValues()[i];
                    //toWrite = toWrite + String.valueOf(entry.getKey().getValues()[i]);
                    //toWrite = toWrite + " ";
                }
                toWrite += "," + entry.getValue();
                out.write(toWrite);
                out.newLine();
            }
        } finally {
            out.close();
        }


/*
        for (Map.Entry<Parameter, Double> entry : getCache().entrySet()) {
            double[] tempTab = new double[entry.getKey().getValues().length];
            String toWrite = new String();
            for (int i = 0; i < entry.getKey().getValues().length; i++) {
                toWrite = toWrite + " " + entry.getKey().getValues()[i];
            }
            toWrite+=","+entry.getValue();
           // oos.writeObject(toWrite);
            stream.(toWrite);
            stream.write('\n');

        }
        stream.close();

*/
    }


    void importCSV(String path) throws IOException {

        this.importCSV(new File(path));

        //        String SAMPLE_CSV_FILE_PATH = path;
//
//        Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
//        CSVReader csvReader = new CSVReader(reader);
//
//        String[] nextRecord;
//        while ((nextRecord = csvReader.readNext()) != null) {
//            // System.out.println("Parameters : " + nextRecord[0]);
//            String[] splitArray = nextRecord[0].split("\\s+");
//            //  System.out.println("Result : " + nextRecord[1]);
//            //   System.out.println("==========================");
//            double[] valuesToMap = new double[splitArray.length - 1];
//            for (int i = 1; i < splitArray.length; i++)
//                valuesToMap[i - 1] = Double.parseDouble(splitArray[i]);
//            put(valuesToMap, Double.parseDouble(nextRecord[1]));
//        }
    }

    void importCSV(File file) throws IOException {
        try (InputStream stream = new FileInputStream(file)) {
            this.importCSV(stream);
        }

//        FileReader in = new FileReader(file);
//        Reader reader = new BufferedReader(in);
//        CSVReader csvReader = new CSVReader(reader);
//        String[] nextRecord;
//        while ((nextRecord = csvReader.readNext()) != null) {
//            String[] splitArray = nextRecord[0].split("\\s+");
//            double[] valuesToMap = new double[splitArray.length - 1];
//            for (int i = 1; i < splitArray.length; i++)
//                valuesToMap[i - 1] = Double.parseDouble(splitArray[i]);
//            put(valuesToMap, Double.parseDouble(nextRecord[1]));
//        }
    }

    void importCSV(InputStream stream) throws IOException {
        Reader reader = new InputStreamReader(stream);
        CSVReader csvReader = new CSVReader(reader);
        String[] nextRecord;

        try {
            while ((nextRecord = csvReader.readNext()) != null) {
                String[] splitArray = nextRecord[0].split("\\s+");
                double[] valuesToMap = new double[splitArray.length - 1];
                for (int i = 1; i < splitArray.length; i++)
                    valuesToMap[i - 1] = Double.parseDouble(splitArray[i]);
                put(valuesToMap, Double.parseDouble(nextRecord[1]));
            }
        } finally {
            csvReader.close();
        }

        size=getCache().size();
    }


    void serialize(String path) throws IOException

    {

        this.serialize(new File(path));

//        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
//        out.writeObject(this);
//        out.close();

    }

    void serialize(File file) throws IOException {
        try (OutputStream stream = new FileOutputStream(file)) {
            this.serialize(stream);
        }


//        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
//        out.writeObject(this);
//        out.close();
    }


    void serialize(OutputStream stream) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(stream);
        out.writeObject(this);
        out.close();
    }

    void deserialize(InputStream stream) throws IOException, ClassNotFoundException {
       // showMap();
        ObjectInputStream read = new ObjectInputStream(stream);
        ExtendedSystemCache serialized = null;
        try {
            serialized = (ExtendedSystemCache) read.readObject();
            //serialized.showMap();
        } finally {
            read.close();
        }
    }

    void deserialize(String path) throws IOException, ClassNotFoundException {
        this.deserialize(new File(path));

        /// /        showMap();
//        ObjectInputStream read = new ObjectInputStream(new FileInputStream(path));
//        ExtendedSystemCache serialized=null;
//        serialized = (ExtendedSystemCache) read.readObject();
//        serialized.showMap();
//        read.close();

    }

    void deserialize(File file) throws IOException, ClassNotFoundException {

        try (InputStream stream = new FileInputStream(file)) {
            this.deserialize(stream);
        }

//        showMap();
//        ObjectInputStream read = new ObjectInputStream(new FileInputStream(file));
//        ExtendedSystemCache serialized=null;
//        serialized = (ExtendedSystemCache) read.readObject();
//        serialized.showMap();
//        read.close();

    }

    void save(String path) throws IOException
    {

        this.save(new File(path));

//        int sizeOfMap=getCache().size();
//
//        int []help=new int[sizeOfMap];
//
//
//        DataOutputStream a=new DataOutputStream(new FileOutputStream(path));
//        a.writeDouble(sizeOfMap); //zapisany rozmiar mapy do pliku na 1szym bicie
//        int j=0;
//       //1 i 2
//        for (Map.Entry<Parameter, Double> entry : getCache().entrySet()) {
//            double[] tempTab = new double[entry.getKey().getValues().length];
//            for (int i = 0; i < entry.getKey().getValues().length; i++) {
//                 //a.writeDouble(entry.getKey().getValues()[i]);
//                 tempTab[i]=entry.getKey().getValues()[i];
//                 //quantity++;
//            }
//
//            help[j]= entry.getKey().getValues().length; //rozmiary poszczególnych elementów
//            a.writeDouble(help[j]);
//            for(int y=0;y<tempTab.length;y++)
//            {
//                a.writeDouble(tempTab[y]); //elementy
//            }
//            j=j+1;
//            a.writeDouble(entry.getValue()); //zapisywanie wartości
  //      }

    }

    void save( File file ) throws IOException
    {
        try(OutputStream stream=new FileOutputStream(file))
        {
            this.save(stream);
        }
    }


    void save (OutputStream stream) throws IOException //     DataOutputStream stream=new DataOutputStream(new FileOutputStream(path));
    {
        int sizeOfMap=getCache().size();

        int []help=new int[sizeOfMap];


        DataOutputStream a=new DataOutputStream(stream);
        a.writeDouble(sizeOfMap); //zapisany rozmiar mapy do pliku na 1szym bicie
        int j=0;
        //1 i 2
        for (Map.Entry<Parameter, Double> entry : getCache().entrySet()) {
            double[] tempTab = new double[entry.getKey().getValues().length];
            for (int i = 0; i < entry.getKey().getValues().length; i++) {
                //a.writeDouble(entry.getKey().getValues()[i]);
                tempTab[i]=entry.getKey().getValues()[i];
                //quantity++;
            }

            help[j]= entry.getKey().getValues().length; //rozmiary poszczególnych elementów
            a.writeDouble(help[j]);
            for(int y=0;y<tempTab.length;y++)
            {
                a.writeDouble(tempTab[y]); //elementy
            }
            j=j+1;
            a.writeDouble(entry.getValue()); //zapisywanie wartości
        }


    }

    void load( String path ) throws IOException
    {
        this.load(new File(path));
//
//        DataInputStream b=new DataInputStream(new FileInputStream(path));
//        double rozmiarMapy=b.readDouble();
//
//        for(int i=0;i<rozmiarMapy;i++)
//        {
//            double a=b.readDouble(); //rozmiar poszczegolnego elementu
//            double []tablica=new double[(int) a];
//            for(int j=0;j<a;j++)
//            {
//                tablica[j]=b.readDouble();
//
//            }
//            //i teraz dodajemy
//            this.put(tablica,b.readDouble());
//
//        }
    }


    void load(File file) throws IOException
    {
        try (InputStream stream=new FileInputStream(file))
        {
            this.load(stream);
        }
    }


    void load(InputStream stream) throws IOException
    {
        DataInputStream b=new DataInputStream(stream);
        double mapSize=b.readDouble();

        for(int i=0;i<mapSize;i++)
        {
            double a=b.readDouble(); //rozmiar poszczegolnego elementu
            double []array=new double[(int) a];
            for(int j=0;j<a;j++)
            {
                array[j]=b.readDouble();

            }
            //teraz dodajemy
            this.put(array,b.readDouble());

        }
    }
}
