package view;

import javax.swing.*;
import java.awt.*;

public class SidePanel extends JPanel {
    private JPanel fileManager;
    private JPanel editManager;
    public JCheckBox doubleSens;
    public JButton deleteButton;
    public JButton exportButton;
    public JButton importButton;
    public JButton resetZoom;
    public TextField graphName;
    public TextField setRue;
    public JButton InsertSommet;
    public JButton InsertArc;
    public JButton getPath;
    public JLabel infoLabel;
    public JLabel pathLength;
    public TextField Xsommet;
    public TextField Ysommet;

    public JLabel X;

    public JLabel y;
    public JPanel Insertoption;
    public JLabel Insertresponse;
    public SidePanel(){
        setBackground(new java.awt.Color(241, 250, 238));
        setBorder(BorderFactory.createLineBorder(new java.awt.Color(29, 53, 87),4));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        fileManager = new JPanel();
        fileManager.add(new JLabel("Nom du fichier:"));

        graphName = new TextField("new_graph");
        graphName.setMaximumSize(new Dimension(280,20));
        fileManager.add(graphName);

        exportButton = new JButton("Export graph");
        fileManager.add(exportButton);

        importButton = new JButton("Import graph");
        fileManager.add(importButton);

        infoLabel = new JLabel("");
        fileManager.add(infoLabel);

        //fileManager.setSize(new Dimension(280,200));
        add(fileManager);

        editManager = new JPanel();
        editManager.add(new JLabel("Modifier le graph:"));

        deleteButton = new JButton("Supprimer");
        editManager.add(deleteButton);

        Insertoption = new JPanel();
        Insertoption.setLayout(new GridLayout(0,1));
        InsertSommet = new JButton("Ajouter sommet");
        X = new JLabel("X :");
        y = new JLabel("Y");
        Insertoption.add(InsertSommet);
        Insertoption.add(X);

        Xsommet = new TextField("");
        Ysommet = new TextField("");
        Insertoption.add(Xsommet);
        Insertoption.add(y);
        Insertoption.add(Ysommet);
        editManager.add(Insertoption);

        InsertArc = new JButton("Ajouter arc");
        editManager.add(InsertArc);

        JPanel editRue = new JPanel();
        editManager.add(editRue);

        editRue.add(new JLabel("Nom de rue"));

        setRue = new TextField("unnamed");
        setRue.setMaximumSize(new Dimension(280,20));
        editRue.add(setRue);

        editManager.add(editRue);

        doubleSens = new JCheckBox("Double Sens");
        doubleSens.setSelected(true);
        editManager.add(doubleSens);

        Insertresponse = new JLabel();
        editManager.add(Insertresponse);

        resetZoom = new JButton("Réinitialiser zoom");
        fileManager.add(resetZoom);

        add(editManager);

        JPanel pathManager = new JPanel();
        getPath = new JButton("Calculer l'itinéraire");
        pathManager.add(getPath);
        pathLength = new JLabel("Distance : 0m");
        pathManager.add(pathLength);

        add(pathManager);

    }
}
