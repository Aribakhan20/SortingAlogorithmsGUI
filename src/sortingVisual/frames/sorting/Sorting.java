package sortingVisual.frames.sorting;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Sorting
{
    public static int[] values;
    public static int counted = 0;
    public static int arrAccess = 0;
    public static int count_range;
    public static long startTime;
    public static long endTime;
    public static long timeTaken=0;


    static int [] array = new int[500];

    public static int[] returnArray()
    {
        try{

//            File fos = new File("DataSort.txt");
//            PrintWriter dos = new PrintWriter(fos);
//
//            for (int i = 0; i<array.length; i++)
//            {
//                int num = i;
//                dos.println(num);
//            }

//            Random generator = new Random();

//            for (int i = 0; i <array.length; i++)
//            {
//                int num = generator.nextInt(999) + 1;
//                dos.println(num);
//            }
//            dos.close();

//            Scanner scanner = new Scanner(fos);
            Scanner scanner = new Scanner(new File("DataInput.txt"));
            int i=0;
            while(scanner.hasNextInt()) {
                array[i] = scanner.nextInt();
                i++;
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return array;
    }

    public static long sortTime()
    {
        return timeTaken+=endTime-startTime;
    }


}

