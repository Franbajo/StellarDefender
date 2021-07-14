
package graphics;

import java.awt.image.BufferedImage;
import math.Vector2D;

public class Animation {
    private BufferedImage[] frames;
    private int velocity;
    private int index;//Indice del fotograma actual en el que está corriendo la animación
    private boolean running;//Nos va a decir si la animación esta corriendo o no.  
    private Vector2D position;
    private long time, lastTime;

    /*en el constructor de Animation uso un método Varag*/
    public Animation(int velocity, Vector2D position,BufferedImage... frames) {
        this.frames = frames;
        this.velocity = velocity;
        this.position = position;
        index=0;
        running=true;
        time=0;
        lastTime=System.currentTimeMillis();
    }
    public void update(){
        time+=System.currentTimeMillis()-lastTime;
        lastTime=System.currentTimeMillis();
        
        if(time>velocity){
            time=0;
            index++;
            if(index>=frames.length){
                running=false;
            }
        }
    }

    public boolean isRunning() {
        return running;
    }

    public Vector2D getPosition() {
        return position;
    }
    
    public BufferedImage getCurrentFrame(){
        return frames[index];
    }

}
