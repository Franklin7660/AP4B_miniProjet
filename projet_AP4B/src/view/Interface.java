package view;

import javax.swing.*;

import model.Graph;

public class Interface extends JFrame {
    private Graph graph;
    private JButton zoomIN;
    private JButton zoomOUT;

    public Interface() {
        setTitle("My Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        graph = new Graph();

        getContentPane().setLayout(null);

        zoomIN = new JButton("ZoomIN");
        zoomIN.setBounds(50,50,100,20);
        zoomIN.addActionListener(e -> graph.zoomIn());

        zoomOUT = new JButton("ZoomOUT");
        zoomOUT.setBounds(50,150,100,20);
        zoomOUT.addActionListener(e -> graph.zoomOut());

        int x = (getWidth()-500) / 2;
        int y = (getHeight() - 500) / 2;

        graph.setBounds(x,y,500,500);
        getContentPane().add(graph);
        getContentPane().add(zoomIN);
        getContentPane().add(zoomOUT);
        setVisible(true);
    }
}
