package sortingVisual.frames;

import sortingVisual.frames.sorting.Sorting;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class barDisplayScreen extends JPanel
{
    private sorterFuncMain sorter;
    private ChangeHandler changeHandler;
    private int maxValue;

    barDisplayScreen() {
        setBackground(Color.BLACK);
    }

    @Override
    public Dimension getPreferredSize() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Trebuchet MS", 0, 18));
        g2d.drawString("Comparisons = " + Sorting.counted, 0, 25);
        g2d.drawString("Array accesses = "+ Sorting.arrAccess,250,25);
//        g2d.drawString("Time for isSorting = "+ (isSorting.endTime-isSorting.startTime),300,250);
        int [] values = getSorter().getVal();
        int width = getWidth() - 1;
        int height = getHeight() - 1;
        int colWidth = Math.round((float) width / (float) values.length);
        int x = 0;
        Color fill = null;
        Color highlight = null;
        switch (getSorter().getState()) {
            case isSorting:
                fill = new Color(0,191,255);
                highlight =new Color(128, 0, 128);
                break;
            case Finished:
                fill = new Color(34,139,37);
                break;
        }
        for (int index = 0; index < values.length; index++) {
            g2d.setColor(fill);
            int value = values[index];
            int colHeight = (int) ((float) height * ((float) value / (float) maxValue));
            g2d.fillRect(x, height - colHeight, colWidth - 1, colHeight);
            if (getSorter().isActiveIndex(index) && highlight != null) {
                g2d.setColor(highlight);
                g2d.fillRect(x, height - colHeight, colWidth - 1, colHeight);
            }
            x += colWidth;
        }
        g2d.dispose();
    }





    private sorterFuncMain getSorter() {
        return sorter;
    }

    void setSorter(sorterFuncMain value) {
        if (sorter != value) {
            if (sorter != null) {
                sorter.removeChangeListener(getChangeHandler());
            }
            sorter = value;
            if (sorter != null) {
                sorter.addChangeListener(getChangeHandler());
                maxValue = 0;
                for (int intValue : sorter.getVal()) {
                    maxValue = Math.max(maxValue, intValue);
                }
            }
            repaint();
        }
    }

    private ChangeHandler getChangeHandler() {
        if (changeHandler == null) {
            changeHandler = new ChangeHandler();
        }
        return changeHandler;
    }

    public class ChangeHandler implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            repaint();
        }
    }
}
