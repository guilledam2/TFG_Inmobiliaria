/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inmobiliaria;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.help.HelpSetException;
import javax.swing.JFrame;
import inmobiliaria.Principal;
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Guille
 */
public class Menu extends javax.swing.JMenuBar {

    String usuario;
    String contrasena;
    boolean existe = false;
    ResultSet resultado = null;
    PreparedStatement consulta = null;
    ResultSet resultado2 = null;
    PreparedStatement consulta2 = null;
    private App instancia;
    private Añadir instancia2;
    private int idAgente;

    JFrame padre;
    
    
    public Menu(JFrame origen) {
        this.padre = origen;

        jMenu1 = new javax.swing.JMenu();

        jMenuItem0 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        jMenu1.setText("Secciones");

        jMenuItem0.setText("Principal");
        jMenuItem0.addActionListener(new java.awt.event.ActionListener() {
            JFrame m = padre;

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                App ap = new App();
                ap.setVisible(true);
                m.dispose();
            }
        });
        jMenu1.add(jMenuItem0);

        jMenuItem1.setText("Añadir");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            JFrame m = padre;

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Añadir a = new Añadir();
                a.setVisible(true);
                m.dispose();
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Informacion Agente");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            JFrame m = padre;

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                App a = new App();
                int idAgente = a.getIdAgente();
                Info_Agentes infoAgentes = new Info_Agentes(idAgente);
                infoAgentes.setVisible(true);
                System.out.println(idAgente);

                m.dispose();
            }
        });
        jMenu1.add(jMenuItem2);

        this.add(jMenu1);

    }
    
    //Otro Constructor
    public Menu(JFrame origen, App a) {
        this.padre = origen;
        instancia=a;

        jMenu1 = new javax.swing.JMenu();

        jMenuItem0 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        jMenu1.setText("Secciones");

        jMenuItem0.setText("Principal");
        jMenuItem0.addActionListener(new java.awt.event.ActionListener() {
            JFrame m = padre;

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                App ap = new App();
                ap.setVisible(true);
                m.dispose();
            }
        });
        jMenu1.add(jMenuItem0);

        jMenuItem1.setText("Añadir");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            JFrame m = padre;

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Añadir an = new Añadir();
                an.setVisible(true);
                m.dispose();
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Informacion Agente");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            JFrame m = padre;

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
                idAgente = a.getIdAgente();
                //System.out.println("ID del Agente Menu: " + idAgente);

                Info_Agentes infoAgentes = new Info_Agentes(idAgente);
                infoAgentes.InfoAgente();
                infoAgentes.setVisible(true);

                m.dispose();
            }
        });
        jMenu1.add(jMenuItem2);

        this.add(jMenu1);

    }
    
    
    //Otro Constructor
    public Menu(JFrame origen, Añadir a) {
        this.padre = origen;
        instancia2=a;

        jMenu1 = new javax.swing.JMenu();

        jMenuItem0 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        jMenu1.setText("Secciones");

        jMenuItem0.setText("Principal");
        jMenuItem0.addActionListener(new java.awt.event.ActionListener() {
            JFrame m = padre;

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                App ap = new App();
                ap.setVisible(true);
                m.dispose();
            }
        });
        jMenu1.add(jMenuItem0);

        jMenuItem1.setText("Añadir");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            JFrame m = padre;

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Añadir an = new Añadir();
                an.setVisible(true);
                m.dispose();
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Informacion Agente");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            JFrame m = padre;

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
                idAgente = a.getIdAgente();
                //System.out.println("ID del Agente Menu: " + idAgente);

                Info_Agentes infoAgentes = new Info_Agentes(idAgente);
                infoAgentes.InfoAgente();
                infoAgentes.setVisible(true);

                m.dispose();
            }
        });
        jMenu1.add(jMenuItem2);

        this.add(jMenu1);

    }

    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem0;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;

}
