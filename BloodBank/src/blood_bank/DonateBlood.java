/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blood_bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Mukul Sachdeva
 */
public class DonateBlood extends javax.swing.JFrame {
    
    private static int userId=0;
    private Connection con;
    private static String grp="";
    /**
     * Creates new form DonateBlood
     */
    public DonateBlood(int id,String bgrp) {
        initComponents();
        userId=id;
        grp=bgrp;
        try{
        Class.forName("oracle.jdbc.driver.OracleDriver");

        con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sachdeva@123");
        }
        catch(Exception E){
            System.out.println(E);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Units to be donated");

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(1, null, 5, 1));

        jButton1.setText("Donate");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(jButton1)))
                .addContainerGap(468, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 289, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(368, 368, 368))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        int value=(int)jSpinner1.getValue();
        
        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
        Date dat1;
        try{
            
            PreparedStatement ps=con.prepareCall("Select donatedon from donors where id=? order by donatedon ");
            ps.setInt(1,userId);
            ResultSet rs1=ps.executeQuery();
            
            int year=0;
            int month=0;
            int day=0;
            while(rs1.next()){
                
                
            Timestamp timestamp = rs1.getTimestamp(1);
            
            System.out.println(timestamp);
                 
            if(rs1.next()==false){
                System.out.println("inside if");
                long time = timestamp.getTime();
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(time);
                 year=cal.get(Calendar.YEAR);
                 month=cal.get(Calendar.MONTH);
                 day=cal.get(Calendar.DAY_OF_MONTH);
                break;
            }
                
            }
            
            
            
            
                long present=date.getTime();
                Calendar cal1 = Calendar.getInstance();
                cal1.setTimeInMillis(present);
                int year1=cal1.get(Calendar.YEAR);
                int month1=cal1.get(Calendar.MONTH);
                int day1=cal1.get(Calendar.DAY_OF_MONTH);
                boolean allow=true;
                if(year1==year){
                    if(month1-month<=1){
                        
                        allow=false;
                    }
                    
                    else{
                        if(month1-month==2){
                            
                            if(day1<day){
                                allow=false;
                            }
                        }
                    }
                }
                
                if(allow){
            
            try{
            PreparedStatement pst=con.prepareCall("Insert into donors values(?,?,?,?)");
            
            
            pst.setInt(1,userId);
            pst.setInt(2,value);
            pst.setTimestamp(3, date);
            pst.setString(4,grp);
            
            
            if(pst.executeUpdate()>0){
                JOptionPane.showMessageDialog(this, "Thankyou !");
            }
            else{
                System.out.println("Error");
            }
            
            PreparedStatement pst1=con.prepareCall(" Select QUANTITY from AVAILABILITY where bloodgrp=?");
            pst1.setString(1,grp);
            ResultSet rst=pst1.executeQuery();
            int quantity =0;
            boolean exists=false;
            
            while(rst.next()){
                
                exists=true;
                quantity=rst.getInt(1);
                
                
            }
            if(exists){
                
                quantity=quantity+value;
                PreparedStatement p=con.prepareCall("Update availability set quantity=? where bloodgrp= ? ");
                p.setInt(1,quantity);
                p.setString(2,grp);
                if(p.executeUpdate()>0){
                    System.out.println("availabilty updated");
                }else{
                    System.out.println("not updated");
                }
            }
            else{
                
                PreparedStatement p=con.prepareCall("Insert into availability values(?,?)");
                p.setString(1,grp);
                p.setInt(2,value);
                
                if(p.executeUpdate()>0){
                    System.out.println("availabilty insert");
                }else{
                    System.out.println("not insert");
                }
                
                
                
            }
            }
            
            
        catch(Exception e){
            System.out.println(e);
        }
        }
                else
                    JOptionPane.showMessageDialog(this, "You can donate once in two months");
        }
        
        catch(Exception e){
            System.out.println(e);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DonateBlood.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DonateBlood.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DonateBlood.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DonateBlood.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DonateBlood(userId,grp).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
