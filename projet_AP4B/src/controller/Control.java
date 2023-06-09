package controller;
import model.Arc;
import model.Graph;
import model.Rue;
import model.Sommet;
import view.Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.filechooser.FileNameExtensionFilter;

import static java.lang.Math.round;

public class Control {


    public Control(Interface inter, Graph graph){

        inter.graphView.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                inter.graphView.click(e.getX(),e.getY());
                if(inter.graphView.selectedArc != null){
                    inter.sidePanel.setRue.setText(inter.graphView.selectedArc.rue.name);
                }
            }
        });

        inter.graphView.addMouseWheelListener(e -> inter.graphView.zoom(e.getWheelRotation()));

        inter.graphView.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) { inter.graphView.mouseGrab(e.getX(),e.getY());}
            @Override
            public void mouseMoved(MouseEvent e) { inter.graphView.checkHover(e.getX(),e.getY()); }
        });

        inter.sidePanel.exportButton.addActionListener(e -> {
            graph.export(inter.sidePanel.graphName.getText() + ".txt");
            inter.sidePanel.infoLabel.setText("Graph exporté !");
        });

        inter.sidePanel.InsertSommet.addActionListener(e -> {
            int x = Integer.parseInt(inter.sidePanel.Xsommet.getText());
            int y = Integer.parseInt(inter.sidePanel.Ysommet.getText());
            inter.graphView.InsertSommet(x,y);
            if(inter.graphView.canAdd){
                inter.sidePanel.Insertresponse.setText("sommet ajoutée");
            }
            else{
                inter.sidePanel.Insertresponse.setText("sommet déja éxiste");
            }



        });

        inter.sidePanel.InsertArc.addActionListener(e -> {
            if(inter.graphView.selectedDestination != null){
                Sommet or = inter.graphView.selectedSommet;
                Sommet dest = inter.graphView.selectedDestination;
                Rue rue = graph.getRueByName(inter.sidePanel.setRue.getText());
                boolean doubleSens = inter.sidePanel.doubleSens.isSelected();
                graph.addArc(new Arc(or,dest,rue));
                if(doubleSens){
                    graph.addArc(new Arc(dest,or,rue));
                }
                inter.repaint();
            }
            else{
                inter.sidePanel.infoLabel.setText("Veuillez sélectionner deux sommets");
            }

        });

        inter.sidePanel.exportButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setApproveButtonText("Save");
            fileChooser.setSelectedFile(new File("myFile.txt"));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
            fileChooser.setFileFilter(filter);

            int result = fileChooser.showOpenDialog(inter.graphView);
            if(result==JFileChooser.APPROVE_OPTION){
                File selectedfile = fileChooser.getSelectedFile();
                graph.export(selectedfile.getPath());

            }


        });

        inter.sidePanel.importButton.addActionListener(new ActionListener() {
            JLabel nameLabel;
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedfileName = "";

                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");

                fileChooser.setFileFilter(filter);
                int result = fileChooser.showOpenDialog(inter.graphView);
                if(result==JFileChooser.APPROVE_OPTION){
                    File selectedfile = fileChooser.getSelectedFile();
                    graph.buildFromFile(selectedfile);
                    selectedfileName = selectedfile.getName();

                    if (nameLabel != null) {
                        inter.remove(nameLabel);
                    }

                    nameLabel = new JLabel();
                    nameLabel.setText("  "+selectedfileName);
                    nameLabel.setBounds(250, 15, 70, 13);
                    nameLabel.setOpaque(true);
                    nameLabel.setBackground(Color.WHITE);
                    inter.add(nameLabel);
                    inter.repaint();

//                    inter.graphView.repaint();
                }

            }
        });

        inter.sidePanel.deleteButton.addActionListener(e -> {
            if (inter.graphView.selectedSommet != null){
                graph.deleteElement(inter.graphView.selectedSommet);
                inter.sidePanel.infoLabel.setText("Sommet supprimé !");
                inter.graphView.selectedSommet = null;
                inter.repaint();
            }
            else if (inter.graphView.selectedArc != null){
                graph.deleteElement(inter.graphView.selectedArc);
                inter.sidePanel.infoLabel.setText("Arc supprimé !");
                inter.graphView.selectedArc = null;
                inter.repaint();
            }
            else{
                inter.sidePanel.infoLabel.setText("Impossible de supprimer, aucun élément sélectionné !");
            }
        });

        inter.sidePanel.resetZoom.addActionListener(e -> inter.graphView.resetZoom());

        inter.sidePanel.getPath.addActionListener(e -> {
            inter.graphView.path = null;
            if(inter.graphView.selectedDestination != null){
                inter.graphView.path = graph.shortestPath(inter.graphView.selectedSommet,inter.graphView.selectedDestination);
                if(inter.graphView.path != null){
                    inter.sidePanel.pathLength.setText("Distance : " + (int)graph.pathLength);
                }
                else{
                    inter.sidePanel.pathLength.setText("Aucun chemin ne relie ces sommets");
                }
            }
            else{
                inter.sidePanel.pathLength.setText("Veuillez sélectionner le départ et la destination");
            }
            inter.repaint();
        });
    }
}
