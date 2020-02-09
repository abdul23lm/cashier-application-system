package ProgramAplikasiPembelianQuickChicken;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class MainPenjualanQuickChicken extends javax.swing.JFrame {
    StrukturBarang barang;
    StrukturPenjualan penjualan=new StrukturPenjualan();
    private Object lblkembalian;
    private double subTotal=0;
    private double ppn=0;
    private double total=0;
    private DefaultTableModel tabel = new DefaultTableModel();
    private double tunai;

    public MainPenjualanQuickChicken() {
        initComponents();
        fillComboBarang();
        tblBarang.setModel(penjualan.getTabel());
        
    }
    
    private void fillComboBarang(){
        StrukturBarang barang1 = new StrukturBarang("Paha Bawah", "pcs", 8182);
        StrukturBarang barang2 = new StrukturBarang("Nasi + Paha Bawah", "pcs", 10909);
        StrukturBarang barang3 = new StrukturBarang("Nasi + Sayap ", "pcs", 10000);
        StrukturBarang barang4 = new StrukturBarang("Nasi + Paha Bawah Marathon", "pcs", 12727);
        StrukturBarang barang5 = new StrukturBarang("Nasi + Sayap Marathon", "pcs", 11818);
        StrukturBarang barang6 = new StrukturBarang("Orange Quash", "pcs", 4545);
        StrukturBarang barang7 = new StrukturBarang("Iced Green Tea Latte", "pcs", 7273);
        
        cboBarang.addItem(barang1);
        cboBarang.addItem(barang2);
        cboBarang.addItem(barang3);
        cboBarang.addItem(barang4);
        cboBarang.addItem(barang5);
        cboBarang.addItem(barang6);
        cboBarang.addItem(barang7);
    }
    public PageFormat getPageFormat(PrinterJob pj)
{
    
    PageFormat pf = pj.defaultPage();
    Paper paper = pf.getPaper();    

    double middleHeight =8.0;  
    double headerHeight = 2.0;                  
    double footerHeight = 2.0;                  
    double width = convert_CM_To_PPI(8);      //printer know only point per inch.default value is 72ppi
    double height = convert_CM_To_PPI(headerHeight+middleHeight+footerHeight); 
    paper.setSize(width, height);
    paper.setImageableArea(                    
        0,
        10,
        width,            
        height - convert_CM_To_PPI(1)
    );  
            
    pf.setOrientation(PageFormat.PORTRAIT);           
    pf.setPaper(paper);    

    return pf;
}
    
    protected static double convert_CM_To_PPI(double cm) {            
	        return toPPI(cm * 0.393600787);            
}
 
protected static double toPPI(double inch) {            
	        return inch * 72d;            
}






public class BillPrintable implements Printable {
    
   
    
    
  public int print(Graphics graphics, PageFormat pageFormat,int pageIndex) 
  throws PrinterException 
  {    
      
                
        
      int result = NO_SUCH_PAGE;    
        if (pageIndex == 0) {                    
        
            Graphics2D g2d = (Graphics2D) graphics;                    

            double width = pageFormat.getImageableWidth();                    
           
            g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY()); 



            FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));

            int idLength=metrics.stringWidth("000");
            int amtLength=metrics.stringWidth("000000");
            int qtyLength=metrics.stringWidth("00000");
            int priceLength=metrics.stringWidth("000000");
            int prodLength=(int)width - idLength - amtLength - qtyLength - priceLength-17;


            
            int productPosition = 0;
            int discountPosition= prodLength+5;
            int pricePosition = discountPosition +idLength+10;
            int qtyPosition=pricePosition + priceLength + 4;
            int amtPosition=qtyPosition + qtyLength;
            
            
              
        try{
            /*Draw Header*/
            int y=20;
            int yShift = 10;
            int headerRectHeight=15;
            int headerRectHeighta=40;
            
                
            g2d.setFont(new Font("Monospaced",Font.PLAIN,9));
            g2d.drawString("            Quick Chicken           ",12,y);y+=yShift;
            g2d.drawString("=====================================",12,y);y+=yShift;
            g2d.drawString("            C Puseur Jaya           ",12,y);y+=yShift;
            g2d.drawString("            08112571383             ",12,y);y+=yShift;
            g2d.drawString("=====================================",12,y);y+=headerRectHeight;
            g2d.drawString("Counter #                           ",12,y);y+=yShift;
            g2d.drawString("======================================",10,y);y+=yShift;
            g2d.drawString("   Tamu                             1",10,y);y+=yShift;
            g2d.drawString("1 Paha Bawah                    8.182",10,y);y+=yShift;
            g2d.drawString("1 Nasi + Paha Bawah             10.909",10,y);y+=yShift;
            g2d.drawString("1 Nasi + Sayap                  10.000",10,y);y+=yShift;
            g2d.drawString("1 Nasi + Paha Bawah Marathon    25.454",10,y);y+=yShift;
            g2d.drawString("1 Nasi + Sayap Marathon         23.636",10,y);y+=yShift;
            g2d.drawString("1 Orange Quash                  4.545",10,y);y+=yShift;
            g2d.drawString("1 Iced Green Tea Latter         7.273",10,y);y+=yShift;
            g2d.drawString("======================================",10,y);y+=yShift;
            g2d.drawString(" Subtotal                       89.999",10,y);y+=yShift;
            g2d.drawString(" P.Rest 10%                      9.000",10,y);y+=yShift;
            g2d.drawString(" 9 Total                        98.999",10,y);y+=yShift;
            g2d.drawString(" Ella                                  ",10,y);y+=yShift;
            g2d.drawString(" Dine In                               ",10,y);y+=yShift;
            g2d.drawString("    Rounding                          1",10,y);y+=yShift;
            g2d.drawString("    Bayar                       100.000",10,y);y+=yShift;
            g2d.drawString("    Cash                         99.000",10,y);y+=yShift;
            g2d.drawString("    Kembali                       1.000",10,y);y+=yShift;
            g2d.drawString("Selasa      5- 6-2018 20:01:19         ",10,y);y+=yShift;
            g2d.drawString("#006771                                ",10,y);y+=yShift;
            g2d.drawString("         T e r i m a  K a s i h        ",10,y);y+=yShift;
            g2d.drawString("           Atas Kunjungannya           ",10,y);y+=yShift;
            g2d.drawString("      Kritik dan saran  0811-250-006   ",10,y);y+=yShift;
            g2d.drawString("      Info Catering     0811-2572-955  ",10,y);y+=yShift;

                           
          

    }
    catch(Exception r){
    r.printStackTrace();
    }

              result = PAGE_EXISTS;    
          }    
          return result;    
      }
   }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cboBarang = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblHarga = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblSatuan = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBarang = new javax.swing.JTable();
        btnSimpan = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblSubtotal = new javax.swing.JLabel();
        chkPPN = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        lblPPN = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        btnHapus = new javax.swing.JButton();
        txtTunai = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btnCetak = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        btnBayar = new javax.swing.JButton();
        lblKembali = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Program Aplikasi Penjualan Quick Chicken");
        setBackground(new java.awt.Color(255, 255, 153));
        setForeground(new java.awt.Color(153, 255, 51));
        setIconImages(null);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Source Sans Pro", 1, 24)); // NOI18N
        jLabel1.setText("Program Aplikasi Penjualan Quick Chicken");

        jLabel2.setText("Pilih Menu");

        cboBarang.setToolTipText("");
        cboBarang.setNextFocusableComponent(txtQuantity);
        cboBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboBarangActionPerformed(evt);
            }
        });

        jLabel3.setText("Jumlah Beli");

        jLabel4.setText("Harga");

        lblHarga.setText("00");

        jLabel5.setText("Rp.");

        lblSatuan.setText("pcs");

        txtQuantity.setNextFocusableComponent(btnSimpan);
        txtQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantityActionPerformed(evt);
            }
        });
        txtQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQuantityKeyPressed(evt);
            }
        });

        tblBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Menu", "Harga", "Jumlah", "Satuan", "Total Harga"
            }
        ));
        jScrollPane1.setViewportView(tblBarang);

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        jLabel6.setText("Subtotal :");

        jLabel7.setText("Rp.");

        lblSubtotal.setText("00");

        chkPPN.setText("PPN");
        chkPPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPPNActionPerformed(evt);
            }
        });

        jLabel8.setText("Rp.");

        lblPPN.setText("00");

        jLabel9.setText("Total");

        jLabel10.setText("Rp.");

        lblTotal.setText("00");

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        txtTunai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTunaiKeyTyped(evt);
            }
        });

        jLabel11.setText("Bayar Tunai");

        btnCetak.setText("Cetak Struk");
        btnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakActionPerformed(evt);
            }
        });

        jLabel13.setText("Kembalian");

        btnBayar.setText("Bayar");
        btnBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBayarActionPerformed(evt);
            }
        });
        btnBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnBayarKeyPressed(evt);
            }
        });

        lblKembali.setText("00");

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icon_qc.png"))); // NOI18N
        jLabel12.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel12.setMinimumSize(new java.awt.Dimension(100, 100));

        jLabel14.setFont(new java.awt.Font("Yu Gothic", 1, 16)); // NOI18N
        jLabel14.setText("Created by Abdul LM x Jamaludin");

        jLabel15.setText("Rp.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3))
                                .addGap(108, 108, 108)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cboBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(jLabel5)
                                                .addGap(58, 58, 58)
                                                .addComponent(lblHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(lblSatuan)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 152, Short.MAX_VALUE)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(chkPPN)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(lblPPN, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel15)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel7)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(114, 114, 114)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel13))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel10)
                                                .addGap(18, 18, 18)
                                                .addComponent(lblKembali))
                                            .addComponent(txtTunai, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnCetak))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(44, 44, 44))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel2)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel4)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(cboBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(lblHarga))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(lblSatuan))))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSimpan)
                        .addGap(18, 18, 18)
                        .addComponent(btnHapus)))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(chkPPN)
                        .addComponent(jLabel8)
                        .addComponent(lblPPN))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(lblSubtotal)
                        .addComponent(jLabel6)
                        .addComponent(jLabel11)
                        .addComponent(txtTunai, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTotal)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(jLabel10)
                        .addComponent(jLabel13)
                        .addComponent(lblKembali)
                        .addComponent(jLabel15)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCetak)
                            .addComponent(btnBayar))
                        .addGap(0, 9, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtQuantityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantityKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
            btnSimpan.requestFocus();
    }//GEN-LAST:event_txtQuantityKeyPressed

    private void cboBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboBarangActionPerformed
        barang = (StrukturBarang)cboBarang.getSelectedItem();
        lblHarga.setText(NumberFormat.getNumberInstance().format(barang.getHarga()));
        lblSatuan.setText(barang.getSatuan()); 
    }//GEN-LAST:event_cboBarangActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        String[] data = new String[5];
        double harga, jumlah=0;
        int qty=0;
        
        data[0]=barang.getNamaBarang();
        harga=barang.getHarga();
        data[1]=String.valueOf(barang.getHarga());
        qty=Integer.parseInt(txtQuantity.getText());
        data[2]=txtQuantity.getText();
        data[3]=barang.getSatuan();
        jumlah=harga*qty;
        data[4]=String.valueOf(jumlah);
        
        penjualan.getTabel().addRow(data);
        lblSubtotal.setText(NumberFormat.getNumberInstance().format(penjualan.countSubtotal()));
        chkPPNActionPerformed(null);
        
        cboBarang.requestFocus();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void chkPPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPPNActionPerformed
        if (chkPPN.isSelected())
            lblPPN.setText(NumberFormat.getNumberInstance().format(penjualan.countPPN()));
        else{
            
            lblPPN.setText("0");
            penjualan.setPpn(0);
        }
        
        lblTotal.setText(NumberFormat.getNumberInstance().format(penjualan.countTotal()));
    }//GEN-LAST:event_chkPPNActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        penjualan.getTabel().removeRow(tblBarang.getSelectedRow());
        lblSubtotal.setText(NumberFormat.getNumberInstance().format(penjualan.countSubtotal()));
        chkPPNActionPerformed(null);
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakActionPerformed

                        
        PrinterJob pj = PrinterJob.getPrinterJob();        
        pj.setPrintable(new BillPrintable(),getPageFormat(pj));
        try {
             pj.print();
          
        }
         catch (PrinterException ex) {
                 ex.printStackTrace();
        }
    }//GEN-LAST:event_btnCetakActionPerformed

    private void txtQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantityActionPerformed

    }//GEN-LAST:event_txtQuantityActionPerformed

    private void txtTunaiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTunaiKeyTyped

    }//GEN-LAST:event_txtTunaiKeyTyped

    private void btnBayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBayarKeyPressed

    }//GEN-LAST:event_btnBayarKeyPressed

    private void btnBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBayarActionPerformed

        
    double tunai = Integer.parseInt(txtTunai.getText());
    total = penjualan.countTotal();
    double kembali = tunai-total;
    lblKembali.setText(NumberFormat.getNumberInstance().format(kembali));
    }//GEN-LAST:event_btnBayarActionPerformed

    public static void main(String args[]) {
      
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MainPenjualanQuickChicken().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBayar;
    private javax.swing.JButton btnCetak;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JComboBox cboBarang;
    private javax.swing.JCheckBox chkPPN;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHarga;
    private javax.swing.JLabel lblKembali;
    private javax.swing.JLabel lblPPN;
    private javax.swing.JLabel lblSatuan;
    private javax.swing.JLabel lblSubtotal;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tblBarang;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtTunai;
    // End of variables declaration//GEN-END:variables

    private double getTotal(String format) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
