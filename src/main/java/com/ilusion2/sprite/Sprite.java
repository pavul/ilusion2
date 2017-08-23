/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilusion2.sprite;

import com.ilusion2.level.GameLevel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;

/**
 * clase que contiene al sprite del juego, cada sprite es un objeto con sus
 * respectivas cordenadas, puntaje, vida y movimientos, esta clase no esta
 * terminada por lo que se tiene que extender en otra que vaya a ser la que sea
 * definitiva, por ejemplo un jugador
 *
 * @author Ilusion
 */
public class Sprite implements Movement 
{

    public static final int FRAME_FIRSTFRAME = 0; //valor del primer frame de 1 animacion
    public static final int ANIM_FOWARD = 10; //valor de la animacion hacia adelante
    public static final int ANIM_BACKWARD = 11; //valor de la animacion hacia atras
    public static final int ANIM_FRAME = 12; //valor de la animacion por ciertos frames
    public static final int ANIM_STOPATEND = 13; //valor de la animacion que termina si se llega al frame final
    
    
    
    /**
     * internal counter in the sprite class to create
     * diferent spriteID
     */
    private static int idCounter = 10_000;
    
    /**
     * with this id we can retrieve a particular sprite
     */
    private int spriteId;
    
    /**
     * this label is to group sprites for example:
     * player, enemy, desctructable object, wheatever
     * what programmer wants and supply their needs
     */
    protected String label="";
    
    protected float x; //coordenada x del sprite
    protected float y; //coordenada y del sprite
    protected float w; //coordenada x del sprite
    protected float h; //coordenada y del sprite

    protected float speedX = 0; //velocidad x que tiene el sprite
    protected float speedY = 0; //velocidad y que tiene el sprite

  
    protected float friction; //valor para friccion

    protected boolean visible; //indica si se renderiza el sprite o no en el room
    protected boolean animationEnd; //indica si se ha llegado al fin de la animacion

    protected int imageSpeed; //velocidad de animacion de los frames del sprite
    protected double degrees; //variable que tiene los grados a ls que se ve el sprite
    
    protected boolean jump; //variable para indicar si el usuario ha brincado
//    protected boolean executeJump; //variable para indicar si el usuario ha brincado
    protected float jumpForce; //fuerza a la que brinca el sprite
    protected float jumpValue;
    protected float gravity; //valor para gravedad
    
       //limites a los que es permitido llegar el sprite, es decir, el sprite no puede salir de
   //esos limites, por lo general son los limites del room en el que se encuentra
   //estas variables se checan en los metodos move, cuando tienen valor diferente de -1
   protected int roomBoundLeft=-1; //limite izquierdo del room hasta donde puede llegar el sprite
   protected int roomBoundRight=-1; //limite derecho del room hasta donde puede llegar el sprite
   protected int roomBoundTop =-1; //limite de cima del room hasta donde puede llegar el sprite
   protected int roomBoundBottom =-1; //limite de fondo del room hasta donde puede llegar el sprite
   
   /**
    * animation speed counter to now at what number of this a frame must change
    */
   protected int animationSpeed; //velocidad a la que se debe de mostrar la animacion 
                               
   /**
    * animation speed will check this limit, if animation speed reach this limit the
    * frame of a subanimation must change, when this frame changes animationSpeed will be
    * set to 0 again to start the counter again
    */
   protected int animationSpeedLimit; //limite para
      
   /**
    * this point will serve as reference to rotate the sprite
    * by default will be put at the center of sprite
    */
   protected Point pivot;
   
   /**
    * this point is a reference to the point the sprite must collide 
    * with some tiles, like slopes, basically by default will be 
    * sprite.x and sprite.y, but if you need to check slopes colision
    * surely you will have to set this variable to 
    * sprite.getCenterX and sprite.y + sprite.h
    * NOTE: to check collisions between slopes you have to use
    * checkCollisionWithSlope, on Collision class
    */
   protected Point anchor;
   
   //variables para las subanimaciones, es decir, un sprite se puede animar solamente
   //con mostrar algunos frames de las imagenes que se tienen
   
//   /**
//    * this is the initial position of the array that contains a subanimation
//    * 
//    
//    */
////   private int iniFrame;
//   
//   /**
//    * this is the last position of the array that contains a subanimation
//    */
////   private int endFrame;
      
   
   
   //THIS WILL BE ALWAYS 0 POSITION
//   /**
//    * initial position of the array containing subanimation
//    */
//   private int subanimationInitialPos = 0;
   
   /**
    * current position of the subanimation array
    */
   protected int subanimationCurrentPos = 0;
   
   /**
    * last position of the array containing subanimation 
    */
   protected int subanimationLastPos = 0;
   
    /*
     *funcionalidad para sprites
      image array that contains all the images that can be used by this sprite
     */
    protected Image[] frames;
    protected int currentFrame;
    protected int lastFrame;
    //    pricate Map<String, Image[]>animation;

    //animacion actual del sprite
    protected AnimationState currentAnimationState;
    
    //stack de subanimaciones
    /**
     * this is used to store/use subanimation ( for non lineal animations )
     */
    protected Map<AnimationState, int[] > subAnimationStack;
    
    /**
     * variable used to put hit points or lives to player
     */
    protected int hp;
    
    /* 
     * CONSTRUCTOR
     */
    
    /**
     * constructor que crea la instancia, hace falta definir todos los
     * parametros del script
     * 
     * this contrustor define few properties of this class, other
     * have to be defined with setters
     */
    public Sprite() 
    {
        
        //setting the id for this sprite
        spriteId = ++idCounter;
        this.gravity = 0.37f;
        this.jumpForce = 10;
        this.animationSpeedLimit = 10;
        this.currentAnimationState = AnimationState.STANDRIGHT;
        
        
        this.pivot = new Point( ( int )this.getCenterX() , ( int )this.getCenterY() );
        this.anchor = new Point( 0, 0 );
        
        
    }//cont 1

    /**
     * constructor que crea el sprite, pero solo con las dimensiones de W y H,
     * en la posicion x = 0 e y = 0
     *
     * @param x
     * @param y
     * @param w
     * @param h
     */
    public Sprite(int x, int y, int w, int h) {
        this();
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }//const 2

    /**
     * constructor 2 donde se crea un sprite con una imagen,
     *
     * @param img
     */
    public Sprite(Image img) {
        this();
        this.frames = new Image[1];
        this.frames[0] = img;
        this.currentFrame = this.frames.length - 1;
        this.lastFrame = this.frames.length - 1;
        this.w = this.frames[FRAME_FIRSTFRAME].getWidth(null);
        this.h = this.frames[FRAME_FIRSTFRAME].getHeight(null);
    }//const 3

    /**
     * constructor 4 donde se crea el sprite con un arreglo de imagenes, estos
     * son tratados como frames de una animacion
     *
     * @param img
     */
    public Sprite(Image[] img) {
        this();
        this.frames = new Image[img.length];
        this.frames = img;
        this.currentFrame = this.frames.length - 1;
        this.lastFrame = this.frames.length - 1;
        this.w = this.frames[FRAME_FIRSTFRAME].getWidth(null);
        this.h = this.frames[FRAME_FIRSTFRAME].getHeight(null);
    }//const 4

    /**
     * constructor 5 se crean los frames de animacion con una imagen como stip
     *
     * it create an animation with a strip image, but developer must
     * define how many frames and the heigth and width of these
     * @param numFrames
     * @param w
     * @param h
     * @param imgRoute
     */
    public Sprite(int numFrames, int w, int h, String imgRoute) {
        this();
        BufferedImage bigImg = null;
        try 
        {
//            bigImg= ImageIO.read(new File("sheet.png"));
            bigImg = ImageIO.read(this.getClass().getResource(imgRoute));
        } 
        catch (IOException ioe) 
        { }

        Image[] im = new Image[numFrames];

        for (int i = 0; i < numFrames; i++) {
            im[i] = bigImg.getSubimage(i * w, 0, w, h);
        }// for
        this.frames = im;
        this.currentFrame = this.frames.length - 1;
        this.lastFrame = this.frames.length - 1;
        this.w = this.frames[ FRAME_FIRSTFRAME ].getWidth(null);
        this.h = this.frames[ FRAME_FIRSTFRAME ].getHeight(null);

                 //ESTE CODIGO DEBE DE SERVIR PARA LOS OTROS CONSTRUCTORES
//    BufferedImage bigImg = ImageIO.read(new File("sheet.png"));
//// The above line throws an checked IOException which must be caught.
//
//final int width = 10;
//final int height = 10;
//final int rows = 5;
//final int cols = 5;
//BufferedImage[] sprites = new BufferedImage[rows * cols];
//
//for (int i = 0; i < rows; i++)
//{
//    for (int j = 0; j < cols; j++)
//    {
//        sprites[(i * cols) + j] = bigImg.getSubimage(
//            j * width,
//            i * height,
//            width,
//            height
//        );
//    }
//}
//     
//                puede servir este codigo tambien para el bg
//                void drawSpriteFrame(Image source, Graphics2D g2d, int x, int y,
//                     int columns, int frame, int width, int height)
//{
//    int frameX = (frame % columns) * width;
//    int frameY = (frame / columns) * height;
//    g2d.drawImage(source, x, y, x+width, y+height,
//                  frameX, frameY, frameX+width, frameY+height, this);
//}
//
//Toolkit tk = Toolkit.getDefaultToolkit();    
//Image pacman = tk.getImage(getURL("pacman.png"));
//...
//drawFrame(pacman, g2d, x, y, 15, 19, 25, 25);
    }// const 5

    /**
     * comstructor 6 este crea los sprites pasandole una imagen que contiene
     * todos los frames, especificandole el ancho y alto de los mismos, este
     * saca el numero de frames automaticamente
     * NOTA: la ruta de la imagen a poner debe de estar en un paquete llamado "res"
     *el valor seria el siguiente: Sprite(32,32,"/res/player.png", this );
     * @param frameWidth
     * @param imgRoute
     * @param frameHeight
     * @param <error>
     * @param ctx
     */
    public Sprite( int frameWidth, int frameHeight, String imgRoute ) {
        this();
        BufferedImage bigImg = null;
        try 
        {
            bigImg = ImageIO.read( this.getClass().getResource( imgRoute) );
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        int bmpRows = bigImg.getHeight() / frameHeight;
        int bmpCols = bigImg.getWidth() / frameWidth;

        if (bmpRows < 1) {
            bmpRows = 1;
        }
        if (bmpCols < 1) {
            bmpCols = 1;
        }

        //por ejemplo si 4 columnas y 5 renglones, son 20 frames
        int totalFrames = bmpRows * bmpCols;

        this.frames = new Image[totalFrames];

        int contx = 0, conty = 0;
        for (int xx = 0; xx < totalFrames; xx++) {
            this.frames[xx] = bigImg.getSubimage(frameWidth * contx,
                    frameHeight * conty, frameWidth, frameHeight);
            contx++;
            if (contx == bmpCols) {
                contx = 0;
                if (conty < bmpRows) {
                    conty++;
                }
                // while(conty<bmpRows)conty++;
            }

        }// for
        this.currentFrame = this.frames.length - 1;
        this.lastFrame = this.frames.length - 1;
        this.w = frameWidth;
        this.h = frameHeight;
    }// Const 6

    
    public Sprite( int frameWidth, int frameHeight, BufferedImage bufImg ) {
        this( );

        int bmpRows = bufImg.getHeight() / frameHeight;
        int bmpCols = bufImg.getWidth() / frameWidth;

        if (bmpRows < 1) {
            bmpRows = 1;
        }
        if (bmpCols < 1) {
            bmpCols = 1;
        }

        //por ejemplo si 4 columnas y 5 renglones, son 20 frames
        int totalFrames = bmpRows * bmpCols;

        this.frames = new Image[totalFrames];

        int contx = 0, conty = 0;
        for (int xx = 0; xx < totalFrames; xx++) {
            this.frames[xx] = bufImg.getSubimage(frameWidth * contx,
                    frameHeight * conty, frameWidth, frameHeight);
            contx++;
            if (contx == bmpCols) {
                contx = 0;
                if (conty < bmpRows) {
                    conty++;
                }
                // while(conty<bmpRows)conty++;
            }

        }// for
        this.currentFrame = this.frames.length - 1;
        this.lastFrame = this.frames.length - 1;
        this.w = frameWidth;
        this.h = frameHeight;
    }// Const 7

    
    
    /* 
     * /CONSTRUCTORES
     */
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
    }

    public float getH() {
        return h;
    }

    public void setH(float h) {
        this.h = h;
    }

    public float getGravity() {
        return gravity;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public float getFriction() {
        return friction;
    }

    public void setFriction(float friction) {
        this.friction = friction;
    }

    //FUNCIONES PARA SACAS LAS MEDICIONES DEL SPRITE,
    //DENTRO DE SUS COORDENADAS
    /**
     * el valor de la izquierda del sprite, por lo general la coordenada x
     *
     * @return
     */
    public float getLeft() {
        return x;
    }

    /**
     * el valor la coordenada x+width
     *
     * @return
     */
    public float getRight() {
        return this.x + this.w;
    }//regresar la derecha osea x mas su ancho

    /**
     * el valor de la coordenada y
     *
     * @return
     */
    public float getTop() {
        return this.y;
    }

    /**
     * el valor de la coordenada y+heigth
     *
     * @return
     */
    public float getBottom() {
        return this.y + this.h;
    }

    /**
     * el valor de la coordenada x+ width/2
     *
     * @return
     */
    public float getCenterX() {
        return this.x + (this.w / 2);
    }

    /**
     * el valor de la coordenada y+heigth/2
     *
     * @return
     */
    public float getCenterY() {
        return this.y + (this.h / 2);
    }

    /**
     * el valor de width/2 del sprite
     *
     * @return
     */
    public float getHalfWidth() {
        return this.w / 2;
    }

    /**
     * el valor de heigth/2 del sprite
     *
     * @return
     */
    public float getHalfHeight() {
        return this.h / 2;
    }

    /**
     * para saber si es visible el sprite
     *
     * @return
     */
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isAnimationEnd() {
        return animationEnd;
    }

    public void setAnimationEnd(boolean animationEnd) {
        this.animationEnd = animationEnd;
    }

    public int getImageSpeed() {
        return imageSpeed;
    }

    public void setImageSpeed(int imageSpeed) {
        this.imageSpeed = imageSpeed;
    }

    public Image[] getFrames() {
        return frames;
    }

    synchronized public void setFrames(Image[] frames) {
        this.frames = frames;
        this.currentFrame = frames.length - 1;
        this.lastFrame = frames.length - 1;
        this.w = frames[ FRAME_FIRSTFRAME ].getWidth(null);
        this.h = frames[ FRAME_FIRSTFRAME ].getHeight(null);
        
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    public int getLastFrame() {
        return lastFrame;
    }

    public void setLastFrame(int lastFrame) {
        this.lastFrame = lastFrame;
    }

    //_____________________//
    /**
     * funcion que regresa el numero total de frames del sprite
     *
     * @return
     */
    public int getNumberOfFrames() {
        return frames.length;
    }

    /**
     * funcon que regresa la imagen del siguiente frame del sprite, se usa
     * principalmente cuando se quiere dibujar la secuencia de imagenes
     *
     * @return
     */
    public Image getNextImageFrame() {
        currentFrame++;
        if (currentFrame > frames.length) {
            setAnimationEnd(true);
            currentFrame = FRAME_FIRSTFRAME;
        } else if (animationEnd) {
            setAnimationEnd(false);
        }
        return frames[currentFrame];
    }//

    /**
     * metodo que regresa la imagen actual ( frame ), en la que se encuentra
     * el sprite
     * @return 
     */
    public Image getCurrentImageFrame()
    {
    return frames[currentFrame];
    }
    
    
    /**
     * funcion que salca el porcentage en el que se encuentra el sprite a lo
     * ancho de la pantalla
     *
     * @param f
     * @param width
     * @return
     */
    public float setXPorcent(float f, int width) {
        return f * width;
    }

    /**
     * funcion que saca el porcentaje en el que se encuentra el sprite a lo alto
     * de la pantalla
     *
     * @param f
     * @param height
     * @return
     */
    public float setYPorcent(float f, int height) {
        return f * height;
    }

    public int getAnimationSpeed() {
        return animationSpeed;
    }

    public void setAnimationSpeed(int animationSpeed) {
        this.animationSpeed = animationSpeed;
    }

    public int getAnimationSpeedLimit() {
        return animationSpeedLimit;
    }

    public void setAnimationSpeedLimit(int animationSpeedLimit) {
        this.animationSpeedLimit = animationSpeedLimit;
    }

    public AnimationState getCurrentAnimationState() {
        return currentAnimationState;
    }

    public void setCurrentAnimationState(AnimationState currentState) {
        this.currentAnimationState = currentState;
    }

    public Map<AnimationState, int[]> getSubAnimationStack() {
        return subAnimationStack;
    }

    public void setSubAnimationStack(Map<AnimationState, int[]> SubAnimationStack) {
        this.subAnimationStack = SubAnimationStack;
    }


   
    
    
    

    /**
     * funcion que renderiza la imagen de sprite en el frame actual, si la
     * variable visible es falsa, entonces no se muestra la imagen, pero el
     * sprite seguira existiendo y posiblemente haciendo colisiones
     *
     * @param g2
     */
    public void draw(Graphics2D g2) {
        if (visible) {
            g2.drawImage(frames[currentFrame], (int) x, (int) y, null);
        }
    }//

    
    /**
     * this method is used when the sprite has several subanimations
     * @param g2 
     */
    public void drawSubanimation(Graphics2D g2, boolean stopAtEnd ) 
    {
        if (visible) 
        {
        
        g2.drawImage(frames[ subAnimationStack.get(currentAnimationState )[ subanimationCurrentPos ] ],
        (int) x, 
        (int) y,
        null);
            
            animationSpeed++;
        if( animationSpeed >= animationSpeedLimit )
        {     
            animationSpeed = 0;
    
            
            subanimationCurrentPos ++;
    
                //check whether the animation must begin again loop must
                if( subanimationCurrentPos > subanimationLastPos )
                {
                    
                    //if animationforward, when is the las frame
                    //gonna begin again
                    if( stopAtEnd )
                    {
                    subanimationCurrentPos = subanimationLastPos;
                    }
                    else
                    {
                        subanimationCurrentPos = 0;       
                    }
                    
                        
                }//
            
            
        }//if animationspeed
    
        
       
        
        }//if visible
        
    }//
    
    
    /**
     * metodo que cambialos frames del sprite, esta funcion es para que se utilice en el
     * metodo de update, no en el render, loop indica si la animacion se repite hacia adelante 
     * o al reverso
     * @param loop
     * @return 
     */
    public boolean updateAnimation(int loop)
    {
        //this method wont process anything if the sprite is not visible
        if( !visible )return false;
        
        animationSpeed++;
        if( animationSpeed >= animationSpeedLimit)
        {     
            animationSpeed=0;
            
        switch ( loop ) 
        {
			case 1:
				currentFrame++;
                                    if (currentFrame >= lastFrame)
                                    {
                                        currentFrame = FRAME_FIRSTFRAME;
                                        animationEnd = true;
                                    }
				break;
			case 2:
				currentFrame--;
				if (currentFrame <= FRAME_FIRSTFRAME)
                                    {
                                        currentFrame = lastFrame;
                                        animationEnd = false;
                                    }
				break;
                            default:
                                currentFrame++;
                                    if (currentFrame >= lastFrame)
                                    {
                                        currentFrame = FRAME_FIRSTFRAME;
                                        animationEnd = true;
                                    }
                                break;
        }
//                if(currentFrame != lastFrame)
//                { animationEnd = false; }
//                else
//                {animationEnd = true;}
        
        
        }//animspeed
        return animationEnd;
    }//
    
    
    /**
     * this function is to make the animation when the sprite have
     * implemented subanimationstack, this function goes in update method
     */
//    public void updateSubanimation()
//    {
//            animationSpeed++;
//        if( animationSpeed >= animationSpeedLimit )
//        {     
//            animationSpeed = 0;
//            
//            
////            this.animation
//            
//            if( currentFrame == endFrame )
//            {
//            currentFrame = iniFrame - 1;//less one to start fom initial frame
//            }
//            
//            currentFrame++;
//        }
//        
//    }//
    
    
    //-- falta otro metodo draw
    /**
     * funcion que regresa la imagen segun el frame especificado si el frame
     * especificado es menor o mayor al numero total de frames del sprite, este
     * regresa el primer frame
     *
     * return image at specified frame,if specified is more of maximum
     * or less that minimum first frame will be taken
     * @param frame
     * @return
     */
    public Image getImageFrame(int frame) 
    {
        if (frame < 0 || frame > frames.length - 1) {
            return frames[FRAME_FIRSTFRAME];
        }

        return frames[frame];
    }

    /**
     * funcion para detectar cuando el puntero esta por encima o dentro del area
     * del sprite funciona para eventos de mouse
     *
     * @param x
     * @param y
     * @return
     */
    public boolean isTouched(float x, float y) {
        return (x >= this.x && x <= this.x + this.w
                && y >= this.y && y <= this.y + this.h);
    }//is touched

    
    
    /**
     * this is to move the sprite depending on the
     * values for spdx and spdy
     */
    @Override
    public void move() 
    {
        move(speedX,speedY);
    }//
    
    
    /**
     * para mover el sprite sobre el eje X
     *to move the sprite along X axis
     * @param speedX
     */
    @Override
    public void moveSpeedX(float speedX) {
        x += speedX;
        
        if(roomBoundLeft != -1)setBoundLeft();
        if(roomBoundRight != -1)setBoundRight();
            
    }//movespeedx

    /**
     * para mover el sprite sobre el eje Y
     *
     * @param speedY
     */
    @Override
    public void moveSpeedY(float speedY) {
        y += speedY;
        
        if(roomBoundTop != -1)setBoundTop();
        if(roomBoundBottom != -1)setBoundBottom();
        
    }

    /**
     * para mover sobre el eje X e Y
     *
     * @param speedX
     * @param speedY
     */
    @Override
    public void move(float speedX, float speedY) {
        x += speedX;
        y += speedY;
        
        if(roomBoundLeft != -1)setBoundLeft();
        if(roomBoundRight != -1)setBoundRight();
        if(roomBoundTop != -1)setBoundTop();
        if(roomBoundBottom != -1)setBoundBottom();
        
    }//move

    /**
     * para posicionar el sprite sobre alguna coordenada X e Y en especifico,
     * este no es mover, aunque se puede usar para lo mismo
     *
     * @param x
     * @param y
     */
    @Override
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * moveTo(Sprite sprite, int speed) EXAMPLE: sprite.moveTo(enemy,5); 
     * function that set X and Y speed and degrees sprite direction,
     * this function wont move the sprite, this only set the speed values,
     * to move the sprite you have to call sprite.move() method on update loop.
     * NOTE: this can be used once to set direction and speed for a buller for example
     * @param sprite
     * @param speed
     *
     */
    @Override
    public void moveTo(Sprite sprite, int speed) {
//        if (speed > 0) {
//            float vx = sprite.getCenterX() - this.getCenterX();
//            float vy = sprite.getCenterY() - this.getCenterY();
//            float mag = (float) Math.sqrt((vx * vx) + (vy * vy));
//            float spdx = vx / mag; //saco la direccion de x
//            float spdy = vy / mag; //saco la direccion de y
//            this.setSpeedX(spdx * speed);
//            this.setSpeedY(spdy * speed);
//        }
        
        moveTo( sprite.getCenterX(), sprite.getCenterY(), speed);
    }

    /**
     * moveTo(float x, float y, int speed) EXAMPLE:
     * sprite.moveTo(100,100,5);<br />
     * function that set the speed in X and Y axis to move toward a specific
     * point
     *
     * @param x
     * @param y
     * @param speed
     *
     */
    @Override
    public void moveTo(float x, float y, int speed) {
        if (speed > 0) {
            float vx = x - this.getCenterX();
            float vy = y - this.getCenterY();
            float mag = (float) Math.sqrt((vx * vx) + (vy * vy));
            float spdx = vx / mag; //saco la direccion de x
            float spdy = vy / mag; //saco la direccion de y
            this.setSpeedX(spdx * speed);
            this.setSpeedY(spdy * speed);
        }
    }

    
    
    
    /**@TODO it doesnt work for scaled screen yet
     * calculate rotation angle from sprite center towards
     * X & Y, and set degrees variable with that value
     * this method can be called in update function, or
     * when click is pressed or released
     * @param x
     * @param y
     * @return 
     */
    public double calculateAngle( int x, int y )
{
        float vx = x - this.getX();
        float vy = y - this.getY();
        degrees =  Math.atan2( vy, vx ) * ( 180 / Math.PI ); // Math.atan2( y ,x );
  
//        System.out.println("angles: "+degrees );
    return degrees;
}
    
    
    /**@TODO it doesnt work for scaled screen yet
     * calculate rotation angle from sprite center towards
     * another sprite center, and set degrees variable 
     * with that value this method can be called in 
     * update function, or when click is pressed or released
     * @param spr
     * @return 
     */
    public double calculateAngle( Sprite spr )
{
    
    return calculateAngle( 
            (int)spr.getCenterX(),
            (int)spr.getCenterY() );
}
    
    
    /**
     * this function return the near sprite to this sprite
     * that is inside view port of the game
     * @return 
     */
    public Sprite spriteNearest( List<Sprite>spriteList, GameLevel level )
    {
        
        //if there is not list return nulll
        if( spriteList.isEmpty() )return null;
        
        
        List<Sprite> nearList = new ArrayList<>();
        
        //get all sprites inside gameView
        for( Sprite spr : spriteList )
        {
        
            if( spr.isInsideView(level) )
            {
            nearList.add(spr);
            }
            
            
        }//for
        
        
        
        int[] magList = new int[ spriteList.size() ];
        for( int i=0; i< spriteList.size() ;i+=1 )
        {
            
            Sprite spr = spriteList.get( i );
            
            float vx = spr.getCenterX() - this.getCenterX();
            float vy = spr.getCenterY() - this.getCenterY();
            float mag = (float) Math.sqrt((vx * vx) + (vy * vy));
		
	    magList[ i ] = (int)mag;
            
                
        }
        
        //getthing the min value Pos of the array
        int minValue = magList[0];
        int minValuePos = 0; 
        for ( int i = 1; i < magList.length; i++) 
        {
            if ( magList[i] < minValue ) 
            {
                minValue = magList[ i ];
                minValuePos = i;
            }
        }//    
        
     return spriteList.get( minValuePos );
    }
    
    
    /**
     * @param spriteList
     * @param level * @TODO quit al repeated code
     * this function return the near sprite to this sprite
     * that is inside view port of the game
     * @param range
     * @return 
     */
    public Sprite spriteNearest( List<Sprite>spriteList, GameLevel level, int range )
    {
        
        //if there is not list return nulll
        if( spriteList.isEmpty() )return null;
        
        
        List<Sprite> nearList = new ArrayList<>();
        
        //get all sprites inside gameView
        for( Sprite spr : spriteList )
        {
        
            if( spr.isInsideView(level) )
            {
            nearList.add(spr);
            }
            
            
        }//for
        
        
        
        int[] magList = new int[ spriteList.size() ];
        for( int i=0; i< spriteList.size() ;i+=1 )
        {
            
            Sprite spr = spriteList.get( i );
            
            float vx = spr.getCenterX() - this.getCenterX();
            float vy = spr.getCenterY() - this.getCenterY();
            float mag = (float) Math.sqrt((vx * vx) + (vy * vy));
		
            //if distance is less or equal than permited range
            //then put inside the array to check later
            if( mag <= range )
            {
                magList[ i ] = (int)mag;
            }
	    
        }
        
        
        //getthing the min value Pos of the array
        int minValue = magList[0];
        int minValuePos = 0; 
        for ( int i = 1; i < magList.length; i++) 
        {
            if ( magList[i] < minValue ) 
            {
                minValue = magList[ i ];
                minValuePos = i;
            }
        }//    
        
    
     return spriteList.get( minValuePos );
    }//
    
    
    public void orbit( Sprite spr, int radio )
    {
        
        this.x = spr.getCenterX() + ( (int)Math.cos( ( double) degrees ) * radio );
        this.y = spr.getCenterY() - ( (int)Math.sin( ( double) degrees ) * radio );
        
    }//
    
    /**
     * this function will change X and y position of this sprite to
     * orbit along centerx and centery, the distance will be the radio
     * @param centerX x acis of the center to orbit
     * @param centerY y acis of the center to orbit
     * @param angle angle to orbit
     * @param radio distance from the center to orbit
     */
    public void orbit( float centerX, float centerY, int angle, int radio )
    {
        
        
        System.out.println("cx -"+centerX+"  cy -"+centerY);
        
        this.x = centerX + ( (float)Math.cos( Math.toRadians( angle ) ) * radio );
        this.y = centerY - ( (float)Math.sin( Math.toRadians( angle ) ) * radio );
        //NOTE: if is centerY + will be clockwise, if is centerY - will be counter clockwise
        
        
    }//
    
    
    
    
    /**
     * to know if the sprite center is inside the view
     * NOTE: check if level has 0 cordinate in X and Y exis
     * @param level
     * @return true if the center sprite is inside the view , false otherwise
     
     */
    public  boolean isInsideView( GameLevel level )
    {
    return ( getCenterX() >= 0 && 
             getCenterX() <= ( 0 + level.getViewWidth() ) &&  
             getCenterY()>= 0 &&
             getCenterY() <= ( 0 + level.getViewHeight()) );
    
    }
    
    /**
     * checl wheter the sprite is outside the view, usually
     * to put out of the game, delete , etc.
     * NOTE: check if level has 0 cordinate in X and Y exis
     * @param level
     * @return 
     */
    public  boolean isOutsidePort( GameLevel level )
    {
        
        return ( getCenterX() < 0 ||
                 getCenterX() > level.getViewWidth() ||
                getCenterY() < 0 ||
                getCenterY() > level.getViewHeight() ); 
    
    }
        
    
    @Override
    public String toString() {
        return "Sprite{" + "x=" + x + ", y=" + y + ", w=" + w + ", h=" + h + ", speedX=" + speedX + ", speedY=" + speedY + ", gravity=" + gravity + ", friction=" + friction + ", visible=" + visible + ", animationEnd=" + animationEnd + ", imageSpeed=" + imageSpeed + ", frames=" + frames + ", currentFrame=" + currentFrame + ", lastFrame=" + lastFrame + '}';
    }

    public double getDegrees() {
        return degrees;
    }

    /**
     * with this method sprite degrees can be set and with that,
     * you can only use drawRotate from render method
     * @param degrees 
     */
    public void setDegrees(int degrees) {
        this.degrees = degrees;
    }

    public boolean isJump() {
        return jump;
    }

    
    /**
     * this make this sprite able to jump, this function is only to specify if the sprite 
     * is going to jump, there is another function called processjump and there is functionality
     * to change y axis of the sprite to make the jump, 
     * every time this function is called, jumpvalue is reseted to default jumpForce*=-1;
     * @param jump 
     */
    public void setJump(boolean jump) {
        this.jump = jump;
        //setJumpValue(jumpForce);
    }

    
    
    
    public float getJumpForce() {
        return jumpForce;
        
    }

    
    
    /**
     * this function set the jump force, the max limit that jumpvalue
     * will take every time jumvalue is decreased by the gravity
     * NOTE:  jumpforce =5, jumpvalue = -5, gravity 0.37
     * every frame: jumpvalue += gravity
     * if( jumpvalue >= jumpforce) jumpvalue = jumpforce
     * that is how it works!
     * @param jumpForce 
     */
    public void setJumpForce(float jumpForce) 
    {
        this.jumpForce = jumpForce;
        setJumpValue(jumpForce);
    }
    
    
    /**
     * this set the jump value to jumpforce * -1;
     * this way it will then go - y Axis ( up ) and then
     * + y Axis ( down)
     */
    public void setJumpValue(float jumpValue)
    {
    this.jumpValue = jumpForce * -1;
    }

    public float getJumpValue()
    {return this.jumpValue;}
    
    
//    public boolean isExecuteJump() {
//        return executeJump;
//    }
//
//    public void setExecuteJump(boolean executeJump) {
//        this.executeJump = executeJump;
//    }

    public int getRoomBoundLeft() {
        return roomBoundLeft;
    }

    public void setRoomBoundLeft(int roomBoundLeft) {
        this.roomBoundLeft = roomBoundLeft;
    }

    public int getRoomBoundRight() {
        return roomBoundRight;
    }

    public void setRoomBoundRight(int roomBoundRight) {
        this.roomBoundRight = roomBoundRight;
    }

    public int getRoomBoundTop() {
        return roomBoundTop;
    }

    public void setRoomBoundTop(int roomBoundTop) {
        this.roomBoundTop = roomBoundTop;
    }

    public int getRoomBoundBottom() {
        return roomBoundBottom;
    }

    public void setRoomBoundBottom(int roomBoundBottom) {
        this.roomBoundBottom = roomBoundBottom;
    }

    public int getSpriteId() {
        return spriteId;
    }

    /**
     * we cannot set manually this sprite ID
     * @return 
     */
//    public static void setSpriteId(int spriteId) {
//        Sprite.spriteId = spriteId;
//    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    
    
    
    
    /**
     * funcion que establece los frames inicial y final de la subanimacion 
     * que se quiere mostrar
     * @param iniFrame
     * @param endFrame
     */
//    public void setSubanimation(int iniFrame, int endFrame)
//    {
//        if(this.iniFrame == iniFrame && this.endFrame == endFrame)
//          return;
        
//        this.iniFrame=iniFrame;
//        this.endFrame=endFrame;
        
        
//        
//        this.currentFrame=iniFrame;
//        this.lastFrame=endFrame;
//        
//    }//

//    /**
//     * metodo que toma un arreglo de enteros como subanimacion
//     * 
//     * this method takes and array of in like subanimation
//     * @param subanimation 
//     */
//        public void setSubanimation(int [] subanimation)
//    {
//        setSubanimation(subanimation[0], subanimation[ subanimation.length-1 ]);
//    }//
        
/**
 * metodo que establece la subanimacion por medio de un estado de animacion,
 * este estado debe de estar cargado en el stack de subanimacion
 * NOTE: si se implementa subanimacion, se debe de usar el metodo 
 * drawSubanimation()
 * 
 * 
 * @param subanimation 
 */        
     public void setSubanimation(AnimationState subanimation)
    {
        if(subAnimationStack == null)
           return;

        
        currentAnimationState = subanimation;
        
        subanimationCurrentPos= 0;
        //@TODO aqui puede haber algun pedo
        subanimationLastPos = subAnimationStack.get( subanimation ).length - 1;
        
        
//        setSubanimation( subAnimationStack.get(subanimation) );
    }//
    
        
        
        
    
    /**
     * renderiza al sprite rotado segun los grados establecidos
     * 
     * render the sprite rotated the specified degrees
     * NOTE: this function goes in render
     * @param g2
     * @param degrees
     */
    public void drawRotate(Graphics2D g2, int degrees)
    {
        if(visible)
        {
             AffineTransform oldTransform =  g2.getTransform();
             //Graphics2D g2d = (Graphics2D)g;
             g2.translate( x , y );
             AffineTransform trans = new AffineTransform();
             trans.setToIdentity();
             
            //trans.setTransform(identity);
             trans.rotate( Math.toRadians(degrees), this.getHalfWidth()  , this.getHalfHeight() );
             g2.drawImage(frames[currentFrame], trans, null);
             
            g2.setTransform(oldTransform);
        
        }
          
    }//
  
    
      /**
     * renderiza al sprite rotado segun los grados establecidos
     * 
     * render the sprite rotated , to make this work build in 
     * degrees variable must be set each step
     * NOTE: this function goes in render
     * @param g2
     */
    public void drawRotate( Graphics2D g2 )
    {
        if(visible)
        {
             AffineTransform oldTransform =  g2.getTransform();
        
             g2.translate( x,y );
             AffineTransform trans = new AffineTransform();
             trans.setToIdentity();
             trans.rotate( Math.toRadians( this.degrees ) , this.getHalfWidth()  , this.getHalfHeight()  );
             g2.drawImage( frames[ currentFrame ] , trans, null );
             
             g2.setTransform(oldTransform);
         
        }
            
    }// 
    
    
    
    
    /**
     * this method process the jump ( y position ) of the player
     * when it jump
     */
    public void processJump()
    {
//        if( jump )
//        {
        
            //este valor define que tan largo brinca
            //jumpvalue means how higher the player jumps
            //every time gravity is increased and eventually
            //make jumpValue goes downward
            jumpValue += gravity; 
            
            //every tick the jump get a limit
                if( jumpValue >= jumpForce )
                {
                    jumpValue = jumpForce;
                }
            
//            System.out.println( y+"  jumpvalue "+jumpValue);    
            y += jumpValue;
            
//        }//
    
    }//
    
    
    
    /**
     * this function show a trayectory of a ballistic object thrown away
     * this must be on renderXXX function
     * NOTE: needs more testing
     * @param g2
     * @param potency
     * @param degrees
     * @param numberOfPoints this is the number of point that will be shown
     * by this function
     * 
     * 
     */
    public void drawBalisticTrayectory( 
            Graphics2D g2, 
            float potency, 
            int degrees,
            int numberOfPoints)
    {
    
        //potency
        float v0 = potency;
        
        float v0x =  ( (float)Math.cos( ( double) degrees )  * v0 );
        float v0y = - ( (float)Math.sin( ( double) degrees ) * v0 );
        
//        System.out.println(" == vx "+v0x+" vy "+v0y);
        
        //initial position where point must start
//        float x0 = ;
//        float y0 = ;
            
        float g = 9.81f;
        
//        for( int t = 0; t < 30; t+=1 )// 30/v0 )
        for( float t = 0; 
             t < numberOfPoints; 
            t+=1 ) //t =  (t + numberOfPoints/v0) )
        {
        
            
//            position of x e y along time
            float xi = v0x * t + this.getCenterX();
            float yi = 0.5f * g * (float)Math.pow( t, 2 ) + v0y * t +this.getCenterY();
            
            System.out.println(" >>> vx "+xi+" vy "+yi);
            
//            g2.setColor( Color.BLACK );
//            g2.drawOval( (int)xi, (int)yi, 4, 0);
            g2.setColor( Color.GREEN );
            g2.drawOval( (int)xi, (int)yi, 4, 0);
            
        }//
                
        
    }//processBalistic
 
    
    /**
     * checa si el sprite se va a salir por el limite izquierdo y no lo deja
     */
    private void setBoundLeft()
    {
      //no se permite salir del bound izquierdo del room
           if(getX() <= roomBoundLeft)
           {
           setX( roomBoundLeft );
           }
    }
    
     /**
     * checa si el sprite se va a salir por el limite derecho y no lo deja
     */
    private void setBoundRight()
    {
     //no se permite salir del bound derecho del room
           if(getX()+getW() >= roomBoundRight)
           {
           setX(  roomBoundRight - getW() );
           }
    }
    
     /**
     * checa si el sprite se va a salir por el limite de la cima y no lo deja
     */
    private void setBoundTop()
    {
    //no se permite salir del bound de la cima del room
           if(getY() <= roomBoundTop)
           {
           setY( roomBoundTop );
           }
    }//
    
     /**
     * checa si el sprite se va a salir por el limite del fondo y no lo deja
     */
     private void setBoundBottom()
    {
     //no se permite salir del bound derecho del room
           if(getY()+getH() >= roomBoundBottom)
           {
           setY(  roomBoundBottom - getH() );
           }
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Point getPivot() {
        return pivot;
    }

    public void setPivot(Point pivot) {
        this.pivot = pivot;
    }

    public Point getAnchor() {
        return anchor;
    }

    public void setAnchor(Point anchor) {
        this.anchor = anchor;
    }

    
    
     
}//class
