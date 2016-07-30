package id.tugasakhir.zakaria.lbsatmastar.model;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by zsuto_000 on 7/30/2016.
 */
public class Bound  {

    private LatLng northEast;
    private LatLng southEasth;

    public LatLng getNorthEast(){
        return northEast;
    }

    public void setNorthEast(LatLng northEast) {
        this.northEast = northEast;
    }

    public LatLng getSouthEast(){
        return southEasth;
    }

    public void setSouthEast(LatLng southEast) {
        this.southEasth = southEast;
    }
}
