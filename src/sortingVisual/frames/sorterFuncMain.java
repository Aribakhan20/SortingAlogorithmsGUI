package sortingVisual.frames;

import javax.swing.event.ChangeListener;

public interface sorterFuncMain
{
    void addChangeListener(ChangeListener listener);
    void removeChangeListener(ChangeListener listener);
    int[] getVal();
    void sortFunc();
    colorState getState();
    boolean isActiveIndex(int index);
}
