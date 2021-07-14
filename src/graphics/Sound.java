
package graphics;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
    private Clip clip;
    private FloatControl volume;
    public Sound(Clip clip) {
        this.clip = clip;
        volume=(FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
        
    }
    
    
    public void loop(){
        clip.setFramePosition(0);//Posici�n del sonido loopleable al inicio
        clip.loop(Clip.LOOP_CONTINUOUSLY);//Para crear el Loop
    }
    public void play(){
        clip.setFramePosition(0);
        clip.start();
    }
    public void stop(){
    clip.stop();
    }

    
    public int getFramePosition(){
        return clip.getFramePosition();
    }
    public void changeVolume(float value){
        volume.setValue(value);
    }
}
