package listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.atomic.AtomicBoolean;

public class handListener implements KeyListener {

    private AtomicBoolean upPressed = new AtomicBoolean();
    private AtomicBoolean downPressed = new AtomicBoolean();
    private AtomicBoolean leftPressed = new AtomicBoolean();
    private AtomicBoolean rightPressed = new AtomicBoolean();

    public handListener() {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int keyInput = e.getKeyCode();

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
