package model;

public class Customer {
    private String id;
    private String nama;
    private String email;
    private String alamat;
    private String hp;

    public Customer() {}

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

    public String getEmail() { 
    	return email; 
    	}
    public void setEmail(String email) {
    	this.email = email;
    	}

    public String getAlamat() {
    	return alamat; 
    	}
    public void setAlamat(String alamat) { 
    	this.alamat = alamat; 
    	}

    public String getHp() {
    	return hp; 
    	}
    public void setHp(String hp) { 
    	this.hp = hp; 
    	}
}
