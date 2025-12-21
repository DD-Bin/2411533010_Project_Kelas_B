package model;

public abstract class produk {
    protected String id;
    protected String nama;
    protected double hargaDasar;

    public produk(String id, String nama, double hargaDasar) {
        this.id = id;
        this.nama = nama;
        this.hargaDasar = hargaDasar;
    }

    public abstract double hitungHargaFinal();

    public String getId() {
    	return id;
    	}
    public String getNama() {
    	return nama; 
    	}
    public double getHargaDasar() {
    	return hargaDasar; 
    	}
    
    public void setNama(String nama) {
    	this.nama = nama; 
    	}
    public void setHargaDasar(double hargaDasar) {
    	this.hargaDasar = hargaDasar; 
    	}
}