package sortingVisual.frames;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.List;

public abstract class sortingFuncMain implements sorterFuncMain
{
    private List<ChangeListener> listeners;
    private int[] val;
    private colorState state = colorState.sortWaiting;
    private List<Integer> activeI;

    public sortingFuncMain(int[] val) {
        this.val = val;
        listeners = new ArrayList<>(25);
        activeI = new ArrayList<>(2);
    }

    @Override
    public colorState getState() {
        return state;
    }

    public void setState(colorState value)
    {
        if (value != state)
        {
            state = value;
            IsfirstChangeState();
        }
    }


    public void IsfirstChangeState()
    {
        if (listeners.size() > 0)
        {
            ChangeEvent evt = new ChangeEvent(this);
            for (ChangeListener listener : listeners)
            {
                listener.stateChanged(evt);
            }
        }
    }



    @Override
    public void addChangeListener(ChangeListener listener)
    {
        listeners.add(listener);
    }

    @Override
    public void removeChangeListener(ChangeListener listener)
    {
        listeners.remove(listener);
    }


    @Override
    public int[] getVal()
    {
        return val;
    }

    @Override
    public boolean isActiveIndex(int index)
    {
        return activeI.contains(index);
    }

    public void setActiveI(int lower, int upper)
    {
        activeI.clear();
        activeI.add(lower);
        activeI.add(upper);
        IsfirstChangeState();
    }

    public void swapping(int[] arr, int i, int j)
    {
        setActiveI(i, j);
        int a = arr[i];
        arr[i] = arr[j];
        arr[j] = a;
        IsfirstChangeState();
    }
}
