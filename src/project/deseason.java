package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class deseason extends javax.swing.JFrame {
    
    DefaultTableModel mode3=new DefaultTableModel();
    public deseason() {
        initComponents();
        String titu[]={"YEAR","QUARTER","NO. OF PASSENGERS","SEASOANL INDEX","DESEASONALISED SALES"};
        mode3.setColumnIdentifiers(titu);
        jTable1.setModel(mode3);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 25)); // NOI18N
        jLabel1.setText("DESEASONALISED DATA");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 25)); // NOI18N
        jButton1.setText("BACK");
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

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setText("SHOW DATA");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(146, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(156, 156, 156))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(154, 154, 154)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2)
                            .addContainerGap()))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
new intro().setVisible(true);
this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        try
        { 
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sem","root","");
            System.out.println("Connected\n");
            
            Statement st = conn.createStatement(); 
            String q1 = "select * from si"; 
            ResultSet rs = st.executeQuery(q1); 
            int row[]=new int[28];
            for(int i=0;i<=28;i++) 
            { 
                if(rs.next()){
                    row[i]=Integer.parseInt(rs.getString(3));
            } }
            int i,j;
           int[] mov_tot= new int[28];
        for(i=0,j=0;i<25;i++)
    {
        mov_tot[j]=row[i]+row[i+1]+row[i+2]+row[i+3];
        System.out.println("dk  "+mov_tot[j]/4);
        j++;      
    }
        float m[]=new float[28];
        for(i=0;i<24;i++)
    {
        m[i]=(mov_tot[i]+mov_tot[i+1])/8;
        System.out.println("centered "+m[i]);
    }     
        float si[];
    si=new float[24];    
    for(i=0;i<24;i++)
    {
    si[i]=(float)(row[i+2]/m[i])*100;
    System.out.println("percent  "+si[i]);
    }
          
        float[] ma = new float[4];
        ma[0]=(si[2]+si[6]+si[10]+si[18])/4;
        ma[1]=(si[3]+si[7]+si[19]+si[23])/4;
        ma[2]=(si[4]+si[8]+si[12]+si[20])/4;
        ma[3]=(si[5]+si[9]+si[13]+si[21])/4;
        float g_avg=(ma[0]+ma[1]+ma[2]+ma[3])/4;
    float sea_ind[];
    sea_ind=new float[4];
    float sid[]=new float[28];
    for(i=0;i<sea_ind.length;i++)
    {
        sea_ind[i]=100*ma[i]/g_avg;
        System.out.println(sea_ind[i]);
    } 
    sid[0]=sid[4]=sid[8]=sid[12]=sid[16]=sid[20]=sid[24]=sea_ind[0];
    sid[1]=sid[5]=sid[9]=sid[13]=sid[17]=sid[21]=sid[25]=sea_ind[1];
    sid[2]=sid[6]=sid[10]=sid[14]=sid[18]=sid[22]=sid[26]=sea_ind[2];
    sid[3]=sid[7]=sid[11]=sid[15]=sid[19]=sid[23]=sid[27]=sea_ind[3];
    float[] ds=new float[28];
    
    System.out.println("ds");
    int k=2,l=3;
    for(i=0,j=1;i<28;)
    { 
        ds[i]=(100*row[i])/sea_ind[0];
        System.out.println(ds[i]);
        i=i+4;
        ds[j]=(100*row[j])/sea_ind[1];
        System.out.println(ds[j]);
        j=j+4;
        ds[k]=(100*row[k])/sea_ind[2];
        System.out.println(ds[k]);
        k=k+4;
        ds[l]=(100*row[l])/sea_ind[3];
        System.out.println(ds[l]);
        l=l+4;
    }
    rs = st.executeQuery(q1); 
    for(i=0;i<=28;i++) 
            { 
                if(rs.next()){
                    String rowData[]={rs.getString(1),rs.getString(2),rs.getString(3),Float.toString(sid[i]),Float.toString(ds[i])};
                    mode3.addRow(rowData);
            } }
    
    conn.close(); 
        } 
        catch(Exception ex) 
        { 
            System.err.println(ex); 
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(deseason.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(deseason.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(deseason.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(deseason.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new deseason().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
