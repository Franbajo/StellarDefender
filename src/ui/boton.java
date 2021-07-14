
package ui;

import Input.MouseInput;
import graphics.Assets;
import graphics.Images;
import graphics.Sound;
import graphics.Text;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import math.Vector2D;
import states.MenuState;
import states.Opciones;
import states.Ready;
import states.state;

public class boton {
    private BufferedImage mouseOutImg;
    private BufferedImage mouseInImg;
    private boolean mouseIn;
    private Rectangle boundingBox;//Para crear el botón
    private String texto;
    private Action action;
    private int posicion, run;
    private Sound menu=new Sound(Assets.start);
    private Sound menu2=new Sound(Assets.options);
    private int temaMusical;
    //private ArrayList <BufferedImage> letrastemas;
    private  ArrayList <Sound> tema1=new <Sound> ArrayList();;
    private boolean verificaEligeTema;
    private ArrayList <Images> titulosTemas= new <Images> ArrayList();
    
    public boton(BufferedImage mouseOutImg, BufferedImage mouseInImg, int x, int y, String texto, int posicion) {
        menu.changeVolume(-10);
        menu2.changeVolume(-7);
        this.mouseOutImg = mouseOutImg;
        this.mouseInImg = mouseInImg;
        this.texto = texto;
        this.posicion=posicion;
        boundingBox = new Rectangle(x, y, mouseInImg.getWidth(), mouseInImg.getHeight()); 
        
        this.action=action;
    }
    
    /*Báiscamente el segundo Constructor es para botones secundarios como el play en el menú de opciones*/
   public boton(BufferedImage mouseOutImg,BufferedImage mouseInImg, int x, int y, int posicion, boolean verifica) {
        this.mouseOutImg = mouseOutImg;
        this.mouseInImg = mouseInImg;
         boundingBox = new Rectangle(x, y, mouseInImg.getWidth(), mouseInImg.getHeight()); 
        this.posicion=posicion;
        verificaEligeTema=verifica;
        tema1.add( new Sound(Assets.musicaMenu));
        tema1.add(new Sound(Assets.backgroundMusic));
        tema1.add(new Sound(Assets.musicaJuego));
        run=0;
        titulosTemas.add(new Images(Assets.LetrasIntro, new Vector2D(490, 160)));
        titulosTemas.add(new Images(Assets.LetrasStage02, new Vector2D(490, 160)));
        titulosTemas.add(new Images(Assets.LetrasStage01, new Vector2D(490, 160)));
   }
 
    public void update(){
      
            if(boundingBox.contains(MouseInput.X, MouseInput.Y)){
                mouseIn=true;
                }else{
                mouseIn=false;
            }
           
            /*Para el siguiente if es importante saber que MouseInput es una clase creada
            para detectar los clics de ratón en nuestro juego y que MLB, es una variable
            de dicha clase que detecta si es verdadero o falso al hacer clic con el ratón*/
            if(mouseIn && MouseInput.MLB){
                
            /*  Aquí meto el método LAMBDA, que por cierto este apartado está estrechamente ligado
                al método MenuState, porque MenuState tiene internamente un arrayList de boton,
                Por tanto enviamos los datos a través de la posicion 0, para empezar el juego
                propiedad que tiene el botón 1 de la clase MenuState y posicion 1, que sería
                salir del juego que es la propiedad que tiene el botón 2.  
                */
  
            action=(a)->{
                  
                 switch(posicion){
                     case 0:
                             menu.play();
                                state.changeState(new Ready());
                             break;
                     case 1:
                         System.exit(0);
                     break;
                     case 2:
                         menu2.play();
                         state.changeState(new Opciones());
                         break;
                     case 3:
                         Assets.backgroundMusic.stop();
                         Assets.musicaJuego.stop();
                          menu2.play();
                         state.changeState(new MenuState());
                         break;
                     case 4:
                         
                         /*Este es el selector de música de nuestro juego, que sale dentro del menú Opciones*/
                            if(verificaEligeTema){
                                    
                                        //El switch está hecho para que no se solapen los temas.
                                     switch(eligeTema()){
                                            case 0:
                                                
                                                Assets.musicaJuego.stop();
                                            break;
                                            case 1:
                                                 Assets.musicaMenu.stop();
                                            break;
                                            case 2:
                                                Assets.backgroundMusic.stop();
                                            break;
                                     }
                                                tema1.get(eligeTema()).play();
                                                MouseInput.MLB=false;/*Con este MLB a false, lo que hacemos es evitar un
                                                                   pedazo de bug que era al pulsar sobre play
                                                                   reproducirse hasta el infinito la música... 
                                                                   Recordemos que MLB es una variable de la clase
                                                                   MouseInput que detecta verdadero o falso
                                                                   al clickear con el ratón.*/
                                                run++;
                         }
                               if(!verificaEligeTema){
                                        //El switch está hecho para que no se solapen los temas.
                                     switch(eligeTema()){
                                            case 0:
                                                Assets.musicaMenu.stop();
                                            break;
                                            case 1:
                                                Assets.musicaJuego.stop();
                                            break;
                                            case 2:
                                                 Assets.backgroundMusic.stop();
                                            break;
                                            
                                     }
                                     MouseInput.MLB=false;
                                     run--;
                         }
                         break;

                     default: break;
                 }
            };
                action.doAction(posicion);
         }
        
    }
    public void draw(Graphics g){

        if(mouseIn){
            g.drawImage(mouseInImg, boundingBox.x, boundingBox.y, null);
        }else{
            g.drawImage(mouseOutImg, boundingBox.x, boundingBox.y, null);
        }
        if(posicion!=4){
        Text.drawText(g, texto, new Vector2D(boundingBox.getX()+boundingBox.getWidth()/2,
                            boundingBox.getY()+boundingBox.getHeight()-8),true, Color.BLACK,
                            Assets.fontMed);
        }
        if(posicion==4){
            for (int i = 0; i < titulosTemas.size(); i++) {
                if(temaMusical==0){
                        titulosTemas.get(0).draw(g);
                }
                if(temaMusical==1){
                        titulosTemas.get(1).draw(g);
                }
                if(temaMusical==2){
                        titulosTemas.get(2).draw(g);
                }
            }
        }

    }
    public int eligeTema(){
        
        if (run!=0){
            temaMusical++;            
            if(temaMusical==3){
                    temaMusical=0;
            }
            return temaMusical;
        }else 
            return temaMusical;
    }
  
}
