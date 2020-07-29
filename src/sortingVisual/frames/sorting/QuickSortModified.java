package sortingVisual.frames.sorting;

import sortingVisual.frames.colorState;
import sortingVisual.frames.sortingFuncMain;

import javax.swing.*;

public class QuickSortModified extends sortingFuncMain
{
    int k =7;
    public QuickSortModified(int[] values) {
        super(values);
        Sorting.counted = 0;
        Sorting.arrAccess = 0;
    }


    @Override
    public void sortFunc()
    {

        setState(colorState.isSorting);
        new Thread(new MyThread()).start();
    }

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
    void InsertionSort(int arr[], int p, int r) {
        int i, j, key;
        Sorting.arrAccess++;
        for (j = p + 1; j < r; j++) {
            key = arr[j];
            for (i = j - 1; i >= p && arr[i] > key; i--) {
                setActiveI(i,j);
                arr[i + 1] = arr[i];
                Sorting.counted++;
            }
            arr[i + 1] = key;

        }
    }




    int partition(int A[], int p, int r) {
        int x, i, j, tmp;

        x = A[r - 1];
        i = p;

        for (j = p; j < r - 1; j++) {
            setActiveI(i,j);
            if (A[j] <= x) {
                swapping(A,i,j);
                i++;
            }
        }

        tmp = A[i];
        A[i] = A[r - 1];
        A[r - 1] = tmp;

        return i;
    }

    private class MyThread implements Runnable
    {

        void modifiedQSort(int A[], int p, int r) {
            quickSortLimited(A, p, r, k);
            Sorting.counted++;
            InsertionSort(A, p, r);

        }

        void quickSortLimited(int A[], int p, int r, int s) {
            if (r - p > s) {
                Sorting.arrAccess++;
                int q =partition(A, p, r);

                quickSortLimited(A, p, q, s);


                quickSortLimited(A, q + 1, r, s);
            }
        }

        @Override
        public void run() {
            int[] val = getVal();
            modifiedQSort(val,0,val.length-1);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    setState(colorState.Finished);
                }
            });
        }
    }











}