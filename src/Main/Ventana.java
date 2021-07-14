
package Main;
import Input.Keyboard;
import Input.MouseInput;
import gameObject.Constants;
import graphics.Assets;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import states.GameState;
//import states.HUDS;
import states.MenuState;
import states.state;



public class Ventana extends JFrame implements Runnable{
    
    private Canvas canvas;//Nos va a permitir detectar ratón y teclado.
    
    public static final int WIDTH=900, HEIGHT=700;
    private Thread thread;
    private boolean running=false;
    private BufferStrategy bs;
    private Graphics g;
    private final int FPS=60;//Esto nos sirve para almacenar la cantidade 
                             //de fotogrmas por segundo a la que queremos correr el juego
    private double TARGETTIME=1000000000/FPS;//tiempo requerido para pasar de fotograma(Medidos en nano segundos por eso el 1000000)
    private double delta=0;//Delta en matemáticas represneta el cambio con respecto al tiempo de una cantidad
    private int AVERAGEFPS=FPS;//Fotogramas por segundo promedios. Nos permite ver a cuanto va corriendo el juego
                                //actualmente.
    
    
    private Keyboard keyBoard;//Para meter eventos de teclado en nuestro juego.
    private MouseInput mouseInput;
    
    //private HUDS hud=new HUDS();
    public Ventana() {
        setTitle("Space Ship Game");
        setSize(Constants.WIDTH, Constants.HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        
        
        canvas=new Canvas();
        keyBoard= new Keyboard();
        mouseInput=new MouseInput();
        
        canvas.setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        canvas.setMaximumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        canvas.setMinimumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        canvas.setFocusable(true);
        add(canvas);
        canvas.addKeyListener(keyBoard);//Para registrar las teclas de teclado.
        canvas.addMouseListener(mouseInput);//Para registrar el ratón.
        canvas.addMouseMotionListener(mouseInput);
        setVisible(true);
    }
   public static void main(String[] args) {
       new Ventana().start();
    }
  
    private void update(){
        keyBoard.update();
        state.getCurrentState().update();
    
    }
   private void draw(){
       bs = canvas.getBufferStrategy();
       if(bs==null){
           canvas.createBufferStrategy(3);
            return;
       }
       g=bs.getDrawGraphics();
    //----------------------------   
        /*CIELO o BACKGROUND de FONDO*/
       g.setColor(Color.BLACK);
       g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
       state.getCurrentState().draw(g);
       /*-------ESTO ES PARA MOSTRAR LOS FPS EN PANTALLA---------*/
       //g.drawString(""+AVERAGEFPS, 10, 20);
    //----------------------------
       g.dispose();
       bs.show();
   }
    @Override
    public void run() {
        
        
        long now=0;
        long lastTime=System.nanoTime();
        int frames=0;
        long time=0;
        init();//NUEVO
        while(running)
        {
            now = System.nanoTime();
            delta +=(now - lastTime)/TARGETTIME;
            time+=(now - lastTime);
            lastTime =now;
            if(delta>=1){
                update();
                draw();
                delta--;
                frames++;
                //System.out.println(frames);
            }
            if(time >= 1000000000){
            
                AVERAGEFPS=frames;
                frames=0;
                time=0;
            }
        }
        stop();
    }
    private void start(){
        
        thread = new Thread(this);
        thread.start();
        running=true;
    }
    private void stop(){
        try{
            thread.join();
            running=false;
        }catch(InterruptedException e){/**/
            e.printStackTrace();
        }
    }
    //VARIABLE QUE SIRVE PARA INICIAR EL JUEGO
    private void init(){
        Assets.init();
        state.changeState(new MenuState());
    }
    
    
}
