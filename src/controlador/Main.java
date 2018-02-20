/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import datechooser.beans.DateChooserCombo;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.Enumeration;
import javax.swing.plaf.FontUIResource;
import modelo.escuchadorLlamadas;

/**
 *
 * @author vesprada
 */
public class Main {

    //TODO - la vista de dependientes, que sea mitad mapa mitad dependiente, 

    
    private static final String MACHINE = "localhost";
    private static final int PORT = 9090;
    public static int TAMANYOLETRA = 2;
    private static Controlador controlador;

    public static void main(String args[]) {
        controlador = new Controlador();
        iniciaEscucha();

    }

    private static void iniciaEscucha() {
        InetSocketAddress sockAddr = new InetSocketAddress(MACHINE, PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                new escuchadorLlamadas(serverSocket.accept(), controlador).start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + PORT);
            System.exit(-1);
        }
    }

    public static void cambiaTamanyo(Component elemento) {
        elemento.setFont(new Font("sansserif", 0, elemento.getFont().getSize() * TAMANYOLETRA));
    }

    public static void recorreComponentesRecursivo(Component comp) {

        if (comp instanceof JButton) {
            Main.cambiaTamanyo((JButton) comp);
        } else if (comp instanceof JTextField) {
            Main.cambiaTamanyo((JTextField) comp);
        } else if (comp instanceof JLabel) {
            Main.cambiaTamanyo((JLabel) comp);
        } else if (comp instanceof JTable) {
            JTable jp = (JTable) comp;
            Main.cambiaTamanyo((JTable) comp);
        } else if (comp instanceof JList) {
            Main.cambiaTamanyo((JList) comp);
        } else if (comp instanceof JComboBox) {
            Main.cambiaTamanyo((JComboBox) comp);
        } else if (comp instanceof JCheckBox) {
            Main.cambiaTamanyo((JCheckBox) comp);
        } else if (comp instanceof DateChooserCombo) {

        } else if (comp instanceof JTextField) {
            Main.cambiaTamanyo((JCheckBox) comp);
        } else if (comp instanceof JPanel) {
            JPanel jp = (JPanel) comp;
            for (Component compo : jp.getComponents()) {
                recorreComponentesRecursivo(compo);
            }
        } else if (comp instanceof JScrollPane) {
            JScrollPane jp = (JScrollPane) comp;
            for (Component compo : jp.getComponents()) {
                recorreComponentesRecursivo(compo);
            }
        } else if (comp instanceof JFrame) {
            JFrame jp = (JFrame) comp;
            for (Component compo : jp.getComponents()) {
                recorreComponentesRecursivo(compo);
            }
        } else if (comp instanceof JTabbedPane) {
            JTabbedPane jp = (JTabbedPane) comp;
            Main.cambiaTamanyo(jp);
            for (Component compo : jp.getComponents()) {
                recorreComponentesRecursivo(compo);
            }
        } else if (comp instanceof JRootPane) {
            JRootPane jp = (JRootPane) comp;
            for (Component compo : jp.getComponents()) {
                recorreComponentesRecursivo(compo);
            }
        } else if (comp instanceof JLayeredPane) {
            JLayeredPane jp = (JLayeredPane) comp;
            for (Component compo : jp.getComponents()) {
                recorreComponentesRecursivo(compo);
            }
        }
    }
}
