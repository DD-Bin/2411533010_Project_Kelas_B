package model;

public class makanan extends produk {
    private boolean isPedas;

    public makanan(String id, String nama, double hargaDasar, boolean isPedas) {
        super(id, nama, hargaDasar);
        this.isPedas = isPedas;
    }

    // Polymorphism
    @Override
    public double hitungHargaFinal() {
        // pedas tambah harga 
        return isPedas ? (hargaDasar + 2000) : hargaDasar;
    }

    public boolean isPedas() { return isPedas; }
    public void setPedas(boolean pedas) { isPedas = pedas; }
}