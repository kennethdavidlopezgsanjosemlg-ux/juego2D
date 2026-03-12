package controller;

public class Main {
    public static void main(String[] args) {
        MainController juego = new MainController();

        // Iniciamos el juego
        juego.getGamePanel().startGameThread();
    }
}