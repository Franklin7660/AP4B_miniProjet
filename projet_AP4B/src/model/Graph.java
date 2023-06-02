package model;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.sqrt;

public class Graph {
    public List<Sommet> listeSommet;
    public List<Arc> listeArc;
    public List<Rue> listeRue;

    public Graph()
    {

        listeSommet = new ArrayList<Sommet>();
        listeArc = new ArrayList<Arc>();
        listeRue = new ArrayList<Rue>();

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
        Arc arc1 = new Arc(s1,s2);
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
        List <Arc> arcsofrue = new ArrayList<>();
        arcsofrue.add(arc);
        arcsofrue.add(arc1);
        Rue r = new Rue("boulevard",arcsofrue);
        addRue(r);

    }

    public void addSommet(Sommet sommet){listeSommet.add(sommet);}
    public void addArc(Arc arc){listeArc.add(arc);}
    public void addRue(Rue rue){listeRue.add(rue);}
    public static double distanceToLineSegment(double px, double py, double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;

        if (dx == 0 && dy == 0) {
            // The segment is a point, return the distance between the point and the segment
            return sqrt((px - x1) * (px - x1) + (py - y1) * (py - y1));
        }

        // Calculate the parameterized position along the line segment
        double t = ((px - x1) * dx + (py - y1) * dy) / (dx * dx + dy * dy);

        if (t < 0) {
            // Closest point is beyond the 'x1' end of the segment
            return sqrt((px - x1) * (px - x1) + (py - y1) * (py - y1));
        }

        if (t > 1) {
            // Closest point is beyond the 'x2' end of the segment
            return sqrt((px - x2) * (px - x2) + (py - y2) * (py - y2));
        }

        // Closest point is within the line segment, calculate the distance to the line
        double closestX = x1 + t * dx;
        double closestY = y1 + t * dy;
        return sqrt((px - closestX) * (px - closestX) + (py - closestY) * (py - closestY));
    }

}
