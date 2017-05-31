/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ahorcado;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @Se obliga a que se implementen los métodos de Action y Mouse Listener
 * Para tener interración con la pantalla.
 */
public class MiPanel  extends JPanel implements ActionListener,  MouseListener{
    
    // Se crea la variable timer que será la que va a coordinar los
    // tiempos de ejecucion, esta llama la clase Timer
   private final Timer timer;
   Toolkit t = Toolkit.getDefaultToolkit();
   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
  int x=0;
  int xExt=screenSize.width;
  int ymin=screenSize.height-80;
  int y;
  int secx=0;
  int j;
  int tiempo;
  int perdio;
// Variables Boolenanas para posiciones
  int DER=0;
  int IZQ=0;
  int ARR=0;
  int bajo=0;
  int aux;
  // Variable para identificar los pasos antes de la muerte
  int paso=0;
    //private Object thread;
  char LETRA;
 // public String pista="LA PISTA ES: MUSICA (Space:-)";
  //public  String Palabra="HELLOWEEN";
  public String pista="LA PISTA ES: CIENCIA (Space:-)";
  public  String Palabra="ISAAC-NEWTON";
  ArrayList<LETRAPAINT>  dibujar=new ArrayList<>();
 int gano=0;
    // Constructor del panel, este tiene como objetivo inicializar el Timer
    // Y agregar el Listener del teclado y del Mouse.
    
     public MiPanel(){
         
         
    //para el teclado inicializa la clase interna Tapadter.
     addKeyListener(new Tadapter());
     setFocusable(true);
     // Agrega el funcionamiento en modo receptivo del Mouse.   
    this.addMouseListener(this);
    // Inicializa el Timer para pasos de 200ms En este caso    
    this.timer=new Timer(50,this);
    //Inicializa el Timer
    this.timer.start();
    }
    // Se crea una clase que va a extender la clase KeyApater,
    // Esta  clase permite trabajar las funciones del teclado
    private class Tadapter extends KeyAdapter{

     // Libera las teclas
     @Override
     public void keyReleased(KeyEvent e){
      LETRA=' ';
      }
     @Override
     // Reacciona ante el evento de presionar una tecla
     public void keyPressed(KeyEvent e){
     LETRA=e.getKeyChar();
      int veri=0;
      for (int i = 0; i < Palabra.length(); i++) {
      char[] Palabrax = Palabra.toCharArray();
      char letra=Palabrax[i];
      int delta=40;
      if(letra==LETRA){
       veri=1;
       gano=gano+1;
      }
      }
      if(veri==0){
      paso=paso+1;
      }
     
     }
  
 }  
  
    
  // En esta parte se deben definir todas las imagenes que se deben definir en el 
  //Panel
   @Override
   protected void paintComponent(Graphics g){
   super.paintComponent(g);
   // ajuste de pantalla y fondo
   Image fondo = loadImage("Fondo.PNG");
   g.drawImage(fondo,0,0,screenSize.width ,screenSize.height, null);
   
// Pasos de Muñecos
   Image Paso0= loadImage("Muñeco2.PNG");
   Image Paso1= loadImage("PASO1.PNG");
   Image Paso2= loadImage("PASO2.PNG");
   Image Paso3= loadImage("PASO3.PNG");
   Image Paso4= loadImage("PASO4.PNG");
   Image Paso5= loadImage("PASO5.PNG");
   Image Paso6= loadImage("PASO6.PNG");
       
   
       if (paso==0) {
      g.drawImage(Paso0,900,200,1300,600,0,0,436,311 ,null);     
       }
      if(paso==1){
             g.drawImage(Paso1,900,200,1300,600,0,0,436,311 ,null);
      }
      if(paso==2){
             g.drawImage(Paso2,900,200,1300,600,0,0,436,311 ,null);
      }
      if(paso==3){
             g.drawImage(Paso3,900,200,1300,600,0,0,436,311 ,null);
      }
      if(paso==4){
             g.drawImage(Paso4,900,200,1300,600,0,0,436,311 ,null);
      }
      if(paso==5){
             g.drawImage(Paso5,900,200,1300,600,0,0,436,311 ,null);
      }
      if(paso==6){
             g.drawImage(Paso6,900,200,1300,600,0,0,436,311 ,null);
      timer.stop();
      }
      
   // Cabeza
  
   
  
   g.setColor(Color.WHITE);
   g.setFont( new Font( "Serif", Font.ITALIC, 70 ) );
   g.drawString(this.pista,100,100);
   
   // fUNCIONES
   DibujarEntorno(g, Palabra);
   
   
  // llama un par me metodos para poder identificar letras
   
   char letra= this.VerificarLetras1(g);
   int [] posi=this.VerificarLetras2(g);
      
   if ('*' ==letra){
           } else {
       LETRAPAINT LP=new LETRAPAINT(posi, letra);
  
       dibujar.add(LP);
       }
       
       for (int i = 0; i < dibujar.size(); i++) {
      g.setFont( new Font( "Serif", Font.ITALIC, 50 ) );
      String LETRAS=dibujar.get(i).getLetra() +"";
      
          
      int [] auxpos=dibujar.get(i).getPosicion();
      for (int k = 0; k <Palabra.length(); k++) {
      if(auxpos[k]==1){
      g.drawString(LETRAS,30+50*k,ymin-300);   
           }
      }
      
       }
       
       System.out.println(gano);
      if (gano==Palabra.length()){
       g.setColor(Color.WHITE);
       g.setFont( new Font( "Serif", Font.ITALIC, 50 ) );
       g.drawString("MUY BIEN PARCE!!! ",screenSize.width/2-200 ,screenSize.height/2-100);
      timer.stop();
      }
   }
   // Método para cargar imagenes  
   
   public Image loadImage(String imageName) {
   ImageIcon ii = new ImageIcon(imageName);
   Image image = ii.getImage();
   return image;
    }

  // Este es un método que se debe implementar
   //Variable para secuencia
  
  @Override
  public void actionPerformed(ActionEvent e) {
  repaint();
  }
  public char VerificarLetras1(Graphics g){
  char retornar='*';
      for (int i = 0; i < this.Palabra.length(); i++) {
      char[] Palabrax = Palabra.toCharArray();
      char letra=Palabrax[i];
      int delta=40;
      if(letra==LETRA){
       retornar=letra;
      }
      }
       
     return retornar;
      }
   public int [] VerificarLetras2(Graphics g){
       
   int tam=this.Palabra.length();
   int [] retornar=new int [tam];
  for (int i = 0; i < this.Palabra.length(); i++) {
      
      char[] Palabrax = Palabra.toCharArray();
      char letra=Palabrax[i];
      
      if(letra==LETRA){
     retornar[i]=1;
      }
      else retornar[i]=0;
      }
       
  return retornar;
      }
  
  public void DibujarEntorno(Graphics g, String Palabra){
      int delta=40;
      for (int i = 0; i <Palabra.length(); i++) {
      g.setColor(Color.BLUE);
      g.drawLine(30+50*i,ymin-300,30+50*i+delta, ymin-300);
      }
      }
      // Como Se definió, Implements obliga a definir todos los métodos del
        // Mouse Los primeros se dejan vacíos, el uso está princupalmente en MouseClicked.
        @Override
        public void mouseClicked(MouseEvent e) { 
        System.out.println("Probemos el Clikc");
        //  this.timer.stop(); 
        //  this.timer.start();
        }
       
        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
           
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }
        @Override
        public void mouseExited(MouseEvent e) {
        }
   

   }
    


