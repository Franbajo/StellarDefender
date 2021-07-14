
package graphics;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Assets {
    
    public static BufferedImage player, player2, player3;
    public static BufferedImage blueLaser, redLaser, greenLaser;
    public static BufferedImage boton1;
    public static BufferedImage boton2;
    public static BufferedImage botonPlay1;
    public static BufferedImage botonPlay2;
    public static BufferedImage botonPasar1;
    public static BufferedImage botonPasar2;
    
    //Logo del Juego
    public static BufferedImage logo;
   //fonts
    public static Font fontBig;
    public static Font fontMed;
    
    //Meteoritos
    public static BufferedImage[]  bigs= new BufferedImage[4];
    public static BufferedImage[]  medianos= new BufferedImage[2];
    public static BufferedImage[]  pequenhios= new BufferedImage[2];
    public static BufferedImage[]  minis= new BufferedImage[2];
    
    //Basura espacial
    public static BufferedImage trash;
    public static BufferedImage[] trashAll= new BufferedImage[4];
    
    //Explosion
    public static BufferedImage[] explosion=new BufferedImage[9];
    public static BufferedImage[] explosionSangre=new BufferedImage[10];
    public static BufferedImage[] choqueLaser=new BufferedImage[9];
    //HUD
    public static BufferedImage[] hudNumeros= new BufferedImage[11];
    public static BufferedImage life;
    public static BufferedImage score;
    public static BufferedImage time;
    public static BufferedImage LetrasIntro;
    public static BufferedImage LetrasStage01;
    public static BufferedImage LetrasStage02;
    public static BufferedImage MusicTest;
   
    //Sonidos
    public static Clip backgroundMusic, explosionSonido, playerLoose, playerShoot, start, options, musicaMenu;
    public static Clip Sonido_Grito, Sonido_Sangre, musicaJuego, choqueLaserSound;
    //Backgrounds
    public static BufferedImage fondoEstrellas;
    public static BufferedImage estrellitas;
    public static BufferedImage meteoritoGigante;
    
    
    //Enemigos
    public static BufferedImage ojo, ojo2, ojo3, ojo4;
    //public static BufferedImage[] ojoAnimado=new BufferedImage[4];
    public static BufferedImage ojoDisparo;
    
    public static void init(){
    
        try {            
            fontBig=Loader.loadFont("/Bonus/INVASION2000.ttf", 42);
            fontMed=Loader.loadFont("/Bonus/INVASION2000.ttf", 20);
            boton1=Loader.ImgeLoader("/recursos/botonAzul1.png");
            boton2=Loader.ImgeLoader("/recursos/botonGris1.png");
            botonPlay1=Loader.ImgeLoader("/recursos/PlayBottom.png");
            botonPlay2=Loader.ImgeLoader("/recursos/PlayBottom2.png");
            botonPasar1=Loader.ImgeLoader("/recursos/pasaTemaBottom01.png");
            botonPasar2=Loader.ImgeLoader("/recursos/PlayBottom1.png");
            player = Loader.ImgeLoader("/recursos/player.png");
            player2=Loader.ImgeLoader("/recursos/player2.png");
            player3=Loader.ImgeLoader("/recursos/player3.png");
           redLaser= Loader.ImgeLoader("/recursos/laserRed01.png");



            life = Loader.ImgeLoader("/others/life.png");
            trash=Loader.ImgeLoader("/SpacialTrash/spacialTrash.png");
            score=Loader.ImgeLoader("/recursos/score.png");
            time=Loader.ImgeLoader("/recursos/time.png");
            
            
            for (int i = 0; i < bigs.length; i++) {
                bigs[i]=Loader.ImgeLoader("/meteoritos/Meteor_big"+(i+1)+".png");
            }
            for (int i = 0; i < medianos.length; i++) {
                medianos[i]=Loader.ImgeLoader("/meteoritos/Meteor_med"+(i+1)+".png");
            }
            for (int i = 0; i < pequenhios.length; i++) {
                pequenhios[i]=Loader.ImgeLoader("/meteoritos/Meteor_small"+(i+1)+".png");
            }
            for (int i = 0; i < minis.length; i++) {
                minis[i]=Loader.ImgeLoader("/meteoritos/Meteor_tiny"+(i+1)+".png");
            }
            for (int i = 0; i < explosion.length; i++) {
                explosion[i]=Loader.ImgeLoader("/Explosion/0_000"+i+".png");
            }
            
            for (int i = 0; i < explosionSangre.length; i++) {
                explosionSangre[i]=Loader.ImgeLoader("/Explosion_Sangre/0_000"+i+".png");
            }
            for (int i = 0; i < choqueLaser.length; i++) {
                choqueLaser[i]=Loader.ImgeLoader("/recursos/Choque_Energia/_000"+i+".png");
            }
            for (int i = 0; i < hudNumeros.length; i++) {
                hudNumeros[i]=Loader.ImgeLoader("/Numeros/"+i+".png");
            }
            for (int i = 0; i < trashAll.length; i++) {
                trashAll[i]=Loader.ImgeLoader("/SpacialTrash/spacialTrash"+i+".png");
            }
            
            musicaMenu=Loader.loadSound("/Sonidos/menuMusic.wav");
            backgroundMusic=Loader.loadSound("/Sonidos/backgroundMusic.wav");
            musicaJuego=Loader.loadSound("/Sonidos/musica_juego.wav");
            explosionSonido=Loader.loadSound("/Sonidos/explosion.wav");
            playerLoose=Loader.loadSound("/Sonidos/playerLoose.wav");
            playerShoot=Loader.loadSound("/Sonidos/playerShoot.wav");
            start=Loader.loadSound("/Sonidos/start.wav");
            options=Loader.loadSound("/Sonidos/Sonido_Startwav2.wav");
            Sonido_Grito=Loader.loadSound("/Sonidos/Sonido_Grito.wav");
            Sonido_Sangre=Loader.loadSound("/Sonidos/Sonido_Sangre.wav");
            choqueLaserSound=Loader.loadSound("/Sonidos/choque_Laser_Sound.wav");
            logo=Loader.ImgeLoader("/recursos/Logo_Juego.png");
             LetrasIntro=Loader.ImgeLoader("/recursos/letras_Intro.png");  
            LetrasStage01=Loader.ImgeLoader("/recursos/letras_Stage01.png");
            LetrasStage02=Loader.ImgeLoader("/recursos/letras_Stage02.png"); 
            MusicTest=Loader.ImgeLoader("/recursos/MusicTest.png");
            
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Assets.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Assets.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Assets.class.getName()).log(Level.SEVERE, null, ex);
        }
        fondoEstrellas=Loader.ImgeLoader("/backgrounds/stars_Pixel_art.png");
        //fondoEstrellas=Loader.ImgeLoader("/backgrounds/star.png");
        estrellitas=Loader.ImgeLoader("/backgrounds/star.png");
        meteoritoGigante=Loader.ImgeLoader("/backgrounds/meteorito_gigante.png");
        ojo=Loader.ImgeLoader("/enemigos/OJO/_0000.png");
        ojo2=Loader.ImgeLoader("/enemigos/OJO/_0002.png");
        ojoDisparo=Loader.ImgeLoader("/enemigos/disparo_enemigo.png");
    }

}
