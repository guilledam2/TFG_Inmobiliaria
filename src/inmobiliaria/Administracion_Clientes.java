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
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Usuario
 */
public class Administracion_Clientes extends javax.swing.JFrame {

    int idAgente;
    int idClienteSeleccionado;

    /**
     * Creates new form Administracion_Clientes
     */
    public Administracion_Clientes() {
        initComponents();
        this.setTitle("Clientes");
        tablaClientes();
        jTable1.getTableHeader().setReorderingAllowed(false);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.jButton4.setVisible(false);
        this.jButton5.setVisible(false);
        this.jButton7.setVisible(false);
        telefono.setDocument(new Administracion_Clientes.TelefonoDocument());
    }

    public Administracion_Clientes(int iDAgente) {
        initComponents();
        idAgente = iDAgente;
        this.setTitle("Clientes");
        tablaClientes();
        jTable1.getTableHeader().setReorderingAllowed(false);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.jButton4.setVisible(false);
        this.jButton5.setVisible(false);
        this.jButton7.setVisible(false);
        telefono.setDocument(new Administracion_Clientes.TelefonoDocument());
    }

    public boolean validarCorreoElectronico(String correo) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
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

    public void setIDClienteSeleccionado(int idCliente) {
        this.idClienteSeleccionado = idCliente;
    }

    public int getIdClienteSeleccionado() {
        return idClienteSeleccionado;
    }

    public void tablaClientes() {
        Connection con = activar();

        try {

            DefaultTableModel dtm = new DefaultTableModel() {
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

            dtm.addColumn("Nombre");
            dtm.addColumn("Correo");
            dtm.addColumn("Teléfono");
            dtm.addColumn("Modificar");
            dtm.addColumn("Eliminar");
            dtm.addColumn("Asignar propiedad");
            dtm.addColumn("ID cliente");

            jTable1.setModel(dtm);

            jTable1.getColumnModel().getColumn(0).setMaxWidth(170);
            jTable1.getColumnModel().getColumn(0).setMinWidth(170);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(170);

            jTable1.getColumnModel().getColumn(1).setMaxWidth(170);
            jTable1.getColumnModel().getColumn(1).setMinWidth(170);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(170);

            jTable1.getColumnModel().getColumn(2).setMaxWidth(290);
            jTable1.getColumnModel().getColumn(2).setMinWidth(290);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(290);

            jTable1.getColumnModel().getColumn(3).setMaxWidth(80);
            jTable1.getColumnModel().getColumn(3).setMinWidth(80);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(80);

            jTable1.getColumnModel().getColumn(4).setMaxWidth(80);
            jTable1.getColumnModel().getColumn(4).setMinWidth(80);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(80);

            jTable1.getColumnModel().getColumn(5).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(5).setMinWidth(0);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(0);

            jTable1.getColumnModel().getColumn(6).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(6).setMinWidth(0);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(0);

            Object[] datos = new Object[7];

            Statement stmt = con.createStatement();
            ResultSet resul = stmt.executeQuery("SELECT DISTINCT nombre_completo, correo, telefono, id_cliente FROM Clientes WHERE nombre_completo LIKE '%" + buscador.getText() + "%'");

            while (resul.next()) {
                datos[0] = resul.getString(1);
                datos[1] = resul.getString(2);
                datos[2] = resul.getString(3);
                datos[3] = new ImageIcon(getClass().getResource("/imagenes/modificar.png"));
                datos[4] = new ImageIcon(getClass().getResource("/imagenes/eliminar.png"));
                datos[5] = new ImageIcon(getClass().getResource("/imagenes/asignar.png"));
                datos[6] = resul.getString(4);
                dtm.addRow(datos);
            }

            jTable1.setModel(dtm);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tablaAsignarClientes() {
        Connection con = activar();

        try {

            DefaultTableModel dtm = new DefaultTableModel() {
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

            dtm.addColumn("Nombre");
            dtm.addColumn("Correo");
            dtm.addColumn("Teléfono");
            dtm.addColumn("Modificar");
            dtm.addColumn("Eliminar");
            dtm.addColumn("Asignar propiedad");
            dtm.addColumn("ID cliente");

            jTable1.setModel(dtm);

            jTable1.getColumnModel().getColumn(0).setMaxWidth(170);
            jTable1.getColumnModel().getColumn(0).setMinWidth(170);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(170);

            jTable1.getColumnModel().getColumn(1).setMaxWidth(170);
            jTable1.getColumnModel().getColumn(1).setMinWidth(170);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(170);

            jTable1.getColumnModel().getColumn(3).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(3).setMinWidth(0);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(0);

            jTable1.getColumnModel().getColumn(4).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(4).setMinWidth(0);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(0);

            jTable1.getColumnModel().getColumn(5).setMaxWidth(120);
            jTable1.getColumnModel().getColumn(5).setMinWidth(120);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(120);

            jTable1.getColumnModel().getColumn(6).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(6).setMinWidth(0);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(0);

            Object[] datos = new Object[7];

            Statement stmt = con.createStatement();
            ResultSet resul = stmt.executeQuery("SELECT DISTINCT nombre_completo, correo, telefono, id_cliente FROM Clientes WHERE nombre_completo LIKE '%" + buscador.getText() + "%'");

            while (resul.next()) {
                datos[0] = resul.getString(1);
                datos[1] = resul.getString(2);
                datos[2] = resul.getString(3);
                datos[3] = new ImageIcon(getClass().getResource("/imagenes/modificar.png"));
                datos[4] = new ImageIcon(getClass().getResource("/imagenes/eliminar.png"));
                datos[5] = new ImageIcon(getClass().getResource("/imagenes/asignar.png"));
                datos[6] = resul.getString(4);
                dtm.addRow(datos);
            }

            jTable1.setModel(dtm);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean propiedadAsignada(int idPropiedad, String tipo) throws SQLException {
        Connection con = activar();
        String sentencia;

        if ("DisponibleCompra".equals(tipo)) {
            sentencia = "SELECT COUNT(*) FROM DisponibleCompra WHERE id_propiedad = ? OR id_cliente_c = ?";
        } else if ("DisponibleAlquiler".equals(tipo)) {
            sentencia = "SELECT COUNT(*) FROM DisponibleAlquiler WHERE id_propiedad = ? OR id_cliente_a = ?";
        } else {
            throw new IllegalArgumentException("Tipo de tabla no válido");
        }

        try ( PreparedStatement stmt = con.prepareStatement(sentencia)) {
            stmt.setInt(1, idPropiedad);
            stmt.setInt(2, this.getIdClienteSeleccionado());
            try ( ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }
        }
        return false;
    }

    public boolean clienteTienePropiedadAsignada(int idCliente) {
    Connection con = activar();
    PreparedStatement preparedStatementCompra = null;
    ResultSet resultSetCompra = null;
    PreparedStatement preparedStatementAlquiler = null;
    ResultSet resultSetAlquiler = null;

    try {
        String sqlCompra = "SELECT * FROM DisponibleCompra WHERE id_cliente_c = ? LIMIT 1";
        preparedStatementCompra = con.prepareStatement(sqlCompra);
        preparedStatementCompra.setInt(1, idCliente);
        resultSetCompra = preparedStatementCompra.executeQuery();

        if (resultSetCompra.next()) {
            return true;
        }

        String sqlAlquiler = "SELECT * FROM DisponibleAlquiler WHERE id_cliente_a = ? LIMIT 1";
        preparedStatementAlquiler = con.prepareStatement(sqlAlquiler);
        preparedStatementAlquiler.setInt(1, idCliente);
        resultSetAlquiler = preparedStatementAlquiler.executeQuery();

        if (resultSetAlquiler.next()) {
            return true;
        }

        return false;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    //Borrar
    public void borrarAsignacionPropiedad(int idCliente, String tipo) {
        Connection con = activar();
        String deleteQuery;

        if ("DisponibleCompra".equals(tipo)) {
            deleteQuery = "UPDATE DisponibleCompra SET id_cliente_c = NULL WHERE id_cliente_c = ?";
        } else if ("DisponibleAlquiler".equals(tipo)) {
            deleteQuery = "UPDATE DisponibleAlquiler SET id_cliente_a = NULL WHERE id_cliente_a = ?";
        } else {
            throw new IllegalArgumentException("Tipo de tabla no válido");
        }

        try ( PreparedStatement stmt = con.prepareStatement(deleteQuery)) {
            stmt.setInt(1, idCliente);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Administracion_Clientes.class.getName()).log(Level.SEVERE, null, ex);
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
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        correo = new javax.swing.JTextField();
        telefono = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        propiedad = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        buscador = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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
        jTable1.setRowHeight(32);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setForeground(new java.awt.Color(255, 255, 0));
        jLabel1.setText("Nombre Completo:");

        jLabel4.setForeground(new java.awt.Color(255, 255, 0));
        jLabel4.setText("Correo electrónico:");

        jLabel5.setForeground(new java.awt.Color(255, 255, 0));
        jLabel5.setText("Teléfono:");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/añadir_inmueble.png"))); // NOI18N
        jButton1.setText("Añadir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/modificar.png"))); // NOI18N
        jButton2.setText("Modificar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(0, 0, 255));
        jLabel6.setFont(new java.awt.Font("SimSun", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 0, 0));
        jLabel6.setText("Administración Clientes");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/limpiar.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/limpiar.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/añadir_inmueble.png"))); // NOI18N
        jButton5.setText("Añadir");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/volver.png"))); // NOI18N
        jButton6.setText("Volver");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/modificar.png"))); // NOI18N
        jButton7.setText("Modificar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lupa.jpg"))); // NOI18N
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        buscador.setToolTipText("Nombre Cliente");
        buscador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscadorKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(46, 46, 46)
                                    .addComponent(propiedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(56, 56, 56)
                            .addComponent(jLabel6)
                            .addGap(34, 34, 34)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(32, 32, 32)
                                    .addComponent(jLabel3))
                                .addComponent(jButton6)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(57, 57, 57)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(jButton4))
                                .addComponent(jButton3)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 785, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(11, 11, 11)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(211, 211, 211)
                        .addComponent(jLabel7)
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(12, 12, 12)
                                .addComponent(telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(buscador, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(16, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(188, 188, 188)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(12, 12, 12)
                        .addComponent(correo, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(12, 12, 12)
                        .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(jButton5))
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(jButton2))
                        .addComponent(jButton7)))
                .addGap(56, 56, 56))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(propiedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel3)
                        .addGap(41, 41, 41)
                        .addComponent(jButton6)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jButton5))
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel1))
                            .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel4))
                            .addComponent(correo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jButton7))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jButton4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel5))
                    .addComponent(telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(buscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String nombre_añadir = this.nombre.getText();
        String correo_añadir = this.correo.getText();
        String telefono_añadir = this.telefono.getText();

        if (nombre_añadir.isEmpty() || nombre_añadir.isBlank() || correo_añadir.isEmpty() || telefono_añadir.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
        } else if (!validarCorreoElectronico(correo_añadir)) {
            JOptionPane.showMessageDialog(this, "Formato de correo electrónico no válido", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                Connection con = activar();

                String sql_verificar_cliente = "SELECT COUNT(*) FROM Clientes WHERE nombre_completo = ? AND correo = ? AND telefono = ?";
                try ( PreparedStatement stmt_verificar_cliente = con.prepareStatement(sql_verificar_cliente)) {
                    stmt_verificar_cliente.setString(1, nombre_añadir);
                    stmt_verificar_cliente.setString(2, correo_añadir);
                    stmt_verificar_cliente.setString(3, telefono_añadir);

                    ResultSet rs = stmt_verificar_cliente.executeQuery();
                    rs.next();
                    int count = rs.getInt(1);

                    if (count > 0) {
                        JOptionPane.showMessageDialog(null, "Este cliente ya existe", "Cliente duplicado", JOptionPane.WARNING_MESSAGE);
                    } else {
                        String sql_insert_clientes = "INSERT INTO Clientes (nombre_completo, correo, telefono) "
                                + "VALUES (?, ?, ?)";
                        try ( PreparedStatement stmt_clientes = con.prepareStatement(sql_insert_clientes)) {
                            stmt_clientes.setString(1, nombre_añadir);
                            stmt_clientes.setString(2, correo_añadir);
                            stmt_clientes.setString(3, telefono_añadir);

                            stmt_clientes.executeUpdate();
                        }

                        tablaClientes();
                        JOptionPane.showMessageDialog(null, "Cliente añadido", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        this.nombre.setText("");
                        this.correo.setText("");
                        this.telefono.setText("");

                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(Administracion_Clientes.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        Connection con = activar();

        //Modificar
        if (jTable1.getSelectedColumn() == 3) {

            int fila = jTable1.getSelectedRow();

            String nombre_nuevo = jTable1.getValueAt(fila, 0).toString();
            String correo_nuevo = jTable1.getValueAt(fila, 1).toString();
            String telefono_nuevo = jTable1.getValueAt(fila, 2).toString();

            this.nombre.setText(nombre_nuevo);
            this.correo.setText(correo_nuevo);
            this.telefono.setText(telefono_nuevo);

        }

        //Eliminar
        if (jTable1.getSelectedColumn() == 4) {
            try {
                int fila = jTable1.getSelectedRow();
                int idCliente = Integer.parseInt(jTable1.getValueAt(fila, 6).toString());

                if (propiedadAsignada(idCliente, "DisponibleCompra") || propiedadAsignada(idCliente, "DisponibleAlquiler")) {
                    int confirmacion = JOptionPane.showConfirmDialog(this, "Este cliente tiene una propiedad asignada. ¿Deseas borrar también la asignación de propiedad?", "Advertencia", JOptionPane.YES_NO_OPTION);

                    if (confirmacion == JOptionPane.NO_OPTION) {
                        return;
                    }

                    borrarAsignacionPropiedad(idCliente, "DisponibleCompra");
                    borrarAsignacionPropiedad(idCliente, "DisponibleAlquiler");
                } else {
                    int respuesta = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas eliminar este cliente?", "Eliminar Cliente", JOptionPane.YES_NO_OPTION);

                    if (respuesta == JOptionPane.YES_OPTION) {
                        try {
                            String borrarCliente = "DELETE FROM Clientes WHERE id_cliente = " + idCliente;
                            Statement stmCliente = con.createStatement();
                            int confirmacionCliente = stmCliente.executeUpdate(borrarCliente);
                            if (confirmacionCliente >= 0) {
                                JOptionPane.showMessageDialog(null, "Cliente eliminado correctamente");
                                tablaClientes();
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(Administracion_Clientes.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                }
            } catch (SQLException ex) {
                Logger.getLogger(Administracion_Clientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //Asignar
        /*if (jTable1.getSelectedColumn() == 5) {
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Quieres asignar este agente?", "Asignar Propiedad", JOptionPane.OK_CANCEL_OPTION);
            int fila = jTable1.getSelectedRow();

            if (respuesta == JOptionPane.YES_OPTION) {
                try {
                    int idCliente = Integer.parseInt(jTable1.getValueAt(fila, 6).toString());
                    int idPropiedad = Integer.parseInt(propiedad.getText());

                    if (propiedadAsignada(idPropiedad, "DisponibleCompra")) {
                        String updateQuery = "UPDATE DisponibleCompra SET id_cliente_c = ? WHERE id_propiedad = ?";

                        try ( PreparedStatement updateStmt = con.prepareStatement(updateQuery)) {
                            updateStmt.setInt(1, idCliente);
                            updateStmt.setInt(2, idPropiedad);
                            updateStmt.executeUpdate();
                            JOptionPane.showMessageDialog(this, "Propiedad asignada al cliente correctamente Compra");
                        }
                    } else if (propiedadAsignada(idPropiedad, "DisponibleAlquiler")) {
                        String updateQuery = "UPDATE DisponibleAlquiler SET id_cliente_a = ? WHERE id_propiedad = ?";

                        try ( PreparedStatement updateStmt = con.prepareStatement(updateQuery)) {
                            updateStmt.setInt(1, idCliente);
                            updateStmt.setInt(2, idPropiedad);
                            updateStmt.executeUpdate();
                            JOptionPane.showMessageDialog(this, "Propiedad asignada al cliente correctamente Alquiler");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "La propiedad ya está asignada a otro cliente", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }*/
        if (jTable1.getSelectedColumn() == 5) {
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Quieres asignar este agente?", "Asignar Propiedad", JOptionPane.OK_CANCEL_OPTION);
            int fila = jTable1.getSelectedRow();

            if (respuesta == JOptionPane.YES_OPTION) {
                try {
                    int idCliente = Integer.parseInt(jTable1.getValueAt(fila, 6).toString());
                    int idPropiedad = Integer.parseInt(propiedad.getText());

                    if (!clienteTienePropiedadAsignada(idCliente)) {
                        if (propiedadAsignada(idPropiedad, "DisponibleCompra")) {
                            String updateQuery = "UPDATE DisponibleCompra SET id_cliente_c = ? WHERE id_propiedad = ?";

                            try ( PreparedStatement updateStmt = con.prepareStatement(updateQuery)) {
                                updateStmt.setInt(1, idCliente);
                                updateStmt.setInt(2, idPropiedad);
                                updateStmt.executeUpdate();
                                JOptionPane.showMessageDialog(this, "Propiedad asignada al cliente correctamente Compra");
                            }
                        } else if (propiedadAsignada(idPropiedad, "DisponibleAlquiler")) {
                            String updateQuery = "UPDATE DisponibleAlquiler SET id_cliente_a = ? WHERE id_propiedad = ?";

                            try ( PreparedStatement updateStmt = con.prepareStatement(updateQuery)) {
                                updateStmt.setInt(1, idCliente);
                                updateStmt.setInt(2, idPropiedad);
                                updateStmt.executeUpdate();
                                JOptionPane.showMessageDialog(this, "Propiedad asignada al cliente correctamente Alquiler");
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "La propiedad no existe o está asignada a otro cliente", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "El cliente ya tiene una propiedad asignada", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        nombre.setText("");
        correo.setText("");
        telefono.setText("");
        buscador.setText("");
        tablaClientes();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        String nombre_nuevo = this.nombre.getText();
        String correo_añadir = this.correo.getText();
        String telefono_añadir = this.telefono.getText();

        int fila = jTable1.getSelectedRow();

        if (fila != -1) {
            Connection con = activar();
            try {
                String modificar = "UPDATE Clientes SET nombre_completo = '" + nombre_nuevo + "', correo = '" + correo_añadir + "', telefono = '" + telefono_añadir + "' WHERE id_cliente = " + jTable1.getValueAt(fila, 6);

                Statement stmt = con.createStatement();
                int filasAfectadas = stmt.executeUpdate(modificar);

                if (filasAfectadas > 0) {
                    tablaClientes();
                    JOptionPane.showMessageDialog(null, "Cliente modificado correctamente");
                } else {
                    JOptionPane.showMessageDialog(null, "No se realizaron cambios", "Aviso", JOptionPane.WARNING_MESSAGE);

                }
            } catch (SQLException ex) {
                Logger.getLogger(Administracion_Clientes.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona una fila para modificar", "Aviso", JOptionPane.WARNING_MESSAGE);
        }


    }//GEN-LAST:event_jButton2ActionPerformed

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

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        nombre.setText("");
        correo.setText("");
        telefono.setText("");
        buscador.setText("");
        tablaAsignarClientes();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        String nombre_añadir = this.nombre.getText();
        String correo_añadir = this.correo.getText();
        String telefono_añadir = this.telefono.getText();

        if (nombre_añadir.isEmpty() || nombre_añadir.isBlank() || correo_añadir.isEmpty() || telefono_añadir.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
        } else if (!validarCorreoElectronico(correo_añadir)) {
            JOptionPane.showMessageDialog(this, "Formato de correo electrónico no válido", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                Connection con = activar();

                String sql_verificar_cliente = "SELECT COUNT(*) FROM Clientes WHERE nombre_completo = ? AND correo = ? AND telefono = ?";
                try ( PreparedStatement stmt_verificar_cliente = con.prepareStatement(sql_verificar_cliente)) {
                    stmt_verificar_cliente.setString(1, nombre_añadir);
                    stmt_verificar_cliente.setString(2, correo_añadir);
                    stmt_verificar_cliente.setString(3, telefono_añadir);

                    ResultSet rs = stmt_verificar_cliente.executeQuery();
                    rs.next();
                    int count = rs.getInt(1);

                    if (count > 0) {
                        JOptionPane.showMessageDialog(null, "Este cliente ya existe", "Cliente duplicado", JOptionPane.WARNING_MESSAGE);
                    } else {
                        String sql_insert_clientes = "INSERT INTO Clientes (nombre_completo, correo, telefono) "
                                + "VALUES (?, ?, ?)";
                        try ( PreparedStatement stmt_clientes = con.prepareStatement(sql_insert_clientes)) {
                            stmt_clientes.setString(1, nombre_añadir);
                            stmt_clientes.setString(2, correo_añadir);
                            stmt_clientes.setString(3, telefono_añadir);

                            stmt_clientes.executeUpdate();
                        }

                        tablaAsignarClientes();
                        JOptionPane.showMessageDialog(null, "Cliente añadido", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        this.nombre.setText("");
                        this.correo.setText("");
                        this.telefono.setText("");

                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(Administracion_Clientes.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        Window[] window = Window.getWindows();
        for (Window w : window) {
            if (w.isDisplayable()) {
                w.dispose();
            }
        }

        App a = new App(idAgente);
        a.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        String nombre_nuevo = this.nombre.getText();
        String correo_añadir = this.correo.getText();
        String telefono_añadir = this.telefono.getText();

        int fila = jTable1.getSelectedRow();

        if (fila != -1) {
            Connection con = activar();
            try {
                String modificar = "UPDATE Clientes SET nombre_completo = '" + nombre_nuevo + "', correo = '" + correo_añadir + "', telefono = '" + telefono_añadir + "' WHERE id_cliente = " + jTable1.getValueAt(fila, 5);

                Statement stmt = con.createStatement();
                int filasAfectadas = stmt.executeUpdate(modificar);

                if (filasAfectadas > 0) {
                    tablaAsignarClientes();
                    JOptionPane.showMessageDialog(null, "Cliente modificado correctamente");
                } else {
                    JOptionPane.showMessageDialog(null, "No se realizaron cambios", "Aviso", JOptionPane.WARNING_MESSAGE);

                }
            } catch (SQLException ex) {
                Logger.getLogger(Administracion_Clientes.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona una fila para modificar", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel7MouseClicked

    private void buscadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscadorKeyReleased
        // TODO add your handling code here:
        tablaClientes();
    }//GEN-LAST:event_buscadorKeyReleased

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
            java.util.logging.Logger.getLogger(Administracion_Clientes.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Administracion_Clientes.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Administracion_Clientes.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Administracion_Clientes.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Administracion_Clientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField buscador;
    private javax.swing.JTextField correo;
    public javax.swing.JButton jButton1;
    public javax.swing.JButton jButton2;
    public javax.swing.JButton jButton3;
    public javax.swing.JButton jButton4;
    public javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    public javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField nombre;
    public javax.swing.JTextField propiedad;
    private javax.swing.JTextField telefono;
    // End of variables declaration//GEN-END:variables

    public class FondoPanel extends javax.swing.JPanel {

        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/imagenes/fondo_app.jpg")).getImage();
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);
        }
    }

    class TelefonoDocument extends PlainDocument {

        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            int maxLength = 12;

            if (getLength() + str.length() <= maxLength) {
                if (str.matches("\\d*")) {
                    super.insertString(offs, str, a);
                }
            }
        }
    }

}
