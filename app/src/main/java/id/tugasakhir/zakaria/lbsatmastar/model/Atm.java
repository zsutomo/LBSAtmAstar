/**
 * 
 */
package id.tugasakhir.zakaria.lbsatmastar.model;

/**
 * @author zsuto_000
 *
 */
public class Atm {
	 	private int id;
	    private String nama;
	    private String alamat;
	    private String informasi;
	    private double latitude;
	    private double longitude;
	    
	    public Atm(String nama, String alamat, String informasi, double latitude, double longitude) {
	        this.id = id;
	        this.nama = nama;
	        this.alamat = alamat;
	        this.informasi = informasi;
	        this.latitude = latitude;
	        this.longitude = longitude;
	    }

	public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getNama() {
	        return nama;
	    }

	    public void setNama(String nama) {
	        this.nama = nama;
	    }

	    public String getAlamat() {
	        return alamat;
	    }

	    public void setAlamat(String alamat) {
	        this.alamat = alamat;
	    }

	    public String getInformasi() {
	        return informasi;
	    }

	    public void setInformasi(String informasi) {
	        this.informasi = informasi;
	    }

	    public double getLatitude() {
	        return latitude;
	    }

	    public void setLatitude(double latitude) {
	        this.latitude = latitude;
	    }

	    public double getLongitude() {
	        return longitude;
	    }

	    public void setLongitude(double longitude) {
	        this.longitude = longitude;
	    }

}
