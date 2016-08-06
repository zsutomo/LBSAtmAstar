package id.tugasakhir.zakaria.lbsatmastar.controller.AStar;

/**
 * Created by zsuto_000 on 7/30/2016.
 */
public class Heuristic {
    public Heuristic() {

}
    public double euclidean(Node a, Node b) {
        double x1 = a.latitude;
        double y1 = a.longitude;

        double x2 = b.latitude;
        double y2 = b.longitude;

        double euclidean = Math.sqrt( ((x2 - x1) * (x2 - x1)) + ((y2-y1) * (y2-y1))  );
        return euclidean;

    }
}
