package id.tugasakhir.zakaria.lbsatmastar.modul;

import java.util.List;

/**
 * Created by zsuto_000 on 7/28/2016.
 */
public interface DirectionFinderListener {
    void onDirectionFinderStart();
    void onDirectionFinderSuccess(List<Route> routes);
}
