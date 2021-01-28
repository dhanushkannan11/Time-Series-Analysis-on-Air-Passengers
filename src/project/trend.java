package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartPanel;  
import org.jfree.chart.JFreeChart;  
import org.jfree.data.category.DefaultCategoryDataset;  

public class trend extends javax.swing.JFrame {
DefaultTableModel mode4=new DefaultTableModel();
private static final long serialVersionUID = 1L;  
  
  public trend(String title) {  
    super(title);  
    // Create dataset  
    DefaultCategoryDataset dataset = createDataset();  
    // Create chart  
    JFreeChart chart = ChartFactory.createLineChart(  
        "Trend Values", // Chart title  
        "Year", // X-Axis Label  
        "Trend values", // Y-Axis Label  
        dataset  
        );  
  
    ChartPanel panel = new ChartPanel(chart);  
    setContentPane(panel);  
  }  
    public trend() {
        initComponents();
        String titu[]={"YEAR","QUARTER","DESEASONALISED DATA","TREND VALUES"};
        mode4.setColumnIdentifiers(titu);
        jTable1.setModel(mode4);
        int i,j;
        try
        { 
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sem","root","");
            System.out.println("Connected\n");
            
            Statement st = conn.createStatement(); 
            String q1 = "select * from si"; 
            ResultSet rs = st.executeQuery(q1); 
            int row[]=new int[28];
            for(i=0;i<=28;i++) 
            { 
                if(rs.next()){
                    row[i]=Integer.parseInt(rs.getString(3));
            } }
            
           int[] mov_tot= new int[28];
        for(i=0,j=0;i<25;i++)
    {
        mov_tot[j]=row[i]+row[i+1]+row[i+2]+row[i+3];
        //System.out.println("dk  "+mov_tot[j]/4);
        j++;      
    }
        float m[]=new float[28];
        for(i=0;i<24;i++)
    {
        m[i]=(mov_tot[i]+mov_tot[i+1])/8;
        //System.out.println("centered "+m[i]);
    }     
        float si[];
    si=new float[24];    
    for(i=0;i<23;i++)
    {
    si[i]=(float)(row[i+2]/m[i])*100;
    //System.out.println("percent  "+si[i]);
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
        //System.out.println(sea_ind[i]);
    } 
    sid[0]=sid[4]=sid[8]=sid[12]=sid[16]=sid[20]=sid[24]=sea_ind[0];
    sid[1]=sid[5]=sid[9]=sid[13]=sid[17]=sid[21]=sid[25]=sea_ind[1];
    sid[2]=sid[6]=sid[10]=sid[14]=sid[18]=sid[22]=sid[26]=sea_ind[2];
    sid[3]=sid[7]=sid[11]=sid[15]=sid[19]=sid[23]=sid[27]=sea_ind[3];
    float[] ds=new float[40];
    
    //System.out.println("ds");
    int k=2,l=3;
    for(i=0,j=1;i<28;)
    { 
        ds[i]=(100*row[i])/sea_ind[0];
        //System.out.println(ds[i]);
        i=i+4;
        ds[j]=(100*row[j])/sea_ind[1];
        //System.out.println(ds[j]);
        j=j+4;
        ds[k]=(100*row[k])/sea_ind[2];
        //System.out.println(ds[k]);
        k=k+4;
        ds[l]=(100*row[l])/sea_ind[3];
        //System.out.println(ds[l]);
        l=l+4;
    }

    i=0;j=0;
    DecimalFormat df= new DecimalFormat("#");
    df.setMaximumFractionDigits(8);
    
        double a;
        double b,a1,b1,b2,sum_y = 0,sum_xy=0,sum_x2=0;
        double year[]={2012,2013,2014,2015,2016,2017,2018};
        double x[]={-3,-2,-1,0,1,2,3};
        double x2[]= new double[x.length];
        double y[]= new double[x.length];
        double xy[]= new double[x.length];
        double trend[]=new double[28];
        while(i<28)
        {
            y[j]=ds[i]+ds[i+1]+ds[i+2]+ds[i+3];
            i=i+4;
            //System.out.println(y[j]);
            j++;
            }
        for(i=0;i<7;i++)
        {//System.out.println("dk");
            
            x2[i]=x[i]*x[i];
            xy[i]=x[i]*y[i];
            //System.out.println(x2[i]);
        }
        for(i=0;i<7;i++)
        {
            sum_y+=y[i];
            sum_xy+=xy[i];
            sum_x2+=x2[i];
        }
        a=sum_y/x.length;
        b=sum_xy/sum_x2;
        a1=12575837.71428571/4;
        b1=1047688.5714285715/16;
        b2=b1*0.5;
        System.out.println(a1+b2);
        System.out.println(b1);
        System.out.println(b2);
        System.out.println("The fitted trend equation is: Y= 3176699.6964285704+65480.53571428572*x");
        String str="Y= 3176699.6964285704 + 65480.53571428572*x";
        int code[]={-14,-13,-12,-11,-10,-9,-8,-7,-6,-5,-4,-3,-2,-1,0,1,2,3,4,5,6,7,8,9,10,11,12,13};
        for(i=0;i<28;i++)
        {
            trend[i]=3176699.6964285704+65480.53571428572*code[i];
            //System.out.println(trend[i]);
        }
        jTextField1.setText(str);
        jTextField1.setEditable(false);
        
        rs = st.executeQuery(q1);
        for(i=0;i<=28;i++) 
            { 
                if(rs.next()){
                    String rowData[]={rs.getString(1),rs.getString(2),Float.toString(ds[i]),Double.toString(trend[i])};
                    mode4.addRow(rowData);
            } }
       
        
        conn.close(); 
        }
        catch(Exception ex) 
        { 
            System.err.println(ex); 
        }
    }
     private DefaultCategoryDataset createDataset() {  
  
    String series1 = "Trend";   
  
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
    
    dataset.addValue(2259972.196, series1, "2012(QTR1)");  
    dataset.addValue(2325452.732, series1, "2012(QTR2)");  
    dataset.addValue(2390933.26, series1, "2012(QTR3)");  
    dataset.addValue(2456413.80, series1, "2012(QTR4)");  
    dataset.addValue(2521894.33, series1, "2013(QTR1)");  
    dataset.addValue(2587374.87, series1, "2013(QTR2)");  
    dataset.addValue(2652855.41, series1, "2013(QTR3)");  
    dataset.addValue(2718335.94, series1, "2013(QTR4)");   
    dataset.addValue(2783816.48, series1, "2014(QTR1)");  
    dataset.addValue(2849297.01, series1, "2014(QTR2)");  
    dataset.addValue(2914777.55, series1, "2014(QTR3)");  
    dataset.addValue(2980258.08, series1, "2014(QTR4)");
    dataset.addValue(3045738.62, series1, "2015(QTR1)");  
    dataset.addValue(3111219.16, series1, "2015(QTR2)");  
    dataset.addValue(3176699.69, series1, "2015(QTR3)");  
    dataset.addValue(3242180.23, series1, "2015(QTR4)");
    dataset.addValue(3307660.76, series1, "2016(QTR1)");  
    dataset.addValue(3373141.30, series1, "2016(QTR2)");  
    dataset.addValue(3438621.83, series1, "2016(QTR3)");  
    dataset.addValue(3504102.37, series1, "2016(QTR4)"); 
    dataset.addValue(3569582.91, series1, "2017(QTR1)");  
    dataset.addValue(3635063.44, series1, "2017(QTR2)");  
    dataset.addValue(3700543.98, series1, "2017(QTR3)");  
    dataset.addValue(3766024.51, series1, "2017(QTR4)"); 
    dataset.addValue(3831505.05, series1, "2018(QTR1)");  
    dataset.addValue(3896985.58, series1, "2018(QTR2)");  
    dataset.addValue(3962466.12, series1, "2018(QTR3)");  
    dataset.addValue(4027943.66, series1, "2018(QTR4)");  
  
    return dataset;  
  }  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 25)); // NOI18N
        jLabel1.setText("TREND VALUES");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 25)); // NOI18N
        jButton1.setText("BACK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("The fitted trend equation is: ");

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jTextField1.setText("0");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
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

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton2.setText("Show Trend");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(357, 357, 357))
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 60, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(117, 117, 117))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(105, 105, 105))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(39, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
new intro().setVisible(true);
this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
  SwingUtilities.invokeLater(() -> {  
      trend example = new trend("Trend Chart");  
      example.setAlwaysOnTop(true);  
      example.pack();  
      example.setSize(600, 400);  
      //example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
      example.setVisible(true);  
    });
        
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
            java.util.logging.Logger.getLogger(trend.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(trend.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(trend.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(trend.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new trend().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
