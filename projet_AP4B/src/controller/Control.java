package controller;
import model.Graph;
import view.Interface;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Control {


    public Control(Interface inter){

        inter.graphView.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                inter.graphView.click(e.getX(),e.getY());
            }
        });

        inter.graphView.addMouseWheelListener(e -> inter.graphView.zoom(e.getWheelRotation()));

        inter.graphView.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) { inter.graphView.move(e.getX(),e.getY());}
            @Override
            public void mouseMoved(MouseEvent e) { inter.graphView.checkHover(e.getX(),e.getY()); }
        });
    }
}
