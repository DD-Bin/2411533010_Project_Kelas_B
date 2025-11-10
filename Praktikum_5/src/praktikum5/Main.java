//package praktikum5;
//
//public class Main {
//    public static void main(String[] args) {
//        Mobil mobil = new Mobil("Toyota", "Avanza", 2021, "Automatic");
//        Bus bus = new Bus("Mercedes-Benz", "Bus Pariwisata", 2018, "eksekutif");
//
//        mobil.tampilkanDetail();
//        System.out.println();  
//        bus.tampilkanDetail();
//    }
//}

package praktikum5;

public class Main {
    public static void main(String[] args) {
        Pesawat pesawat = new Pesawat("Garuda", "Boeing 737", 100);
        pesawat.tampilkanDetail();
        System.out.println("Maskapai: " + pesawat.namaMaskapai());
        pesawat.jenisPenerbangan();
    }
}