
package ProgramAplikasiPembelianQuickChicken;

import javax.swing.table.DefaultTableModel;


public class StrukturPenjualan {
    private double subTotal=0;
    private double ppn=0;
    private double total=0;
    private DefaultTableModel tabel = new DefaultTableModel();

    
    public StrukturPenjualan(){
        getTabel().addColumn("Menu");
        getTabel().addColumn("Harga");
        getTabel().addColumn("Jumlah");
        getTabel().addColumn("Satuan");
        getTabel().addColumn("Total Harga");
    }

    public double countSubtotal(){
        subTotal=0;
        for (int i=0;i<tabel.getRowCount();i++){
            subTotal=subTotal+Double.parseDouble(tabel.getValueAt(i, 4).toString());
        }
        return subTotal;
    }
    
    public double countPPN(){
        ppn=subTotal*0.1;
        ppn = Math.ceil(ppn);
        return ppn;
    }
    
    public double countTotal(){
        total=subTotal+ppn+1;
        return total;
    }

    public double getSubTotal() {
        return subTotal;
    }


    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getPpn() {
        return ppn;
    }


    public void setPpn(double ppn) {
        this.ppn = ppn;
    }


    public double getTotal() {
        return total;
    }


    public void setTotal(double total) {
        this.total = total;
    }


    public DefaultTableModel getTabel() {
        return tabel;
    }


    public void setTabel(DefaultTableModel tabel) {
        this.tabel = tabel;
    }

    
}
