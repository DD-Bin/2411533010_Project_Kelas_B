package model;

public class minuman extends produk {
    private String ukuran; 

    public minuman(String id, String nama, double hargaDasar, String ukuran) {
        super(id, nama, hargaDasar);
        this.ukuran = ukuran;
    }

    // Polymorphism
    @Override
    public double hitungHargaFinal() {
    	// harga berdasarkan ukuran
        switch (ukuran.toLowerCase()) {
            case "large": return hargaDasar + 5000;
            case "medium": return hargaDasar + 3000;
            default: return hargaDasar; 
        }
    }

    public String getUkuran() { return ukuran; }
    public void setUkuran(String ukuran) { this.ukuran = ukuran; }
}