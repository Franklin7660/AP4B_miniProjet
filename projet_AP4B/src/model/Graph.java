package model;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
public class Graph extends JPanel {
    private double zoomScale = 1.0; // Initial zoom scale
    private double minZoomScale = 0.5; // Minimum allowed zoom scale
    private double maxZoomScale = 2.0; // Maximum allowed zoom scale
    private JSlider horizontalSlider;
    private int offsetX = 0;
    private int offsetY = 0;
    private List<Sommet> listeSommet;
    private List<Arc> listeArc;
    private int startX; // Stores the initial X position when dragging
    private int startY;
    private int startOffsetY;
    private int startOffsetX; // Stores the initial offsetX when dragging
    private Cursor customCursor;


    public Graph()
    {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
//        setBackground(Color.BLACK);
        listeSommet = new ArrayList<>();
        listeArc = new ArrayList<>();
        customCursor = new Cursor(Cursor.HAND_CURSOR);
        Sommet s = new Sommet(50,50,"s");
        addSommet(s);
        Sommet s1= new Sommet(100,100,"s1");
        addSommet(s1);
        Arc arc = new Arc(s,s1);
        addArc(arc);
        Sommet s2 = new Sommet(200,200,"s2");
        addSommet(s2);
        Sommet s3 = new Sommet(300,200,"s3");
        addSommet(s3);
        Arc arc1 = new Arc(s2,s3);
        addArc(arc1);
        Sommet s4= new Sommet(200,140,"s4");
        addSommet(s4);
        Sommet s5 = new Sommet(30,400,"s5");
        addSommet(s5);
        Sommet s6 = new Sommet(70,280,"s6");
        addSommet(s6);
        Sommet s7 = new Sommet(200,400,"s7");
        addSommet(s7);
        Sommet s8 = new Sommet(900,400,"s7");
        addSommet(s8);
        addMouseListener(new MouseAdapter() {


            @Override
            public void mousePressed(MouseEvent e) {
                startX = e.getX();
                startY = e.getY();
                startOffsetY = offsetY;
                startOffsetX = offsetX;

            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();
                int offsetXChange = mouseX - startX;
                int offsetYChange = mouseY - startY;
                // Update the offsetX based on the mouse movement
                offsetX = startOffsetX - offsetXChange;
                offsetY = startOffsetY - offsetYChange;
                // Repaint the graph to reflect the updated offsetX
                repaint();
            }
            @Override
            public void mouseMoved(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();

                boolean arcisHovered = false;

                for (Arc arc : listeArc) {
                    int originX = arc.getOrigineX();
                    int originY = arc.getOrigineY();
                    int destinationX = arc.getDestinationX();
                    int destinationY = arc.getDestinationY();
                    double distanceToArc = distanceToLineSegment(mouseX, mouseY, originX-offsetX, originY - offsetY, destinationX-offsetX, destinationY - offsetY);

                    double distanceToOrigin = Math.sqrt(Math.pow(mouseX - (originX-offsetX), 2) + Math.pow(mouseY - (originY-offsetY), 2));
                    double distanceToDestination = Math.sqrt(Math.pow(mouseX - (destinationX-offsetX), 2) + Math.pow(mouseY - (destinationY-offsetY), 2));
                    if (distanceToArc <= 5 * zoomScale && distanceToOrigin >15 &&distanceToDestination>15) {
                        arcisHovered = true;
                        setCursor(customCursor);
                        String arcinfo = "Origin: "+arc.getOrigineNom()+",Destination: "+arc.getDestinationNom()+"";
                        setToolTipText(arcinfo);

                        break;
                    }
                }

                if (!arcisHovered) {
                    for (Sommet sommet : listeSommet) {
                        int sommetX = sommet.getX();
                        int sommetY = sommet.getY();
                        double distanceToSommet = Math.sqrt(Math.pow(mouseX - (sommetX-offsetX), 2) + Math.pow(mouseY - (sommetY-offsetY), 2));

                        if (distanceToSommet <= 15 * zoomScale ) {
                            setCursor(customCursor);
                            String position = ""+sommet.getNom()+" (" + sommetX + "," + sommetY + ")";
                            setToolTipText(position);
                            break;
                        } else {
                            setCursor(Cursor.getDefaultCursor());
                            setToolTipText(null);
                        }
                    }
                }
            }
        });

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.scale(zoomScale, zoomScale);
        g2d.translate(-offsetX,-offsetY);


        g2d.setColor(Color.RED);
        for (Sommet sommet : listeSommet) {
            int x = (int) (sommet.getX() / zoomScale);
            int y = (int) (sommet.getY() / zoomScale);
            g2d.fillOval(x - 15, y - 15, 30, 30);
        }


        g2d.setColor(Color.BLUE);
        for (Arc arc : listeArc) {
            int origineX = (int) (arc.getOrigineX() / zoomScale);
            int origineY = (int) (arc.getOrigineY() / zoomScale);
            int destinationX = (int) (arc.getDestinationX() / zoomScale);
            int destinationY = (int) (arc.getDestinationY() / zoomScale);
            g2d.drawLine(origineX, origineY, destinationX, destinationY);
        }

        g2d.dispose();
    }


//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        // Draw the background
//    //        g.setColor(Color.blue);
//    //        g.fillRect(0, 0, getWidth(), getHeight());
//        Graphics2D g2d = (Graphics2D) g.create();
//        g2d.scale(zoomScale,zoomScale);
//
//        g.setColor(Color.RED);
//        for (Sommet sommet : listeSommet) {
//            int x = sommet.getX();
//            int y = sommet.getY();
//            g.fillOval(x - 15, y - 15, 30, 30);
//        }
//
//        // Set the color and draw the edges
//        g.setColor(Color.blue);
//        for (Arc arc : listeArc) {
//            g.drawLine(arc.getOrigineX(), arc.getOrigineY(), arc.getDestinationX(), arc.getDestinationY());
//        }
//    }
    public void addSommet(Sommet sommet){
        listeSommet.add(sommet);
    }
    public void addArc(Arc arc){
        listeArc.add(arc);
    }



    public static double distanceToLineSegment(double px, double py, double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;

        if (dx == 0 && dy == 0) {
            // The segment is a point, return the distance between the point and the segment
            return Math.sqrt((px - x1) * (px - x1) + (py - y1) * (py - y1));
        }

        // Calculate the parameterized position along the line segment
        double t = ((px - x1) * dx + (py - y1) * dy) / (dx * dx + dy * dy);

        if (t < 0) {
            // Closest point is beyond the 'x1' end of the segment
            return Math.sqrt((px - x1) * (px - x1) + (py - y1) * (py - y1));
        }

        if (t > 1) {
            // Closest point is beyond the 'x2' end of the segment
            return Math.sqrt((px - x2) * (px - x2) + (py - y2) * (py - y2));
        }

        // Closest point is within the line segment, calculate the distance to the line
        double closestX = x1 + t * dx;
        double closestY = y1 + t * dy;
        return Math.sqrt((px - closestX) * (px - closestX) + (py - closestY) * (py - closestY));
    }




}
