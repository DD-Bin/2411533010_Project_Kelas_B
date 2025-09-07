package model;

public class Costumer {
	
	String id, nama, alamat, nomorHp;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getNomorHp() {
		return nomorHp;
	}

	public void setNomorHp(String nomorHp) {
		this.nomorHp = nomorHp;
	}

	public Costumer ( String id, String nama, String alamat, String nomorHp )	{
		Costumer costumer = new Costumer("001", "dede", "kapalo koto", "082222");

	}
}
