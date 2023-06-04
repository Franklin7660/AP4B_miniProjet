package view;

import model.Arc;
import model.Graph;
import model.Rue;
import model.Sommet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.lang.Math.sqrt;

public class GraphView extends JPanel {
    private int width;
    private int height;
    private JSlider horizontalSlider;
    private int offsetX = 0;
    private int offsetY = 0;
    private double zoomScale =1.0;
    private int startX; // Stores the initial X position when dragging
    private int startY;
    private int startOffsetY;
    private int startOffsetX; // Stores the initial offsetX when dragging
    private Cursor customCursor;
    private Graph graph;
    public Sommet selectedSommet;
    public Arc selectedArc;
    public boolean canAdd ;

    public GraphView(int graphWidth,int graphHeight, Graph pgraph) {
        graph = pgraph;

        width = graphWidth;
        height = graphHeight;

        setBackground(new java.awt.Color(241, 250, 238));
        setBorder(BorderFactory.createLineBorder(new java.awt.Color(29, 53, 87),4));

        customCursor = new Cursor(Cursor.HAND_CURSOR);
    }

    public void click(int mouseX, int mouseY){
        startX = mouseX;
        startY = mouseY;
        startOffsetY = offsetY;
        startOffsetX = offsetX;

        selectedArc = null;
        selectedSommet = null;

        for (Sommet sommet : graph.listeSommet) {
            int[] coordinates = modelToScreenCoordinates(sommet.getX(),sommet.getY());

            double distanceToSommet = sqrt(Math.pow(mouseX - coordinates[0], 2) + Math.pow(mouseY - coordinates[1], 2));

            if (distanceToSommet <= 8 * zoomScale ) {
                selectedSommet = sommet;
                repaint();
                break;
            }
        }

        if (selectedSommet == null) {
            for(Rue rue : graph.listeRue){
                for (Arc arc: rue.arcs){
                    int[] origin = modelToScreenCoordinates(arc.getOrigineX(),arc.getOrigineY());
                    int[] destination = modelToScreenCoordinates(arc.getDestinationX(),arc.getDestinationY());
                    double distanceToArc = Graph.distanceToLineSegment(mouseX, mouseY, origin[0], origin[1], destination[0], destination[1]);

                    if (distanceToArc <= 5 * zoomScale ) {
                        selectedArc = arc;
                        repaint();
                        break;
                    }
                }
            }
            if(selectedArc==null){
                setCursor(Cursor.getDefaultCursor());
            }
        }

    }

    public void move(int mouseX, int mouseY){
        int offsetXChange = mouseX - startX;
        int offsetYChange = mouseY - startY;
        // Update the offsetX based on the mouse movement
        offsetX = (int) (startOffsetX - offsetXChange/zoomScale);
        offsetY = (int) (startOffsetY - offsetYChange/zoomScale);
        // Repaint the graph to reflect the updated offsetX
        repaint();
    }

    public void zoom(int scroll) {

        if (scroll == 1){zoomScale*=0.9;}
        else{zoomScale*=1.1;}

        if(zoomScale < 0.1) zoomScale = 0.1;
        else if(zoomScale > 5) zoomScale = 5;

        repaint();
    }

    private int[] modelToScreenCoordinates(int x, int y){
        int X = (int) ((x - offsetX -width/2)*zoomScale +width/2);
        int Y = (int) ((y - offsetY -height/2)*zoomScale +height/2);
        return new int[]{X,Y};
    }

    public void checkHover(int mouseX, int mouseY){

        boolean arcisHovered = false;

        for(Rue rue : graph.listeRue){
            for (Arc arc: rue.arcs){
                int[] origin = modelToScreenCoordinates(arc.getOrigineX(),arc.getOrigineY());
                int[] destination = modelToScreenCoordinates(arc.getDestinationX(),arc.getDestinationY());
                double distanceToArc = Graph.distanceToLineSegment(mouseX, mouseY, origin[0], origin[1], destination[0], destination[1]);

                if (distanceToArc <= 5 * zoomScale ) {
                    arcisHovered = true;
                    setCursor(customCursor);
                    String arcinfo = "Rue: "+rue.name;
                    setToolTipText(arcinfo);

                    break;
                }

            }
        }

        if (!arcisHovered) {
            for (Sommet sommet : graph.listeSommet) {
                int[] coordinates = modelToScreenCoordinates(sommet.getX(),sommet.getY());

                double distanceToSommet = sqrt(Math.pow(mouseX - coordinates[0], 2) + Math.pow(mouseY - coordinates[1], 2));

                if (distanceToSommet <= 8 * zoomScale ) {
                    setCursor(customCursor);
                    String position = ""+sommet.getNom()+" (" + sommet.getX()+ "," + sommet.getY() + ")";
                    setToolTipText(position);
                    break;
                } else {
                    setCursor(Cursor.getDefaultCursor());
                    setToolTipText(null);
                }
            }
        }
    }
    public void InsertSommet(int x, int y){
        int a = 0;

                for(Sommet som :graph.listeSommet){
                    if(som.getY() == y && som.getX()==x){
                        a+=1;
                    }
                }
                if(a==0){
                    Sommet s = new Sommet(200,x,y,"unnamed");
                    graph.addSommet(s);
                    canAdd = true;
                    repaint();
                }
                else{
                    canAdd = false;
                }



    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.translate(width/2,height/2);
        g2d.scale(zoomScale, zoomScale);
        g2d.translate(-offsetX-width/2,-offsetY-height/2);

        g2d.setColor(new java.awt.Color(69, 123, 157));
        for (Sommet sommet : graph.listeSommet) {
            int x = sommet.getX();
            int y = sommet.getY();
            g2d.fillOval(x - 8, y - 8, 16, 16);
        }

        g2d.setColor(new java.awt.Color(29, 53, 87));
        for(Rue rue : graph.listeRue){
            for (Arc arc: rue.arcs){
                int origineX = arc.getOrigineX();
                int origineY = arc.getOrigineY();
                int destinationX = arc.getDestinationX();
                int destinationY = arc.getDestinationY();
                Stroke stroke = new BasicStroke(4f);
                g2d.setStroke(stroke);
                g2d.drawLine(origineX, origineY, destinationX, destinationY);
            }
        }

        g2d.setColor(new java.awt.Color(230, 57, 70));
        if (selectedSommet!=null){
            int x = selectedSommet.getX();
            int y = selectedSommet.getY();
            g2d.fillOval(x - 8, y - 8, 16, 16);
        }
        else if (selectedArc!=null){
            int origineX = selectedArc.getOrigineX();
            int origineY = selectedArc.getOrigineY();
            int destinationX = selectedArc.getDestinationX();
            int destinationY = selectedArc.getDestinationY();
            Stroke stroke = new BasicStroke(4f);
            g2d.setStroke(stroke);
            g2d.drawLine(origineX, origineY, destinationX, destinationY);
        }

        g2d.dispose();
    }
}
