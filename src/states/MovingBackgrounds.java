
package states;

import gameObject.CronometroDisparo;
import gameObject.Star_move;
import gameObject.Stars;
import graphics.Assets;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import math.Vector2D;

public class MovingBackgrounds extends state{
    private ArrayList<Star_move>stars=new ArrayList<Star_move>();
    private Star_move star2;
    //Para ver el número de estrellas en pantalla
    private int starNumero;

    public MovingBackgrounds() {
       
        startStars();

    }
    
        private void startStars(){
        double x,y;//Coordenadas para usar en la posición de las estrellas
        x=Math.random()*(0-1200)+1200;
        y=Math.random()*(-50-(-400))+-(400);
        BufferedImage textureStar=Assets.estrellitas;
        double vel;
        //starNumero=(int)(Math.random()*(1-5)+5);
        starNumero=1;
        vel=Math.random()*(1-5)+5;
        for (int i = 0; i < starNumero; i++) {
               stars.add(star2=new Star_move(new Vector2D(x, y), textureStar, this, vel));
        }
 
        
    }
    
    @Override
    public void update() {
                    /*.........Estrellas movimiento...........*/
       for (int i = 0; i < stars.size(); i++) {
            stars.get(i).update();
        }
    
        /*...........................................*/
        startStars();
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D  g2d=(Graphics2D)g;
               /*.......Para dibujar las estrellas......*/
        for (int i = 0; i < stars.size(); i++) {
            stars.get(i).draw(g2d);
             
        }
        /*......................................*/ 
    }
    public ArrayList<Star_move> getMovingStars() {
        return stars;
        
    }
}
