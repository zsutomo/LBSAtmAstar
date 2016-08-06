package id.tugasakhir.zakaria.lbsatmastar.controller.AStar;

/**
 * Created by zsuto_000 on 7/30/2016.
 */
public class Edge {
    public final double cost;
    public final Node target;

    public Edge(Node targetNode, double costVal){
        target = targetNode;
        cost = costVal;
    }
}
