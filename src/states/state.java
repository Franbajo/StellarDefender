
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
    
    public abstract void update();//método que nos va a valer para actualizar
    public abstract void draw(Graphics g);//método para insertar gráficos.
}
