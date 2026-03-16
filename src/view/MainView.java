package view;

import model.Data;

import javax.swing.*;

/**
 * Ventana principal (Frame) del juego
 * Configura el contenedor donde se alojará el panel del juego
 */
public class MainView extends JFrame {
    private Data data;

    /**
     * Constructor de la vista principal.
     * @param data Referencia a los datos para obtener el título de la ventana
     */
    public MainView(Data data) {
        this.data = data;
        settings();
    }

    /**
     * Configuración básica de la ventana de Windows/Swing
     */
    public void settings() {
        // Establece el título desde la clase Data
        this.setTitle(data.getTexto(0));
        // Finaliza el proceso al cerrar la ventana
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // Centra la ventana en la pantalla (debe llamarse después de definir el tamaño o hacer pack())
        this.setLocationRelativeTo(null);
    }
}
