package sortingVisual.frames.sorting;

import sortingVisual.frames.colorState;
import sortingVisual.frames.sortingFuncMain;

import javax.swing.*;

public class HeapSort extends sortingFuncMain
{
    public HeapSort(int[] values) {
        super(values);
        Sorting.counted = 0;
        Sorting.arrAccess = 0;
    }

    @Override
    public void sortFunc() {
        setState(colorState.isSorting);
        new Thread(new MyThread()).start();
    }

    @Override
    public void swapping(int[] anArrayOfInt, int i, int j) {
        setActiveI(i, j);
        int x = anArrayOfInt[i];
        anArrayOfInt[i] = anArrayOfInt[j];
        anArrayOfInt[j] = x;
        Sorting.counted++;
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    IsfirstChangeState();
                }
            });
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }
    private class MyThread implements Runnable {
        private void heap(int[] arr, int n, int i) {
            int maximum = i;
            int l = 2*i + 1;
            int r = 2*i + 2;
            if (l < n && arr[l] > arr[maximum])
                maximum = l;
            if (r < n && arr[r] > arr[maximum])
                maximum = r;
            if (maximum != i)
            {
                swapping(arr,i,maximum);
                Sorting.arrAccess++;
                heap(arr, n, maximum);
            }
            Sorting.arrAccess++;
        }
        private void heapSort(int[] anArrayOfInt) {
            int r = anArrayOfInt.length-1;
            for (int l = anArrayOfInt.length/2 ; l>=0; --l)
                heap(anArrayOfInt, r, l);
            while (r > 0) {
                swapping(anArrayOfInt, 0, r);
                heap(anArrayOfInt, --r, 0);
            }
        }
        @Override
        public void run() {
            int[] values = getVal();
            heapSort(values);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    setState(colorState.Finished);
                }
            });
        }
    }
}