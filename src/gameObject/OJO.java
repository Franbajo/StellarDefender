
package gameObject;

import graphics.Assets;
import graphics.Sound;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import math.Vector2D;
import states.GameState;

public class OJO extends GameObject {
    
    private ArrayList<Vector2D>nodos;
    private Vector2D NodoActual, velocity;
    private int index;
    private boolean following;
    private double maxVel;
    private CronometroDisparo disparo;
    private Sound shoot;
    private long timeFrameAqui, lastTime;
    private int velocidad;
    
    public OJO(Vector2D position, BufferedImage texture, GameState gamestate, double maxVel, ArrayList<Vector2D>nodos,
            Vector2D velocity) {
        super(position, texture, gamestate);
        this.nodos=nodos;
        index=0;
        following=true;  
        this.maxVel=maxVel;
        this.velocity=velocity;
        disparo=new CronometroDisparo();
        //disparo.run(Constants.OJO_FIRE_RATE);
        shoot=new Sound(Assets.playerShoot);
        timeFrameAqui=0;
        lastTime=System.currentTimeMillis();
        velocidad=250;
    }

    private Vector2D seekForce(Vector2D target){
        Vector2D desiredVelocity = target.subtract(getCenter());
        //hacemos que la magnitud sea 1
       // desiredVelocity=desiredVelocity.normalize().scale(maxVel);
        return desiredVelocity.subtract(velocity);
    }
    
    private Vector2D pathFollowing(){
        NodoActual= nodos.get(index);
        double distanceToNode = NodoActual.subtract(getCenter()).getMagnitude();
        //El if sirve para cambiar de un nodo a otro
        if(distanceToNode < Constants.NODO_RADIO){
            index++;
            //Y este otro indica el final del recorrido
            if(index>= nodos.size()){
                following=false;
            }
        }
        return seekForce(NodoActual);
    }
    
    @Override
    public void update() {
        /*.......MOVIMIENTO EN IDLE.........*/
        //Para complementar este método véase el draw.
        timeFrameAqui+=System.currentTimeMillis()-lastTime;
        lastTime=System.currentTimeMillis();
        if(timeFrameAqui>velocidad){
            timeFrameAqui=0; 
            }
        /*..................................*/
        Vector2D pathFollowing;
	//En el siguiente if preguntamos si estamos o no siguiendo el camino.
		if(following){
			pathFollowing = pathFollowing();
                } else{
        //Sino sigue ningún NODO es igual a 0 o nuevo Vector
			pathFollowing = new Vector2D();
                    }
        //Ahora vamos a seguir con la aceleración.  
        pathFollowing=pathFollowing.scale(1/Constants.OJO_MASA);
        
        velocity=velocity.add(pathFollowing);
        /*limitamos la velocidad*/
        velocity=velocity.limit(maxVel);
        position=position.add(velocity);

        //Y ahora destruimos el OJO cuando acabe su recorrido
        if( position.getY() > Constants.HEIGHT)
            Destroy();
        
        //Cantidad de veces que dispara
        if(!disparo.isRunning()){
           gamestate.getMovingObjects().add(0, new Laser_Enemies(this.salidaDisparo(), Assets.ojoDisparo, gamestate));
           disparo.run(Constants.OJO_FIRE_RATE);
            shoot.changeVolume(-10);
            shoot.play();
        }
        disparo.update();
        //collidesWith();
        if(timeFrameAqui==Constants.timeFrame)timeFrameAqui=0;
    }

	@Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        //Aquí viene digamos como conseguí el IDLE del ojo, es decir el Ojo se mueve constantemente
        //Para ello usé esta lógica acompañada del código creado en UPDATE
       if(timeFrameAqui<=velocidad/2) g2d.drawImage(texture,(int)position.getX(), (int)position.getY(), null);
        if(timeFrameAqui>=velocidad/2)g2d.drawImage(Assets.ojo2,(int)position.getX(), (int)position.getY(), null);
        
    }
    @Override
   protected void Destroy(){
       if(!gamestate.subtractLifeOJO()){
           super.Destroy();
           gamestate.addScore(Constants.OJO_SCORE, position);
       }
       resetValues();
    }
    public void resetValues(){
        velocity= new Vector2D();
       
    }
   public Vector2D salidaDisparo(){
        return new Vector2D(position.getX()+width/2, position.getY() + (width+32));
    }
}
