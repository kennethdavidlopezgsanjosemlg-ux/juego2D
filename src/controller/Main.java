package controller;

/**
 * Punto de entrada principal de la aplicación
 * Sigue el patrón MVC iniciando el controlador que orquesta todo
 */
public class Main {
    /**
     * Método principal que lanza el programa
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        // Creamos el controlador principal
        MainController juego = new MainController();

        // Iniciamos el hilo del juego (Game Loop) a través del panel
        juego.getGamePanel().startGameThread();
    }
}