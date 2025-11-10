package praktikum5;

public final class Bus extends Kendaraan implements TransportasiUmum {
    private String kelasBus;

    public Bus(String merk, String model, int tahunProduksi, String kelasBus) {
        super(merk, model, tahunProduksi);
        this.kelasBus = kelasBus;
    }

    @Override
    public void nyalakanMesin() {
        System.out.println("Putar kunci untuk menyalakan");
    }

    @Override
    public String jenisBahanBakar() {
        return "Solar";
    }

    @Override
    public int kapasitasPenumpang() {
        return 45;
    }

    public void fiturBus() {
        System.out.println("Fitur Bus: Dilengkapi kursi nyaman dan fasilitas hiburan");
    }

    // Inner class JadwalPerjalanan
    class JadwalPerjalanan {
        private String rute;
        private String waktuBerangkat;

        public JadwalPerjalanan(String rute, String waktuBerangkat) {
            this.rute = rute;
            this.waktuBerangkat = waktuBerangkat;
        }

        public void tampilkanInfoJadwal() {
            System.out.println("Jadwal Perjalanan: Rute " + rute + ", Waktu Berangkat: " + waktuBerangkat);
        }
    }

    public void tampilkanDetail() {
        tampilkanInfo();
        System.out.print("Nyalakan Mesin: ");
        nyalakanMesin();
        System.out.println("Jenis Bahan Bakar: " + jenisBahanBakar());
        System.out.print("Info Konsumsi: ");
        infoKonsumsi();
        System.out.println("Kapasitas Penumpang: " + kapasitasPenumpang() + " penumpang");
        fiturBus();

        JadwalPerjalanan jadwal = new JadwalPerjalanan("Jakarta – Bandung", "08:00");
        jadwal.tampilkanInfoJadwal();
    }
}
