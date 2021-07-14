
package states;

import gameObject.CronometroDisparo;
import gameObject.Meteorito_Fondo;
import gameObject.Star_move;
import graphics.Assets;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import math.Vector2D;

public class Moving_Meteor_Background extends state{
    //private ArrayList<Meteorito_Fondo>meteorList=new ArrayList<Meteorito_Fondo>();
    private Meteorito_Fondo meteor;
    //GameOver

    public Moving_Meteor_Background() {
        startMeteor();

    }
    
    private void startMeteor(){
        double x,y;//Coordenadas para usar en la posición de las estrellas
        x=100;
        y=-290;
        BufferedImage textureStar=Assets.meteoritoGigante;
        double vel;
        vel=0.13;
           /* for (int i = 0; i < meteorList.size(); i++) {
                meteorList.add(meteor=new Meteorito_Fondo(new Vector2D(x, y), textureStar, this, vel));
            }*/
            meteor=new Meteorito_Fondo(new Vector2D(x, y), textureStar, this, vel);
            
    }
    
    @Override
    public void update() {
        /*for (int i = 0; i < meteorList.size(); i++) {
            meteorList.get(i).update();
        }*/
        meteor.update();
        
        //startMeteor();

    }

    @Override
    public void draw(Graphics g) {
        //Graphics2D  g2d=(Graphics2D)g;
      /* for (int i = 0; i < meteorList.size(); i++) {
            meteorList.get(i).draw(g2d);     
        }   */
    
       meteor.draw(g);
    }

    public Meteorito_Fondo getMovimiento() {
        return meteor;
        
    }
    
}
