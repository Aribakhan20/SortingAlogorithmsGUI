package sortingVisual.frames.sorting;

import sortingVisual.frames.colorState;
import sortingVisual.frames.sortingFuncMain;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class CountSort extends sortingFuncMain
{
    public CountSort(int[] values) {
        super(values);
        Sorting.counted = 0;
        Sorting.arrAccess = 0;
    }


    @Override
    public void swapping(int[] arr, int i, int j) {
        setActiveI(i, j);
//        int x = anArrayOfInt[i];
//        anArrayOfInt[i] = anArrayOfInt[j];
//        anArrayOfInt[j] = x;
        Sorting.counted++;
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    IsfirstChangeState();
                }
            });
        } catch (InterruptedException | InvocationTargetException exp) {
            exp.printStackTrace();
        }
    }

    @Override
    public void sortFunc() {
        setState(colorState.isSorting);
        new Thread(new CountSort.MyThread()).start();
    }



    private class MyThread extends Thread
    {
        private void cSort(int [] arr)
        {
            int len = arr.length;
            if (len == 0)
                return;
            int max = arr[0], min = arr[0];
            for (int i = 1; i < len; i++)
            {
                if (arr[i] > max)
                    max = arr[i];
                if (arr[i] < min)
                    min = arr[i];
            }
            int range = max - min + 1;

            int[] count = new int[range];

            for (int i = 0; i < len; i++) {
                count[arr[i] - min]++;
                Sorting.arrAccess++;
            }

            for (int i = 1; i < range; i++) {
                count[i]=count[i]+count[i - 1];
                Sorting.counted++;
                Sorting.arrAccess++;
                setActiveI(i, i-1);
                try {
                    SwingUtilities.invokeAndWait(new Runnable() {
                        @Override
                        public void run() {
                            IsfirstChangeState();
                        }
                    });
                } catch (InterruptedException | InvocationTargetException exp) {
                    exp.printStackTrace();
                }
            }


            int j = 0;
            for (int i = 0; i < range; i++) {
                while (j < count[i]) {
                    arr[j++] = i + min;
                    setActiveI(i, j);
                    Sorting.counted++;
                    Sorting.arrAccess++;
                    try {
                        SwingUtilities.invokeAndWait(new Runnable() {
                            @Override
                            public void run()
                            {
                                IsfirstChangeState();
                            }
                        });
                    } catch (InterruptedException | InvocationTargetException exp) {
                        exp.printStackTrace();
                    }
                }
            }
        }

        @Override
        public  void run()
        {
            int [] values = getVal();
            try
            {
                cSort(values);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    setState(colorState.Finished);
                }
            });
        }
    }
}
