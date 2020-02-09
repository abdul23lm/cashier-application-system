
package ProgramAplikasiPembelianQuickChicken;

public class StrukturBarang {
    private String namaBarang;
    private String satuan;
    private double harga;

    public StrukturBarang(String namaBarang, String satuan, double harga) {
        this.namaBarang = namaBarang;
        this.satuan = satuan;
        this.harga = harga;
    }

    @Override
    public String toString() {
        return this.namaBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }


    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }


    public String getSatuan() {
        return satuan;
    }


    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }


    public double getHarga() {
        return harga;
    }


    public void setHarga(double harga) {
        this.harga = harga;
    }
    
}
