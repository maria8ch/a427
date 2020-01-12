public class Main
{

    public static void main(String[] args)
    {
        float smallest = findSmallestFloat();
        System.out.println("Smallest Float: " + smallest);
    }

    public static float findSmallestFloat()
    {
        float max_bad = 0.0f;
        float min_good = 0.5f;
        boolean keepRunning = true;
        while( keepRunning )
        {
            float midpoint = (max_bad + min_good)/2.0f;
            if( midpoint == max_bad || midpoint == min_good )
            {
                keepRunning = false;
                System.out.println("max bad: " + max_bad + "; min good: " + min_good);
            }
            else
            {
                if (isMidPointGood(midpoint))
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

    public static boolean isMidPointGood(float midpoint)
    {

        if( 1.0f - midpoint != 1.0f)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}



