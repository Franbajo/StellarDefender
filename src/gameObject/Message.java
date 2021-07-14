
package gameObject;

import graphics.Text;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import math.Vector2D;
import states.GameState;

public class Message {
   
    private float alpha;
    private Vector2D position;
    private Color color;
    private boolean center;
    private boolean fade;
    private Font font;
    private final float deltaAlpha=0.01f;
    private String text;
    private boolean muerte;

    public Message(Vector2D position, boolean fade, String text, Color color,
			boolean center, Font font) {
        
        this.position = position;
        this.color = color;
        this.center = center;
        this.fade = fade;
        this.font = font;
        this.text=text;
        this.muerte=false;
        if(fade)alpha=1;
        else alpha=0;
    }
    /*PARA CREAR TEXTOS MOVIBLES y CON TRANSPARENCIA*/
    public void draw(Graphics2D g2d){
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        Text.drawText(g2d, text, position, center, color, font);
         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
         position.setY(position.getY()-1);
         if(fade)alpha-=deltaAlpha;
         else alpha+=deltaAlpha;
		
		if(fade && alpha < 0) {
			muerte = true;
		}
		
		if(!fade && alpha > 1) {
			fade = true;
			alpha = 1;
		}
         
    }
    /*PARA CREAR TEXTOS QUIETOS*/
        public void draw2(Graphics2D g2d){
        
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        Text.drawText(g2d, text, position, center, color, font);
         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
         
         if(fade)alpha-=deltaAlpha;
         else alpha+=deltaAlpha;
		
		if(fade && alpha < 0) {
			muerte = true;
		}
		
		if(!fade && alpha > 1) {
			fade = true;
			alpha = 1;
		}
        
    }
    
    public boolean finTexto() {return muerte;}
    
    
}
