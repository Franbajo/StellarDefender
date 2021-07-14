
package graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import math.Vector2D;


public class Images {
       protected BufferedImage texture;
      protected Vector2D position;

    public Images(BufferedImage texture, Vector2D position) {
        this.texture = texture;
        this.position = position;
    }
    public void draw(Graphics g) {
        g.drawImage(texture, (int)position.getX(), (int)position.getY(), null);
    } 
}
