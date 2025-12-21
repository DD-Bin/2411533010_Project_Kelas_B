package model;

import java.util.List;
import util.FormatterUtil;

public class pesanan {
    private String id;
    private String namaPelanggan;
    private List<produk> listItem;
    private double totalHarga;
    private String status;

    public pesanan(String id, String namaPelanggan, List<produk> listItem, double totalHarga) {
        this.id = id;
        this.namaPelanggan = namaPelanggan;
        this.listItem = listItem;
        this.totalHarga = totalHarga;
        this.status = "LUNAS";
    }

    public void setStatus(String status) { this.status = status; }
    public String getStatus() { return status; }
    public double getTotalHarga() { return totalHarga; }

    public String getStruk() {
        StringBuilder sb = new StringBuilder();
        sb.append(" STRUK PEMESANAN\n");
        sb.append("ID    : ").append(id).append("\n");
        sb.append("Nama  : ").append(namaPelanggan).append("\n");
        sb.append(",\n");
        
        for (produk p : listItem) {
            sb.append("- ").append(p.getNama())
              .append(" : ").append(FormatterUtil.formatRupiah(p.hitungHargaFinal())).append("\n");
        }
        
        sb.append(".\n");
        sb.append("TOTAL : ").append(FormatterUtil.formatRupiah(totalHarga));
        return sb.toString();
    }
}