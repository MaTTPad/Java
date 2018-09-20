import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SystemCache implements Serializable
{    int size=0;


    public HashMap<Parameter, Double> getCache() {
        return cache;
    }

    public void setCache(HashMap<Parameter, Double> cache) {
        this.cache = cache;
    }

    private HashMap<Parameter, Double> cache = new HashMap<>();

        public void put( double[] input, double output )
        {
            this.cache.put( new Parameter( input ), output );
            size=getCache().size();


        }
        public Double get( double[] input )
        {
            return this.cache.get( new Parameter( input ) );

        }

    void showMap()
    {
        System.out.println("Pokazuję mapę:");
        for (Map.Entry<Parameter, Double> entry : cache.entrySet()) {
            System.out.println("Key = " + get(entry.getKey().getValues()) + ", Value = " + entry.getValue());
        }
    }

        public class Parameter implements Serializable
        {
            private double[] values;
            public Parameter( double[] values )
            {
                this.values = values;
            }
            @Override
            public int hashCode()
            {
                return 31 + Arrays.hashCode( this.values );
            }
            @Override
            public boolean equals( Object obj )
            {
                if ( this == obj )
                    return true;
                if ( obj == null )
                    return false;
                if ( this.getClass() != obj.getClass() )
                    return false;
                Parameter other = (Parameter) obj;
                if ( !Arrays.equals( this.values, other.values ) )
                    return false;
                return true;
            }

            public double[] getValues() {
                return values;
            }
        }
}
