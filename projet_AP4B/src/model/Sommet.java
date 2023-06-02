package model;

import java.util.List;
public class Sommet {
    private int id ;
    private String nom ;
    private int x;
    private int y;
    private List<Arc> listeArc;

    public Sommet(int id,int x, int y , String nom){
        this.id = id;
        this.x = x;
        this.y = y;
        this.nom = nom;
    }
    public int getId(){
        return this.id;
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
