package Calculator;

import Calculator.BasicCalculator;
import Calculator.FieldCalculator;

import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        BasicCalculator basicCalc=new BasicCalculator();
        FieldCalculator fieldCalc=new FieldCalculator();
        boolean cont=true;
        int choose, choose2;
        Scanner read = new Scanner(System.in);


        while(cont)
        {
            System.out.println("1. Basic Calculator. ");
            System.out.println("2. Field Calculator. ");
            System.out.println("3. Exit. ");
            choose = read.nextInt();

            switch(choose)
            {
                case 1:
                {
                    System.out.println("1. Sum.");
                    System.out.println("2. Difference.");
                    System.out.println("3. Multiplication.");
                    System.out.println("4. Division.");
                    System.out.println("5. Pow.");
                    System.out.println("6. Sqrt.");
                    choose2=read.nextInt();

                    switch(choose2)
                    {
                        case 1:
                        {
                            System.out.println("Enter the first number");
                            double a=read.nextDouble();
                            System.out.println("Enter the second number");
                            double b=read.nextDouble();
                            System.out.println("Sum: " + basicCalc.calculateSum(a,b));
                            break;
                        }

                        case 2:
                        {
                            System.out.println("Enter the first number");
                            double a=read.nextDouble();
                            System.out.println("Enter the second number");
                            double b=read.nextDouble();
                            System.out.println("Difference: " + basicCalc.calculateDifference(a,b));
                            break;
                        }

                        case 3:
                        {
                            System.out.println("Enter the first number");
                            double a=read.nextDouble();
                            System.out.println("Enter the second number");
                            double b=read.nextDouble();
                            System.out.println("Multiplication: " + basicCalc.calculateMultiplication(a,b));
                            break;
                        }

                        case 4:
                        {
                            System.out.println("Enter the first number");
                            double a=read.nextDouble();
                            System.out.println("Enter the second number");
                            double b=read.nextDouble();
                            try
                            {
                                System.out.println("Division: " + basicCalc.calculateDivision(a, b));
                            }
                            catch(java.lang.IllegalArgumentException e)
                            {
                                System.out.println(e.getMessage());
                            }
                            break;
                        }

                        case 5:
                        {
                            System.out.println("Enter the first number");
                            double a=read.nextDouble();
                            System.out.println("Enter the second number");
                            double b=read.nextDouble();
                            System.out.println("Pow: " + basicCalc.calculatePow(a,b));
                            break;

                        }

                        case 6:
                        {
                            System.out.println("Enter the number:");
                            double a=read.nextDouble();
                            try
                            {
                                System.out.println("Sqrt: " + basicCalc.calculateSqrt(a));
                            }
                            catch (java.lang.IllegalArgumentException e)
                            {
                                System.out.println(e.getMessage());
                            }
                            break;
                        }

                        default:
                        {
                            System.out.println("Choose between 1 and 6.");
                            break;

                        }
                    }
                    break;
                }

                case 2:
                {
                    System.out.println("1. Square.");
                    System.out.println("2. Circle.");
                    System.out.println("3. Triangle.");
                    System.out.println("4. Rectangle.");

                    choose2=read.nextInt();

                    switch(choose2)
                    {
                        case 1:
                        {
                            System.out.println("Enter a: ");
                            double a=read.nextDouble();
                            try
                            {
                                fieldCalc.calculateSquare(a);
                            }
                            catch (java.lang.IllegalArgumentException e)
                            {
                                System.out.println(e.getMessage());
                            }
                            break;
                        }

                        case 2:
                        {
                            System.out.println("Enter r: ");
                            double r=read.nextDouble();
                            try
                            {
                                fieldCalc.calculateCircle(r);
                            }
                            catch (java.lang.IllegalArgumentException e)
                            {
                                System.out.println(e.getMessage());
                            }
                            break;
                        }

                        case 3:
                        {
                            System.out.println("Enter a and h: ");
                            double a=read.nextDouble();
                            double h=read.nextDouble();
                            try
                            {
                                fieldCalc.calculateTriangle(a,h);
                            }
                            catch (java.lang.IllegalArgumentException e)
                            {
                                System.out.println(e.getMessage());
                            }
                            break;
                        }

                        case 4:
                        {
                            System.out.println("Enter a and b: ");
                            double a=read.nextDouble();
                            double b=read.nextDouble();
                            try
                            {
                                fieldCalc.calculateRectangle(a,b);
                            }
                            catch (java.lang.IllegalArgumentException e)
                            {
                                System.out.println(e.getMessage());
                            }
                            break;
                        }

                        default:
                        {
                            System.out.println("Choose between 1 and 4.");
                            break;
                        }

                    }
                    break;
                }

                case 3:
                {
                    cont=false;
                    break;
                }

                default:
                {
                    System.out.println("Choose between 1 and 3.");
                    break;
                }
            }
        }
    }
}
