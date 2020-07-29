package sortingVisual.frames;

import sortingVisual.frames.sorting.*;

import javax.swing.*;
import java.awt.*;

public class mainFunction
{

    private static JFrame frame;
    private static JPanel panel;
    private static JButton bub, rad, inser, q,m, h,c,b,cms,qms;
    mainFunction(){
        frame=new JFrame();
        frame.setSize(500,700);
        frame.setDefaultCloseOperation(3);
        frame.setLocationRelativeTo(null);


        panel=new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.BLACK);

        bub =new JButton("Bubble");
        rad =new JButton("Radix");
        inser =new JButton("Insertion");
        q =new JButton("Quick");
        m=new JButton("Merge");
        h =new JButton("Heap");
        c=new JButton("Count");
        b=new JButton("Bucket");
        cms=new JButton("CountSortModifed");
        qms=new JButton("QuickSortModifed");


        bub.setBounds(100,100,100,50);
        rad.setBounds(100,200,100,50);
        inser.setBounds(300,100,100,50);
        q.setBounds(300,200,100,50);
        m.setBounds(100,300,100,50);
        h.setBounds(300,300,100,50);
        c.setBounds(100,400,100,50);
        b.setBounds(300,400,100,50);
        cms.setBounds(100,500,100,50);
        qms.setBounds(300,500,100,50);

        bub.addActionListener(e->{
            Sorting.counted =0;
            barDisplayScreen s=new barDisplayScreen();
            Sorting.values=Sorting.returnArray();
            BubbleSort sorter = new BubbleSort(Sorting.values);
            s.setSorter(sorter);
            frame = returnFrame(s);
            sorter.sortFunc();
        });

        rad.addActionListener(e->{
            Sorting.counted =0;
            barDisplayScreen s=new barDisplayScreen();
            Sorting.values=Sorting.returnArray();
            RadixSort sorter = new RadixSort(Sorting.values);
            s.setSorter(sorter);
            frame = returnFrame(s);
            sorter.sortFunc();
        });
//
        inser.addActionListener(e->{
            Sorting.counted =0;
            barDisplayScreen s=new barDisplayScreen();
            Sorting.values=Sorting.returnArray();
            InsertionSort sorter = new InsertionSort(Sorting.values);
            s.setSorter(sorter);
            frame = returnFrame(s);
            sorter.sortFunc();
        });
//
        q.addActionListener(e->{
            Sorting.counted =0;
            barDisplayScreen s=new barDisplayScreen();
            Sorting.values=Sorting.returnArray();
            QuickSort sorter = new QuickSort(Sorting.values);
            s.setSorter(sorter);
            frame = returnFrame(s);
            sorter.sortFunc();
        });
//
        m.addActionListener(e->{
            Sorting.counted =0;
            barDisplayScreen s=new barDisplayScreen();
            Sorting.values=Sorting.returnArray();
            MergeSort sorter = new MergeSort(Sorting.values);
            s.setSorter(sorter);
            frame = returnFrame(s);
            sorter.sortFunc();
        });
//
        h.addActionListener(e->{
            Sorting.counted =0;
            barDisplayScreen s=new barDisplayScreen();
            Sorting.values=Sorting.returnArray();
            HeapSort sorter = new HeapSort(Sorting.values);
            s.setSorter(sorter);
            frame = returnFrame(s);
            sorter.sortFunc();
        });

        c.addActionListener(e->{
            Sorting.counted =0;
            barDisplayScreen s=new barDisplayScreen();
            Sorting.values=Sorting.returnArray();
            CountSort sorter = new CountSort(Sorting.values);
            s.setSorter(sorter);
            frame = returnFrame(s);
            sorter.sortFunc();
        });

        b.addActionListener(e->{
            Sorting.counted =0;
            barDisplayScreen s=new barDisplayScreen();
            Sorting.values=Sorting.returnArray();
            BucketSort sorter = new BucketSort(Sorting.values);
            s.setSorter(sorter);
            frame = returnFrame(s);
            sorter.sortFunc();
        });

        cms.addActionListener(e->{
            CountModScreen s=new CountModScreen();
            Sorting.values=Sorting.returnArray();
            CountSortModified sorter= new CountSortModified(Sorting.values);

           s.setSorter(sorter);
            frame = returnFrame(s);
             sorter.sortFunc();
        });

        qms.addActionListener(e->{
            Sorting.counted =0;
            barDisplayScreen s=new barDisplayScreen();
            Sorting.values=Sorting.returnArray();
            QuickSortModified sorter = new QuickSortModified(Sorting.values);
            s.setSorter(sorter);
            frame = returnFrame(s);
            sorter.sortFunc();
        });





        panel.add(bub);panel.add(rad);panel.add(inser);panel.add(q);
        panel.add(m);panel.add(h);panel.add(c);panel.add(b);panel.add(cms);panel.add(qms);
        frame.add(panel);
        frame.setVisible(true);
        JOptionPane.showMessageDialog(frame,"PLEASE CHOSE A SORT :).\n");
    }
    private JFrame returnFrame(JPanel panel){
        JFrame frame;
        frame = new JFrame("isSorting Visualisation");
        frame.setDefaultCloseOperation(2);
        frame.setLayout(new BorderLayout());
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        return frame;
    }
    public static void main(String ...s){
        new mainFunction();
    }
}