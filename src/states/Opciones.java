
package states;

import gameObject.Constants;
import gameObject.GameObject;
import gameObject.Message;

import graphics.Assets;
import graphics.Images;
import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import math.Vector2D;

import ui.boton;

public class Opciones extends state{
    private boton returnButton;
    private boton play;
    private boton stop;
    private Images musicTest;
    public Opciones () {
        returnButton=new boton(Assets.boton2, Assets.boton1, Constants.WIDTH/2-Assets.boton2.getWidth()/2,
                                Constants.HEIGHT/2-Assets.boton2.getHeight()*(-5),Constants.RETURN, 3);
        play=new boton(Assets.botonPlay2, Assets.botonPlay1, 490, 160, 4, true);
        stop=new boton(Assets.botonPasar2, Assets.botonPasar1, 420, 150, 4, false);
        musicTest=new Images(Assets.MusicTest, new Vector2D(470,100));
    }
    
    @Override
    public void update() {

            returnButton.update();//llamamos al método update de la clase botón.
            stop.update();
            play.update();
    }

    @Override
    public void draw(Graphics g) {
           
        stop.draw(g);
        play.draw(g);
        returnButton.draw(g);
        musicTest.draw(g);
    

    }

    
}
