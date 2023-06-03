package view;

import javax.swing.*;
import java.awt.*;

public class SidePanel extends JPanel {
    private JPanel fileManager;
    private JPanel editManager;
    public JButton deleteButton;
    public JButton exportButton;
    public JButton importButton;
    public TextField graphName;

    public JLabel infoLabel;

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

        add(fileManager);

        editManager = new JPanel();
        editManager.add(new JLabel("Modifier le graph:"));

        deleteButton = new JButton("Supprimer");
        editManager.add(deleteButton);

        add(editManager);

    }
}
