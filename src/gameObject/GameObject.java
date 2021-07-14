
package gameObject;

import graphics.Animation;
import graphics.Assets;
import static graphics.Assets.choqueLaserSound;
import graphics.Sound;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import math.Vector2D;
import states.GameState;

import states.MovingBackgrounds;
import states.Moving_Meteor_Background;

public abstract class GameObject {
    protected BufferedImage texture;
    protected Animation texture2;
    protected Vector2D position;
    protected GameState gamestate;
 
    protected MovingBackgrounds mov;
    protected Moving_Meteor_Background back;
    protected int width, height;
    protected Sound explosion, sonidoSangre, sonidoGrito, choqueLaserSound;
   
    
    public GameObject(Vector2D position, BufferedImage texture, GameState gamestate) {
        this.texture = texture;
        this.position = position;
        width=texture.getWidth();
        height=texture.getHeight();
        this.gamestate=gamestate;
        explosion=new Sound(Assets.explosionSonido);
        sonidoSangre=new Sound(Assets.Sonido_Sangre);
        sonidoGrito=new Sound(Assets.Sonido_Grito);
        choqueLaserSound=new Sound(Assets.choqueLaserSound);
    }

    public GameObject() {
    }
    
    /*...........CONSTRUCTOR PARA FONDOS.........*/
    public GameObject(Vector2D position, BufferedImage texture, MovingBackgrounds gamestate) {
        this.texture = texture;
        this.position = position;

        this.mov=gamestate;

    }
    //HECHO este constructor para el meteorito de fondo
    public GameObject(Vector2D position, BufferedImage texture, Moving_Meteor_Background back) {
        this.texture = texture;
        this.position = position;
        this.back=back;
    }
    /*....................................*/
    /*El método GETCENTER lo usamos para detectar el centro de colisión de un objeto
            y así calcular al MAGNITUD entre dos objetos diferentes*/
    protected Vector2D getCenter(){
        return new Vector2D(position.getX()+width/2, position.getY() + height/2);
    }
    /*....................................*/
    protected void collidesWith(){
        ArrayList<GameObject>movingObjects=gamestate.getMovingObjects();
        for (int i = 0; i < movingObjects.size(); i++) {
            GameObject m= movingObjects.get(i);
            if(m.equals(this))continue;//Continue significa saltar la siguiente iteración.
        double distance=m.getCenter().subtract(getCenter()).getMagnitude();  
        if(distance<(m.width/2)+ (width/2)&&movingObjects.contains(this)){
            objectCollision(m, this);
        }
        }
    }    
    private void objectCollision(GameObject a, GameObject b){
        if(a instanceof Player && ((Player)a).isSpawning()){
            return;
        }
        if(b instanceof Player && ((Player)b).isSpawning()){
            return;
        }
        /*Cuando hago referencia a playExplosion hacemos referencia a la Animación de explosión*/
        if(!(a instanceof Meteor && b instanceof Meteor)){
            if(a instanceof Meteor || b instanceof Meteor){
                
            gamestate.playExplosion(getCenter());}
            if(a instanceof OJO || b instanceof OJO){
                sonidoSangre.changeVolume(-6);
                sonidoSangre.play();
            gamestate.playExplosionSangre(getCenter());
            
            }
            if(a instanceof Laberynth || b instanceof Laberynth){
                gamestate.playExplosion(getCenter());
            }
            if(a instanceof Laser && b instanceof Laser){
                gamestate.addScore(Constants.ChoqueLaser_SCORE, position);
                choqueLaserSound.changeVolume(-7);
                choqueLaserSound.play();
                gamestate.choqueLaseres(getCenter());
            }
            if(a instanceof Player || b instanceof Player){
            gamestate.playExplosion(getCenter());}
            a.Destroy();
            b.Destroy();
        }

    }
    protected void Destroy(){
       
        gamestate.getMovingObjects().remove(this);

            //PARA EL SONIDO DE EXPLOSIÓN CONTRA LOS OBJETOS//
            explosion.changeVolume(-5);
            
        if(!(this instanceof Laser))explosion.play();
        if((this instanceof OJO)){
            sonidoGrito.changeVolume(-3);
            sonidoGrito.play();
        }
        //y el if que nos permite recortar el sonido si es muy largo
       if(explosion.getFramePosition()>8000){
            explosion.stop();
        }
    }
 
    
    public abstract void update();
    public abstract void draw(Graphics g);

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }
   
    
    
}
