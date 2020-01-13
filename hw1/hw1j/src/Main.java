import javax.swing.*;

public class Main
{

    public static void main(String[] args)
    {
        // Problem 1
        // Part a
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
    }

    public static float findSmallestFloatForSubtraction()
    {
        /*for my 1-epsilon =/= 1 expression, max_bad is the 'worst" guess for epsilon (0), with min_good being the lowest
        "acceptable" value for epsilon (0.5 as initial guess). These are my two boundaries, with true epsilon being
        somewhere between. The loop goes between the two and using the midpoint algorithm arrives at true epsilon.
        The loop runs while the midpoint exists between the two boundaries. Upon there not being a midpoint between the
        boundaries, aka, it equals one of the boundaries, the loop stops, as it has arrived at the lowest epsilon that
        yields a 1 - epsilon
        */
        float max_bad = 0.0f;
        float min_good = 0.5f;
        boolean keepRunning = true;
        while( keepRunning )
        {
            float midpoint = (max_bad + min_good)/2.0f;
            if( midpoint == max_bad || midpoint == min_good )
            {
                keepRunning = false;
                System.out.println("max bad for subtraction: " + max_bad + "; min good for subtraction: " + min_good);
            }
            else
            {
                if ( isMidPointGood_1a(midpoint) )
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
       // write what this is
      float max_bad = 0.0f;
      float min_good = 0.5f;
      boolean keepRunning = true;
      while( keepRunning )
      {
          float midpoint = (max_bad + min_good)/2.0f;
          if ( midpoint == max_bad || midpoint == min_good)
          {
              keepRunning = false;
              System.out.println("max bad for addition: " + max_bad + "; min good for addition: " + min_good);
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

    public static boolean isMidPointGood_1a(float midpoint)
    {

        if( 1.0f - midpoint != 1.0f )
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean isMidPointGood_1b(float midpoint)
    {
        if ( 1.0f + midpoint != 1.0f )
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static float findLargestRepresentableFloat()
    {
        //this determines my first step towards infinity to avoid stepping by one, which would take forever
        float step = 1.0f;
        while ( !Float.isInfinite(step * 2.0f) )
        {
            step = step * 2.0f;
        }
        float whereAmI = 0.0f;
        while ( step > 0.0f )
        {
            /* whereAmI, the current position relative to infinity, is determined by adding the step; if this is
            impossible the step is halved and increasing whereAmI attempted again. If the step becomes zero, it cannot
            advance us to infinity, so the halving stops. Concurrently, whereAmI is compared to whereAmIOld (whereAmI
            before the step was added) and if they are indistinguishable, the loop breaks, and we have found our
            max representable float.
            */
            if ( Float.isInfinite(whereAmI + step) )
            {
                step = step / 2.0f;
            }
            else
            {
                float whereAmIOld = whereAmI;
                whereAmI += step;
                if(whereAmI == whereAmIOld)
                {
                    break;
                }
            }
        }
        return whereAmI;
    }


    public static float findSmallestRepresentableFloat()
    {
        /* since we know what 0 is, the smallest positive representable float is between 0 and 1. Having a step of 1/2
        is a reasonable first guess */
        float minRepresentableFloat = 0.5f;

        while ( minRepresentableFloat / 2.0f > 0.0f )
        {
            minRepresentableFloat /= 2.0f;
        }

        return minRepresentableFloat;
    }


}



