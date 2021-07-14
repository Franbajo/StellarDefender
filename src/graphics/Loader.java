
package graphics;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Loader {
    public static BufferedImage ImgeLoader(String path){//Este primer método sirve para cargar una imagen al juego
        try {
           return ImageIO.read(Loader.class.getResource(path));//Esto nos indica la ruta donde está nuestra imagen
        } catch (IOException ex) {//El try Cath te lo sugiere NetBeans y es necesario ponerlo.
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public static Font loadFont(String path, int size){
        try {
            return Font.createFont(Font.PLAIN, Loader.class.getResourceAsStream(path)).deriveFont(Font.PLAIN, size);
        } catch (FontFormatException | IOException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
         return null;
    }
    public static Clip loadSound(String path) throws UnsupportedAudioFileException, LineUnavailableException, IOException{
        Clip clip =AudioSystem.getClip();
        clip.open(AudioSystem.getAudioInputStream(Loader.class.getResource(path)));
        return clip;
    }
    
}
