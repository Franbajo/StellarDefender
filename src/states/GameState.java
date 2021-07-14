
package states;
import gameObject.Constants;
import gameObject.CronometroDisparo;
import gameObject.GameObject;
import gameObject.Laberynth;
import gameObject.Message;
import gameObject.Meteor;
import gameObject.OJO;
import gameObject.Player;
//import gameObject.SizeMeteoroide;
import graphics.Animation;
import graphics.Assets;
import static graphics.Assets.choqueLaser;
import graphics.Sound;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import math.Vector2D;
import ui.boton;

public class GameState extends state{
    public Player player;
    /*TIEMPO FIN DE LA FASE*/
    protected CronometroDisparo tiempoFin;
    protected CronometroDisparo ultimoMensaje;
  
    /*...................*/
    private ArrayList<GameObject>movingObjects=new ArrayList<GameObject>();
    private ArrayList<Animation>explosion=new ArrayList<Animation>();
    private ArrayList<Animation>explosionSangre=new ArrayList<Animation>();
    private ArrayList<Animation>choqueLaser=new ArrayList<Animation>();
    private  ArrayList<Message>messages= new ArrayList<Message>();
    private  ArrayList<Message>messages2= new ArrayList<Message>();
    /*Las vidas y el score son estáticos para que se puedan mantener de una fase a la otra*/
    public static int score=0;
    public static int lives = 3;
    //Para representar el número de meteoritos en pantalla:
    private int meteors;
    //Para ver el número de estrellas en pantalla
    private int starNumero;
    //Musica
    private Sound backgroundMusic;
    //GameOver
    private CronometroDisparo gameOverTimer;
    private boolean gameOver;
    //Background
    private MovingBackgrounds mov;
    private Moving_Meteor_Background met;
    //Laberinto de Basura
    private final int numeroFilas=4;
    private final int numeroColumnas=8;
    private final int widthTrash=Constants.widthTrash;
    private final int heightTrash=Constants.heightTrash;
    private final BufferedImage trashDraw=Assets.trash;
    private GameObject[][] laberintoBasura=new Laberynth[2][3];
    private double XLa=(Math.random()*(100-600)+600), YLa=-300;
    private double velocity;
    private ArrayList <GameObject> object=new  ArrayList <GameObject>();     
    //Para contrlar las oleadas de basura espacial o Laberynth
    protected Timer tiempo;
     //vidas y Spawn del OJO
    private int livesOJO = 5;
    private CronometroDisparo ojoSpawner;
    //Botón para volver al inicio
     private boton returnButton;
    
    /*.......................CONSTRUCTOR....................*/
    public GameState() {
       
        player=new Player(new Vector2D(100, 500), Assets.player, this);
        tiempo=new Timer();
       mov=new MovingBackgrounds();
       met=new Moving_Meteor_Background();
      ultimoMensaje=new CronometroDisparo();
    
      // confirmaFin=false;
       
        gameOverTimer= new CronometroDisparo();
        gameOver=false;
        
        movingObjects.add(player);
        
        meteors=1;
        /*LABERINTO*/
        velocity=0.9;
      
        /*.........*/
        round();
        startWave();
        startWave2();

        /*Control sobre la música de la pantalla*/
        backgroundMusic = new Sound(Assets.backgroundMusic);
        backgroundMusic.loop();
        backgroundMusic.changeVolume(0);
        
        /*EL BOTON DE VUELTA AL MENÚ DE INICIO*/
        returnButton=new boton(Assets.boton2, Assets.boton1,
        Constants.WIDTH/2-Assets.boton2.getWidth()/2,
        615,
        Constants.RETURN,3);
        
        //Para el spawn del Ojo
       // ojoSpawner=new CronometroDisparo();
        //ojoSpawner.run(Constants.OJO_SPAWN);
        
        /*Tiempo fin de fase y Mensaje de: -¡Último Combate!-*/
         tiempoFin=new CronometroDisparo();
         tiempoFin.run(Constants.CAMBIOFASE);
         messages2.add(ultimoMensaje.ultimaOleada());
    } 
    /*........................................................*/
   public void addScore(int valor, Vector2D position){
        score+=valor;
        messages.add(new Message(position, true, "+"+valor+" score", Color.WHITE, false, Assets.fontMed));
    }
    public void playExplosion(Vector2D position){
        explosion.add(new Animation(50, position.subtract(new Vector2D(Assets.explosion[0].getWidth()/2,
                                Assets.explosion[0].getHeight()/2)),Assets.explosion));
    }
    public void playExplosionSangre(Vector2D position){
        explosionSangre.add(new Animation(50, position.subtract(new Vector2D(Assets.explosionSangre[0].getWidth()/2,
                                Assets.explosionSangre[0].getHeight()/2)),Assets.explosionSangre));
    }
    public void choqueLaseres(Vector2D position){
        choqueLaser.add(new Animation(50, position.subtract(new Vector2D(Assets.choqueLaser[0].getWidth()/2,
                                Assets.choqueLaser[0].getHeight()/2)),Assets.choqueLaser));
    }
   @Override
    public void update(){
          
          returnButton.update();//Botón para volver al menú de inicio.
              //  labSta.update();

        for (int i = 0; i < movingObjects.size(); i++) {
            
            movingObjects.get(i).update();
            
        }

        for (int i = 0; i < explosion.size(); i++) {
            Animation anim=explosion.get(i);
            anim.update();
            if(!anim.isRunning()){
                explosion.remove(i);
            }
        }
        for (int i = 0; i < explosionSangre.size(); i++) {
           Animation anim=explosionSangre.get(i);
           anim.update();
           if(!anim.isRunning()){
               explosionSangre.remove(i);
           }
       }
        for (int i = 0; i < choqueLaser.size(); i++) {
           Animation anim=choqueLaser.get(i);
           anim.update();
           if(!anim.isRunning()){
               choqueLaser.remove(i);
           }
       }
        for (int i = 0; i < movingObjects.size(); i++) {
            
            if(movingObjects.get(i) instanceof Meteor){
                return;//El return nos dice que si despues de iterar  todo el array no hay ningún meteoro
                        //Puedes irte al starWave(); indicado más abajo
            }
        }
        /*OLEADAS DE BASURA, tienen un TimerTask para controlar el tiempo de salida*/
        TimerTask tarea=new TimerTask() {
        @Override
            public void run() {
                    /*Oleada Trozos de basura o Laberinto*/
                for (int i = 0; i < movingObjects.size(); i++) {

                    if(movingObjects.get(i) instanceof Laberynth){/*En esta línea además digo que
                                                                   mientras iteres Laberynth, me estés
                                                                    generando meteoritos normales, la
                                                                    cosa es que mientras salgan las
                                                                     matrices de basura, salgan los mete
                                                                    oritos y las acción sea más constante.*/
                        startWave();
                        return;

                    }
                }
            }
        };
        /*.......................................................................*/
        
        //Si no hay ningún meteoro, entonces reiniciamos la Oleada
            startWave();
        //No se porqué pero si quito este timerTask, la aplicación se vuelve majareta
        TimerTask tarea2=new TimerTask() {
            @Override
            public void run() {
                startWave2();
            }
        };
       tiempo.scheduleAtFixedRate(tarea, 10000, 16000);
       
       //PARA VOLVER DE MENÚ DE JUEGO AL GAMEOVER
       if(gameOver && !gameOverTimer.isRunning()){
           //backgroundMusic.stop();
           state.changeState(new MenuState());
          
       }
       gameOverTimer.update();
       
     
        
        //ultimoMensaje.update();

        /*........CAMBIO DE ESTADO.......*/
        if(!tiempoFin.isRunning()&&!gameOver){
            backgroundMusic.stop();
            state.changeState(new Ready2());
        }
        /*...............................*/ 
  
    }
    public void round(){
        messages.add(new Message(new Vector2D(Constants.WIDTH/2, Constants.HEIGHT/2), 
        true, "START!!!", Color.WHITE, true, Assets.fontBig));
    }
    public void draw(Graphics g){
    /*EN ESTE MÉTODO EL ORDEN ES DE SUMA IMPORTANCIA, si está arriba las cosas se colocan
        en el fondo y si están abajo del método se superponen en plan capas del
        photoshop*/
        /*..........Estrellas y asteroides gigantes de fondo, BACKGROUND DEL JUEGO........*/
       mov.draw(g);
       mov.update();
       met.draw(g);
       met.update();
       /*................................................................................*/
        Graphics2D  g2d=(Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);


        
        for (int i = 0; i < movingObjects.size(); i++) {
            movingObjects.get(i).draw(g);
        
        }
        

        for (int i = 0; i < explosion.size(); i++) {
            Animation anim=explosion.get(i);
            g2d.drawImage(anim.getCurrentFrame(), (int)anim.getPosition().getX(), (int)anim.getPosition().getY(),null);
        }
        for (int i = 0; i < explosionSangre.size(); i++) {
            Animation anim=explosionSangre.get(i);
            g2d.drawImage(anim.getCurrentFrame(), (int)anim.getPosition().getX(), (int)anim.getPosition().getY(),null);
        }
        for (int i = 0; i < choqueLaser.size(); i++) {
            Animation anim=choqueLaser.get(i);
            g2d.drawImage(anim.getCurrentFrame(), (int)anim.getPosition().getX(), (int)anim.getPosition().getY(),null);
        }
        
       for (int i = 0; i < messages.size(); i++) {
            messages.get(i).draw(g2d);
            if(messages.get(i).finTexto())messages.remove(i);//Eliminar para optimizar
        }
         drawScore(g);
        drawLives(g);
        drawTiempo(g);
        
        /*..............MENSAJE LAST COMBAT!!!.............*/
        
        if(!tiempoFin.isRunning()&&!gameOver){
            for (int i = 0; i < messages2.size(); i++) {
                messages2.get(i).draw(g2d);
                ultimoMensaje.run(Constants.GAME_OVER_TIME);
                 if(messages2.get(i).finTexto())messages2.remove(i);//Eliminar para optimizar
            } 
        }
        /*...................................................*/
       // labSta.draw(g);
       /*LABERINTO*/
        /*NO BORRAR... PQ ES INTERESANTE A LA HORA DE CREAR UN LABERINTO*/
              /*int laberintoMatriz[][]=getLaberynth();

                   for (int i = 0; i < laberintoMatriz.length; i++) {
                        for (int j = 0; j < laberintoMatriz[i].length; j++) {
                            if(laberintoMatriz[i][j]==1){
                                 laberintoBasura[i][j].draw(g);
                            }
                        }
                     }*/
        /*....................*/ 
        if(gameOver) returnButton.draw(g);//Botón para volver al menú de inicio.
    }
    public ArrayList<GameObject> getMovingObjects() {
        return movingObjects;
    }
    public ArrayList<Message> getMessages() {
        return messages;
    }
    private void startWave(){        
        
        double x,y;//Estos doubles son para crear la posición del meteoro
        //Iteramos sobre la cantidad de meteoros que tenemos, porque no siempre va a ser 1:
        for (int i = 0; i < meteors; i++) {
            //Para situar en el eje x los Meteoritos
           x=Math.random()*(100-1000)+1000;
            int tipo=(int)(Math.random()*(1-4)+4);
            BufferedImage texture;
            double vel;
            switch(tipo){
                case 1:
                    texture = Assets.bigs[(int)(Math.random()*Assets.bigs.length)];
                    vel=Math.random()*(1-1.4)+1.4;
                    movingObjects.add(new Meteor( new Vector2D(x, y=-100), texture, this,/* SizeMeteoroide.BIG,*/ vel));
                    
                    break;
                case 2:
                    texture = Assets.medianos[(int)(Math.random()*Assets.medianos.length)];
                    vel=Math.random()*(1-1.4)+1.4;
                    movingObjects.add(new Meteor( new Vector2D(x, y=-100), texture, this, /*SizeMeteoroide.MED,*/ vel));
                    break;
                case 3: 
                    texture = Assets.pequenhios[(int)(Math.random()*Assets.pequenhios.length)];
                    vel=Math.random()*(1-1.4)+1.4;
                    movingObjects.add(new Meteor( new Vector2D(x, y=-100), texture, this, /*SizeMeteoroide.SMALL,*/ vel));
                    break;
                case 4: 
                    texture = Assets.minis[(int)(Math.random()*Assets.minis.length)];
                    vel=Math.random()*(1-1.4)+1.4;
                    movingObjects.add(new Meteor( new Vector2D(x, y=-100), texture, this, /*SizeMeteoroide.TINY,*/ vel));
                    break;
            }
            
        }
        meteors++;
        if(meteors>=6){
        meteors=(int)(Math.random()*(1-5)+5);
        }
     // spawnOJO();
       
    }
    private void startWave2(){
        /*Esta Oleada2 es para la basura espacial*/
        /*Si no le pongo otro TimerTask, no me hace caso y no hace esta oleada... */
        TimerTask tarea=new TimerTask() {
        @Override
            public void run() {
    /*................LABERINTO........................*/ 
              laberintoBasura=new Laberynth[(int)(Math.random()*(1-5)+5)][(int)(Math.random()*(1-5)+5)];
              int tipodeBasura;
        XLa=(Math.random()*(100-600)+600);
        int consX, consY;
                    for (int i = 0; i < laberintoBasura.length; i++) {
                        consX=100;consY=0;
                          for (int j = 0; j < laberintoBasura[i].length; j++) {
                              switch(i){
                                  case 0:
                                       tipodeBasura=(int)(Math.random()*(1-4)+4);
                                      laberintoBasura[i][j]=new Laberynth(new Vector2D(XLa+consX, YLa), Assets.trashAll[tipodeBasura-1], GameState.this, velocity);
                                      movingObjects.add(laberintoBasura[i][j]);
                                         /* if(j>0){
                                              laberintoBasura[i][j]=new Laberynth(new Vector2D(XLa+consX, YLa), trashDraw, this, velocity);
                                             movingObjects.add(laberintoBasura[i][j]);
                                          }*/
                                      consX+=100;
                                      break;
                                  case 1: 
                                       tipodeBasura=(int)(Math.random()*(1-4)+4);
                                      consY+=110;
                                          laberintoBasura[i][j]=new Laberynth(new Vector2D(XLa+consX, YLa+consY), Assets.trashAll[tipodeBasura-1], GameState.this, velocity);
                                          movingObjects.add(laberintoBasura[i][j]);
                                      /*if(j>0){
                                          laberintoBasura[i][j]=new Laberynth(new Vector2D(XLa+consX, YLa+consY), trashDraw, this, velocity);
                                          movingObjects.add(laberintoBasura[i][j]);
                                      }*/
                                      consX+=100;
                                      break;
                                  case 2: 
                                       tipodeBasura=(int)(Math.random()*(1-4)+4);
                                      consY+=220;
                                          laberintoBasura[i][j]=new Laberynth(new Vector2D(XLa+consX, YLa+consY), Assets.trashAll[tipodeBasura-1], GameState.this, velocity);
                                          movingObjects.add(laberintoBasura[i][j]);
                                          /*if(j>0){
                                              laberintoBasura[i][j]=new Laberynth(new Vector2D(XLa+consX, YLa+consY), trashDraw, this, velocity);
                                              movingObjects.add(laberintoBasura[i][j]);
                                          }*/
                                      consX+=100;break;
                                  case 3:
                                       tipodeBasura=(int)(Math.random()*(1-4)+4);
                                          consY+=320;
                                          laberintoBasura[i][j]=new Laberynth(new Vector2D(XLa+consX, YLa+consY), Assets.trashAll[tipodeBasura-1], GameState.this, velocity);
                                          movingObjects.add(laberintoBasura[i][j]);
                                     /* if(j>0){

                                          laberintoBasura[i][j]=new Laberynth(new Vector2D(XLa+consX, YLa+consY), trashDraw, this, velocity);
                                          movingObjects.add(laberintoBasura[i][j]);
                                      }*/
                                      consX+=100;
                                      break;
                                  }
                             consY=0;
                          }
                      }
                  /*.............................................................*/
                           }
        };
          tiempo.scheduleAtFixedRate(tarea, 4000, 10000);

          
    }
    private void drawScore(Graphics g){
        Vector2D positionLetras = new Vector2D(150, 615);
        g.drawImage(Assets.score, (int)positionLetras.getX(), (int)positionLetras.getY(), null);
       Vector2D position = new Vector2D(150, 640);
       String scoreEscrito=Integer.toString(score);
       for (int i = 0; i < scoreEscrito.length(); i++) {
           g.drawImage(Assets.hudNumeros[Integer.parseInt(scoreEscrito.substring(i, i+1))], 
                   (int)position.getX(),  (int)position.getY(), null);
           position.setX(position.getX()+20);
       }
    
    }
    /*.................CRONÓMETRO...............*/
    public void drawTiempo(Graphics g){
        Vector2D positionLetras= new Vector2D(Constants.WIDTH/2.05, 615);
        g.drawImage(Assets.time, (int)positionLetras.getX(), (int)positionLetras.getY(), null);
        Vector2D position = new Vector2D(Constants.WIDTH/2.08, 635);
        long tiempoCrono=tiempoFin.getDelta();
        String tiempoTranscurso=Long.toString(tiempoCrono);
        for (int i = 0; i < tiempoTranscurso.length(); i++) {
            g.drawImage(Assets.hudNumeros[Integer.parseInt(tiempoTranscurso.substring(i, i+1))], 
                   (int)position.getX(),  (int)position.getY(), null);
           position.setX(position.getX()+20);
        }
    }
    /*...........................................*/
    private void drawLives(Graphics g){
		if(lives<0)return;
                
        
		Vector2D livePosition = new Vector2D(25, 620);
		
		g.drawImage(Assets.life, (int)livePosition.getX(), (int)livePosition.getY()+5, null);
		
		g.drawImage(Assets.hudNumeros[10], (int)livePosition.getX() + 40,
				(int)livePosition.getY() + 5, null);
		
		String livesToString = Integer.toString(lives);
		
		Vector2D pos = new Vector2D(livePosition.getX(), livePosition.getY());
		
		for(int i = 0; i < livesToString.length(); i ++)
		{
                    try{
			int number = Integer.parseInt(livesToString.substring(i, i+1));
			
			if(number < 0)
				break;
                       
			g.drawImage(Assets.hudNumeros[number],
					(int)pos.getX() + 60, (int)pos.getY() + 5, null);
			pos.setX(pos.getX() + 20);
                    }catch(NumberFormatException e){}
		}   
	}
    public void gameOverMetodo(){
        Message gameOverMag=new Message (new Vector2D(Constants.WIDTH/2, Constants.HEIGHT/2), true, "GAME OVER", Color.WHITE, true, Assets.fontBig);
        this.messages.add(gameOverMag);
        gameOverTimer.run(Constants.GAME_OVER_TIME);
        gameOver=true;
        if(gameOver)backgroundMusic.stop();

        //EL METODO GAMEOVER se complementa con el METODO DESTROY de la clase PLAYER
    }    
    
    /*public void ultimaOleada(){
            ultimoMensaje.update();
            if(confirmaFin){
                Message ultima=new Message (new Vector2D(Constants.WIDTH/2, Constants.HEIGHT/2), true, "LAST COMBAT!!!", Color.WHITE, true, Assets.fontBig);
                this.messages.add(ultima);
                ultimoMensaje.run(Constants.GAME_OVER_TIME);
            }
    }*/
    
    public boolean subtractLife(){
        lives --;
        return lives>=0;
    }
/*Este método getPlayer lo necesita el enemigo para saber donde está el jugador*/
    public Player getPalyer(){
       return player;
   }
  /*...........LABERINTO...........*/
    /*NO BORRAR... PQ ES INTERESANTE*/
  /* public  int[][] getLaberynth(){

        int laberynth[][]={
            {1, 0, 1, 0, 1, 0, 1, 0}, 
            {0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1}
            };
                return laberynth;
    }*/
    /*............................*/
 /*Método necesario para el funcionamiento del OJO*/
  public boolean subtractLifeOJO(){
        boolean verifica=true;
       livesOJO --;
        if(livesOJO>=0){
            return verifica;
        }else verifica=false;
        if(livesOJO<0)livesOJO=5;
        return verifica;
       
    }


    


 }