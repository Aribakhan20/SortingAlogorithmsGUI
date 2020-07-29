package sortingVisual.frames.sorting;

import sortingVisual.frames.colorState;
import sortingVisual.frames.sortingFuncMain;

import javax.swing.*;
import java.util.TimerTask;
import java.util.Timer;

public class MergeSort extends sortingFuncMain
{
    public MergeSort(int[] values) {
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
        try{
            java.util.Timer timer = new Timer();
            SwingUtilities.invokeAndWait(()->{
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        IsfirstChangeState();
                    }
                }, 3);
            });
        }
        catch (Exception H){
            H.getMessage();
        }
    }





    @Override
    public void sortFunc() {
        setState(colorState.isSorting);
        new Thread(new MyThread()).start();
    }


    private class MyThread implements Runnable {
        private void mergeSort(int[] anArrayOfInt, int l, int r) {
            int[][] B = new int[2][r+1];
            mSort(anArrayOfInt, B, l, r);
        }

        private void mSort(int[] A, int[][] arr, int left, int righht) {
            if (left >= righht)
                return;
            int end = (left+righht)/2;

            mSort(A, arr, left, end);

            mSort(A, arr, end+1, righht);

            Merge(A, arr, left, end, righht);
        }
        private void Merge(int[] arr, int[][] A, int left, int m, int right) {
            for (int i=left;i<=m;i++) {
                A[0][i] = i;
                A[1][i] = i;
            }
            for (int i=right;i>m;i--) {
                A[0][i] = right+m+1-i;
                A[1][i] = right+m+1-i;
            }
            int i = left;
            int j = right;
            for (int k=left; k<right;k++)
            {
                Sorting.arrAccess++;
                int s = A[0][i];
                int t = A[0][j];
                if (arr[s]<=arr[t])
                {
                    i++;
                }
                else
                    {
                    s = t;
                    j--;
                }
                swapping(arr, s, k);

                Sorting.arrAccess++;
                t = A[1][k];
                A[0][t] = s;
                A[1][s] = t;
            }
        }
        @Override
        public void run() {
            int[] values = getVal();
            try{mergeSort(values,0,values.length-1);}
            catch (Exception e){e.printStackTrace();}
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    setState(colorState.Finished);
                }
            });
        }
    }
}
