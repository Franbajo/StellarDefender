
package gameObject;

import graphics.Assets;
import java.awt.Color;
import java.awt.Graphics;
import math.Vector2D;

public class CronometroDisparo {
    private long delta, lastTime, time;
    private boolean running;
    
    public CronometroDisparo(){
        delta=0;
        lastTime=System.currentTimeMillis();
        running=false;
    }
    
    public void run(long time){
        running=true;
        this.time=time;
    }

    public void update(){
        if(running)
            delta+=System.currentTimeMillis()-lastTime;
        if(delta>=time){
            running =false;
            delta=0;
        }
        
        lastTime=System.currentTimeMillis();
    }
    public boolean isRunning(){
        return running;
    }

    public void setDelta(long delta) {
       
        this.delta = delta;
    }

    public long getDelta() {
        update();
        long resultado=time-delta;
        if(resultado==time)resultado=0;
        return resultado;
        
    }

    public long getLastTime() {
        return lastTime;
    }

    public long getTime() {
        return time;
    }
    public Message ultimaOleada(){
          Message ultima=new Message (new Vector2D(Constants.WIDTH/2, Constants.HEIGHT/2), true, "LAST COMBAT!!!", Color.WHITE, true, Assets.fontBig);
     return ultima;
    }

}
