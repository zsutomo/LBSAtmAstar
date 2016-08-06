package id.tugasakhir.zakaria.lbsatmastar.controller.AStar;

/**
 * Created by zsuto_000 on 7/30/2016.
 */
public class Node {
    public final String value;
    public double nilaiG;
    public double nilaiH;
    public double nilaiF = 0;
    public Edge[] adjacencies;
    public Node parent;

    public double latitude;
    public double longitude;

    public Node(String val, double hVal){
        value = val;
        nilaiH = hVal;
    }

    public Node(String val, double latitude, double longitude) {
        value = val;
        this.latitude = latitude;
        this.longitude = longitude;
    }



    public String toString(){
        return value;
    }

    public void setHscores(double hVal) {
        this.nilaiH = hVal;
    }
}
