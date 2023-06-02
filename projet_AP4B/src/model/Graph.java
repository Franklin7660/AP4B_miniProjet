package model;

import java.io.*;
import java.util.*;

import java.nio.file.*;

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

        buildFromFile("test.txt");
    }

    public void buildFromFile(String fileName){
        try {
            File file = new File("src/saved_files/" + fileName);
            BufferedReader buffer = new BufferedReader(new FileReader(file));

            String line = "";
            while (!Objects.equals((line = buffer.readLine()), "end")){
                System.out.println(line);
                String[] subStrings = line.split(" ");
                String objectType = subStrings[0];

                if (Objects.equals(objectType, "sommet")){
                    String[] attributes = Arrays.copyOfRange(subStrings, 1, subStrings.length);
                    int id =0;
                    int x =0;
                    int y =0;
                    String nom = "unnamed";

                    for(String attribute : attributes){
                        String[] separated = attribute.split("=");
                        String name = separated[0];
                        String value = separated[1];

                        if (Objects.equals(name, "id")){id = Integer.parseInt(value);}
                        else if (Objects.equals(name, "x")){x = Integer.parseInt(value);}
                        else if (Objects.equals(name, "y")){y = Integer.parseInt(value);}
                        else if (Objects.equals(name, "nom")){nom = value;}
                    }
                    addSommet(new Sommet(id,x,y,nom));
                }

                else if(Objects.equals(objectType, "rue")){
                    String[] attributes = Arrays.copyOfRange(subStrings, 1, subStrings.length);
                    String nom = "unnamed";

                    for(String attribute : attributes){
                        String[] separated = attribute.split("=");
                        String name = separated[0];
                        String value = separated[1];

                        if (Objects.equals(name, "nom")){nom = value;}
                    }
                    addRue(new Rue(nom));
                }

                else if(Objects.equals(objectType, "arc")){
                    String[] attributes = Arrays.copyOfRange(subStrings, 1, subStrings.length);
                    int or =0;
                    int dest =0;
                    String rue = "unnamed";

                    for(String attribute : attributes){
                        String[] separated = attribute.split("=");
                        String name = separated[0];
                        String value = separated[1];

                        if (Objects.equals(name, "or")){or = Integer.parseInt(value);}
                        else if (Objects.equals(name, "dest")){dest = Integer.parseInt(value);}
                        else if (Objects.equals(name, "rue")){rue = value;}
                    }
                    addArc(new Arc(getSommetById(or),getSommetById(dest),getRueByName(rue)));
                }
            }


        } catch (IOException e) {
            System.out.println("Error : Graph file was not found");
        }
    }

    private Sommet getSommetById(int id){
        for(Sommet sommet : listeSommet){if (sommet.getId() == id){
            return sommet;
        }}
        System.out.println("Error : No sommet with that id");
        return null;
    }
    private Rue getRueByName(String name){
        for(Rue rue : listeRue){if (Objects.equals(rue.name, name)){
            return rue;
        }}
        System.out.println("Error : No rue with that name");
        return null;
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
