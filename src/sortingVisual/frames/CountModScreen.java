package sortingVisual.frames;

import sortingVisual.frames.sorting.Sorting;

import javax.swing.*;
import java.awt.*;

public class CountModScreen extends JPanel
{
    private sorterFuncMain sorter;
    private barDisplayScreen.ChangeHandler changeHandler;
    private int maxValue;

    CountModScreen() {
        setBackground(Color.BLACK);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(300, 300);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Trebuchet MS", 0, 18));
        g2d.drawString("Answer For Given Range = " + Sorting.count_range, 0, 25);
        //g2d.drawString("Array accesses = "+ isSorting.arrAccess,250,25);
//        int[] values = getSorter().getVal();
//        int width = getWidth() - 1;
//        int height = getHeight() - 1;
//        int colWidth = Math.round((float) width / (float) values.length);
//        int x = 0;
//        Color fill = null;
//        Color highlight = null;
//        switch (getSorter().getState()) {
//            case isSorting:
//                fill = new Color(0,191,255);
//                highlight =new Color(128, 0, 128);
//                break;
//            case Finished:
//                fill = new Color(34,139,37);
//                break;
//        }
//        for (int index = 0; index < values.length; index++) {
//            g2d.setColor(fill);
//            int value = values[index];
//            int colHeight = (int) ((float) height * ((float) value / (float) maxValue));
//            g2d.fillRect(x, height - colHeight, colWidth - 1, colHeight);
//            if (getSorter().isActiveIndex(index) && highlight != null) {
//                g2d.setColor(highlight);
//                g2d.fillRect(x, height - colHeight, colWidth - 1, colHeight);
//            }
//            x += colWidth;

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

    private barDisplayScreen.ChangeHandler getChangeHandler()
    {
        return changeHandler;
    }

//    public class ChangeHandler implements ChangeListener {
//        @Override
//        public void stateChanged(ChangeEvent e) {
//            repaint();
//        }
//    }
}


