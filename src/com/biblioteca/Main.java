package com.biblioteca;

import javax.swing.SwingUtilities;

/**
 * Clase de arranque de la aplicación.
 */
public class Main {
    public static void main(String[] args) {
        // invokeLater asegura que la interfaz se construya en el
        // hilo de eventos correcto de Swing.
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });
    }
}
