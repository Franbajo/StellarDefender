
package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import math.Vector2D;

public class Text {
        public static void drawText(Graphics g, String texto, Vector2D pos, boolean center, Color color, Font font){
        g.setColor(color);
        g.setFont(font);
        Vector2D position=new Vector2D(pos.getX(), pos.getY());
        if(center){
            FontMetrics fm=g.getFontMetrics();
            position.setX(position.getX()-fm.stringWidth(texto)/2);//Estp es para centrar el texto en la coordenada en X
            position.setY(position.getY()-fm.getHeight()/2);
        }
        g.drawString(texto, (int)position.getX(), (int)position.getY());
    }
}
