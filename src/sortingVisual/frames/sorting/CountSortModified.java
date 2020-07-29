package sortingVisual.frames.sorting;

import sortingVisual.frames.colorState;
import sortingVisual.frames.sortingFuncMain;

import javax.swing.*;

public class CountSortModified extends sortingFuncMain
{

    public CountSortModified(int[] values) {
        super(values);
        Sorting.counted = 0;
        Sorting.arrAccess = 0;
    }

    @Override
    public void sortFunc() {
        setState(colorState.isSorting);
        new Thread(new CountSortModified.MyThread()).start();
    }

    private class MyThread extends Thread
    {
        private  void CountSortMod(int [] countArr,int x, int y) {
            int arlength = countArr.length;
            if (arlength == 0)
                return ;
            int maximum = countArr[0], minimum = countArr[0];
            for (int i = 1; i < arlength; i++) {
                if (countArr[i] > maximum)
                    maximum = countArr[i];
                if (countArr[i] < minimum)
                    minimum = countArr[i];
            }
            int range = maximum - minimum + 1;

            int[] count = new int[range];

            for (int i = 0; i < arlength; i++) {
                count[countArr[i] - minimum]++;
            }

            for (int i = 1; i < range; i++) {
                count[i] = count[i] + count[i - 1];//cumulative
            }

            if (x > 0)
            {
                Sorting.count_range = count[y + 1] - count[x];
            }
            else
            {Sorting.count_range = count[y + 1];}


        }


        @Override
        public  void run()
        {
            int [] values = Sorting.returnArray();
            int x=4,y=10;
            try
            {
                CountSortMod(values,x,y);
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
