package model.Entity;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Clase base para todos los objetos móviles o estáticos del juego
 * Contiene propiedades comunes como posición, velocidad y gestión de sprites
 */
public class Entity {
    // Posición X en el mundo (utiliza double para suavizar el movimiento)
    public double x;
    // Posición Y en el mundo
    public double y;
    // Velocidad base de la entidad
    public int speed;

    // --- Gestión de Sprites ---
    // Dirección actual para seleccionar el sprite adecuado
    public String direction = "down";

    // Indica si la entidad está realizando una acción de movimiento
    public boolean moving = false;

    // Listas de imágenes para animaciones (Idle, Walk, Run)
    public ArrayList<BufferedImage> idleUp, idleDown, idleLeft, idleRight;
    public ArrayList<BufferedImage> walkUp, walkDown, walkLeft, walkRight;
    public ArrayList<BufferedImage> runUp, runDown, runLeft, runRight;

    // Control de animación
    public int spriteCounter = 0;
    public int spriteNum = 1;

}
