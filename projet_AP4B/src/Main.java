import controller.Control;
import model.Graph;
import view.Interface;
public class Main {
    public static void main(String[] args) {

        Graph graph = new Graph();

        Interface inter = new Interface(1280,740,graph);

        new Control(inter,graph);

    }
 }



