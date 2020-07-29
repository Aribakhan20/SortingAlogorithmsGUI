package sortingVisual.frames.sorting;

import sortingVisual.frames.colorState;
import sortingVisual.frames.sortingFuncMain;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class BucketSort extends sortingFuncMain {
    public BucketSort(int[] values) {
        super(values);
        Sorting.arrAccess = 0;
        Sorting.counted = 0;
    }


    @Override
    public void swapping(int[] arr, int i, int j) {
        setActiveI(i, j);
        int x = arr[i];
        arr[i] = arr[j];
        arr[j] = x;
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
    public void sortFunc()
    {
        setState(colorState.isSorting);
        new Thread(new BucketSort.MyThread()).start();
    }



    static int maxVal(int[] A)
    {
        int maxVal = 0;
        for (int i = 0; i < A.length; i++)
            if (A[i] > maxVal) {
                maxVal = A[i];
                Sorting.arrAccess++;
                Sorting.counted++;
            }
        return maxVal;
    }

    private class MyThread implements Runnable
    {
        private void bucketsort(int [] array, int maxiValue)
        {
            int [] bucket=new int[maxiValue+1];

            for (int i=0; i<bucket.length; i++) {
                bucket[i]=0;
            }

            for (int i=0; i<array.length; i++)
            {
                bucket[array[i]]++;
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

            int X=0;
            for (int i=0; i<bucket.length; i++)
            {
                for (int j=0; j<bucket[i]; j++)
                {
                    array[X++]=i;
                    Sorting.counted++;
                    Sorting.arrAccess++;
                    try {
                        SwingUtilities.invokeAndWait(new Runnable() {
                            @Override
                            public void run() {
                                IsfirstChangeState();
                            }
                        });
                    }
                    catch (InterruptedException | InvocationTargetException exp)
                    {
                        exp.printStackTrace();
                    }
                }
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
        }

        @Override
        public void run() {
            int[] values = getVal();
            try{
                int max = maxVal(values);
                bucketsort(values,max);}
            catch (Exception h){h.printStackTrace();}
            SwingUtilities.invokeLater(()-> IsfirstChangeState());
            setState(colorState.Finished);
        }

    }
}
