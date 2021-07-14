
package states;

import gameObject.Constants;
import gameObject.CronometroDisparo;
import gameObject.Message;
import graphics.Assets;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import math.Vector2D;
import ui.boton;

public class Ready3 extends Ready{
     private boton buttons;
    private CronometroDisparo tiempo;
   // private Message ready1;
    private ArrayList<Message>messages= new ArrayList<Message>();

    public Ready3() {
        tiempo=new CronometroDisparo();
        
         tiempo.run(Constants.READYGO);
        
        messages.add(new Message(new Vector2D(Constants.WIDTH/2, Constants.HEIGHT/3), 
        false, "CONGRATULATIONS!!!", Color.WHITE, true, Assets.fontBig));
       //BOTÓN -OPTIONS-
        buttons=new boton(Assets.boton2, Assets.boton1, (int) 500,
        500, Constants.RETURN,3);
    }
        @Override
    public void update() {
       tiempo.update();
        /*........CAMBIO DE ESTADO.......*/
        if(!tiempo.isRunning()){
                buttons.update();
        }
        /*...............................*/
    }
        @Override
    public void draw(Graphics g) {
         Graphics2D  g2d=(Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        //Este TRY CATCH lo he tenido que poner por Narices porque sino no funciona el botoón ...Muy loco
        try{
            for (int i = 0; i < messages.size(); i++) {
                messages.get(i).draw2(g2d);
                }
        }catch(IllegalArgumentException e){}
        //Y dibujo el botón
        if(!tiempo.isRunning()){
                buttons.draw(g);
        }
    }
    
}
