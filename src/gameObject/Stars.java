
package gameObject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import math.Vector2D;
import states.GameState;

public class Stars extends GameObject{
private Vector2D gra=new Vector2D();
private double maxVel;

    public Stars(Vector2D position, BufferedImage texture, GameState gamestate, double maxVel) {
        super(position, texture, gamestate);
        this.maxVel=maxVel;
    }

    @Override
    public void update() {
       
         gra.setY(maxVel);
        gra.setY(maxVel);
        position=position.add(gra);
         super.setPosition(position);
       /* if(position.getY()>Constants.HEIGHT*2){
           gamestate.getMovingStars().remove(this);
        }*/

    }

    @Override
    public void draw(Graphics g) {
       g.drawImage(texture, (int)position.getX(), (int)position.getY(), null);
    }

}
