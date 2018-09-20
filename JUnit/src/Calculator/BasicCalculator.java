package Calculator;

public class BasicCalculator {
    public double calculateSum(double a, double b)
    {
        return a+b;
    }

    public double calculateDifference(double a, double b)
    {
        return a-b;
    }

    public double calculateMultiplication(double a, double b)
    {
        return a*b;
    }

    public double calculateDivision(double a, double b) throws java.lang.IllegalArgumentException
    {
        if(b==0) {
            throw new java.lang.IllegalArgumentException("Nie można dzielić przez zero.");
        }
            return a/b;
    }

    public double calculatePow(double a, double b)
    {
        return Math.pow(a,b);
    }

    public double calculateSqrt(double a) throws java.lang.IllegalArgumentException
    {
        if(a<0)
            throw new java.lang.IllegalArgumentException("Nie można pierwiastkować liczby ujemnej.");

        return Math.sqrt(a);
    }


}
