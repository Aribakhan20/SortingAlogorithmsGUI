package sortingVisual.frames.sorting;

import sortingVisual.frames.colorState;
import sortingVisual.frames.sortingFuncMain;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class RadixSort extends sortingFuncMain {
    public RadixSort(int[] values) {
        super(values);
        Sorting.arrAccess = 0;
        Sorting.counted = 0;
    }

    @Override
    public void sortFunc()
    {
        setState(colorState.isSorting);
        new Thread(new RadixSort.MyThread()).start();
    }


    static int getMaximum(int A[], int len) {
        int maximum = A[0];
        for (int i = 1; i < len; i++) {
            if (A[i] > maximum)
                maximum = A[i];
        }
        return maximum;
    }

    private void cSort(int [] A, int n)
    {
        if (n == 0)
            return;
        int maximum = A[0];
        int minimum = A[0];

        for (int i = 1; i < n; i++)
        {
            if (A[i] > maximum)
                maximum = A[i];
            if (A[i] < minimum)
                minimum = A[i];
        }
        int k = maximum - minimum + 1;

        int[] count = new int[k];

        for (int i = 0; i < n; i++) {
            count[A[i] - minimum]++;
//            isSorting.arrAccess++;
        }

        for (int i = 1; i < k; i++) {
            count[i]=count[i]+count[i - 1];
            //isSorting.counted++;
            Sorting.arrAccess++;
            setActiveI(i, i-1);
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

        int j = 0;
        for (int i = 0; i < k; i++) {
            while (j < count[i]) {
                A[j++] = i + minimum;
                setActiveI(i, j);
                Sorting.counted++;
                //isSorting.arrAccess++;
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
    }


    private class MyThread implements Runnable {
        private void radixsort(int [] arr)
        {
            int m = getMaximum(arr, arr.length);
            for (int exp = 1; m/exp > 0; exp *= 10)

                cSort(arr, arr.length);
        }

        @Override
        public void run() {
            int[] values = getVal();
            try {
                radixsort(values);
            } catch (Exception e) {
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