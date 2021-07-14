
package gameObject;

import graphics.Assets;

public class Constants {
    //frame dimensions
    public static final int WIDTH=1200;
    public static final int HEIGHT=700;
    
    //BOTONES
    public static final String PLAY="PLAY";
    public static final String OPTIONS="OPTIONS";
    public static final String EXIT="EXIT";
    public static final String RETURN="RETURN";
    
    //NOMBRE DE LAS CANCIONES
    public static final String NADA="";
    public static final String temaIntro="01. Intro";
    
    
    //Velocidad de disparo del Jugador
    public static final int fireRate=150;
    
    //Para el parpadeo del Jugador
    public static final long SPAWNING_TIME=3000;
    public static final long FLICKER_TIME=50;
    
    //Puntuación de los meteoritos
    public static final int MeteorScore=25;
    
    //Tiempo para el GameOver
    public static final long GAME_OVER_TIME = 3000;
    
    //Ancho y alto de las basuras espaciales
    public static final int widthTrash=Assets.trash.getWidth();
    public static final int heightTrash=Assets.trash.getHeight();
    public static final int TrashScore=15;
    
    //Tiempo para la Pantalla de Ready
    public static final long READYGO = 2500;
    public static final long CAMBIOFASE = 70000;
    public static final long CAMBIOFASE2 = 140000;
    
    //Tiempo al pulsar los botones
    public static final long PUSHBOTTON = 1000;
    
    //Para los nodos del OJO... El OJO usa nodos para traza un camino
    	public static final int NODO_RADIO = 100;
	public static final double OJO_MASA = 60;
	public static final int OJO_MAX_VEL = 2;
	public static final long OJO_FIRE_RATE = 2500;
        public static final long OJO_SPAWN= 30000;
        public static final int OJO_SCORE=75;
        public static final long timeFrame=225;
    //Choque de lasers
        public static final int ChoqueLaser_SCORE=60;
}
