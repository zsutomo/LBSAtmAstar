package id.tugasakhir.zakaria.lbsatmastar.modul;

import java.util.List;

import id.tugasakhir.zakaria.lbsatmastar.model.Route;

/**
 * Created by zsuto_000 on 7/28/2016.
 */
public interface DirectionFinderListener {
    void onDirectionFinderStart();
    void onDirectionFinderSuccess(List<Route> routes);
}
