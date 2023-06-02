package view;

import javax.swing.*;
import javax.swing.JButton;
import java.awt.*;

import model.Graph;

public class Interface extends JFrame {
    private Graph graph;
    public GraphView graphView;
    private JButton zoomIN;
    private JButton zoomOUT;

    public Interface(int graphWidth, int graphHeight, Graph graph) {

        setTitle("Graph Interface");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        Container c = getContentPane();
        c.setLayout(null);
        c.setBackground( new java.awt.Color(69, 123, 157) );

        graphView = new GraphView(graphWidth,graphHeight,graph);
        graphView.setBounds(230,50,graphWidth,graphHeight);

        getContentPane().add(graphView);

        setVisible(true);
    }
}
