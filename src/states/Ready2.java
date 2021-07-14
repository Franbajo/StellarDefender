
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


public class Ready2 extends Ready{
        private CronometroDisparo tiempo;
   // private Message ready1;
    private ArrayList<Message>messages= new ArrayList<Message>();
    
    public Ready2() {
        tiempo=new CronometroDisparo();
        
         tiempo.run(Constants.READYGO);
        
        messages.add(new Message(new Vector2D(Constants.WIDTH/2, Constants.HEIGHT/3), 
        false, "STAGE 2", Color.WHITE, true, Assets.fontBig));
    }
    
    
    @Override
    public void update() {
       tiempo.update();
        /*........CAMBIO DE ESTADO.......*/
        if(!tiempo.isRunning()){
        state.changeState(new GameState2());
        }
        /*...............................*/
    }

    @Override
    public void draw(Graphics g) {
         Graphics2D  g2d=(Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        for (int i = 0; i < messages.size(); i++) {
            messages.get(i).draw2(g2d);
        }
        
    }
}
