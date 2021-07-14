
package gameObject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import math.Vector2D;
import states.GameState;


public class Laberynth extends GameObject {
    private double maxVel;
    private Vector2D gra=new Vector2D();
    private GameState la;


    public Laberynth(Vector2D position, BufferedImage texture, GameState gamestate, double maxVel) {
        super(position, texture, gamestate);
          this.maxVel=maxVel;
          this.la=gamestate;
    }
    

    @Override
    public void update() {
        gra.setY(maxVel);
        
        position=position.add(gra);
        if(position.getY()>1500){

            la.getMovingObjects().remove(this);
        }
  
    }

    @Override
    public void draw(Graphics g) {
       g.drawImage(texture, (int)position.getX(), (int)position.getY(), null);
    }
          @Override
    public void Destroy(){
        gamestate.addScore(Constants.TrashScore, position);
        super.Destroy();
        
    }

    
    
    
  
}
