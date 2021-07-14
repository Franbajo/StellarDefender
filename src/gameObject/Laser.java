
package gameObject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import math.Vector2D;
import states.GameState;

public class Laser extends GameObject{
    //mio--------------
    private final double maxVel=-10;
    private Vector2D gra=new Vector2D();
    //---------------
    public Laser(Vector2D position, BufferedImage texture, GameState gamestate) {
        super(position, texture, gamestate);
        
    }

    @Override
    public void update() {
        
        
        gra.setY(maxVel);
        position=position.add(gra);
        if(position.getY()<-50){//D�nde desaparece el Laser
           Destroy();
        }
        collidesWith();
     // collidesWithLaberynth();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(texture, (int)position.getX()-5, (int)position.getY()+40, null);//Posici�n de salida del Laser
    }
    
    /*El m�todo getCenter lo usamos para detectar el centro de colisi�n de un objeto
    y as� calcular al MAGNITUD entre dos objetos diferentes*/
    @Override
    protected Vector2D getCenter(){
        return new Vector2D(position.getX()+width/2, position.getY() + width/2);
    }
}
