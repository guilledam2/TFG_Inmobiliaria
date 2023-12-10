/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package inmobiliaria;

import com.toedter.calendar.JDateChooser;
import static inmobiliaria.Añadir.jTextField2;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Usuario
 */
public class Modificar extends javax.swing.JFrame {

    private static int idAgente;

    /**
     * Creates new form Modificar
     */
    public Modificar() {
        initComponents();

        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Modificar");

        combo();

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

        jTextField6.setDocument(new Modificar.IntegerDocument());
        jTextField7.setDocument(new Modificar.IntegerDocument());
        jTextField11.setDocument(new Modificar.IntegerDocument2());

        setIconImage(getIconImage());

    }

    public Modificar(int iDAgente) {
        initComponents();

        idAgente = iDAgente;

        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Modificar");

        combo();

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

        jTextField6.setDocument(new Modificar.IntegerDocument());
        jTextField7.setDocument(new Modificar.IntegerDocument());
        jTextField11.setDocument(new Modificar.IntegerDocument2());

        setIconImage(getIconImage());

    }

    public static void actualizarDatos(String datos) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    jTextField11.setText(datos);
                } catch (Exception e) {
                }

            }
        });
    }

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("imagenes/logo.png"));
        return retValue;
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

    public void cargarDatosDesdeTabla(int id_propiedad, String titulo, String descripcion, String tipo, String direccion, double precio, int habitaciones, int banos, double m2, int idagente, String rutaImagen, String disponibilidad) {

        try {
            Connection con = activar();

            String queryAlquiler = "SELECT * FROM DisponibleAlquiler WHERE id_propiedad = " + id_propiedad;
            Statement stmtAlquiler = con.createStatement();
            ResultSet rsAlquiler = stmtAlquiler.executeQuery(queryAlquiler);

            String queryCompra = "SELECT * FROM DisponibleCompra WHERE id_propiedad = " + id_propiedad;
            Statement stmtCompra = con.createStatement();
            ResultSet rsCompra = stmtCompra.executeQuery(queryCompra);

            jTextField1.setText(titulo);
            jTextArea1.setText(descripcion);
            tipo_combo1.setSelectedItem(tipo);
            jTextField4.setText(direccion);
            jTextField5.setText(Double.toString(precio));
            jTextField6.setText(Integer.toString(habitaciones));
            jTextField7.setText(Integer.toString(banos));
            jTextField8.setText(Double.toString(m2));
            jTextField11.setText(Integer.toString(idagente));
            jTextField10.setText(Integer.toString(id_propiedad));
            dispo_combo.setSelectedItem(disponibilidad);

            while (rsAlquiler.next()) {
                estado_combo.setSelectedIndex(1);
            }

            while (rsCompra.next()) {
                estado_combo.setSelectedIndex(0);
            }

            if (rutaImagen != null && !rutaImagen.isEmpty()) {
                ImageIcon imageIcon = new ImageIcon(rutaImagen);
                imageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(290, 200, Image.SCALE_SMOOTH));
                label_foto.setIcon(imageIcon);
            } else {
                label_foto.setIcon(null);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Modificar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void combo() {
        try {
            Connection con = activar();
            Statement stmt2 = con.createStatement();
            ResultSet resul2 = stmt2.executeQuery("SELECT DISTINCT tipo FROM propiedades");

            while (resul2.next()) {
                tipo_combo1.addItem(resul2.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Modificar.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            this.jTextField3.setText(String.valueOf(selectedFile));
            String selectedFilePath = selectedFile.getAbsolutePath();
            ImageIcon imageIcon = new ImageIcon(selectedFilePath);
            int maxWidth = 268;
            int maxHeight = 149;

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

    /*public void modificar_datos(int id, String titulo_nuevo, String descripcion_nuevo, String tipo_nuevo, String direccion_nuevo, double precio_nuevo, int habitaciones_nuevo, int banos_nuevo, double m2_nuevo, int idagente_nuevo, String rutaRelativaDestino, String nombreArchivo) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        Connection con = activar();
        int opcion = JOptionPane.showConfirmDialog(null, "¿Desea realizar los datos en la base de datos?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            try {
                
                String rutaArchivo = this.jTextField3.getText();

                if (rutaArchivo != null) {
                    File selectedFile = new File(rutaArchivo);
                    nombreArchivo = selectedFile.getName();
                    String directorioTrabajo = System.getProperty("user.dir");
                    rutaRelativaDestino = "src/fotos/";
                    File destino = new File(directorioTrabajo, rutaRelativaDestino);

                    Path sourcePath = selectedFile.toPath();
                    Path destinationPath = destino.toPath().resolve(selectedFile.getName());
                    Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

                    Statement stmt = con.createStatement();

                    String actualizado = "UPDATE propiedades SET titulo = '" + titulo_nuevo + "', descripcion = '" + descripcion_nuevo + "', tipo = '" + tipo_nuevo
                            + "', direccion = '" + direccion_nuevo + "', precio = " + precio_nuevo + ", habitaciones = " + habitaciones_nuevo + ", banos = " + banos_nuevo + ", metros_cuadrados = " + m2_nuevo + ", agente_id = " + idagente_nuevo + ", ruta = '" + rutaRelativaDestino + nombreArchivo + "'"
                            + " WHERE id_propiedad = " + id;

                    stmt.executeUpdate(actualizado);
                    JOptionPane.showMessageDialog(null, "Ha sido modificado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    tabla();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/
    //Modificar
    public void modificar_datos(int id, String titulo_nuevo, String descripcion_nuevo, String tipo_nuevo, String direccion_nuevo, double precio_nuevo, int habitaciones_nuevo, int banos_nuevo, double m2_nuevo, int idagente_nuevo, String rutaRelativaDestino, String nombreArchivo, String disponibilidad_nuevo) {

        Connection con = activar();
        int opcion = JOptionPane.showConfirmDialog(null, "¿Desea realizar los cambios en la base de datos?", "Confirmación", JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            try {
                String rutaArchivo = this.jTextField3.getText();

                if (rutaArchivo != null && !rutaArchivo.isEmpty()) {
                    File selectedFile = new File(rutaArchivo);
                    nombreArchivo = selectedFile.getName();
                    String directorioTrabajo = System.getProperty("user.dir");
                    rutaRelativaDestino = "src/fotos/";
                    File destino = new File(directorioTrabajo, rutaRelativaDestino);

                    Path sourcePath = selectedFile.toPath();
                    Path destinationPath = destino.toPath().resolve(selectedFile.getName());
                    Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                } else {
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT ruta FROM propiedades WHERE id_propiedad = " + id);
                    if (rs.next()) {
                        String existingImagePath = rs.getString("ruta");
                        rutaRelativaDestino = existingImagePath.substring(0, existingImagePath.lastIndexOf('/') + 1);
                        nombreArchivo = existingImagePath.substring(existingImagePath.lastIndexOf('/') + 1);
                    }
                }

                JDateChooser dateChooserInicio = new JDateChooser();
                int confirmacionFecha = JOptionPane.showConfirmDialog(null, "¿Desea cambiar la fecha de entrada?", "Confirmar", JOptionPane.YES_NO_OPTION);

                if (confirmacionFecha == JOptionPane.YES_OPTION) {
                    int resultInicio = JOptionPane.showConfirmDialog(null, dateChooserInicio, "Seleccione la fecha de entrada", JOptionPane.OK_CANCEL_OPTION);

                    if (resultInicio == JOptionPane.OK_OPTION) {
                        Date fechaInicio = dateChooserInicio.getDate();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String fechaInicioStr = dateFormat.format(fechaInicio);

                        if (estado_combo.getSelectedIndex() == 0) {
                            //Eliminar de DisponibleAlquiler
                            String sql_eliminar_alquiler = "DELETE FROM DisponibleAlquiler WHERE id_propiedad = " + id;
                            Statement stmt_eliminar = con.createStatement();
                            stmt_eliminar.executeUpdate(sql_eliminar_alquiler);
                            stmt_eliminar.close();

                            //Añadir en DisponibleCompra
                            String sql_insert_compra = "INSERT INTO DisponibleCompra (id_compra, id_propiedad, fecha_compra) VALUES(NULL, " + id + ", '" + fechaInicioStr + "')";
                            Statement stmt_compra = con.createStatement();
                            stmt_compra.executeUpdate(sql_insert_compra);
                            stmt_compra.close();

                            //Actualizar en Propiedades
                            String sql_actualizar_disponibilidad = "UPDATE Propiedades SET disponibilidad = 'Disponible' WHERE id_propiedad = " + id;
                            Statement stmt_actualizar = con.createStatement();
                            stmt_actualizar.executeUpdate(sql_actualizar_disponibilidad);
                            stmt_actualizar.close();

                            JOptionPane.showMessageDialog(null, "Ha sido modificado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        }

                        if (estado_combo.getSelectedIndex() == 1) {
                            //Eliminar de DisponibleCompra
                            String sql_eliminar_compra = "DELETE FROM DisponibleCompra WHERE id_propiedad = " + id;
                            Statement stmt_eliminar_compra = con.createStatement();
                            stmt_eliminar_compra.executeUpdate(sql_eliminar_compra);
                            stmt_eliminar_compra.close();

                            //Añadir en DisponibleAlquiler
                            String sql_insert_alquiler = "INSERT INTO DisponibleAlquiler (id_alquiler, id_propiedad, fecha_inicio) VALUES(NULL, " + id + ", '" + fechaInicioStr + "')";
                            Statement stmt_alquiler = con.createStatement();
                            stmt_alquiler.executeUpdate(sql_insert_alquiler);
                            stmt_alquiler.close();

                            //Actualizar en Propiedades
                            String sql_actualizar_disponibilidad = "UPDATE Propiedades SET disponibilidad = 'Disponible' WHERE id_propiedad = " + id;
                            Statement stmt_actualizar = con.createStatement();
                            stmt_actualizar.executeUpdate(sql_actualizar_disponibilidad);
                            stmt_actualizar.close();

                            JOptionPane.showMessageDialog(null, "Ha sido modificado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        }

                        String sql_actualizar = "UPDATE Propiedades SET titulo = '" + titulo_nuevo + "', descripcion = '" + descripcion_nuevo + "', tipo = '" + tipo_nuevo + "', direccion = '" + direccion_nuevo + "', precio = " + precio_nuevo + ", habitaciones = " + habitaciones_nuevo + ", banos = " + banos_nuevo + ", metros_cuadrados = " + m2_nuevo + ", agente_id = " + idagente_nuevo + ", ruta = '" + rutaRelativaDestino + nombreArchivo + "', disponibilidad = '" + disponibilidad_nuevo + "'"
                                + " WHERE id_propiedad = " + id;
                        Statement stmt = con.createStatement();
                        stmt.executeUpdate(sql_actualizar);

                    }
                } else {
                    if (estado_combo.getSelectedIndex() == 0) {
                        // Obtener la fecha de DisponibleAlquiler
                        String sql_obtener_fecha_alquiler = "SELECT fecha_inicio FROM DisponibleAlquiler WHERE id_propiedad = " + id;
                        Statement stmt_obtener_fecha_alquiler = con.createStatement();
                        ResultSet rs_fecha_alquiler = stmt_obtener_fecha_alquiler.executeQuery(sql_obtener_fecha_alquiler);

                        String fecha_alquiler = null;
                        if (rs_fecha_alquiler.next()) {
                            fecha_alquiler = rs_fecha_alquiler.getString("fecha_inicio");
                        }

                        // Cerrar recursos
                        rs_fecha_alquiler.close();
                        stmt_obtener_fecha_alquiler.close();

                        // Eliminar de DisponibleAlquiler
                        String sql_eliminar_alquiler = "DELETE FROM DisponibleAlquiler WHERE id_propiedad = " + id;
                        Statement stmt_eliminar_alquiler = con.createStatement();
                        stmt_eliminar_alquiler.executeUpdate(sql_eliminar_alquiler);
                        stmt_eliminar_alquiler.close();

                        // Añadir en DisponibleCompra con la fecha obtenida de DisponibleAlquiler
                        if (fecha_alquiler != null) {
                            String sql_insert_compra = "INSERT INTO DisponibleCompra (id_compra, id_propiedad, fecha_compra) VALUES(NULL, " + id + ", '" + fecha_alquiler + "')";
                            Statement stmt_compra = con.createStatement();
                            stmt_compra.executeUpdate(sql_insert_compra);
                            stmt_compra.close();
                        }

                        // Actualizar en Propiedades
                        String sql_actualizar_disponibilidad = "UPDATE Propiedades SET disponibilidad = 'Disponible' WHERE id_propiedad = " + id;
                        Statement stmt_actualizar = con.createStatement();
                        stmt_actualizar.executeUpdate(sql_actualizar_disponibilidad);
                        stmt_actualizar.close();

                        JOptionPane.showMessageDialog(null, "Ha sido modificado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    }

                    if (estado_combo.getSelectedIndex() == 1) {
                        // Obtener la fecha de DisponibleCompra
                        String sql_obtener_fecha_compra = "SELECT fecha_compra FROM DisponibleCompra WHERE id_propiedad = " + id;
                        Statement stmt_obtener_fecha_compra = con.createStatement();
                        ResultSet rs_fecha_compra = stmt_obtener_fecha_compra.executeQuery(sql_obtener_fecha_compra);

                        String fecha_compra = null;
                        if (rs_fecha_compra.next()) {
                            fecha_compra = rs_fecha_compra.getString("fecha_compra");
                        }

                        // Cerrar recursos
                        rs_fecha_compra.close();
                        stmt_obtener_fecha_compra.close();

                        // Eliminar de DisponibleCompra
                        String sql_eliminar_compra = "DELETE FROM DisponibleCompra WHERE id_propiedad = " + id;
                        Statement stmt_eliminar_compra = con.createStatement();
                        stmt_eliminar_compra.executeUpdate(sql_eliminar_compra);
                        stmt_eliminar_compra.close();

                        // Añadir en DisponibleAlquiler con la fecha obtenida de DisponibleCompra
                        if (fecha_compra != null) {
                            String sql_insert_alquiler = "INSERT INTO DisponibleAlquiler (id_alquiler, id_propiedad, fecha_inicio) VALUES(NULL, " + id + ", '" + fecha_compra + "')";
                            Statement stmt_alquiler = con.createStatement();
                            stmt_alquiler.executeUpdate(sql_insert_alquiler);
                            stmt_alquiler.close();
                        }

                        // Actualizar en Propiedades
                        String sql_actualizar_disponibilidad = "UPDATE Propiedades SET disponibilidad = 'Disponible' WHERE id_propiedad = " + id;
                        Statement stmt_actualizar = con.createStatement();
                        stmt_actualizar.executeUpdate(sql_actualizar_disponibilidad);
                        stmt_actualizar.close();

                        JOptionPane.showMessageDialog(null, "Ha sido modificado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    }
                }

                String sql_actualizar = "UPDATE Propiedades SET titulo = '" + titulo_nuevo + "', descripcion = '" + descripcion_nuevo + "', tipo = '" + tipo_nuevo + "', direccion = '" + direccion_nuevo + "', precio = " + precio_nuevo + ", habitaciones = " + habitaciones_nuevo + ", banos = " + banos_nuevo + ", metros_cuadrados = " + m2_nuevo + ", agente_id = " + idagente_nuevo + ", ruta = '" + rutaRelativaDestino + nombreArchivo + "', disponibilidad = '" + disponibilidad_nuevo + "'"
                        + " WHERE id_propiedad = " + id;
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql_actualizar);

            } catch (Exception e) {
                e.printStackTrace();
            }
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
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        label_foto = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        tipo_combo1 = new javax.swing.JComboBox<>();
        estado_combo = new javax.swing.JComboBox<>();
        dispo_combo = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setForeground(new java.awt.Color(255, 255, 0));
        jLabel2.setText("Título:");

        jTextField1.setPreferredSize(new java.awt.Dimension(300, 22));

        jLabel3.setForeground(new java.awt.Color(255, 255, 0));
        jLabel3.setText("Descripción:");

        jLabel4.setForeground(new java.awt.Color(255, 255, 0));
        jLabel4.setText("Tipo:");

        jLabel5.setForeground(new java.awt.Color(255, 255, 0));
        jLabel5.setText("Dirección:");

        jTextField4.setPreferredSize(new java.awt.Dimension(300, 22));

        jLabel6.setForeground(new java.awt.Color(255, 255, 0));
        jLabel6.setText("Precio:");

        jTextField5.setPreferredSize(new java.awt.Dimension(300, 22));

        jLabel7.setForeground(new java.awt.Color(255, 255, 0));
        jLabel7.setText("Habitaciones:");

        jTextField6.setPreferredSize(new java.awt.Dimension(300, 22));

        jLabel8.setForeground(new java.awt.Color(255, 255, 0));
        jLabel8.setText("Baños:");

        jTextField7.setPreferredSize(new java.awt.Dimension(300, 22));

        jLabel9.setForeground(new java.awt.Color(255, 255, 0));
        jLabel9.setText("Metros cuadrados:");

        jTextField8.setPreferredSize(new java.awt.Dimension(300, 22));

        jLabel12.setForeground(new java.awt.Color(255, 255, 0));
        jLabel12.setText("Estado:");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/modificar.png"))); // NOI18N
        jButton1.setText("Modificar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Frank Ruhl Hofshi", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("Modificar Inmuebles");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logo.png"))); // NOI18N
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        jTextField10.setPreferredSize(new java.awt.Dimension(0, 0));

        jTextField3.setPreferredSize(new java.awt.Dimension(0, 0));

        jPanel2.setBackground(new java.awt.Color(255, 255, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Imagen seleccionada", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(label_foto, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(label_foto, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/volver.png"))); // NOI18N
        jButton3.setText("Volver");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jLabel13.setForeground(new java.awt.Color(255, 255, 0));
        jLabel13.setText("Id agente:");

        jTextField11.setEditable(false);
        jTextField11.setText("Seleccione Agente");
        jTextField11.setPreferredSize(new java.awt.Dimension(300, 22));
        jTextField11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField11MouseClicked(evt);
            }
        });

        estado_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Comprar", "Alquilar" }));

        dispo_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Disponible", "Anulado", "Comprado", "Alquilado" }));

        jLabel15.setForeground(new java.awt.Color(255, 255, 0));
        jLabel15.setText("Disponibilidad:");

        jButton5.setText("Seleccione imagen");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/log-out.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(99, 99, 99)
                        .addComponent(jLabel1)
                        .addGap(85, 85, 85)
                        .addComponent(jButton3)
                        .addGap(62, 62, 62)
                        .addComponent(jLabel16))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(jLabel9)
                        .addGap(12, 12, 12)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel3)
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(119, 119, 119)
                                .addComponent(jLabel12)
                                .addGap(23, 23, 23)
                                .addComponent(estado_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(106, 106, 106)
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(868, 868, 868)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(26, 26, 26)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(34, 34, 34)
                                            .addComponent(jLabel8)))
                                    .addGap(12, 12, 12)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(320, 320, 320))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton1)
                                    .addGap(230, 230, 230)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addComponent(jLabel5)
                                        .addGap(17, 17, 17)
                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(60, 60, 60)
                                        .addComponent(jLabel6)
                                        .addGap(14, 14, 14)
                                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(61, 61, 61)
                                        .addComponent(jLabel4)
                                        .addGap(24, 24, 24)
                                        .addComponent(tipo_combo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(80, 80, 80)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(18, 18, 18)
                                .addComponent(dispo_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(jButton5))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jButton3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel16)))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel9))
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(45, 45, 45)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(estado_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(tipo_combo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel7)
                                .addGap(41, 41, 41)
                                .addComponent(jLabel8))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(dispo_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addComponent(jButton5)
                        .addGap(28, 28, 28)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(48, Short.MAX_VALUE))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        String titulo_nuevo = this.jTextField1.getText();
        String descripcion_nuevo = this.jTextArea1.getText();
        String tipo_nuevo = (String) this.tipo_combo1.getSelectedItem();
        String direccion_nuevo = this.jTextField4.getText();
        double precio_nuevo = Double.parseDouble(this.jTextField5.getText());
        int habitaciones_nuevo = Integer.parseInt(this.jTextField6.getText());
        int banos_nuevo = Integer.parseInt(this.jTextField7.getText());
        double m2_nuevo = Double.parseDouble(this.jTextField8.getText());
        int idagente_nuevo = Integer.parseInt(this.jTextField11.getText());
        int id = Integer.parseInt(this.jTextField10.getText());
        String disponibilidad_nuevo = (String) this.dispo_combo.getSelectedItem();

        String rutaArchivo = this.jTextField3.getText();

        File selectedFile = new File(rutaArchivo);
        String nombreArchivo = selectedFile.getName();
        String rutaRelativaDestino = "src/fotos/";

        this.modificar_datos(id, titulo_nuevo, descripcion_nuevo, tipo_nuevo, direccion_nuevo, precio_nuevo, habitaciones_nuevo, banos_nuevo, m2_nuevo, idagente_nuevo, rutaRelativaDestino, nombreArchivo, disponibilidad_nuevo);

    }//GEN-LAST:event_jButton1ActionPerformed

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

    private void jTextField11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField11MouseClicked
        // TODO add your handling code here:
        Aministracion_Agentes ada = new Aministracion_Agentes(idAgente);
        ada.InfoAgente();
        ada.InfoAgentesRestantes();
        ada.setVisible(true);
    }//GEN-LAST:event_jTextField11MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(Modificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Modificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Modificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Modificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Modificar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> dispo_combo;
    private javax.swing.JComboBox<String> estado_combo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private static javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JLabel label_foto;
    private javax.swing.JComboBox<String> tipo_combo1;
    // End of variables declaration//GEN-END:variables

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

    class IntegerDocument2 extends PlainDocument {

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
