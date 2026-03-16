package listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Escuchador de teclado que gestiona los controles de movimiento.
 * Utiliza AtomicBoolean para garantizar que el estado de las teclas sea seguro
 * para el acceso multihilo entre el hilo de eventos de Swing y el Game Loop.
 */
public class handListener implements KeyListener {

    // Estado de las teclas (true si están pulsadas)
    private AtomicBoolean upPressed = new AtomicBoolean();
    private AtomicBoolean downPressed = new AtomicBoolean();
    private AtomicBoolean leftPressed = new AtomicBoolean();
    private AtomicBoolean rightPressed = new AtomicBoolean();

    public handListener() {

    }

    /**
     * Invocado cuando se escribe una tecla (pulsar y soltar).
     * No se utiliza en este gestor de movimiento.
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Invocado cuando se presiona una tecla.
     * Actualiza el estado a 'true'.
     */
    @Override
    public void keyPressed(KeyEvent e) {

        int keyInput = e.getKeyCode();

        // Mapeo de teclas W, S, A, D para el movimiento
        if (keyInput == KeyEvent.VK_W) {
            upPressed.set(true);

        }

        if (keyInput == KeyEvent.VK_S) {
            downPressed.set(true);

        }

        if (keyInput == KeyEvent.VK_A) {
            leftPressed.set(true);

        }

        if (keyInput == KeyEvent.VK_D) {
            rightPressed.set(true);

        }


    }

    /**
     * Invocado cuando se suelta una tecla.
     * Restablece el estado a 'false'.
     */
    @Override
    public void keyReleased(KeyEvent e) {

        int keyInput = e.getKeyCode();


        if (keyInput == KeyEvent.VK_W) {
            upPressed.set(false);

        }

        if (keyInput == KeyEvent.VK_S) {
            downPressed.set(false);

        }

        if (keyInput == KeyEvent.VK_A) {
            leftPressed.set(false);

        }

        if (keyInput == KeyEvent.VK_D) {
            rightPressed.set(false);

        }

    }

    // Métodos de consulta de estado para la lógica de actualización (Update)

    public boolean isUpPressed() {
        return upPressed.get();
    }

    public boolean isDownPressed() {
        return downPressed.get();
    }

    public boolean isLeftPressed() {
        return leftPressed.get();
    }

    public boolean isRightPressed() {
        return rightPressed.get();
    }
}
