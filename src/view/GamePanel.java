package view;

import listener.handListener;
import model.Data;
import model.Entity.Player;

import javax.swing.*;
import java.awt.*;

/**
 * Panel principal del juego donde ocurre la renderización y la lógica.
 * Implementa Runnable para ejecutar el "Game Loop" en un hilo separado.
 */
public class GamePanel extends JPanel implements Runnable {

    private Data data;

    // Configuración de la ventana y los tiles
    private final int ORIGINAL_TILESIZE;
    private final int SCALE;
    private final int TILE_SIZE;
    private final int MAX_SCREENCOL;
    private final int MAX_SCREENROW;
    private final int SCREEN_WIDTH;
    private final int SCREEN_HEIGHT;
    private final int FPS;

    // Hilo encargado del Game Loop
    private Thread gameThread; 

    // Métricas de rendimiento
    private double actualFPS;
    private final int SECOND = 1000000000; // Representación de un segundo en nanosegundos

    // Listeners para entrada de usuario
    private handListener keyInputListener;

    // Entidades del juego
    Player player;

    /**
     * Constructor del GamePanel.
     * Carga las configuraciones iniciales desde el almacén de datos (Data).
     * @param data Objeto Data con las constantes y variables del juego.
     */
    public GamePanel(Data data) {
        this.data = data;

        // Carga de datos base (Numeros almacenados en ArrayList)
        this.ORIGINAL_TILESIZE = data.getNumero(0);
        this.SCALE = data.getNumero(1);
        this.MAX_SCREENCOL = data.getNumero(2);
        this.MAX_SCREENROW = data.getNumero(3);
        this.FPS = data.getNumero(4);

        // Cálculos de dimensiones de pantalla
        this.TILE_SIZE = ORIGINAL_TILESIZE * SCALE;
        this.SCREEN_WIDTH = TILE_SIZE * MAX_SCREENCOL;
        this.SCREEN_HEIGHT = TILE_SIZE * MAX_SCREENROW;

        settings();
    }

    /**
     * Dibuja los componentes gráficos en el panel.
     * Es invocado automáticamente por el sistema (vía repaint()).
     * @param g Contexto gráfico básico.
     */
    public void paintComponent(Graphics g) {
        // Limpiamos el panel antes de redibujar
        super.paintComponent(g);

        // Convertimos a Graphics2D para mayor control (antialiasing, transformaciones, etc.)
        Graphics2D g2 = (Graphics2D) g;
        
        // Mejora la suavidad de los bordes
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Sincroniza la renderización para evitar "tearing" (desgarro de pantalla)
        Toolkit.getDefaultToolkit().sync();

        // Dibujamos al jugador si existe
        if (player != null) {
            player.draw(g2);
        }

        // Overlay de información de rendimiento
        g2.setColor(Color.white);
        g2.drawString("FPS: " + (int) actualFPS, 10, 12);

        // Liberar recursos gráficos
        g2.dispose(); 
    }

    /**
     * Actualiza la lógica de las entidades.
     * @param delTime Tiempo transcurrido para cálculos de movimiento.
     */
    public void update(double delTime) {
        if (player != null) {
            player.update(delTime);
        }
    }

    /**
     * Inicia el hilo principal del juego si no está ya en ejecución.
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Configuración estética y técnica del JPanel.
     */
    public void settings() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        /*
         * El doble búfer dibuja en una imagen oculta en RAM y luego vuelca
         * todo al monitor de golpe, eliminando el parpadeo.
         */
        this.setDoubleBuffered(true);
        // Permite que el panel sea el receptor de los eventos de teclado
        this.setFocusable(true); 
    }

    /**
     * Vincula el listener de teclado al panel y crea el objeto jugador.
     * @param keyInputListener El gestor de pulsaciones de teclas.
     */
    public void addHandListener(handListener keyInputListener) {
        this.keyInputListener = keyInputListener;
        this.addKeyListener(keyInputListener);
        // Creamos al jugador una vez que el panel está listo
        this.player = new Player(this, keyInputListener, data, TILE_SIZE);
    }

    /**
     * Implementación del Game Loop (Método Delta/Acumulador).
     * Este bucle garantiza que el juego se ejecute a una velocidad constante
     * independientemente de la potencia de procesamiento del ordenador.
     */
    @Override
    public void run() {
        // Cálculo del intervalo de tiempo entre cada frame
        double drawInterval = 1000000000.0 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        // Variables para métricas de FPS
        long timer = 0;
        int drawCount = 0;

        // Bucle infinito mientras el hilo esté activo
        while (gameThread != null) {
            currentTime = System.nanoTime();

            // Calculamos cuánto tiempo ha pasado y lo añadimos al acumulador 'delta'
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime); 
            lastTime = currentTime;

            // Cuando delta llega a 1 (o más), ha pasado el tiempo suficiente para un frame
            if (delta >= 1) {
                // 1. UPDATE: Actualización lógica (movimiento, colisiones)
                // Enviamos el tiempo real de frame para cálculos de física/movimiento
                update(drawInterval / 1000000000);

                // 2. DRAW: Ordenamos redibujar la pantalla
                repaint();
                
                delta--; // Restamos 1 ciclo completado
                drawCount++; // Contador para cálculo de FPS reales
            }

            // Si ha pasado un segundo completo, actualizamos el contador de FPS visibles
            if (timer >= SECOND) {
                actualFPS = drawCount; 
                drawCount = 0; 
                timer = 0;
            }

            // Pequeña pausa para no sobrecargar la CPU innecesariamente
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
