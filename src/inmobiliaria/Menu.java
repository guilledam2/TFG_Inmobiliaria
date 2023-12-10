/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inmobiliaria;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;

/**
 *
 * @author Guille
 */
public class Menu extends javax.swing.JMenuBar {

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
        jMenuItem3 = new javax.swing.JMenuItem();

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

        jMenuItem2.setText("Administración Agente");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            JFrame m = padre;

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                App a = new App();
                int idAgente = a.getIdAgente();
                Aministracion_Agentes infoAgentes = new Aministracion_Agentes(idAgente);
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
        jMenuItem3 = new javax.swing.JMenuItem();

        jMenu1.setText("Secciones");

        jMenuItem0.setText("Añadir");
        jMenuItem0.addActionListener(new java.awt.event.ActionListener() {
            JFrame m = padre;

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
                idAgente = a.getIdAgente();
                
                Añadir an = new Añadir(idAgente);
                Aministracion_Agentes infoAgentes = new Aministracion_Agentes(idAgente);
                infoAgentes.InfoAgente();
                infoAgentes.InfoAgentesRestantes();
                System.out.println("ID del Agente Menu: " + idAgente);
                an.setVisible(true);
                m.dispose();
            }
        });
        jMenu1.add(jMenuItem0);

        jMenuItem1.setText("Administración Agente");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            JFrame m = padre;

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
                idAgente = a.getIdAgente();
                //System.out.println("ID del Agente Menu: " + idAgente);

                Aministracion_Agentes infoAgentes = new Aministracion_Agentes(idAgente);
                infoAgentes.Solo_InfoAgente();
                infoAgentes.Solo_InfoAgentesRestantes();
                infoAgentes.setVisible(true);

                m.dispose();
            }
        });
        jMenu1.add(jMenuItem1);
        
        
        jMenuItem3.setText("Administración Clientes");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            JFrame m = padre;

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                App a = new App();
                int idAgente = a.getIdAgente();
                Administracion_Clientes clientes = new Administracion_Clientes(idAgente);
                clientes.setVisible(true);
                System.out.println(idAgente);

                m.dispose();
            }
        });
        jMenu1.add(jMenuItem3);

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
