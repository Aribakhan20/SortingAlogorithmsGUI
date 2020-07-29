package sortingVisual.frames.sorting;

import sortingVisual.frames.colorState;
import sortingVisual.frames.sortingFuncMain;

import javax.swing.*;

public class QuickSort extends sortingFuncMain
{
    public QuickSort(int[] values) {
        super(values);
        Sorting.counted = 0;
        Sorting.arrAccess = 0;
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
        } catch (Exception exp) {
            exp.getMessage();
        }
    }




    @Override
    public void sortFunc() {
        setState(colorState.isSorting);
        Sorting.startTime=System.nanoTime();
        new Thread(new MyThread()).start();
    }

    private class MyThread implements Runnable {
        private void quickSort(int[] arr, int left, int right){
            if (left >= right)
                return;
            swapping(arr, left, (left+right)/2);
            int last = left;
            for (int i=left+1; i<=right; ++i) {
                if (arr[i] < arr[left]) {
                    swapping(arr, ++last, i);
                }
                Sorting.arrAccess++;
            }
            swapping(arr, left, last);

            quickSort(arr, left, last-1);

            quickSort(arr, last+1, right);
        }
        @Override
        public void run() {
            int[] values = getVal();
            quickSort(values,0,values.length-1);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run()
                {
                    //isSorting.startTime=System.nanoTime();
                    setState(colorState.Finished);
                }
            });
        }
    }
}
