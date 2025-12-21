package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class pesananBuilder {
    private String id;
    private String namaPelanggan;
    private List<produk> listItem = new ArrayList<>();


    public pesananBuilder setIdOtomatis() {
        int angkaAcak = new Random().nextInt(9000) + 1000; 
        this.id = "DEDE-" + angkaAcak; 
        return this;
    }
    
    public pesananBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public pesananBuilder setNamaPelanggan(String nama) {
        this.namaPelanggan = nama;
        return this;
    }

    public pesananBuilder tambahItem(produk p) {
        this.listItem.add(p);
        return this;
    }

    public pesanan build() {
        double total = 0;
        for (produk p : listItem) {
            total += p.hitungHargaFinal();
        }
        return new pesanan(id, namaPelanggan, listItem, total);
    }
}