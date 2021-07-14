
package states;

import java.awt.Graphics;

public abstract class state {
    private static state currentState= null;

    public static state getCurrentState() {
        return currentState;
    }
    
    public static void changeState(state nuevoEstado){
        currentState=nuevoEstado;
    }
    
    public abstract void update();//m�todo que nos va a valer para actualizar
    public abstract void draw(Graphics g);//m�todo para insertar gr�ficos.
}
