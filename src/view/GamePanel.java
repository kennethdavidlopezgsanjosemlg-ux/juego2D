package view;

import listener.handListener;
import model.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements Runnable {
    private Data data;
    private final int OriginalTileSize = 16;
    private final int Scale = 3;

    private final int TileSize = OriginalTileSize * Scale;
    private final int MaxScreenCol = 16;
    private final int MaxScreenRow = 12;
    private final int ScreenWidth = TileSize * MaxScreenCol;
    private final int ScreenHeight = TileSize * MaxScreenRow;
    private Thread gameThread;

    // Listeners

    private handListener keyInputListener; // movimiento jugador

    // Configuración del jugador

    // posición inicial del jugador
    private int playerX = 384;
    private int playerY = 288;

    // velocidad del jugador
    private int playerSpeed = 20;

    // Constructor por defecto
    public GamePanel(Data data) {
        this.data = data;
        settings();

    }

    // Métodos

    // pincel del juego
    public void paintComponent(Graphics g) {
        // limpia la ventana
        super.paintComponent(g);

        // Cambiamos los graficos a 2D
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, TileSize, TileSize);

        g2.dispose(); // guardamos los cambios


    }

    public void update() {

        if (keyInputListener.isUpPressed() ) {
            playerY -= playerSpeed;

        }

        if (keyInputListener.isDownPressed()) {
            playerY += playerSpeed;

        }

        if (keyInputListener.isLeftPressed()) {
            playerX -= playerSpeed;

        }

        if (keyInputListener.isRightPressed()) {
            playerX += playerSpeed;

        }

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void settings() {
        this.setPreferredSize(new Dimension(ScreenWidth, ScreenHeight));
        this.setBackground(Color.BLACK);
        /*
         *Activa el doble búfer para eliminar el parpadeo (flickering) al redibujar la interfaz.
         *Esto dibuja primero en memoria y luego vuelca todo el contenido a la pantalla de golpe.
         */
        this.setDoubleBuffered(true);
        this.setFocusable(true); // Permite que el panel reciba eventos de teclado
    }

    // Listeners

    public void addHandListener(handListener keyInputListener) {
        this.keyInputListener = keyInputListener;
        this.addKeyListener(keyInputListener);
    }


    @Override
    public void run() {
        // Aquí es donde sucede la magia del Game Loop
        while (gameThread != null) {

            // 1. UPDATE: Actualizar posiciones de personajes
            update();

            // 2. DRAW: Dibujar la pantalla con los nuevos datos que es lo que se va a actualizar
            repaint();

            // 3. SLEEP: Pausar un poquito para que el juego vaya a 60 FPS y no a mil millones
            long currenTime = System.nanoTime();
            try {
                Thread.sleep(16); // Aproximadamente 60 FPS
                System.out.println("Tiempo: " + (System.nanoTime() - currenTime) / 1000000 + "ms");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
