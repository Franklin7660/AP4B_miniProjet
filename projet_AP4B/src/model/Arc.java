package model;

public class Arc {
    private Sommet origine;
    private Sommet destination;
    private float distance;
    public Rue rue;

    public Arc(Sommet origine, Sommet destination,Rue rue_){
        this.origine = origine;
        this.destination = destination;
        this.rue = rue_;
        rue.arcs.add(this);
    }
    public Sommet getOrigine(){
        return  origine;
    }
    public Sommet getDestination(){
        return destination;
    }
    public int getOrigineX(){
        return origine.getX();
    }
    public int getOrigineY(){
        return  origine.getY();
    }
    public String getOrigineNom(){
        return origine.getNom();
    }
    public String getDestinationNom(){
        return destination.getNom();
    }
    public double getAngle(Sommet origin, Sommet destination) {
        int originX = origin.getX();
        int originY = origin.getY();
        int destinationX = destination.getX();
        int destinationY = destination.getY();

        double deltaX = destinationX - originX;
        double deltaY = destinationY - originY;

        return Math.atan2(deltaY, deltaX);
    }

    public int getDestinationX(){
        return  destination.getX();
    }
    public int getDestinationY(){
        return  destination.getY();
    }
}
