package Calculator;

public class FieldCalculator {

    public double calculateSquare(double a) throws java.lang.IllegalArgumentException
    {
        if(a<=0)
            throw new java.lang.IllegalArgumentException("Długość boku musi być większa od 0.");

        return a*a;
    }

    public double calculateCircle(double r) throws java.lang.IllegalArgumentException
    {
        if(r<=0)
            throw new java.lang.IllegalArgumentException("Długość promienia musi być większa od 0.");

        return Math.PI*r*r;
    }

    public double calculateTriangle(double a, double h) throws java.lang.IllegalArgumentException
    {
        if(a<=0 || h<=0)
            throw new java.lang.IllegalArgumentException("Długości boku i wysokości muszą być większe od 0.");

        return a*h/2;
    }

    public double calculateRectangle(double a, double b) throws java.lang.IllegalArgumentException
    {
        if(a<=0 || b<=0)
            throw new java.lang.IllegalArgumentException("Długości boków muszą być większe od 0.");

        return a*b;
    }



}
