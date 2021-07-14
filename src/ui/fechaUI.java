
package ui;

import gameObject.Constants;
import graphics.Assets;
import graphics.Text;
import java.awt.Color;
import java.awt.Graphics;
import java.time.LocalDate;
import java.time.LocalDateTime;
import math.Vector2D;

public class fechaUI {
      private String fechaEscritorio;

    public fechaUI(LocalDate d) {
        fechaEscritorio=d.toString();
     
    }
      
      
    public void drawFecha (Graphics g){
 
     Vector2D position = new Vector2D(Constants.WIDTH/2,660);
           
        Text.drawText(
            g,
            fechaEscritorio,
            position,
            true,
            Color.WHITE,
            Assets.fontMed);
    }
}
