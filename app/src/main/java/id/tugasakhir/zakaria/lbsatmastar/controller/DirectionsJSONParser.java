package id.tugasakhir.zakaria.lbsatmastar.controller;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zsuto_000 on 7/1/2016.
 */
public class DirectionsJSONParser {
    public List<List<HashMap<String, String>>> parse(JSONObject jObject) {
        List<List<HashMap<String, String>>> routes = new ArrayList<List<HashMap<String,String>>>();
        JSONArray jRoutes = null;
        JSONArray jLegs = null;
        JSONArray jSteps = null;

        try {
            jRoutes = jObject.getJSONArray("routes");

            for (int i = 0; i<jRoutes.length();i++) {
                jLegs = ( (JSONObject)jLegs.get(i)).getJSONArray("legs");
                List path = new ArrayList<HashMap<String, String>>();

                for (int j = 0; j<jLegs.length();j++) {
                    jSteps = ( (JSONObject) jLegs.get(j)).getJSONArray("steps");

                    for (int k = 0; k<jSteps.length(); k++) {
                        String polyline = "";
                        polyline = (String) ((JSONObject) ((JSONObject) jSteps.get(k)).get("polyline")).get("points");
                        List<LatLng> list = decodePolyline(polyline);

                        for (int l=0; l<list.size(); l++) {
                            HashMap<String, String> hm = new HashMap<String, String>();
                            hm.put("lat", Double.toString(((LatLng) list.get(l)).latitude));
                            hm.put("long", Double.toString(((LatLng) list.get(l)).longitude));
                            path.add(hm);
                        }
                    }
                    routes.add(path);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {

        }
        return routes;
    }

    private List<LatLng> decodePolyline(String encoded) {

        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lati =0, longi=0;

        while (index < len) {
            int b, shift = 0, result =0;
            do {
                b = encoded.charAt(index++) -63;
                result |= (b & 0x1f) << shift;
                shift +=5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lati += dlat;

            LatLng p = new LatLng((((double) lati / 1E5)), (((double) longi / 1E5)));
            poly.add(p);
        }
        return poly;
    }
}
