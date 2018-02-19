/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import modelo.escuchadorLlamadas;

/**
 *
 * @author vesprada
 */
public class Main {

    private static final String MACHINE = "localhost";
    private static final int PORT = 9090;
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
}
