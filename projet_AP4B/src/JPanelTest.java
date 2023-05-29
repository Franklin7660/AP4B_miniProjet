import javax.swing.*;
import java.awt.*;

public class JPanelTest {
    JPanelTest() {
        //Créer le Jframe
        JFrame f = new JFrame("Welcome To WayToLearnX!");
        //Créer le JPanel
        JPanel panel = new JPanel();
        //Spécifier la position et la taille du JPanel
        panel.setBounds(40, 50, 150, 150);
        //Spécifier la couleur d'arrière-plan du JPanel
        panel.setBackground(Color.lightGray);
        //Créer le bouton 1
        JButton btn1 = new JButton("Bouton 1");
        //Spécifier la position et la taille du bouton
        btn1.setBounds(50, 100, 80, 30);
        //Spécifier la couleur d'arrière-plan du bouton
        btn1.setBackground(Color.WHITE);
        //Créer le bouton 2
        JButton btn2 = new JButton("Bouton 2");
        btn2.setBounds(100, 100, 80, 30);
        btn2.setBackground(Color.RED);
        //Ajouter les deux boutons au JPanel
        panel.add(btn1);
        panel.add(btn2);
        //Ajouter le JPanel au JFrame
        f.add(panel);
        f.setSize(350, 350);
        f.setLayout(null);
        f.setVisible(true);
    }
}