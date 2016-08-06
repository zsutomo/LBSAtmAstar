package id.tugasakhir.zakaria.lbsatmastar.model;

/**
 * Created by zsuto_000 on 6/24/2016.
 */
public class Jalan {
    public String nm_jalan;


    public Jalan() {

    }

    public Jalan(int id_jalan, String nm_jalan)
    {
        this.nm_jalan = nm_jalan;
    }

    public void setNamaJalan(String nm_jalan)
    {
        this.nm_jalan = nm_jalan;
    }

    public String getNamaJalan()
    {
        return nm_jalan;
    }
}
