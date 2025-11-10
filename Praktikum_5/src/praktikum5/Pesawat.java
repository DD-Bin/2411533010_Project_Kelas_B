package praktikum5;

public class Pesawat extends Kendaraan implements TransportasiUdara, Maskapai {

    public Pesawat(String merk, String model, int tahunProduksi) {
        super(merk, model, tahunProduksi);
    }

    @Override
    public void nyalakanMesin() {
        System.out.println("Bersiap lepas landas");
    }

    @Override
    public String jenisBahanBakar() {
        return "Avtur";
    }

    @Override
    public void jenisPenerbangan() {
        System.out.println("Jenis Penerbangan: Domestik");
    }

    @Override
    public String namaMaskapai() {
        return merk; 
    }

    public void tampilkanDetail() {
        tampilkanInfo();
        System.out.print("Nyalakan Mesin: ");
        nyalakanMesin();
        System.out.println("Jenis Bahan Bakar: " + jenisBahanBakar());
    }
}

