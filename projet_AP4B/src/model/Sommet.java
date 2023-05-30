package model;

import java.util.List;
public class Sommet {
    private String name = "intersection";
    private String nom ;
    private int x;
    private int y;
    private List<Arc> listeArc;

    public Sommet(int x, int y , String nom){
        this.x = x;
        this.y = y;
        this.nom = nom;
    }
    public int getX(){
        return this.x;

    }
    public int getY(){
        return this.y;
    }

    public String getNom(){
        return nom;
    }
    public int Radius = 15;



}
