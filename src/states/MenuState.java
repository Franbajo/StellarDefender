
package states;

import gameObject.Constants;
import gameObject.CronometroDisparo;
import gameObject.GameObject;
import graphics.Assets;
import graphics.Images;
import graphics.Sound;
import java.awt.Graphics;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import java.util.ArrayList;
import math.Vector2D;
//import ui.Action;
import ui.boton;
import ui.fechaUI;


public class MenuState extends state{

    private ArrayList<boton>buttons;
    
        //Fecha
    private LocalDate fecha;
    private DateTimeFormatter nuevoFormato;
    private fechaUI fech;
    private Images logo;
    //Musica
    private Sound hola;
    
   //Tiempo para salir los botones... Me evito un bug si pongo esto.
    private CronometroDisparo tiempo;
    
    /*...............CONSTRUCTOR.................*/
    public MenuState (){
        //FECHA
        fecha=LocalDate.now();
        nuevoFormato= DateTimeFormatter.ofPattern("dd/MM/yyyy");
        fecha.format(nuevoFormato);
        fech= new fechaUI(fecha);
        buttons=new ArrayList<boton>();
        //PRIMER BOTÓN -PLAY-
        buttons.add(new boton(Assets.boton2, Assets.boton1,
        Constants.WIDTH/3-Assets.boton2.getWidth()/2,
        500,
        Constants.PLAY,0));
        //SEGUNDO BOTÓN -EXIT-
        buttons.add(new boton(Assets.boton2, Assets.boton1,
        515,
        550,
        Constants.EXIT,1));
        //TERCER BOTÓN -OPTIONS-
        buttons.add(new boton(Assets.boton2, Assets.boton1, (int) (Constants.WIDTH/1.3-Assets.boton2.getWidth()),
        500,
        Constants.OPTIONS,2));
        //PARA LA MUSICA DEL MENU
         hola=new Sound(Assets.musicaMenu);
         hola.play();
         //PARA EL LOGO DEL JUEGO
         logo=new Images(Assets.logo, new Vector2D(90, 10));
         //TIEMPO PARA LOS BOTONES
        tiempo=new CronometroDisparo();
         tiempo.run(Constants.PUSHBOTTON);
    }

    /*.....................................*/
    
    @Override
    public void update() {
               tiempo.update();
        /*........CAMBIO DE ESTADO.......*/
        if(!tiempo.isRunning()){
            for (boton button : buttons) {
                button.update();
            } 
        }

        /*Aquí le digo que si detecta un cambio de estado con respecto
           al estado actual que pare la música.*/
        if(state.getCurrentState()!=this){
        hola.stop();
        }
        
    }

    @Override
    public void draw(Graphics g) {
        logo.draw(g);
         if(!tiempo.isRunning()){
            for (boton button : buttons) {
                button.draw(g);

            } 
         }
       fech.drawFecha(g);
    }
    
}
