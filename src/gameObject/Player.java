
package gameObject;

import Input.Keyboard;
import static gameObject.Constants.fireRate;
import graphics.Assets;
import graphics.Sound;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import math.Vector2D;
import states.GameState;

public class Player extends GameObject{
private CronometroDisparo fireRate;

//Variables para el parpadeo del jugador al resucitar:
private boolean spawning, visible;
private CronometroDisparo spawnTime, flickerTime;
private Sound shoot, playerLoose;
private Vector2D velocity;
private boolean izquierda;

    //private long time, lasTime;
    public Player(Vector2D position, BufferedImage texture, GameState gamestate) {
        super(position, texture, gamestate);
        this.gamestate= gamestate;
        fireRate=new CronometroDisparo();
        spawnTime=new CronometroDisparo();
        flickerTime=new CronometroDisparo();
       // time=0;
       // lasTime=System.currentTimeMillis();
       shoot=new Sound(Assets.playerShoot);
       playerLoose=new Sound(Assets.playerLoose);
       izquierda=false;
    }

    
    @Override
    public void update() {
        //El primer y segundo if son para comprobar si el jugador está parpadeando
        if(!spawnTime.isRunning()){
            spawning=false;
            visible=true;
        }
        if(spawning){
            if(!flickerTime.isRunning()){
                flickerTime.run(Constants.FLICKER_TIME);
                visible= !visible;
            }
        }
        
        //Esto es para situar el disparo del jugador//
        if(Keyboard.SHOOT && !fireRate.isRunning()){//fireRate es una variable estática de la clase Constantes
            gamestate.getMovingObjects().add(0, new Laser(this.salidaDisparo(), Assets.redLaser, gamestate));
            fireRate.run(Constants.fireRate);
        //El sonido del disparo//
        shoot.changeVolume(-10);
            shoot.play();
        }
        //Esto es para recortar el sonido del disparo y vayan con el timing del jugador...Por cierto
        //8500 milisegundos.
        if(shoot.getFramePosition()>4000){
            shoot.stop();
        }
        
        //ESTO SON LOS CONTROLES DEL PLAYER//
            //-----------------------------------//
       if(Keyboard.RIGHT&& position.getX()<=1163) position.setX(position.getX()+4);
       if(Keyboard.LEFT && position.getX()>=-17) position.setX(position.getX()-4); 
       if(Keyboard.UP && position.getY()>=-9) position.setY(position.getY()-4);
       if(Keyboard.DOWN && position.getY()<=637) position.setY(position.getY()+4);
         //-----------------------------------//
         fireRate.update();
         spawnTime.update();
         flickerTime.update();
         collidesWith();
   
         
    }
    @Override
    public void Destroy (){
        
        spawning=true;
        spawnTime.run(Constants.SPAWNING_TIME);
        playerLoose.play();
        if(!gamestate.subtractLife()){
            
            gamestate.gameOverMetodo();
            super.Destroy();
        }
        resetValues();
    }
    public void resetValues(){
    
    velocity= new Vector2D();
    //position=new Vector2D(100, 500);
    }
    public boolean isSpawning() {
        return spawning;
    }
    
    
    
    @Override
    public void draw(Graphics g) {
        if(!visible)return;
        /*.....PARA CAMBIAR EL GRÁFICO DEL PERSONAJE AL MOVERSE DE IZQUIERDA A DERECHA.....*/
        if(!Keyboard.LEFT&&!Keyboard.RIGHT)
        g.drawImage(texture, (int)position.getX(), (int)position.getY(), null);
        if(Keyboard.LEFT)g.drawImage(Assets.player2, (int)position.getX(), (int)position.getY(), null);
        if(Keyboard.RIGHT)g.drawImage(Assets.player3, (int)position.getX(), (int)position.getY(), null);
        /*.................................................................................*/
    }

    
    //ESTE MÉTODO ES PARA CALCULAR LA SALIDA DEL DISPARO
    public Vector2D salidaDisparo(){
        return new Vector2D(position.getX()+width/2, position.getY() - (height+13));
    }
    //ESTE MÉTODO ES PARA CALCULAR LA SALIDA DEL DISPARO
}
