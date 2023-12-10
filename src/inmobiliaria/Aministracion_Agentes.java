/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package inmobiliaria;

import static inmobiliaria.Añadir.jTextField2;
import static inmobiliaria.PropiedadesAsociadas.jTextField2;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class Aministracion_Agentes extends javax.swing.JFrame {

    /**
     * Creates new form Aministracion_Agentes
     */
    int idAgente;

    public Aministracion_Agentes() {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jTable2.getTableHeader().setReorderingAllowed(false);
        this.setTitle("Administración Agentes");

        setIconImage(getIconImage());

    }

    public Aministracion_Agentes(int idAgente) {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jTable2.getTableHeader().setReorderingAllowed(false);
        this.idAgente = idAgente;
        //System.out.println("Info Agentes: "+this.idAgente);
        this.setTitle("Administración Agentes");

        setIconImage(getIconImage());

    }

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("imagenes/logo.png"));
        return retValue;
    }

    public int getIDAGENTE() {
        return idAgente;
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

    public void Solo_InfoAgente() {

        int idAgenteActual = getIDAGENTE();

        DefaultTableModel model = new DefaultTableModel() {

            @Override
            public Class getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 5:
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
        model.addColumn("Id Agente");
        model.addColumn("Nombre Completo");
        model.addColumn("Nombre Usuario");
        model.addColumn("Contraseña");
        model.addColumn("Correo Electrónico");
        model.addColumn("Modificar");

        try ( Connection con = activar();  PreparedStatement pstmt = con.prepareStatement("SELECT DISTINCT id_agente, nombre, nombre_usuario, contrasena, correo FROM AgentesInmobiliarios WHERE id_agente = " + idAgenteActual)) {

            ResultSet resul = pstmt.executeQuery();

            while (resul.next()) {
                Object[] row = {resul.getInt(1), resul.getString(2), resul.getString(3), resul.getString(4), resul.getString(5), new ImageIcon(getClass().getResource("/imagenes/modificar.png"))};

                model.addRow(row);

            }

            jTable1.setModel(model);

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public void Solo_InfoAgentesRestantes() {

        int idAgenteActual = getIDAGENTE();

        DefaultTableModel model = new DefaultTableModel() {

            @Override
            public Class getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 3:
                        return ImageIcon.class;
                    case 4:
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
        model.addColumn("Id Agente");
        model.addColumn("Nombre Completo");
        model.addColumn("Correo Electrónico");
        model.addColumn("Modificar");
        model.addColumn("Eliminar");

        try ( Connection con = activar();  PreparedStatement pstmt = con.prepareStatement("SELECT DISTINCT id_agente, nombre, correo FROM AgentesInmobiliarios WHERE id_agente != " + idAgenteActual)) {

            ResultSet resul = pstmt.executeQuery();

            while (resul.next()) {
                Object[] row = {resul.getInt(1), resul.getString(2), resul.getString(3), new ImageIcon(getClass().getResource("/imagenes/modificar.png")), new ImageIcon(getClass().getResource("/imagenes/eliminar.png"))};

                model.addRow(row);

            }

            jTable2.setModel(model);

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public void InfoAgente() {

        int idAgenteActual = getIDAGENTE();

        DefaultTableModel model = new DefaultTableModel() {

            @Override
            public Class getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 5:
                        return ImageIcon.class;
                    case 6:
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
        model.addColumn("Id Agente");
        model.addColumn("Nombre Completo");
        model.addColumn("Nombre Usuario");
        model.addColumn("Contraseña");
        model.addColumn("Correo Electrónico");
        model.addColumn("Modificar");
        model.addColumn("Cargar Agente");

        try ( Connection con = activar();  PreparedStatement pstmt = con.prepareStatement("SELECT DISTINCT id_agente, nombre, nombre_usuario, contrasena, correo FROM AgentesInmobiliarios WHERE id_agente = " + idAgenteActual)) {

            ResultSet resul = pstmt.executeQuery();

            while (resul.next()) {
                Object[] row = {resul.getInt(1), resul.getString(2), resul.getString(3), resul.getString(4), resul.getString(5), new ImageIcon(getClass().getResource("/imagenes/modificar.png")), new ImageIcon(getClass().getResource("/imagenes/cargar_id_agente.png"))};

                model.addRow(row);

            }

            jTable1.setModel(model);

            jTable1.getColumnModel().getColumn(5).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(5).setMinWidth(0);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(0);

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public void InfoAgentesRestantes() {

        int idAgenteActual = getIDAGENTE();

        DefaultTableModel model = new DefaultTableModel() {

            @Override
            public Class getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 3:
                        return ImageIcon.class;
                    case 4:
                        return ImageIcon.class;
                    case 5:
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
        model.addColumn("Id Agente");
        model.addColumn("Nombre Completo");
        model.addColumn("Correo Electrónico");
        model.addColumn("Modificar");
        model.addColumn("Eliminar");
        model.addColumn("Cargar Agente");

        try ( Connection con = activar();  PreparedStatement pstmt = con.prepareStatement("SELECT DISTINCT id_agente, nombre, correo FROM AgentesInmobiliarios WHERE id_agente != " + idAgenteActual)) {

            ResultSet resul = pstmt.executeQuery();

            while (resul.next()) {
                Object[] row = {resul.getInt(1), resul.getString(2), resul.getString(3), new ImageIcon(getClass().getResource("/imagenes/modificar.png")), new ImageIcon(getClass().getResource("/imagenes/eliminar.png")), new ImageIcon(getClass().getResource("/imagenes/cargar_id_agente.png"))};

                model.addRow(row);

            }

            jTable2.setModel(model);

            jTable2.getColumnModel().getColumn(3).setMaxWidth(0);
            jTable2.getColumnModel().getColumn(3).setMinWidth(0);
            jTable2.getColumnModel().getColumn(3).setPreferredWidth(0);

            jTable2.getColumnModel().getColumn(4).setMaxWidth(0);
            jTable2.getColumnModel().getColumn(4).setMinWidth(0);
            jTable2.getColumnModel().getColumn(4).setPreferredWidth(0);

        } catch (Exception e) {
            e.printStackTrace(System.out);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logo.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 0, 255));
        jLabel1.setFont(new java.awt.Font("SimSun", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("ADMINISTRACIÓN AGENTES");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/log-out.jpg"))); // NOI18N

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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("TUS DATOS");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("DATOS DE LOS AGENTES");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable2.setRowHeight(27);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable2);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/volver.png"))); // NOI18N
        jButton3.setText("Volver");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextField1.setPreferredSize(new java.awt.Dimension(0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 908, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(365, 365, 365)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 908, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(84, 84, 84)
                                        .addComponent(jLabel1))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(47, 47, 47)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(56, 56, 56))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addGap(230, 230, 230)))
                        .addComponent(jButton3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel3)
                                .addGap(51, 51, 51)
                                .addComponent(jButton3)))
                        .addGap(25, 25, 25))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:

        //Modificar
        if (jTable2.getSelectedColumn() == 3) {
            int fila = jTable2.getSelectedRow();
            int idA = (int) jTable2.getValueAt(fila, 0);

            try ( Connection con = activar()) {
                String modificar = "SELECT id_agente, nombre, correo FROM AgentesInmobiliarios WHERE id_agente= " + idA;
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(modificar);
                if (rs.next()) {

                    String input = JOptionPane.showInputDialog("Introduce contraseña administrador");

                    if (input != null && input.equals("root")) {

                        String modificar2 = "SELECT id_agente, nombre, correo, nombre_usuario, contrasena FROM AgentesInmobiliarios WHERE id_agente= " + idA;
                        Statement stmt2 = con.createStatement();
                        ResultSet rs2 = stmt2.executeQuery(modificar2);
                        if (rs2.next()) {

                            Modificar_Agentes mod = new Modificar_Agentes(idAgente, Aministracion_Agentes.this);
                            mod.cargarDatos(rs2.getInt("id_agente"), rs2.getString("nombre"), rs2.getString("correo"), rs2.getString("nombre_usuario"), rs2.getString("contrasena"));
                            mod.jButton1.setVisible(false);
                            mod.setVisible(true);
                            this.dispose();
                        }

                    } else {
                        Modificar_Agentes mo = new Modificar_Agentes(idAgente, Aministracion_Agentes.this);
                        mo.cargarDatos2(rs.getInt("id_agente"), rs.getString("nombre"), rs.getString("correo"));
                        mo.usuario_textfield.setText("No puedes modificarlo");
                        mo.usuario_textfield.setEditable(false);
                        mo.contrasena_textfield.setText("No puedes modificarlo");
                        mo.contrasena_textfield.setEditable(false);
                        mo.jButton2.setVisible(false);
                        mo.setVisible(true);
                        this.dispose();
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        //Eliminar
        if (jTable2.getSelectedColumn() == 4) {
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Quieres eliminar este agente?", "Eliminar", JOptionPane.OK_CANCEL_OPTION);

            if (respuesta == JOptionPane.YES_OPTION) {
                int fila = jTable2.getSelectedRow();

                if (fila != -1) {
                    try ( Connection con = activar();  Statement stmt = con.createStatement()) {
                        int idAgente = (int) jTable2.getValueAt(fila, 0);

                        String verificaPropiedadesSql = "SELECT COUNT(*) FROM Propiedades WHERE agente_id = " + idAgente;
                        try ( ResultSet resultSet = stmt.executeQuery(verificaPropiedadesSql)) {
                            if (resultSet.next()) {
                                int count = resultSet.getInt(1);
                                if (count > 0) {
                                    int confirmacion = JOptionPane.showConfirmDialog(this, "Este agente tiene propiedades asociadas. ¿Deseas modificar el agente de las propiedades asociadas?", "Propiedades asociadas", JOptionPane.YES_NO_OPTION);

                                    if (confirmacion == JOptionPane.YES_OPTION) {

                                        PropiedadesAsociadas pa = new PropiedadesAsociadas(idAgente);
                                        pa.mostrarPropiedadesAsociadas(idAgente);

                                        pa.setVisible(true);

                                        return;
                                    } else {
                                        JOptionPane.showMessageDialog(this, "No se puede eliminar este agente porque tiene propiedades asociadas.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                                        return;
                                    }
                                }
                            }
                        }

                        String sqlEliminarAgente = "DELETE FROM AgentesInmobiliarios WHERE id_agente = " + idAgente;
                        stmt.executeUpdate(sqlEliminarAgente);

                        JOptionPane.showMessageDialog(null, "Agente Inmobiliario eliminado correctamente");
                        Solo_InfoAgentesRestantes();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecciona una fila en la tabla antes de intentar eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        //Cargar
        if (jTable2.getSelectedColumn() == 5) {
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Quieres cargar este agente?", "Eliminar", JOptionPane.OK_CANCEL_OPTION);
            int fila = jTable2.getSelectedRow();
            if (respuesta == JOptionPane.YES_OPTION) {
                try ( Connection con = activar();  Statement stmt = con.createStatement()) {
                    int idAgente = (int) jTable2.getValueAt(fila, 0);
                    String sql = "SELECT id_agente FROM AgentesInmobiliarios WHERE id_agente = " + idAgente;
                    stmt.executeQuery(sql);

                    //JOptionPane.showMessageDialog(null, "Agente Inmobiliario cargado correctamente: " + idAgente);
                    this.jTextField1.setText(String.valueOf(idAgente));

                    String datos = jTextField1.getText();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Añadir.actualizarDatos(datos);
                            } catch (Exception e) {
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Modificar.actualizarDatos(datos);
                            } catch (Exception e) {
                            }
                        }
                    }).start();
                    
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                PropiedadesAsociadas.actualizarDatos(datos);
                            } catch (Exception e) {
                            }
                        }
                    }).start();

                    this.dispose();
                } catch (Exception e) {
                }

            }
        }


    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:

        //Modificar
        if (jTable1.getSelectedColumn() == 5) {
            //int fila = jTable1.getSelectedRow();

            try ( Connection con = activar()) {
                String modificar = "SELECT id_agente, nombre, correo, nombre_usuario, contrasena FROM AgentesInmobiliarios WHERE id_agente= " + idAgente;
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(modificar);
                if (rs.next()) {

                    Modificar_Agentes mo = new Modificar_Agentes(idAgente, Aministracion_Agentes.this);
                    mo.cargarDatos(rs.getInt("id_agente"), rs.getString("nombre"), rs.getString("correo"), rs.getString("nombre_usuario"), rs.getString("contrasena"));
                    mo.jButton1.setVisible(false);
                    mo.setVisible(true);
                    this.dispose();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        //Cargar
        if (jTable1.getSelectedColumn() == 6) {
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Quieres cargar este agente?", "Eliminar", JOptionPane.OK_CANCEL_OPTION);
            int fila = jTable1.getSelectedRow();
            if (respuesta == JOptionPane.YES_OPTION) {
                try ( Connection con = activar();  Statement stmt = con.createStatement()) {
                    int idAgente = (int) jTable1.getValueAt(fila, 0);
                    String sql = "SELECT id_agente FROM AgentesInmobiliarios WHERE id_agente = " + idAgente;
                    stmt.executeQuery(sql);

                    //JOptionPane.showMessageDialog(null, "Agente Inmobiliario cargado correctamente: " + idAgente);
                    this.jTextField1.setText(String.valueOf(idAgente));

                    String datos = jTextField1.getText();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Añadir.actualizarDatos(datos);
                        }
                    }).start();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Modificar.actualizarDatos(datos);
                        }
                    }).start();

                    this.dispose();
                } catch (SQLException e) {
                }

            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

        //App a = new App(idAgente);
        //Añadir ana = new Añadir(idAgente);
        //ana.setVisible(false);
        //a.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Aministracion_Agentes.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Aministracion_Agentes.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Aministracion_Agentes.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Aministracion_Agentes.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Aministracion_Agentes().setVisible(true);
                new Añadir();

            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
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
