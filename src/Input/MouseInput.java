
package Input;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;

public class MouseInput implements MouseInputListener{

    public static int X, Y;
    public static boolean MLB;
     private boolean click;
    
    @Override
    public void mouseClicked(MouseEvent me) {
                if(!click){
            click=true;
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if(me.getButton()==MouseEvent.BUTTON1){
            MLB=true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        if(me.getButton()==MouseEvent.BUTTON1){
            MLB=false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        X=me.getX();
        Y=me.getY();
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        X=me.getX();
        Y=me.getY();
    }
        //Métodos para detectar clics de ráton

    public boolean obtenerClic(){
        return click;
    }
    public void reiniciarClic(){
        if(click){
            click=false;
        }
    }
}
