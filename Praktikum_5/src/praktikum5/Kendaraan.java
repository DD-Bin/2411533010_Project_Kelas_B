package praktikum5;

public abstract class Kendaraan {
    protected String merk;
    protected String model;
    protected int tahunProduksi;

    public Kendaraan(String merk, String model, int tahunProduksi) {
        this.merk = merk;
        this.model = model;
        this.tahunProduksi = tahunProduksi;
    }

    // Method abstrak
    public abstract void nyalakanMesin();

    // Method final agar tidak bisa di-override
    public final void tampilkanInfo() {
        System.out.println("Merk: " + merk);
        System.out.println("Model: " + model);
        System.out.println("Tahun Produksi: " + tahunProduksi);
    }
}
