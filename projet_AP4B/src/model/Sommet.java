package model;

import java.util.List;
public class Sommet {
    public static int ID = 0 ;
    private int id;
    private String nom ;
    private int x;
    private int y;
    public double shortestDistance;
    public Sommet predecessor;
    public boolean settled;

    public Sommet(int id, int x, int y , String nom){
        if(id==-1){
            ID++;
            this.id = ID;
        }
        else{
            this.id = id;
        }
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
    public void setX(int n){x = n;}
    public void setY(int n){y = n;}
    public String getNom(){
        return nom;
    }
    public int Radius = 15;



}
