
package gameObject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import math.Vector2D;
import states.GameState;
import states.MovingBackgrounds;

public class Star_move extends GameObject{
    private double maxVel;
    private Vector2D gra=new Vector2D();
   private MovingBackgrounds mov;

    public Star_move(Vector2D position, BufferedImage texture, MovingBackgrounds hola, double maxVel) {
        super(position, texture, hola);
        this.maxVel = maxVel;
        this.mov=hola;
    }

    @Override
    public void update() {
        gra.setY(maxVel);
    
        position=position.add(gra);
         super.setPosition(position);
                 if(position.getY()>700){

            mov.getMovingStars().remove(this);
           
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(texture, (int)position.getX(), (int)position.getY(), null);
    }
    
    
}
