package sortingVisual.frames.sorting;

import sortingVisual.frames.colorState;
import sortingVisual.frames.sortingFuncMain;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class BubbleSort extends sortingFuncMain {
    public BubbleSort(int[] values) {
        super(values);
        Sorting.arrAccess = 0;
        Sorting.counted = 0;
    }

    @Override
    public void sortFunc() {
        setState(colorState.isSorting);
        new Thread(new MyThread()).start();
    }

    @Override
    public void swapping(int[] arr, int i, int j) {
        setActiveI(i, j);
        int y = arr[i];
        arr[i] = arr[j];
        arr[j] = y;
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

    private class MyThread implements Runnable {
        private void bubbleSort(int[] anArrayOfInt) {
            for (int i = 0; i < anArrayOfInt.length; ++i) {
                for (int j = 1; j < anArrayOfInt.length - i; ++j) {
                    Sorting.arrAccess++;
                    if (anArrayOfInt[j - 1] > anArrayOfInt[j]) {
                        swapping(anArrayOfInt, j - 1, j);
                    }
                }
            }
        }

        @Override
        public void run() {
            int[] values = getVal();
            try {
                bubbleSort(values);
            } catch (Exception h) {
                h.printStackTrace();
            }
            SwingUtilities.invokeLater(() -> IsfirstChangeState());
        }

    }

}