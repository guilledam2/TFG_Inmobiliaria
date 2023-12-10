/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package inmobiliaria;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Window;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class PropiedadesAsociadas extends javax.swing.JFrame {

    /**
     * Creates new form PropiedadesAsociadas
     */
    int idAgente;

    public PropiedadesAsociadas() {
        initComponents();
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        this.setTitle("Propiedades Asociadas");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public PropiedadesAsociadas(int iDAgente) {
        initComponents();
        idAgente = iDAgente;
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        this.setTitle("Propiedades Asociadas");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public static void actualizarDatos(String datos) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    jTextField2.setText(datos);

                } catch (Exception e) {
                }
            }
        });
    }

    public Connection activar() {
        String bbdd = "jdbc:hsqldb:hsql://localhost/";
        String usuario = "SA";
        String contrasena = "";
        Connection con = null;

        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            con = DriverManager.getConnection(bbdd, usuario, contrasena);
            if (con != null) {
                System.out.println("Connection created successfully");
            } else {
                System.out.println("Problem with creating connection");
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return con;
    }

    public void mostrarPropiedadesAsociadas(int idAgente) {
        Connection con = activar();

        try {

            DefaultTableModel dtm = new DefaultTableModel() {
                @Override
                public Class getColumnClass(int columnIndex) {
                    switch (columnIndex) {
                        case 11:
                            return ImageIcon.class;
                        default:
                            return Object.class;
                    }
                }

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            dtm.addColumn("Id propiedad");
            dtm.addColumn("Título");
            dtm.addColumn("Descripción");
            dtm.addColumn("Tipo");
            dtm.addColumn("Direccion");
            dtm.addColumn("Precio");
            dtm.addColumn("Habitaciones");
            dtm.addColumn("Baños");
            dtm.addColumn("M2");
            dtm.addColumn("Id Agente");
            dtm.addColumn("Disponibilidad");

            jTable1.setModel(dtm);

            jTable1.getColumnModel().getColumn(0).setMaxWidth(50);
            jTable1.getColumnModel().getColumn(0).setMinWidth(50);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);

            jTable1.getColumnModel().getColumn(1).setMaxWidth(150);
            jTable1.getColumnModel().getColumn(1).setMinWidth(150);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);

            jTable1.getColumnModel().getColumn(2).setMaxWidth(230);
            jTable1.getColumnModel().getColumn(2).setMinWidth(230);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(230);

            jTable1.getColumnModel().getColumn(3).setMaxWidth(80);
            jTable1.getColumnModel().getColumn(3).setMinWidth(80);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(80);

            jTable1.getColumnModel().getColumn(4).setMaxWidth(210);
            jTable1.getColumnModel().getColumn(4).setMinWidth(210);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(210);

            jTable1.getColumnModel().getColumn(5).setMaxWidth(60);
            jTable1.getColumnModel().getColumn(5).setMinWidth(60);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(60);

            jTable1.getColumnModel().getColumn(7).setMaxWidth(40);
            jTable1.getColumnModel().getColumn(7).setMinWidth(40);
            jTable1.getColumnModel().getColumn(7).setPreferredWidth(40);

            jTable1.getColumnModel().getColumn(10).setMaxWidth(100);
            jTable1.getColumnModel().getColumn(10).setMinWidth(100);
            jTable1.getColumnModel().getColumn(10).setPreferredWidth(100);

            Object[] datos = new Object[11];

            Statement stmt = con.createStatement();
            ResultSet resul = stmt.executeQuery("SELECT DISTINCT id_propiedad, titulo, descripcion, tipo, direccion, precio, habitaciones, banos, metros_cuadrados, agente_id, disponibilidad FROM propiedades WHERE agente_id =" + idAgente);

            while (resul.next()) {
                datos[0] = resul.getInt(1);
                datos[1] = resul.getString(2);
                datos[2] = resul.getString(3);
                datos[3] = resul.getString(4);
                datos[4] = resul.getString(5);
                datos[5] = resul.getBigDecimal(6);
                datos[6] = resul.getInt(7);
                datos[7] = resul.getInt(8);
                datos[8] = resul.getBigDecimal(9);
                datos[9] = resul.getInt(10);
                datos[10] = resul.getString(11);
                dtm.addRow(datos);
            }

            jTable1.setModel(dtm);

        } catch (Exception e) {
            e.printStackTrace();
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

        jPanel1 = new FondoPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setBackground(new java.awt.Color(0, 0, 255));
        jLabel1.setFont(new java.awt.Font("SimSun", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("PROPIEDADES ASOCIADAS");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logo.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/log-out.jpg"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
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
        jTable1.setRowHeight(27);
        jScrollPane1.setViewportView(jTable1);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/volver.png"))); // NOI18N
        jButton3.setText("Volver");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel4.setText("Id agente:");

        jTextField2.setEditable(false);
        jTextField2.setText("Seleccione Agente");
        jTextField2.setPreferredSize(new java.awt.Dimension(300, 22));
        jTextField2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField2MouseClicked(evt);
            }
        });

        jButton1.setText("Modificar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addGap(168, 168, 168))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(186, 186, 186)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(81, 81, 81)
                                .addComponent(jButton1)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 286, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addGap(21, 21, 21))))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addComponent(jButton3)))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Connection con = activar();


        int idagente_nuevo = Integer.parseInt(this.jTextField2.getText());

        int opcion = JOptionPane.showConfirmDialog(null, "¿Desea realizar los cambios en la base de datos?", "Confirmación", JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            try {
                String sql_actualizar = "UPDATE Propiedades SET agente_id = " + idagente_nuevo
                        + " WHERE agente_id = " + idAgente;
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql_actualizar);
                this.mostrarPropiedadesAsociadas(idAgente);
                JOptionPane.showMessageDialog(null, "Ha sido modificado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                Logger.getLogger(PropiedadesAsociadas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        Window[] window = Window.getWindows();
        for (Window w : window) {
            if (w.isDisplayable()) {
                w.dispose();
            }
        }

        App a = new App(idAgente);
        a.setVisible(true);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jTextField2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField2MouseClicked
        // TODO add your handling code here:

        Aministracion_Agentes ada = new Aministracion_Agentes(idAgente);
        ada.InfoAgente();
        ada.InfoAgentesRestantes();
        ada.setVisible(true);

    }//GEN-LAST:event_jTextField2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        Window[] window = Window.getWindows();
        for (Window w : window) {
            if (w.isDisplayable()) {
                w.dispose();
            }
        }
        Principal p = new Principal();
        p.setVisible(true);
    }//GEN-LAST:event_jLabel3MouseClicked

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
            java.util.logging.Logger.getLogger(PropiedadesAsociadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PropiedadesAsociadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PropiedadesAsociadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PropiedadesAsociadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PropiedadesAsociadas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    public static javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

    public class FondoPanel extends javax.swing.JPanel {

        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/imagenes/fondo_AdminAgentes.jpg")).getImage();
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);
        }
    }

}
