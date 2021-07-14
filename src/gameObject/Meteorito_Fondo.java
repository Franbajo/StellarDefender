
package gameObject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import math.Vector2D;
import states.MovingBackgrounds;
import states.Moving_Meteor_Background;


public class Meteorito_Fondo extends GameObject{
    private double maxVel;
    private Vector2D gra=new Vector2D();
   private Moving_Meteor_Background mov;

    public Meteorito_Fondo(Vector2D position, BufferedImage texture, Moving_Meteor_Background back, double maxVel) {
        super(position, texture, back);
       this.maxVel = maxVel;
        this.mov=back;
    }

    @Override
    public void update() {
               gra.setY(maxVel);
    
        position=position.add(gra);
         super.setPosition(position);
                 if(position.getY()>1000){

            mov.getMovimiento().Destroy();
           
        }
    }
    @Override
    public void draw(Graphics g) {
       g.drawImage(texture, (int)position.getX(), (int)position.getY(), null);
    }
    
}
