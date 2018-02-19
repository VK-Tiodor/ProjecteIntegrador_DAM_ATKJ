/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import controlador.Controlador;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan Carlos Blanco
 */
public class escuchadorLlamadas extends Thread {

    // Constantes
    private static final String CADENA_COMPROBACION_ALERTA_CLIENTE = "eb8d3f1b179bfca7a3d31880b4d66778";
    private static final String CADENA_COMPROBACION_ALERTA_SERVIDOR = "3779ba59f06f7ae68c14527375ff4654";
    
    // Atributos
    private final Socket socket;
    private final Controlador controlador;
    
    // Constructor
    public escuchadorLlamadas(Socket socket, Controlador controlador) {
        this.socket = socket;
        this.controlador = controlador;
    }

    /**
     *  Método run del Thread, se ejecuta cuándo se abre una conexión y se encarga de escuchar el mensaje 
     * que se reciba. Comprueba que es la cadena esperada y si es así envía de vuelta al cliente la cadena que
     * espera el cliente. Si todo es correcto el cliente manda de vuelta su id y entonces se llama al método
     * lanzaAlerta.
     */
    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            
            // Se lee la cadena que manda el cliente
            String message = br.readLine();
            
            // Si la cadena es igual a la que posee el servidor
            if (message.equals(CADENA_COMPROBACION_ALERTA_CLIENTE)) {
                
                // El servidor manda la cadena para que el cliente sepa que todo está correcto
                bw.write(CADENA_COMPROBACION_ALERTA_SERVIDOR);
                bw.newLine();
                bw.flush();
                
                // El cliente manda su id
                this.controlador.lanzaAlerta(br.readLine(), br.readLine(), br.readLine());
            }
            bw.close();
            br.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(escuchadorLlamadas.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}