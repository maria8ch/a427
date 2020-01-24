import java.io.IOException;
import java.lang.Math;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Main
{
    private static class Point
    {
        public Point(double xCoordinate, double yCoordinate)
        {
            this.x = xCoordinate;
            this.y = yCoordinate;
        }

        public double x;
        public double y;
    }

    public static void main(String[] args)
    {
        // Problem 1
        // Part a
        System.out.println("Problem 1");
        float smallestForSubtraction = findSmallestFloatForSubtraction();
        System.out.println("Smallest Float for Subtraction: " + smallestForSubtraction);
        // Part b
        float smallestForAddition = findSmallestFloatForAddition();
        System.out.println("Smallest Float for Addition: " + smallestForAddition);
        // Part c
        float largestFloat = findLargestRepresentableFloat();
        System.out.println("Largest Representable Float (empirically): " + largestFloat + "; and theoretically is: " + Float.MAX_VALUE);
        // Part d
        float smallestFloat = findSmallestRepresentableFloat();
        System.out.println("Smallest Representable Float (empirically): " + smallestFloat + "; and theoretically is " + Float.MIN_VALUE);
        System.out.println("");
        // Problem 2
        System.out.println("Problem 2");
        Point[] problem2result = computeLimitOfFunctionFromProblem2();
        for (Point p : problem2result)
        {
            System.out.println("x = " + p.x + "; y = " + p.y);
        }
        // Problem 3
        System.out.println("Problem 3");
        // Part a
        // Part b
        double x_3b = 0.75;
        double y_3b = linearInterpolator(x_3b);
        System.out.println("x_3b = " + x_3b + "; y_3b = " + y_3b);
        // Part c
        double x_3c = 0.75;
        double y_3c = NevilleInterpolator(x_3c);
        System.out.println("x_3c = " + x_3c + "; y_3c =" + y_3c);
        // Part d
        double x_3d = 0.75d;
        double y_3d = 1.0d / (1.0d + 25.0d*x_3d*x_3d);
        System.out.println("Actual value = " + y_3d);
    }

    //Here I have my functions, in order of question, more or less.


    public static float findSmallestFloatForSubtraction()
    {
        /* For my 1-epsilon =/= 1 expression, max_bad is the 'worst" guess for epsilon (0), with min_good being the lowest
        "acceptable" value for epsilon (0.5 as initial guess). These are my two boundaries, with true epsilon being
        somewhere between. The loop goes between the two and using the midpoint arrives at true epsilon. The loop runs
        while the midpoint exists between the two boundaries. Upon there not being a midpoint between the boundaries,
        aka, it equals one of the boundaries, the loop stops, as it has arrived at the lowest epsilon that the computer
        no longer recognizes as a "number".
        */
        float max_bad = 0.0f;
        float min_good = 0.5f;
        boolean keepRunning = true;
        while (keepRunning)
        {
            float midpoint = (max_bad + min_good) / 2.0f;
            if (midpoint == max_bad || midpoint == min_good)
            {
                keepRunning = false;
                System.err.println("max bad for subtraction: " + max_bad + "; min good for subtraction: " + min_good);
            }
            else
            {
                if (isMidPointGood_1a(midpoint))
                {
                    min_good = midpoint;
                }
                else
                {
                    max_bad = midpoint;
                }
            }
        }

        return min_good;
    }

    public static float findSmallestFloatForAddition()
    {
        /*
        For my 1+epsilon =/= 1 expression, the concept is the same as 1a. Again, max_bad is the 'worst" guess for
        epsilon (0), with min_good being the lowest "acceptable" value for epsilon (0.5 as initial guess). These are
        once againg my two boundaries, with true epsilon being somewhere between. The loop works the same way as 1a,
        and uses the midpoint to arrive at true epsilon. The loop runs while the midpoint exists between the two.
        Upon there not being a midpoint between the boundaries, aka, it equals one of the boundaries, the loop stops,
        as it has arrived at the lowest epsilon that the computer no longer recognizes as a "number".
         */

        float max_bad = 0.0f;
        float min_good = 0.5f;
        boolean keepRunning = true;
        while (keepRunning)
        {
            float midpoint = (max_bad + min_good) / 2.0f;
            if (midpoint == max_bad || midpoint == min_good)
            {
                keepRunning = false;
                System.err.println("max bad for addition: " + max_bad + "; min good for addition: " + min_good);
            }
            else
            {
                if (isMidPointGood_1b(midpoint))
                {
                    min_good = midpoint;
                }
                else
                {
                    max_bad = midpoint;
                }
            }
        }

        return min_good;
    }

    //this boolean determines if the loop for 1a runs or not
    public static boolean isMidPointGood_1a(float midpoint)
    {
        if (1.0f - midpoint != 1.0f)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    //this boolean determines if the loop for 1b runs or not
    public static boolean isMidPointGood_1b(float midpoint)
    {
        if (1.0f + midpoint != 1.0f)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    // To get to infinity, which we don't know what value counts as, we need to take steps that make sense.
    public static float findLargestRepresentableFloat()
    {
        //this determines my first step towards infinity to avoid taking forever to do so. I utilized a power rule of 2.
        float step = 1.0f;
        while (!Float.isInfinite(step * 2.0f))
        {
            step = step * 2.0f;
        }
        float whereAmI = 0.0f;
        while (step > 0.0f)
        {
            /* whereAmI, the current position relative to infinity, is determined by adding the step; if this is
            impossible the step is halved and increasing whereAmI attempted again. If the step becomes zero, it cannot
            advance us to infinity, so the halving stops. Concurrently, whereAmI is compared to whereAmIOld (whereAmI
            before the step was added) and if they are indistinguishable, the loop breaks, and we have found our
            max representable float.
            */
            if (Float.isInfinite(whereAmI + step))
            {
                step = step / 2.0f;
            }
            else
            {
                float whereAmIOld = whereAmI;
                whereAmI += step;
                if (whereAmI == whereAmIOld)
                {
                    break;
                }
            }
        }
        return whereAmI;
    }

    // To find the smallest positive representable float, I am using the same principle as 1c, but power 1/2 instead.
    public static float findSmallestRepresentableFloat()
    {
        /* since we know what 0 is, the smallest positive representable float is between 0 and 1. Having a step of 1/2
        is a reasonable first guess */
        float minRepresentableFloat = 0.5f;

        while (minRepresentableFloat / 2.0f > 0.0f)
        {
            minRepresentableFloat /= 2.0f;
        }

        return minRepresentableFloat;
    }

    /* How these values make sense together-
    A and B's values make sense because according to IEEE 745, single precision accuracy is power E-8. Also, due to the
    nature of binary representation, the number line looks logarithmic rather than linear. There are 4 bits between 0.5
    and 1; 4 bits between 1 and 2. Each bit represents 0.125 before 1 on the line, and 0.25 after 1, so we get different
    values for epsilon depending on which side of 1 we're looking at. C makes sense because of the dynamic range that is
    possible for single float by IEEE- 10^38 is the power limit (this matches) because of the 8 bits allowed for storing
    the power. The largest number that fits in that is 228. 2^228 is roughly 10^38. D makes sense because of
     */


    //Problem 2
    // TODO: write explanation

    public static Point[] computeLimitOfFunctionFromProblem2()
    {
        int count = 4; //TODO: make it 40
        Point[] result = new Point[count];
        double x_start = 1.0E-7;
        double x = x_start;

        for (int i = 0; i < count; i++)
        {
            double y = (1.0d - Math.cos(x)) / (x * x);
            result[i] = new Point(x, y);
            x = x - x_start / count;
        }

        return result;
    }


    //Problem 3

    public static Point[] readTheFileFake()
    {
        Point[] result = new Point[5];
        result[0] = new Point(-1.0, 0.03846154);
        result[1] = new Point(-0.5, 0.13793103);
        result[2] = new Point(0.0, 1.0);
        result[3] = new Point(0.5, 0.13793103);
        result[4] = new Point(1.0, 0.03846154);

        return result;
    }

    public static Point[] readTheFile(String pathToFile)
    {
        List<Point> allPoints = new ArrayList<Point>();
        try
        {
            List<String> lines = Files.readAllLines(Paths.get(pathToFile), StandardCharsets.UTF_8);
            for( String line : lines )
            {
                String[] parts = line.split(",");
                double x = Double.valueOf(parts[0]);
                double y = Double.valueOf(parts[1]);

                allPoints.add(new Point(x,y));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return allPoints.toArray(new Point[1]);
    }
        //TODO: do a plot of the points

    public static double linearInterpolator(double x)
    {
        // read the file
        Point[] dataPoints = readTheFile("/Users/maria/hw1.dat");

        //find the left and right points between which the X is located
        Point leftPoint = null;
        Point rightPoint = null;

        for( int i = 0; i < dataPoints.length - 1; i++)
        {
            if( dataPoints[i].x <= x && x <= dataPoints[i+1].x )
            {
                // we found it!
                leftPoint = dataPoints[i];
                rightPoint = dataPoints[i+1];
                break;
            }
        }

        // Interpolate Y for the given X
        double yright = rightPoint.y;
        double yleft = leftPoint.y;
        double xright = rightPoint.x;
        double xleft = leftPoint.x;

        double tanAlpha = (yright - yleft) / (xright - xleft);
        double dx = xright - xleft;
        double dy = dx * tanAlpha;
        double y_3b = yleft + dy;

        return y_3b;
    }
    private static class Polynomial
    {
        double value;
        int startIndex;
        int endIndex;

        public Polynomial(double value, int startIndex, int endIndex)
        {
            this.value = value;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }
    }

    public static double NevilleInterpolator(double x)
    {
        Point[] points = readTheFile("/Users/maria/hw1.dat");

        Polynomial[] polynomials = new Polynomial[points.length];
        // initialize the polynomials of zero order
        for (int i = 0; i < points.length; i++ )
        {
            polynomials[i] = new Polynomial(points[i].y, i, i);
        }

        // Neville....
        while (polynomials.length > 1)
        {
            Polynomial[] nextLevelPolynomials = new Polynomial[polynomials.length - 1];

            for(int i = 0; i < polynomials.length - 1; i++)
            {
                Polynomial headPolynomial = polynomials[i];
                Polynomial tailPolynomial = polynomials[i+1];

                Point headPoint = points[headPolynomial.startIndex];
                Point tailPoint = points[tailPolynomial.endIndex];
                double headX = headPoint.x;
                double tailX = tailPoint.x;

                Polynomial nextP = new Polynomial( ((x - headX)* tailPolynomial.value - (x - tailX)* headPolynomial.value)/(tailX - headX), headPolynomial.startIndex, tailPolynomial.endIndex);
                nextLevelPolynomials[i] = nextP;
            }

            polynomials = nextLevelPolynomials;
        }

        return polynomials[0].value;
    }


}
