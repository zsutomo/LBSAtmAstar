package id.tugasakhir.zakaria.lbsatmastar.modul;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;


/**
 * Created by zsuto_000 on 7/28/2016.
 */
public class Route {
    public Distance distance;
    public Duration duration;
    public String endAddress;
    public LatLng endLocation;
    public String startAddress;
    public LatLng startLocation;

    public List<LatLng> points;

}
