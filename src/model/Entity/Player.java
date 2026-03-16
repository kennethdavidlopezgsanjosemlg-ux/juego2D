package model.Entity;

import listener.handListener;
import model.Data;
import view.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.util.ArrayList;

/**
 * Representa al jugador dentro del juego.
 * Gestiona su posición, movimiento basado en teclado y representación visual con animaciones extraídas de plantillas.
 */
public class Player extends Entity {

    private GamePanel gp;
    private handListener keyInputListener;
    private Data data;
    private final int TILE_SIZE;

    // Dimensiones de cada frame en las hojas de sprites proporcionadas
    private final int FRAME_WIDTH = 64;
    private final int FRAME_HEIGHT = 128;

    /**
     * Constructor del jugador.
     */
    public Player(GamePanel gp, handListener keyInputListener, Data data, int tileSize) {
        this.gp = gp;
        this.keyInputListener = keyInputListener;
        this.data = data;
        this.TILE_SIZE = tileSize;

        getPlayerImage();
        setDefaultValues();
    }

    /**
     * Carga las animaciones del jugador desde las hojas de sprites.
     */
    public void getPlayerImage() {
        // Carga de la hoja de sprites para reposo (Idle)
        BufferedImage idleSheet = loadFullImage("assets/player/64X128_Idle_Free.png");
        if (idleSheet != null) {
            idleDown = setupAnimation(idleSheet, 0);  // Fila 0 - Down
            idleLeft = setupAnimation(idleSheet, 1);  // Fila 1 - Left
            idleRight = setupAnimation(idleSheet, 2); // Fila 2 - Right
            idleUp = setupAnimation(idleSheet, 3);    // Fila 3 - Up
        }

        // Carga de la hoja de sprites para caminar (Walking)
        BufferedImage walkSheet = loadFullImage("assets/player/64X128_Walking_Free.png");
        if (walkSheet != null) {
            walkDown = setupAnimation(walkSheet, 0);
            walkLeft = setupAnimation(walkSheet, 1);
            walkRight = setupAnimation(walkSheet, 2);
            walkUp = setupAnimation(walkSheet, 3);
        }

        // Carga de la hoja de sprites para correr (Running)
        BufferedImage runSheet = loadFullImage("assets/player/64X128_Runing_Free.png");
        if (runSheet != null) {
            runDown = setupAnimation(runSheet, 0);
            runLeft = setupAnimation(runSheet, 1);
            runRight = setupAnimation(runSheet, 2);
            runUp = setupAnimation(runSheet, 3);
        }
    }

    /**
     * Carga una imagen completa desde el disco.
     */
    private BufferedImage loadFullImage(String path) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                System.err.println("Archivo no encontrado: " + file.getAbsolutePath());
                return null;
            }
            return ImageIO.read(file);
        } catch (IOException e) {
            System.err.println("Error cargando imagen: " + path);
            return null;
        }
    }

    /**
     * Divide una fila de la hoja de sprites en frames individuales.
     * @param sheet La imagen completa de la hoja.
     * @param row La fila que se desea extraer.
     * @return Lista de frames.
     */
    private ArrayList<BufferedImage> setupAnimation(BufferedImage sheet, int row) {
        ArrayList<BufferedImage> list = new ArrayList<>();
        int cols = sheet.getWidth() / FRAME_WIDTH;
        
        for (int i = 0; i < cols; i++) {
            list.add(sheet.getSubimage(i * FRAME_WIDTH, row * FRAME_HEIGHT, FRAME_WIDTH, FRAME_HEIGHT));
        }
        return list;
    }

    /**
     * Establece los valores iniciales del jugador.
     */
    public void setDefaultValues() {
        x = data.getDecimal(0); // playerX
        y = data.getDecimal(1); // playerY
        speed = data.getNumero(5); // playerSpeed
        direction = "down";
        moving = false;
        spriteNum = 0;
    }

    /**
     * Actualiza la lógica del jugador (movimiento y animación).
     */
    public void update(double delTime) {
        double speedPerSecond = speed * 10;
        double velocity = speedPerSecond * delTime;

        boolean wasMoving = moving;
        moving = false;

        // Gestión de entrada de teclado
        if (keyInputListener.isUpPressed()) {
            direction = "up";
            y -= velocity;
            moving = true;
        } else if (keyInputListener.isDownPressed()) {
            direction = "down";
            y += velocity;
            moving = true;
        }

        if (keyInputListener.isLeftPressed()) {
            direction = "left";
            x -= velocity;
            moving = true;
        } else if (keyInputListener.isRightPressed()) {
            direction = "right";
            x += velocity;
            moving = true;
        }

        // Si el estado de movimiento ha cambiado, reiniciamos el contador
        if (wasMoving != moving) {
            spriteCounter = 0;
            spriteNum = 0;
        }

        // Ciclo de animación (Actualizamos el frame cada cierto tiempo)
        spriteCounter++;
        // Ajustamos la velocidad de la animación (más alto = más lento)
        int animationSpeed = moving ? 10 : 15; 
        
        if (spriteCounter > animationSpeed) {
            spriteNum++;
            spriteCounter = 0;
        }
    }

    /**
     * Dibuja al jugador en pantalla.
     */
    public void draw(Graphics2D g2) {
        ArrayList<BufferedImage> currentAnim = null;

        // Selección de la lista de animación según dirección y estado
        switch (direction) {
            case "up":
                currentAnim = moving ? walkUp : idleUp;
                break;
            case "down":
                currentAnim = moving ? walkDown : idleDown;
                break;
            case "left":
                currentAnim = moving ? walkLeft : idleLeft;
                break;
            case "right":
                currentAnim = moving ? walkRight : idleRight;
                break;
        }

        if (currentAnim != null && !currentAnim.isEmpty()) {
            // Aseguramos que spriteNum no se salga de los límites
            int frameIndex = spriteNum % currentAnim.size();
            BufferedImage image = currentAnim.get(frameIndex);
            
            // Dibujamos el sprite escalado
            // Nota: Como el frame es 64x128 (alto), ajustamos el dibujo
            int drawWidth = TILE_SIZE; 
            int drawHeight = TILE_SIZE * 2; // El doble de alto para mantener proporción
            
            g2.drawImage(image, (int) x, (int) y - TILE_SIZE, drawWidth, drawHeight, null);
        } else {
            // Rectángulo de respaldo si no hay imagen
            g2.setColor(Color.white);
            g2.fillRect((int) x, (int) y, TILE_SIZE, TILE_SIZE);
        }
    }
}
