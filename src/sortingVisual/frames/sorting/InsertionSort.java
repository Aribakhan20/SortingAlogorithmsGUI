package sortingVisual.frames.sorting;
import sortingVisual.frames.colorState;
import sortingVisual.frames.sortingFuncMain;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class InsertionSort extends sortingFuncMain
{
//    public static long startTime;
//    public static long endTime;
    public InsertionSort(int[] values) {
        super(values);
        Sorting.counted = 0;
        Sorting.arrAccess = 0;
    }
    @Override
    public void sortFunc() {
        setState(colorState.isSorting);
        //isSorting.startTime = System.currentTimeMillis();
        new Thread(new MyThread()).start();
    }


    @Override
    public void swapping(int[] arr, int i, int j) {
        setActiveI(i, j);
        int x = arr[i];
        arr[i] = arr[j];
        arr[j] = x;
        Sorting.counted = Sorting.counted + 1;
        try {
            java.util.Timer timer = new Timer();
            SwingUtilities.invokeAndWait(()-> {
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        IsfirstChangeState();
                    }
                }, 0);
            });
        } catch (Exception exp) {
            System.out.println(exp);
        }
    }



    private class MyThread implements Runnable {
        @Override
        public void run() {
            int[] values = getVal();
            for (int i = 0; i < values.length; ++i) {
                for (int j = i - 1; j >= 0 && values[j] > values[j + 1]; --j) {
                    swapping(values, j, j + 1);
                    Sorting.arrAccess++;
                }
                Sorting.arrAccess++;
            }
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    setState(colorState.Finished);
                    Sorting.endTime=System.currentTimeMillis();
                }
            });
        }
    }
}
