/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package inmobiliaria;

import com.toedter.calendar.JDateChooser;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Usuario
 */
public class Añadir extends javax.swing.JFrame {

    /**
     * Creates new form Añadir
     */
    private static int idAgente;

    public Añadir() {
        initComponents();
        this.setTitle("Añadir");

        setIconImage(getIconImage());

        this.setResizable(false);
        this.setLocationRelativeTo(null);

        //combo();
        jTextField5.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
                String currentText = getText(0, getLength());
                String newText = currentText.substring(0, offset) + str + currentText.substring(offset);

                if (esNumeroValido(newText)) {
                    super.insertString(offset, str, attr);
                }
            }

            private boolean esNumeroValido(String str) {
                try {
                    String[] parts = str.split("\\.");
                    if (parts.length > 1) {
                        if (parts[0].length() + parts[1].length() > 15 || parts[1].length() > 2) {
                            return false;
                        }
                    } else {
                        if (str.length() > 15) {
                            return false;
                        }
                    }

                    Double.parseDouble(str);
                    return true;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Introduzca un precio válido", "Formato", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            }
        });

        jTextField8.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
                String currentText = getText(0, getLength());
                String newText = currentText.substring(0, offset) + str + currentText.substring(offset);

                if (esNumeroValido(newText)) {
                    super.insertString(offset, str, attr);
                }
            }

            private boolean esNumeroValido(String str) {
                try {
                    String[] parts = str.split("\\.");
                    if (parts.length > 1) {
                        if (parts[0].length() + parts[1].length() > 15 || parts[1].length() > 2) {
                            return false;
                        }
                    } else {
                        if (str.length() > 15) {
                            return false;
                        }
                    }

                    Double.parseDouble(str);
                    return true;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Introduzca un tamaño válido", "Formato", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            }
        });

        jTextField6.setDocument(new IntegerDocument());
        jTextField7.setDocument(new IntegerDocument());

    }

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("imagenes/logo.png"));
        return retValue;
    }

    public Añadir(int iDAgente) {
        initComponents();
        this.setTitle("Añadir");

        setIconImage(getIconImage());

        idAgente = iDAgente;
        System.out.println(idAgente);

        this.setResizable(false);
        this.setLocationRelativeTo(null);

        //combo();
        jTextField5.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
                String currentText = getText(0, getLength());
                String newText = currentText.substring(0, offset) + str + currentText.substring(offset);

                if (esNumeroValido(newText)) {
                    super.insertString(offset, str, attr);
                }
            }

            private boolean esNumeroValido(String str) {
                try {
                    String[] parts = str.split("\\.");
                    if (parts.length > 1) {
                        if (parts[0].length() + parts[1].length() > 15 || parts[1].length() > 2) {
                            return false;
                        }
                    } else {
                        if (str.length() > 15) {
                            return false;
                        }
                    }

                    Double.parseDouble(str);
                    return true;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Introduzca un precio válido", "Formato", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            }
        });

        jTextField8.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
                String currentText = getText(0, getLength());
                String newText = currentText.substring(0, offset) + str + currentText.substring(offset);

                if (esNumeroValido(newText)) {
                    super.insertString(offset, str, attr);
                }
            }

            private boolean esNumeroValido(String str) {
                try {
                    // Asegurarse de que haya como máximo 15 dígitos y 2 decimales
                    String[] parts = str.split("\\.");
                    if (parts.length > 1) {
                        if (parts[0].length() + parts[1].length() > 15 || parts[1].length() > 2) {
                            return false;
                        }
                    } else {
                        if (str.length() > 15) {
                            return false;
                        }
                    }

                    Double.parseDouble(str);
                    return true;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Introduzca un tamaño válido", "Formato", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            }
        });

        jTextField6.setDocument(new IntegerDocument());
        jTextField7.setDocument(new IntegerDocument());

    }

    public int getIdAgente() {
        return idAgente;
    }

    public void setIdAgente(String idAgente) {
        jTextField2.setText(idAgente);
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


    /*public void combo() {

        try {
            Connection con = activar();
            Statement stmt2 = con.createStatement();
            ResultSet resul2 = stmt2.executeQuery("SELECT DISTINCT id_agente FROM AgentesInmobiliarios");

            while (resul2.next()) {
                idagente_combo.addItem(resul2.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
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

    //Verificar tipo
    public boolean verificarTipoExistenteEnBaseDeDatos(String tipo) {

        Connection con = activar();

        try {

            String consulta = "SELECT COUNT(*) FROM propiedades WHERE tipo LIKE '%" + tipo + "%'";

            Statement stmt = (Statement) con.createStatement();

            try ( ResultSet resultado = stmt.executeQuery(consulta)) {
                if (resultado.next()) {
                    int cantidad = resultado.getInt(1);
                    return cantidad > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    //Cargar en label
    public void cargarYmostrarImagen() {
        JFileChooser archivo = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formatos de Archivos JPEG(*.JPG;*.JPEG;*.PNG)", "jpg", "jpeg", "png");
        archivo.setFileFilter(filtro);
        archivo.addChoosableFileFilter(filtro);
        archivo.setDialogTitle("Abrir Archivo");

        String directorioTrabajo = System.getProperty("user.dir");
        File ruta = new File(directorioTrabajo);
        archivo.setCurrentDirectory(ruta);

        int ventana = archivo.showOpenDialog(null);

        if (ventana == JFileChooser.APPROVE_OPTION) {
            File selectedFile = archivo.getSelectedFile();
            this.jTextField10.setText(String.valueOf(selectedFile));
            String selectedFilePath = selectedFile.getAbsolutePath();
            ImageIcon imageIcon = new ImageIcon(selectedFilePath);
            int maxWidth = 323;
            int maxHeight = 179;

            Image foto = imageIcon.getImage();
            int width = foto.getWidth(null);
            int height = foto.getHeight(null);
            double scaleFactor = Math.min(1.0 * maxWidth / width, 1.0 * maxHeight / height);
            int newWidth = (int) (width * scaleFactor);
            int newHeight = (int) (height * scaleFactor);

            foto = foto.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(foto);

            label_foto.setIcon(scaledIcon);
            label_foto.setHorizontalAlignment(label_foto.CENTER);
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
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        txtimagen = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        label_foto = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        estado_combo1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField2 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        disponibilidad_combo = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/añadir_inmueble.png"))); // NOI18N
        jButton1.setText("Añadir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Frank Ruhl Hofshi", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("Añadir Inmuebles");

        jLabel2.setForeground(new java.awt.Color(255, 255, 0));
        jLabel2.setText("Título:");

        jLabel3.setForeground(new java.awt.Color(255, 255, 0));
        jLabel3.setText("Descripción:");

        jLabel4.setForeground(new java.awt.Color(255, 255, 0));
        jLabel4.setText("Tipo:");

        jLabel5.setForeground(new java.awt.Color(255, 255, 0));
        jLabel5.setText("Dirección:");

        jLabel6.setForeground(new java.awt.Color(255, 255, 0));
        jLabel6.setText("Precio:");

        jLabel7.setForeground(new java.awt.Color(255, 255, 0));
        jLabel7.setText("Habitaciones:");

        jLabel8.setForeground(new java.awt.Color(255, 255, 0));
        jLabel8.setText("Baños:");

        jLabel9.setForeground(new java.awt.Color(255, 255, 0));
        jLabel9.setText("Metros cuadrados:");

        jTextField1.setPreferredSize(new java.awt.Dimension(300, 22));

        jTextField4.setPreferredSize(new java.awt.Dimension(300, 22));

        jTextField5.setPreferredSize(new java.awt.Dimension(300, 22));

        jTextField6.setPreferredSize(new java.awt.Dimension(300, 22));

        jTextField7.setPreferredSize(new java.awt.Dimension(300, 22));

        jTextField8.setPreferredSize(new java.awt.Dimension(300, 22));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logo.png"))); // NOI18N
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        jLabel12.setForeground(new java.awt.Color(255, 255, 0));
        jLabel12.setText("Id agente:");

        jTextField3.setPreferredSize(new java.awt.Dimension(300, 22));

        jButton2.setText("Seleccione imagen");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtimagen.setPreferredSize(new java.awt.Dimension(300, 22));

        jPanel2.setBackground(new java.awt.Color(255, 255, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Imagen seleccionada", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(label_foto, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_foto, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/volver.png"))); // NOI18N
        jButton3.setText("Volver");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel13.setForeground(new java.awt.Color(255, 255, 0));
        jLabel13.setText("Estado");

        estado_combo1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Comprar", "Alquilar" }));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTextField2.setEditable(false);
        jTextField2.setText("Seleccione Agente");
        jTextField2.setPreferredSize(new java.awt.Dimension(300, 22));
        jTextField2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField2MouseClicked(evt);
            }
        });

        jLabel14.setForeground(new java.awt.Color(255, 255, 0));
        jLabel14.setText("Disponibilidad");

        disponibilidad_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Disponible", "Anulado", "Comprado", "Alquilado" }));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/log-out.jpg"))); // NOI18N

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/limpiar.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(19, 19, 19)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel4)
                        .addGap(24, 24, 24)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel8))))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(211, 211, 211)
                                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jButton1)
                                .addGap(94, 94, 94)
                                .addComponent(jButton4)))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(116, 116, 116)
                                .addComponent(jLabel13)
                                .addGap(15, 15, 15)
                                .addComponent(estado_combo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton3)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(120, 120, 120)
                                            .addComponent(jButton2))
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(108, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(disponibilidad_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(201, 201, 201))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87)
                .addComponent(jLabel9)
                .addGap(12, 12, 12)
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addGap(98, 98, 98)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(15, 15, 15))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3))
                            .addComponent(jLabel1))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel9))
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(estado_combo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel14)
                                .addComponent(disponibilidad_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(34, 34, 34)
                                .addComponent(jLabel7)
                                .addGap(34, 34, 34)
                                .addComponent(jLabel8))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton4))))
                        .addContainerGap(101, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        /*String titulo = this.jTextField1.getText();
        String descripcion = this.jTextField2.getText();
        String tipo = this.jTextField3.getText();
        String direccion = this.jTextField4.getText();
        double precio = this.jTextField5.getText().isEmpty() ? 0.0 : Double.parseDouble(jTextField5.getText());
        int habitaciones = this.jTextField6.getText().isEmpty() ? 0 : Integer.parseInt(this.jTextField6.getText());
        int banos = this.jTextField7.getText().isEmpty() ? 0 : Integer.parseInt(this.jTextField7.getText());
        double metros_cuadrados = this.jTextField8.getText().isEmpty() ? 0.0 : Double.parseDouble(this.jTextField8.getText());
        String id_agente_str = this.idagente_combo.getSelectedItem().toString();
        int id_agente = id_agente_str.isEmpty() ? 0 : Integer.parseInt(id_agente_str);

        if (titulo.isEmpty() || descripcion.isEmpty() || tipo.isEmpty() || direccion.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
        } else {
            Connection con = activar();

            try {

                if (!verificarTipoExistenteEnBaseDeDatos(tipo)) {
                    int respuesta = JOptionPane.showConfirmDialog(null, "El tipo no existe en la base de datos. ¿Quieres añadirlo?", "Tipo No Existente", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                    if (respuesta == JOptionPane.NO_OPTION) {

                        return;
                    }
                    //Imagen
                    String rutaArchivo = this.jTextField10.getText();

                    if (rutaArchivo != null) {
                        File selectedFile = new File(rutaArchivo);
                        String nombreArchivo = selectedFile.getName();
                        String directorioTrabajo = System.getProperty("user.dir");
                        String rutaRelativaDestino = "src/fotos/";
                        File destino = new File(directorioTrabajo, rutaRelativaDestino);

                        Path sourcePath = selectedFile.toPath();
                        Path destinationPath = destino.toPath().resolve(selectedFile.getName());
                        Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

                        String sql_insert = "INSERT INTO propiedades (titulo, descripcion, tipo, direccion, precio, habitaciones, banos, metros_cuadrados, agente_id, ruta) "
                                + "VALUES('" + titulo + "', '" + descripcion + "', '" + tipo + "', '" + direccion + "', " + precio + ", " + habitaciones + ", " + banos + ", " + metros_cuadrados + ", " + id_agente + ", '" + rutaRelativaDestino + nombreArchivo + "')";
                        Statement stmt = (Statement) con.createStatement();
                        stmt.executeUpdate(sql_insert);
                        JOptionPane.showMessageDialog(null, "Ha sido añadido", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {

                    //Imagen
                    String rutaArchivo = this.jTextField10.getText();

                    if (rutaArchivo != null) {
                        File selectedFile = new File(rutaArchivo);
                        String nombreArchivo = selectedFile.getName();
                        String directorioTrabajo = System.getProperty("user.dir");
                        String rutaRelativaDestino = "src/fotos/";
                        File destino = new File(directorioTrabajo, rutaRelativaDestino);

                        Path sourcePath = selectedFile.toPath();
                        Path destinationPath = destino.toPath().resolve(selectedFile.getName());
                        Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

                        String sql_insert = "INSERT INTO propiedades (titulo, descripcion, tipo, direccion, precio, habitaciones, banos, metros_cuadrados, agente_id, ruta) "
                                + "VALUES('" + titulo + "', '" + descripcion + "', '" + tipo + "', '" + direccion + "', " + precio + ", " + habitaciones + ", " + banos + ", " + metros_cuadrados + ", " + id_agente + ", '" + rutaRelativaDestino + nombreArchivo + "')";
                        Statement stmt = (Statement) con.createStatement();
                        stmt.executeUpdate(sql_insert);
                        JOptionPane.showMessageDialog(null, "Ha sido añadido", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    }
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Inserte imagen", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }*/

        String titulo = this.jTextField1.getText();
        String descripcion = this.jTextArea1.getText();
        String tipo = this.jTextField3.getText();
        String direccion = this.jTextField4.getText();
        double precio = this.jTextField5.getText().isEmpty() ? 0.0 : Double.parseDouble(jTextField5.getText());
        int habitaciones = this.jTextField6.getText().isEmpty() ? 0 : Integer.parseInt(this.jTextField6.getText());
        int banos = this.jTextField7.getText().isEmpty() ? 0 : Integer.parseInt(this.jTextField7.getText());
        double metros_cuadrados = this.jTextField8.getText().isEmpty() ? 0.0 : Double.parseDouble(this.jTextField8.getText());
        String id_agente_str = this.jTextField2.getText();
        int id_agente = id_agente_str.isEmpty() ? 0 : Integer.parseInt(id_agente_str);
        String disponibilidad = (String) this.disponibilidad_combo.getSelectedItem();

        if (titulo.isEmpty() || descripcion.isEmpty() || tipo.isEmpty() || direccion.isEmpty() || id_agente_str.isEmpty() || disponibilidad.isEmpty() || titulo.isBlank() || descripcion.isBlank() || tipo.isBlank() || direccion.isBlank() || id_agente_str.isBlank() || disponibilidad.isBlank()) {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
        } else {
            Connection con = activar();

            try {

                if (!verificarTipoExistenteEnBaseDeDatos(tipo)) {
                    int respuesta = JOptionPane.showConfirmDialog(null, "El tipo no existe en la base de datos. ¿Quieres añadirlo?", "Tipo No Existente", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                    if (respuesta == JOptionPane.NO_OPTION) {

                        return;
                    }

                    // Generar id_propiedad
                    String sql_get_id = "SELECT MAX(id_propiedad) + 1 AS next_id FROM Propiedades";
                    Statement stmt_id = con.createStatement();
                    ResultSet rs = stmt_id.executeQuery(sql_get_id);
                    int id_propiedad = 1;
                    if (rs.next()) {
                        id_propiedad = rs.getInt("next_id");
                    }

                    // Imagen
                    String rutaArchivo = this.jTextField10.getText();
                    if (rutaArchivo != null) {

                        File selectedFile = new File(rutaArchivo);
                        String nombreArchivo = selectedFile.getName();
                        String directorioTrabajo = System.getProperty("user.dir");
                        String rutaRelativaDestino = "src/fotos/";
                        File destino = new File(directorioTrabajo, rutaRelativaDestino);

                        Path sourcePath = selectedFile.toPath();
                        Path destinationPath = destino.toPath().resolve(selectedFile.getName());
                        Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

                        JDateChooser dateChooserInicio = new JDateChooser();
                        int resultInicio = JOptionPane.showConfirmDialog(null, dateChooserInicio, "Seleccione la fecha de entrada", JOptionPane.OK_CANCEL_OPTION);
                        if (resultInicio == JOptionPane.OK_OPTION) {
                            Date fechaInicio = dateChooserInicio.getDate();

                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            String fechaInicioStr = dateFormat.format(fechaInicio);

                            if (estado_combo1.getSelectedIndex() == 1) {
                                String sql_insert_propiedad = "INSERT INTO Propiedades (id_propiedad, titulo, descripcion, tipo, direccion, precio, habitaciones, banos, metros_cuadrados, agente_id, ruta, disponibilidad) "
                                        + "VALUES(" + id_propiedad + ", '" + titulo + "', '" + descripcion + "', '" + tipo + "', '" + direccion + "', " + precio + ", " + habitaciones + ", " + banos + ", " + metros_cuadrados + ", " + id_agente + ", '" + rutaRelativaDestino + nombreArchivo + "', '" + disponibilidad + "')";
                                Statement stmt_propiedad = con.createStatement();
                                stmt_propiedad.executeUpdate(sql_insert_propiedad);

                                String sql_insert_alquiler = "INSERT INTO DisponibleAlquiler (id_alquiler, id_propiedad, fecha_inicio) "
                                        + "VALUES(NULL, " + id_propiedad + ", '" + fechaInicioStr + "')";
                                Statement stmt_alquiler = con.createStatement();
                                stmt_alquiler.executeUpdate(sql_insert_alquiler);

                                JOptionPane.showMessageDialog(null, "Propiedad añadida Correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                            }

                            if (estado_combo1.getSelectedIndex() == 0) {
                                String sql_insert_propiedad = "INSERT INTO Propiedades (id_propiedad, titulo, descripcion, tipo, direccion, precio, habitaciones, banos, metros_cuadrados, agente_id, ruta, disponibilidad) "
                                        + "VALUES(" + id_propiedad + ", '" + titulo + "', '" + descripcion + "', '" + tipo + "', '" + direccion + "', " + precio + ", " + habitaciones + ", " + banos + ", " + metros_cuadrados + ", " + id_agente + ", '" + rutaRelativaDestino + nombreArchivo + "', '" + disponibilidad + "')";
                                Statement stmt_propiedad = con.createStatement();
                                stmt_propiedad.executeUpdate(sql_insert_propiedad);

                                String sql_insert_compra = "INSERT INTO DisponibleCompra (id_compra, id_propiedad, fecha_compra) "
                                        + "VALUES(NULL, " + id_propiedad + ", '" + fechaInicioStr + "')";
                                Statement stmt_compra = con.createStatement();
                                stmt_compra.executeUpdate(sql_insert_compra);

                                JOptionPane.showMessageDialog(null, "Propiedad añadida Correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                            }

                        }
                    }

                } else {
                    String sql_get_id = "SELECT MAX(id_propiedad) + 1 AS next_id FROM Propiedades";
                    Statement stmt_id = con.createStatement();
                    ResultSet rs = stmt_id.executeQuery(sql_get_id);
                    int id_propiedad = 1;
                    if (rs.next()) {
                        id_propiedad = rs.getInt("next_id");
                    }

                    // Imagen
                    String rutaArchivo = this.jTextField10.getText();
                    if (rutaArchivo != null) {

                        File selectedFile = new File(rutaArchivo);
                        String nombreArchivo = selectedFile.getName();
                        String directorioTrabajo = System.getProperty("user.dir");
                        String rutaRelativaDestino = "src/fotos/";
                        File destino = new File(directorioTrabajo, rutaRelativaDestino);

                        Path sourcePath = selectedFile.toPath();
                        Path destinationPath = destino.toPath().resolve(selectedFile.getName());
                        Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

                        JDateChooser dateChooserInicio = new JDateChooser();
                        int resultInicio = JOptionPane.showConfirmDialog(null, dateChooserInicio, "Seleccione la fecha de entrada", JOptionPane.OK_CANCEL_OPTION);
                        if (resultInicio == JOptionPane.OK_OPTION) {
                            Date fechaInicio = dateChooserInicio.getDate();

                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            String fechaInicioStr = dateFormat.format(fechaInicio);

                            //Alquiler
                            if (estado_combo1.getSelectedIndex() == 1) {
                                String sql_insert_propiedad = "INSERT INTO Propiedades (id_propiedad, titulo, descripcion, tipo, direccion, precio, habitaciones, banos, metros_cuadrados, agente_id, ruta, disponibilidad) "
                                        + "VALUES(" + id_propiedad + ", '" + titulo + "', '" + descripcion + "', '" + tipo + "', '" + direccion + "', " + precio + ", " + habitaciones + ", " + banos + ", " + metros_cuadrados + ", " + id_agente + ", '" + rutaRelativaDestino + nombreArchivo + "', '" + disponibilidad + "')";
                                Statement stmt_propiedad = con.createStatement();
                                stmt_propiedad.executeUpdate(sql_insert_propiedad);

                                String sql_insert_alquiler = "INSERT INTO DisponibleAlquiler (id_alquiler, id_propiedad, fecha_inicio) "
                                        + "VALUES(NULL, " + id_propiedad + ", '" + fechaInicioStr + "')";
                                Statement stmt_alquiler = con.createStatement();
                                stmt_alquiler.executeUpdate(sql_insert_alquiler);

                                JOptionPane.showMessageDialog(null, "Propiedad añadida Correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                            }

                            //Compra
                            if (estado_combo1.getSelectedIndex() == 0) {
                                String sql_insert_propiedad = "INSERT INTO Propiedades (id_propiedad, titulo, descripcion, tipo, direccion, precio, habitaciones, banos, metros_cuadrados, agente_id, ruta, disponibilidad) "
                                        + "VALUES(" + id_propiedad + ", '" + titulo + "', '" + descripcion + "', '" + tipo + "', '" + direccion + "', " + precio + ", " + habitaciones + ", " + banos + ", " + metros_cuadrados + ", " + id_agente + ", '" + rutaRelativaDestino + nombreArchivo + "', '" + disponibilidad + "')";
                                Statement stmt_propiedad = con.createStatement();
                                stmt_propiedad.executeUpdate(sql_insert_propiedad);

                                String sql_insert_compra = "INSERT INTO DisponibleCompra (id_compra, id_propiedad, fecha_compra) "
                                        + "VALUES(NULL, " + id_propiedad + ", '" + fechaInicioStr + "')";
                                Statement stmt_compra = con.createStatement();
                                stmt_compra.executeUpdate(sql_insert_compra);

                                JOptionPane.showMessageDialog(null, "Propiedad añadida Correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                            }

                        }
                    }
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Introduzca imagen", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        // TODO add your handling code here:

        Window[] window = Window.getWindows();
        for (Window w : window) {
            if (w.isDisplayable()) {
                w.dispose();
            }
        }

        App a = new App(idAgente);
        a.setVisible(true);
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        cargarYmostrarImagen();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Window[] window = Window.getWindows();
        for (Window w : window) {
            if (w.isDisplayable()) {
                w.dispose();
            }
        }

        App a = new App(idAgente);
        a.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField2MouseClicked
        // TODO add your handling code here:
        Aministracion_Agentes ada = new Aministracion_Agentes(idAgente);
        ada.InfoAgente();
        ada.InfoAgentesRestantes();
        ada.setVisible(true);
    }//GEN-LAST:event_jTextField2MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.jTextField1.setText("");
        this.jTextArea1.setText("");
        this.jTextField3.setText("");
        this.jTextField4.setText("");
        this.jTextField5.setText("");
        this.jTextField6.setText("");
        this.jTextField7.setText("");
        this.jTextField8.setText("");
        this.jTextField2.setText("");
        this.estado_combo1.setSelectedIndex(0);
        this.disponibilidad_combo.setSelectedIndex(0);
        this.label_foto.setIcon(null);
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
            java.util.logging.Logger.getLogger(Añadir.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Añadir.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Añadir.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Añadir.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Añadir().setVisible(true);

            }
        });

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> disponibilidad_combo;
    private javax.swing.JComboBox<String> estado_combo1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    public static javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JLabel label_foto;
    private javax.swing.JTextField txtimagen;
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

    class IntegerDocument extends PlainDocument {

        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            if (getLength() + str.length() <= 2) {
                try {
                    Integer.parseInt(str);
                    super.insertString(offs, str, a);
                } catch (NumberFormatException e) {
                }
            }
        }
    }

}
