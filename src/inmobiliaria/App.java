/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package inmobiliaria;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Usuario
 */
public class App extends javax.swing.JFrame {

    String usuario;
    String contrasena;
    boolean existe = false;
    ResultSet resultado = null;
    PreparedStatement consulta = null;
    ResultSet resultado2 = null;
    PreparedStatement consulta2 = null;
    private int IDAgente;

    /**
     * Creates new form App
     */
    public App(int idAgente) {
        initComponents();
        this.setTitle("Filtrar Datos");

        IDAgente = idAgente;
        //System.out.println(IDAgente);

        this.setResizable(false);
        this.setLocationRelativeTo(null);

        Menu menu = new Menu(this, this);
        setJMenuBar(menu);

        SpinnerNumberModel model = new SpinnerNumberModel(0, 0, Double.MAX_VALUE, 5000);
        SpinnerNumberModel model2 = new SpinnerNumberModel(0, 0, Double.MAX_VALUE, 5000);
        jSpinner1.setModel(model);
        jSpinner2.setModel(model2);

        JSpinner.NumberEditor editor = (JSpinner.NumberEditor) jSpinner1.getEditor();
        JSpinner.NumberEditor editor2 = (JSpinner.NumberEditor) jSpinner2.getEditor();
        DefaultFormatter formatter = (DefaultFormatter) editor.getTextField().getFormatter();
        DefaultFormatter formatter2 = (DefaultFormatter) editor2.getTextField().getFormatter();
        formatter.setAllowsInvalid(false);
        formatter.setOverwriteMode(true);
        formatter2.setAllowsInvalid(false);
        formatter2.setOverwriteMode(true);

        tabla();
        jTable1.getTableHeader().setReorderingAllowed(false);

        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (((AbstractButton) e.getSource()).isSelected()) {
                    ((AbstractButton) e.getSource()).setBackground(Color.RED);
                    ((AbstractButton) e.getSource()).setForeground(Color.WHITE);
                } else {
                    ((AbstractButton) e.getSource()).setBackground(null);
                    ((AbstractButton) e.getSource()).setForeground(null);
                }
            }
        };
        comprar_boton.addChangeListener(changeListener);
        alquilar_boton.addChangeListener(changeListener);

        tipo_combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrar();
            }

        });

        habitaciones_combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrar();
            }

        });

        banos_combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrar();
            }

        });

        comprar_boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrar();
            }

        });

        alquilar_boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrar();
            }

        });

    }

    public App() {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Filtrar Datos");

        Menu menu = new Menu(this);
        setJMenuBar(menu);

        SpinnerNumberModel model = new SpinnerNumberModel(0, 0, Double.MAX_VALUE, 5000);
        SpinnerNumberModel model2 = new SpinnerNumberModel(0, 0, Double.MAX_VALUE, 5000);
        jSpinner1.setModel(model);
        jSpinner2.setModel(model2);

        JSpinner.NumberEditor editor = (JSpinner.NumberEditor) jSpinner1.getEditor();
        JSpinner.NumberEditor editor2 = (JSpinner.NumberEditor) jSpinner2.getEditor();
        DefaultFormatter formatter = (DefaultFormatter) editor.getTextField().getFormatter();
        DefaultFormatter formatter2 = (DefaultFormatter) editor2.getTextField().getFormatter();
        formatter.setAllowsInvalid(false);
        formatter.setOverwriteMode(true);
        formatter2.setAllowsInvalid(false);
        formatter2.setOverwriteMode(true);

        tabla();
        jTable1.getTableHeader().setReorderingAllowed(false);

        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (((AbstractButton) e.getSource()).isSelected()) {
                    ((AbstractButton) e.getSource()).setBackground(Color.RED);
                    ((AbstractButton) e.getSource()).setForeground(Color.WHITE);
                } else {
                    ((AbstractButton) e.getSource()).setBackground(null);
                    ((AbstractButton) e.getSource()).setForeground(null);
                }
            }
        };
        comprar_boton.addChangeListener(changeListener);
        alquilar_boton.addChangeListener(changeListener);

        tipo_combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrar();
            }

        });

        habitaciones_combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrar();
            }

        });

        banos_combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrar();
            }

        });

        comprar_boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrar();
            }

        });

        alquilar_boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrar();
            }

        });

    }

    public int getIdAgente() {
        return IDAgente;
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

    public void tabla2() {
        Connection con = activar();

        try {

            DefaultTableModel dtm = new DefaultTableModel() {
                @Override
                public Class getColumnClass(int columnIndex) {
                    switch (columnIndex) {
                        case 4:
                            return ImageIcon.class;
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

            dtm.addColumn("Tipo");
            dtm.addColumn("Direccion");
            dtm.addColumn("Precio");
            dtm.addColumn("M2");
            dtm.addColumn("Info");
            dtm.addColumn("Modificar");
            dtm.addColumn("Eliminar");
            dtm.addColumn("id");

            jTable1.setModel(dtm);

            jTable1.getColumnModel().getColumn(0).setMaxWidth(90);
            jTable1.getColumnModel().getColumn(0).setMinWidth(90);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(90);

            jTable1.getColumnModel().getColumn(1).setMaxWidth(240);
            jTable1.getColumnModel().getColumn(1).setMinWidth(240);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(240);

            jTable1.getColumnModel().getColumn(2).setMaxWidth(80);
            jTable1.getColumnModel().getColumn(2).setMinWidth(80);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(80);

            jTable1.getColumnModel().getColumn(3).setMaxWidth(70);
            jTable1.getColumnModel().getColumn(3).setMinWidth(70);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(70);

            jTable1.getColumnModel().getColumn(4).setMaxWidth(50);
            jTable1.getColumnModel().getColumn(4).setMinWidth(50);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(50);

            jTable1.getColumnModel().getColumn(7).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(7).setMinWidth(0);
            jTable1.getColumnModel().getColumn(7).setPreferredWidth(0);

            Object[] datos = new Object[8];

            Statement stmt = con.createStatement();
            ResultSet resul = stmt.executeQuery("SELECT DISTINCT tipo, direccion, precio, metros_cuadrados, id_propiedad FROM propiedades");

            while (resul.next()) {
                datos[0] = resul.getString(1);
                datos[1] = resul.getString(2);
                datos[2] = resul.getBigDecimal(3);
                datos[3] = resul.getBigDecimal(4);
                datos[4] = new ImageIcon(getClass().getResource("/imagenes/info.png"));
                datos[5] = new ImageIcon(getClass().getResource("/imagenes/modificar.png"));
                datos[6] = new ImageIcon(getClass().getResource("/imagenes/eliminar.png"));
                datos[7] = resul.getInt(5);
                dtm.addRow(datos);
            }

            jTable1.setModel(dtm);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tabla() {
        Connection con = activar();

        try {

            DefaultTableModel dtm = new DefaultTableModel() {
                @Override
                public Class getColumnClass(int columnIndex) {
                    switch (columnIndex) {
                        case 4:
                            return ImageIcon.class;
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

            dtm.addColumn("Tipo");
            dtm.addColumn("Direccion");
            dtm.addColumn("Precio");
            dtm.addColumn("M2");
            dtm.addColumn("Info");
            dtm.addColumn("Modificar");
            dtm.addColumn("Eliminar");
            dtm.addColumn("id");

            jTable1.setModel(dtm);

            jTable1.getColumnModel().getColumn(0).setMaxWidth(90);
            jTable1.getColumnModel().getColumn(0).setMinWidth(90);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(90);

            jTable1.getColumnModel().getColumn(1).setMaxWidth(240);
            jTable1.getColumnModel().getColumn(1).setMinWidth(240);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(240);

            jTable1.getColumnModel().getColumn(2).setMaxWidth(80);
            jTable1.getColumnModel().getColumn(2).setMinWidth(80);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(80);

            jTable1.getColumnModel().getColumn(3).setMaxWidth(70);
            jTable1.getColumnModel().getColumn(3).setMinWidth(70);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(70);

            jTable1.getColumnModel().getColumn(4).setMaxWidth(50);
            jTable1.getColumnModel().getColumn(4).setMinWidth(50);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(50);

            jTable1.getColumnModel().getColumn(7).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(7).setMinWidth(0);
            jTable1.getColumnModel().getColumn(7).setPreferredWidth(0);

            Object[] datos = new Object[8];

            Statement stmt = con.createStatement();
            ResultSet resul = stmt.executeQuery("SELECT DISTINCT tipo, direccion, precio, metros_cuadrados, id_propiedad FROM propiedades P");

            while (resul.next()) {
                datos[0] = resul.getString(1);
                datos[1] = resul.getString(2);
                datos[2] = resul.getBigDecimal(3);
                datos[3] = resul.getBigDecimal(4);
                datos[4] = new ImageIcon(getClass().getResource("/imagenes/info.png"));
                datos[5] = new ImageIcon(getClass().getResource("/imagenes/modificar.png"));
                datos[6] = new ImageIcon(getClass().getResource("/imagenes/eliminar.png"));
                datos[7] = resul.getInt(5);
                dtm.addRow(datos);
            }

            jTable1.setModel(dtm);

            Statement stmt2 = con.createStatement();
            ResultSet resul2 = stmt2.executeQuery("SELECT DISTINCT tipo FROM propiedades");

            while (resul2.next()) {
                tipo_combo.addItem(resul2.getString(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actualizar_combo() {

        try {
            Connection con = activar();
            Statement stmt2 = con.createStatement();
            ResultSet resul2 = stmt2.executeQuery("SELECT DISTINCT tipo FROM propiedades");

            tipo_combo.removeAllItems();
            tipo_combo.addItem("");

            while (resul2.next()) {
                tipo_combo.addItem(resul2.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Obtener M2
    public static int obtenerValorM2(String metrosCuadrados) {
        switch (metrosCuadrados) {
            case "50 m²":
                return 50;
            case "200 m²":
                return 200;
            case "400 m²":
                return 400;
            case "1000 m²":
                return 1000;
            case "2000 m²":
                return 2000;
            default:
                return 0;
        }
    }

    //Filtrar
    public void filtrar() {
        String texto = buscador.getText();
        String comboBox = (String) tipo_combo.getSelectedItem();
        String comboBox2 = (String) habitaciones_combo.getSelectedItem();
        String comboBox3 = (String) banos_combo.getSelectedItem();
        String desde_m2 = (String) desde_combo.getSelectedItem();
        String hasta_m2 = (String) hasta_combo.getSelectedItem();
        double valorDesdePrecio = (double) jSpinner1.getValue();
        double valorHastaPrecio = (double) jSpinner2.getValue();

        Connection con = activar();

        try {

            DefaultTableModel dtm = new DefaultTableModel() {
                @Override
                public Class
                        getColumnClass(int columnIndex) {
                    switch (columnIndex) {
                        case 4:
                            return ImageIcon.class;
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

            dtm.addColumn("Tipo");
            dtm.addColumn("Direccion");
            dtm.addColumn("Precio");
            dtm.addColumn("M2");
            dtm.addColumn("Info");
            dtm.addColumn("Modificar");
            dtm.addColumn("Eliminar");
            dtm.addColumn("id");

            jTable1.setModel(dtm);

            jTable1.getColumnModel().getColumn(0).setMaxWidth(90);
            jTable1.getColumnModel().getColumn(0).setMinWidth(90);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(90);

            jTable1.getColumnModel().getColumn(1).setMaxWidth(240);
            jTable1.getColumnModel().getColumn(1).setMinWidth(240);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(240);

            jTable1.getColumnModel().getColumn(2).setMaxWidth(80);
            jTable1.getColumnModel().getColumn(2).setMinWidth(80);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(80);

            jTable1.getColumnModel().getColumn(3).setMaxWidth(70);
            jTable1.getColumnModel().getColumn(3).setMinWidth(70);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(70);

            jTable1.getColumnModel().getColumn(4).setMaxWidth(50);
            jTable1.getColumnModel().getColumn(4).setMinWidth(50);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(50);

            jTable1.getColumnModel().getColumn(7).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(7).setMinWidth(0);
            jTable1.getColumnModel().getColumn(7).setPreferredWidth(0);

            Object[] datos = new Object[8];

            Statement stmt = con.createStatement();
            String sentencia = "SELECT DISTINCT P.tipo, P.direccion, P.precio, P.metros_cuadrados, id_propiedad FROM propiedades P";

            //Alquilar activado
            if (alquilar_boton.isSelected()) {
                sentencia += " JOIN DisponibleAlquiler DA ON P.id_propiedad = DA.id_propiedad ";
            } //Comprar activado
            else if (comprar_boton.isSelected()) {
                sentencia += " JOIN DisponibleCompra DC ON P.id_propiedad = DC.id_propiedad ";
            }
            sentencia += " WHERE P.direccion LIKE '%" + texto + "%' AND P.tipo LIKE '%" + comboBox + "%'";

            //Tamaño
            if ((desde_m2 != null && !desde_m2.isEmpty() && (desde_m2.equals("50 m²") || desde_m2.equals("200 m²") || desde_m2.equals("400 m²") || desde_m2.equals("1000 m²") || desde_m2.equals("2000 m²")))
                    || (hasta_m2 != null && !hasta_m2.isEmpty() && (hasta_m2.equals("50 m²") || hasta_m2.equals("200 m²") || hasta_m2.equals("400 m²") || hasta_m2.equals("1000 m²") || hasta_m2.equals("2000 m²")))) {

                sentencia += " AND (";

                if (desde_m2 != null && !desde_m2.isEmpty() && (desde_m2.equals("50 m²") || desde_m2.equals("200 m²") || desde_m2.equals("400 m²") || desde_m2.equals("1000 m²") || desde_m2.equals("2000 m²"))) {
                    sentencia += "P.metros_cuadrados >= " + obtenerValorM2(desde_m2);
                }

                if (hasta_m2 != null && !hasta_m2.isEmpty() && (hasta_m2.equals("50 m²") || hasta_m2.equals("200 m²") || hasta_m2.equals("400 m²") || hasta_m2.equals("1000 m²") || hasta_m2.equals("2000 m²"))) {
                    if (desde_m2 != null && !desde_m2.isEmpty() && (desde_m2.equals("50 m²") || desde_m2.equals("200 m²") || desde_m2.equals("400 m²") || desde_m2.equals("1000 m²") || desde_m2.equals("2000 m²"))) {
                        sentencia += " AND ";
                    }
                    sentencia += "P.metros_cuadrados <= " + obtenerValorM2(hasta_m2);
                }

                sentencia += ")";
            }

            //Precio
            if ((valorDesdePrecio > 0 || valorHastaPrecio > 0)) {

                sentencia += " AND (";

                if (valorDesdePrecio > 0) {
                    sentencia += "P.precio >= " + valorDesdePrecio;
                }

                if (valorHastaPrecio > 0) {
                    if (valorDesdePrecio > 0) {
                        sentencia += " AND ";
                    }
                    sentencia += "P.precio <= " + valorHastaPrecio;
                }

                sentencia += ")";
            }

            //Habitaciones
            if (comboBox2 != null && !comboBox2.isEmpty()) {
                try {
                    int habitacionesSeleccionadas = Integer.parseInt(comboBox2);
                    sentencia += " AND P.habitaciones = " + habitacionesSeleccionadas;
                } catch (NumberFormatException e) {
                }
            }

            //Baños
            if (comboBox3 != null && !comboBox3.isEmpty()) {
                try {
                    int banosSeleccionados = Integer.parseInt(comboBox3);
                    sentencia += " AND P.banos = " + banosSeleccionados;
                } catch (NumberFormatException e) {
                }
            }

            ResultSet resul = stmt.executeQuery(sentencia);

            if (!resul.next()) {
                JOptionPane.showMessageDialog(null, "No hay resultados para los filtros proporcionados", "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
            } else {
                do {
                    datos[0] = resul.getString(1);
                    datos[1] = resul.getString(2);
                    datos[2] = resul.getBigDecimal(3);
                    datos[3] = resul.getBigDecimal(4);
                    datos[4] = new ImageIcon(getClass().getResource("/imagenes/info.png"));
                    datos[5] = new ImageIcon(getClass().getResource("/imagenes/modificar.png"));
                    datos[6] = new ImageIcon(getClass().getResource("/imagenes/eliminar.png"));
                    datos[7] = resul.getInt(5);
                    dtm.addRow(datos);
                } while (resul.next());

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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new FondoPanel();
        jLabel1 = new javax.swing.JLabel();
        buscador = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        tipo_combo = new javax.swing.JComboBox<>();
        comprar_boton = new javax.swing.JToggleButton();
        alquilar_boton = new javax.swing.JToggleButton();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        habitaciones_combo = new javax.swing.JComboBox<>();
        banos_combo = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        desde_combo = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        hasta_combo = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        jSpinner2 = new javax.swing.JSpinner();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logo.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        buscador.setToolTipText("Direccion");
        buscador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscadorKeyReleased(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lupa.jpg"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/log-out.jpg"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel3MousePressed(evt);
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        tipo_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        tipo_combo.setToolTipText("");

        buttonGroup1.add(comprar_boton);
        comprar_boton.setText("Comprar");

        buttonGroup1.add(alquilar_boton);
        alquilar_boton.setText("Alquilar");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/limpiar.png"))); // NOI18N
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jButton1.setText("Informe");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        habitaciones_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4 o mas" }));
        habitaciones_combo.setSelectedIndex(3);

        banos_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3 o mas" }));
        banos_combo.setSelectedIndex(2);

        jLabel15.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 0));
        jLabel15.setText("Tipo");

        jLabel16.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 0));
        jLabel16.setText("Habitaciones");

        jLabel17.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 0));
        jLabel17.setText("Baños");

        jPanel2.setBackground(new java.awt.Color(255, 255, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Más Filtros", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/aplicar_precio.png"))); // NOI18N
        jButton3.setText("Aplicar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(153, 153, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tamaño", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        jLabel10.setText("Desde:");

        desde_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sin límite", "50 m²", "200 m²", "400 m²", "1000 m²", "2000 m²", " ", " " }));

        jLabel11.setText("Hasta:");

        hasta_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sin límite", "50 m²", "200 m²", "400 m²", "1000 m²", "2000 m²" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(desde_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hasta_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(desde_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(hasta_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        jPanel4.setBackground(new java.awt.Color(153, 153, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Precio", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        jLabel5.setText("Desde:");

        jSpinner1.setPreferredSize(new java.awt.Dimension(80, 22));

        jLabel6.setText("Hasta:");

        jSpinner2.setPreferredSize(new java.awt.Dimension(80, 22));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jButton4.setText("Reiniciar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jButton3)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addGap(70, 70, 70))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jButton1)
                        .addGap(111, 111, 111)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscador, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(tipo_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(131, 131, 131)
                        .addComponent(comprar_boton)
                        .addGap(52, 52, 52)
                        .addComponent(alquilar_boton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15)
                        .addGap(29, 29, 29)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jLabel16)
                        .addGap(75, 75, 75)
                        .addComponent(jLabel17)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(habitaciones_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(banos_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addGap(163, 163, 163)))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(comprar_boton)
                                    .addComponent(alquilar_boton))))
                        .addGap(23, 23, 23))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel16)
                                .addComponent(jLabel17))
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tipo_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(habitaciones_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(banos_combo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        App a = new App();
        a.setVisible(true);
        a.dispose();
        buscador.setText("");
        tipo_combo.setSelectedIndex(0);
        buttonGroup1.clearSelection();
        tabla2();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MousePressed
        // TODO add your handling code here:
        Window[] window = Window.getWindows();
        for (Window w : window) {
            if (w.isDisplayable()) {
                w.dispose();
            }
        }
        Principal p = new Principal();
        p.setVisible(true);
    }//GEN-LAST:event_jLabel3MousePressed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        Connection con = activar();
        //Ampliar
        if (jTable1.getSelectedColumn() == 4) {
            int fila = jTable1.getSelectedRow();
            try {
                String ampliar = "SELECT id_propiedad FROM propiedades WHERE precio= " + jTable1.getValueAt(fila, 2) + " AND metros_cuadrados = " + jTable1.getValueAt(fila, 3);
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(ampliar);
                rs.next();
                int c = rs.getInt(1);
                AmpliarInfo dial = new AmpliarInfo(this, true, c);
                dial.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //Modificar
        if (jTable1.getSelectedColumn() == 5) {

            int fila = jTable1.getSelectedRow();

            try {
                String modificar = "SELECT * FROM propiedades WHERE precio= " + jTable1.getValueAt(fila, 2) + " AND metros_cuadrados = " + jTable1.getValueAt(fila, 3);
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(modificar);
                if (rs.next()) {
                    Window[] window = Window.getWindows();
                    for (Window w : window) {
                        if (w.isDisplayable()) {
                            w.dispose();
                        }
                    }
                    Modificar mo = new Modificar();
                    mo.cargarDatosDesdeTabla(rs.getInt("id_propiedad"), rs.getString("titulo"), rs.getString("descripcion"), rs.getString("tipo"), rs.getString("direccion"), rs.getDouble("precio"), rs.getInt("habitaciones"), rs.getInt("banos"), rs.getDouble("metros_cuadrados"), rs.getInt("agente_id"), rs.getString("ruta"));
                    mo.setVisible(true);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //Eliminar
        if (jTable1.getSelectedColumn() == 6) {
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Quieres eliminar esta fila?", "Eliminar", JOptionPane.OK_CANCEL_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                int fila = jTable1.getSelectedRow();

                try {

                    // Eliminar de la tabla 'DisponibleCompra'
                    String borrarDisponibleCompra = "DELETE FROM DisponibleCompra D WHERE id_propiedad = " + jTable1.getValueAt(fila, 7);
                    Statement stmDisponibleCompra = con.createStatement();
                    int confirmacionDisponibleCompra = stmDisponibleCompra.executeUpdate(borrarDisponibleCompra);
                    if (confirmacionDisponibleCompra >= 0) {
                        //JOptionPane.showMessageDialog(null, "Registro en DisponibleCompra eliminado correctamente");
                    }

                    // Eliminar de la tabla 'DisponibleAlquiler'
                    String borrarDisponibleAlquiler = "DELETE FROM DisponibleAlquiler D WHERE id_propiedad = " + jTable1.getValueAt(fila, 7);
                    Statement stmDisponibleAlquiler = con.createStatement();
                    int confirmacionDisponibleAlquiler = stmDisponibleAlquiler.executeUpdate(borrarDisponibleAlquiler);
                    if (confirmacionDisponibleAlquiler >= 0) {
                        //JOptionPane.showMessageDialog(null, "Registro en DisponibleAlquiler eliminado correctamente");
                    }

                    // Eliminar de la tabla 'propiedades'
                    String borrarPropiedad = "DELETE FROM propiedades P WHERE id_propiedad = " + jTable1.getValueAt(fila, 7);
                    Statement stmPropiedad = con.createStatement();
                    int confirmacionPropiedad = stmPropiedad.executeUpdate(borrarPropiedad);
                    if (confirmacionPropiedad >= 0) {
                        JOptionPane.showMessageDialog(null, "Propiedad eliminada correctamente");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error eliminar");
                }
            }
            actualizar_combo();
            filtrar();
        }


    }//GEN-LAST:event_jTable1MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        tabla2();
        buscador.setText("");
        tipo_combo.setSelectedIndex(0);
        habitaciones_combo.setSelectedIndex(3);
        banos_combo.setSelectedIndex(2);
        buttonGroup1.clearSelection();
        tabla2();
        jSpinner1.setValue(0.0);
        jSpinner2.setValue(0.0);
        desde_combo.setSelectedIndex(0);
        hasta_combo.setSelectedIndex(0);

    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        String texto = buscador.getText();
        String comboBox = (String) tipo_combo.getSelectedItem();
        String comboBox2 = (String) habitaciones_combo.getSelectedItem();
        String comboBox3 = (String) banos_combo.getSelectedItem();
        String desde_m2 = (String) desde_combo.getSelectedItem();
        String hasta_m2 = (String) hasta_combo.getSelectedItem();
        double valorDesdePrecio = (double) jSpinner1.getValue();
        double valorHastaPrecio = (double) jSpinner2.getValue();

        Connection con = activar();

        try {

            String sentencia = "";

            //Alquilar activado
            if (alquilar_boton.isSelected()) {
                sentencia += " JOIN DisponibleAlquiler DA ON P.id_propiedad = DA.id_propiedad ";
            } //Comprar activado
            else if (comprar_boton.isSelected()) {
                sentencia += " JOIN DisponibleCompra DC ON P.id_propiedad = DC.id_propiedad ";
            }

            sentencia += " WHERE P.direccion LIKE '%" + texto + "%' AND P.tipo LIKE '%" + comboBox + "%'";

            //Tamaño
            if ((desde_m2 != null && !desde_m2.isEmpty() && (desde_m2.equals("50 m²") || desde_m2.equals("200 m²") || desde_m2.equals("400 m²") || desde_m2.equals("1000 m²") || desde_m2.equals("2000 m²")))
                    || (hasta_m2 != null && !hasta_m2.isEmpty() && (hasta_m2.equals("50 m²") || hasta_m2.equals("200 m²") || hasta_m2.equals("400 m²") || hasta_m2.equals("1000 m²") || hasta_m2.equals("2000 m²")))) {

                sentencia += " AND (";

                if (desde_m2 != null && !desde_m2.isEmpty() && (desde_m2.equals("50 m²") || desde_m2.equals("200 m²") || desde_m2.equals("400 m²") || desde_m2.equals("1000 m²") || desde_m2.equals("2000 m²"))) {
                    sentencia += "P.metros_cuadrados >= " + obtenerValorM2(desde_m2);
                }

                if (hasta_m2 != null && !hasta_m2.isEmpty() && (hasta_m2.equals("50 m²") || hasta_m2.equals("200 m²") || hasta_m2.equals("400 m²") || hasta_m2.equals("1000 m²") || hasta_m2.equals("2000 m²"))) {
                    if (desde_m2 != null && !desde_m2.isEmpty() && (desde_m2.equals("50 m²") || desde_m2.equals("200 m²") || desde_m2.equals("400 m²") || desde_m2.equals("1000 m²") || desde_m2.equals("2000 m²"))) {
                        sentencia += " AND ";
                    }
                    sentencia += "P.metros_cuadrados <= " + obtenerValorM2(hasta_m2);
                }

                sentencia += ")";
            }

            //Precio
            if ((valorDesdePrecio > 0 || valorHastaPrecio > 0)) {

                sentencia += " AND (";

                if (valorDesdePrecio > 0) {
                    sentencia += "P.precio >= " + valorDesdePrecio;
                }

                if (valorHastaPrecio > 0) {
                    if (valorDesdePrecio > 0) {
                        sentencia += " AND ";
                    }
                    sentencia += "P.precio <= " + valorHastaPrecio;
                }

                sentencia += ")";
            }

            //Habitaciones
            if (comboBox2 != null && !comboBox2.isEmpty()) {
                try {
                    int habitacionesSeleccionadas = Integer.parseInt(comboBox2);
                    sentencia += " AND P.habitaciones = " + habitacionesSeleccionadas;
                } catch (NumberFormatException e) {
                }
            }

            //Baños
            if (comboBox3 != null && !comboBox3.isEmpty()) {
                try {
                    int banosSeleccionados = Integer.parseInt(comboBox3);
                    sentencia += " AND P.banos = " + banosSeleccionados;
                } catch (NumberFormatException e) {
                }
            }

            System.out.println(sentencia);

            InputStream vinculararchivo = null;
            vinculararchivo = getClass().getResourceAsStream("/informes/Informe3.jrxml");
            //String ruta_imagen = "informes/coffe.jpg";
            JasperReport jr = null;

            Map<String, Object> mapa = new HashMap<>();
            mapa.put("filtrar", sentencia);
            //mapa.put("icon", ruta_imagen);
            jr = JasperCompileManager.compileReport(vinculararchivo);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jr, mapa, con);
            JasperViewer visor = new JasperViewer(jasperPrint, false);
            visor.setVisible(true);

        } catch (JRException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void buscadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscadorKeyReleased
        // TODO add your handling code here:
        filtrar();
    }//GEN-LAST:event_buscadorKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        filtrar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        jSpinner1.setValue(0.0);
        jSpinner2.setValue(0.0);
        desde_combo.setSelectedIndex(0);
        hasta_combo.setSelectedIndex(0);
        filtrar();
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(App.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(App.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(App.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(App.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new App().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton alquilar_boton;
    private javax.swing.JComboBox<String> banos_combo;
    private javax.swing.JTextField buscador;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JToggleButton comprar_boton;
    private javax.swing.JComboBox<String> desde_combo;
    private javax.swing.JComboBox<String> habitaciones_combo;
    private javax.swing.JComboBox<String> hasta_combo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> tipo_combo;
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

}
