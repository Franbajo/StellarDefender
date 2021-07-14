
package gameObject;

import graphics.Assets;
import graphics.Sound;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.sound.sampled.Clip;
import math.Vector2D;
import states.GameState;


public class Meteor extends GameObject{
    //private SizeMeteoroide size;
    private double maxVel;
    private Vector2D gra=new Vector2D();

    public Meteor(Vector2D position, BufferedImage texture, GameState gamestate, /*SizeMeteoroide size,*/ double maxVel) {
        super(position, texture, gamestate);
       // this.size=size;
        this.maxVel=maxVel;
       
    }
    @Override
    public void update() {
        gra.setY(maxVel);
        
        position=position.add(gra);
        if(position.getY()>650){
           
            gamestate.getMovingObjects().remove(this);
            
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(texture, (int)position.getX(), (int)position.getY(), null);
    }

    /*public SizeMeteoroide getSize() {
        return size;
    }*/
    @Override
    public void Destroy(){
        gamestate.addScore(Constants.MeteorScore, position);
        super.Destroy();
        
    }

}
