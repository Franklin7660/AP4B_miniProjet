package controller;
import model.Graph;
import view.Interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Control {


    public Control(Interface inter, Graph graph){

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

        inter.sidePanel.exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graph.export(inter.sidePanel.graphName.getText() + ".txt");
                inter.sidePanel.infoLabel.setText("Graph exporté !");
            }
        });

        inter.sidePanel.importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graph.buildFromFile(inter.sidePanel.graphName.getText() + ".txt");
                inter.sidePanel.infoLabel.setText("Graph importé !");
                inter.repaint();
            }
        });

        inter.sidePanel.deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (inter.graphView.selectedSommet != null){
                    graph.listeSommet.remove(inter.graphView.selectedSommet);
                    inter.sidePanel.infoLabel.setText("Sommet supprimé !");
                    inter.graphView.selectedSommet = null;
                    inter.repaint();
                }
                else if (inter.graphView.selectedArc != null){
                    inter.graphView.selectedArc.rue.arcs.remove(inter.graphView.selectedArc);
                    graph.listeArc.remove(inter.graphView.selectedArc);
                    inter.sidePanel.infoLabel.setText("Arc supprimé !");
                    inter.graphView.selectedArc = null;
                    inter.repaint();
                }
                else{
                    inter.sidePanel.infoLabel.setText("Impossible de supprimer, aucun élément sélectionné !");
                }
            }
        });
    }
}
