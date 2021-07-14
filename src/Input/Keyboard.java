
package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{
    private boolean[]keys= new boolean[256];
    public static boolean UP, LEFT, RIGHT, DOWN, SHOOT;
   
    public Keyboard (){
        UP=false;
        DOWN=false;
        LEFT=false;
        RIGHT=false; 
        SHOOT=false;
    }
    public void update(){
        UP=keys[KeyEvent.VK_UP];
        DOWN=keys[KeyEvent.VK_DOWN];
        LEFT=keys[KeyEvent.VK_LEFT];
        RIGHT=keys[KeyEvent.VK_RIGHT];
        SHOOT=keys[KeyEvent.VK_D];
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()]=true;
            }

    @Override
    public void keyReleased(KeyEvent ke) {
        keys[ke.getKeyCode()]=false;
           }
    
    @Override
    public void keyTyped(KeyEvent ke) {}
}

