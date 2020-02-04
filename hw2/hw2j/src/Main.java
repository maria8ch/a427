public class Main
{
    public static final int SYSTEM_SIZE = 4;

    public static void main(String[] args)
    {
        double[] Y_0 = new double[SYSTEM_SIZE];
        double[] Y_1 = new double[SYSTEM_SIZE];
        SolveHw2_2();
        SolveHw2_3();
    }

    public static double[][] RunEuler(double t_start, double t_end, int interval_count)
    {
        double[][] result;

        result = new double[SYSTEM_SIZE][];
        for( int i = 0; i < SYSTEM_SIZE; i++)
        {
            result[i] = new double[interval_count];
            for( int j = 0; j < interval_count; j++)
            {
                result[i][j] = Math.random();
            }
        }

        return result;
    }


    public static void SolveHw2_2()
    {
        double[][] result2_2 = RunEuler(0.0, 30.0, 30);

        for(int i = 0; i < SYSTEM_SIZE; i++)
        {
            for(double d : result2_2[i])
            {
                System.out.print(" " + d);
            }
            System.out.println(";");
        }
    }

    public static void SolveHw2_3()
    {
    }


}
