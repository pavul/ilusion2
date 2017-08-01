
package com.ilusion2.gamemanager;


import com.ilusion2.audio.Sound;
import com.ilusion2.control.GpioGameControl;
import com.ilusion2.exception.EmptyLevelStackException;
import com.ilusion2.level.GameLevel;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Map;
import java.util.logging.Logger;
import com.ilusion2.net.ClientSocket;
import com.ilusion2.net.Server;
import java.awt.Dimension;
import java.awt.Toolkit;


/**
 * clase que es la escena o cuarto donde hay fondos, enemigos, jugadores, puntajes, etc.
 * este viene siendo el canvas
 * 
 * this class manages all levels of the game and is the class that
 * handle the process under the hood
 * 
 * @author pavulzavala
 */
public class GameManager extends Canvas implements 
        Runnable
{
    private static final long serialVersionUID = -6489796311509601114L;
    
    /**
     * graphics object for rendering
     */
    public Graphics g = null;
    
    //numeros que va a tener el buffer
    protected final int BUFFER_NUMBER = 3;
    
    /**
     *
     thread principal del juego
     principal thread of the game
     */
    protected Thread gameThread;
    
    
    /**
     *variable indica si el juego esta corriendo o no
     indicate if the game is running or not 
     */
    protected boolean running;
    
   /**
    this flag indicate when a room is loaded to 
    show it in full screen mode
    */
    protected boolean fullScreen = false;
    
    //variable para pausar el juego
    //this variable is a flag that indicate if the game is paued
    protected boolean pause;
    
    //variable que sirve para contar cuantos frames por segundo hay en la aplicacion
   //we store FPS count
    protected int frames; //contador de FPS
   
    protected int offsetX;  //x_ofset del room para que se renderize el area del dibujo
   
   
   //these variables are use to fit the game view in the device screen
    //if is gonna be player on full screen
    protected double xScale = 1;
    protected double yScale = 1;
    
   
//   protected GameState gameState; //estado del juego
   
//   this variable saves the reference to current level that we can
//   use when we wan to change lvl or use tome util methods
    /**
     * this contains the reference to the current level 
     * that is rendered
     */
   protected GameLevel currentLevel;
   
   //esta estructura se utiliza para guardar aqui todos los objetos que
   //necesitan estar en todo el juego, pueden ser datos como el usuario
   //, puntajes, upgrades, etc/
   /**
    *this structure is used to store all data that is persistent
    between levels, like, score, upgrades, items, general HUD, etc, because each
    level can set new ammount of data when it starts
    */
   protected Map< String, ? >persistentData;
   
   
   //stack que mantiene todos los niveles
   /**
    * this is the stack where we have all levels for the game
    */
   Map<String, GameLevel> levelStack;
   
   /**
    * this variable is to stablish first level to load for default
    */
   String firsLvlToLoad;
   
   
   //objeto para hacer las transiciones del room
   /**
    * this transition object is to show transition animations
    * from one level to another
    */
   protected Transition transition;
    
   //para saber los frames count
   //these variables are used for testing purposes, to know
   //the ammount of frames counted each cicle
   /**
    * this is to now frame count, this variable
    * can be also shown in HUD data for testing purposes 
    */
   protected int frameCount = 1; //30 fps
   int count;//
   int fps = 60;
   
   //variables para cliente servidor
   //these variables are only useful if we are using
   //server/client architecture, i mean, an online game
   //@TODO still need a lot of work
   /**
    * if this game is for multiplayer, then this will create
    * the server socket
    */
   Server gameServer;
   
   /**
    * client socket in case this game need to connect to a multiplayer
    * game
    */
   ClientSocket gameClient;
   
   /**
    * indica si el room va a fungir como servidor, es decir
    * no procesara interfaz grafica
    * 
    *true: indicate if this game is an online game and is the server
    * false: by default and indicate this game is client that connect
    * to a server, in case of online game
    */
   private boolean serverApplication; 
   
   
    /**
     * constructor 1 este constructor crea un room por default con
     * width 640 y heigth 480, se toma la referencia de la ventana que se ve
     * para poder asi cambiar de rooms
     * 
     * creates a room with width = 640, heigth = 480, fps = 60
     * by default and it accept a list of levels
     * 
     * @param lvltoLoad primer nivel a cargar
     * @param levelStack listado de niveles que hay en el juego
     */
    public GameManager(String lvltoLoad, Map<String, 
            GameLevel>levelStack, boolean fullScreen) 
           throws EmptyLevelStackException 
    {
        fps = 60;//setea por default a 60 frames por segundo
        
        this.fullScreen = fullScreen;
        
        if( null == levelStack )
        {
            System.out.println("::: level stack is null");
            throw new NullPointerException( "Level Stack cannot be null" );
        }
        if( levelStack.isEmpty() )
            throw new EmptyLevelStackException( "Level Stack should have at least one level" );
        
        
        this.levelStack = levelStack;
        
        //if there is no current level it will take the first of the stack
        //this.currentLevel = levelStack.get( lvltoLoad );
         firsLvlToLoad = lvltoLoad;
         loadLvl( firsLvlToLoad );
    }//
    
 
    
    /**
     * this constructor accept the game level that is gonna be
     * loaded/shown
     * @param gamelevel 
     */
    public GameManager( GameLevel gamelevel , boolean fullScreen )
    {
    
        fps = 60;//setea por default a 60 frames por segundo
        this.fullScreen = fullScreen;
         
        this.fullScreen = fullScreen;
        
         loadLvl( gamelevel );
        
    }//
    
    
    /**
     * constructor 2 este constructor crea un room por default con
     * width 640 y heigth = width /12 * 9, se toma la referencia de la ventana que se ve
     * para poder asi cambiar de rooms
     * @param window 
     * @param width 
     */
//    public Room(int width)
//    {
////        this.window=window;
//        this();
//    this.roomWidth=width;
//    this.roomHeight = roomWidth / 12 * 9;
//    }//
    
    /**
     * constructor 1 este constructor crea un room por default con
     * width y heigth definidos por el usuario, se toma la referencia de la ventana que se ve
     * para poder asi cambiar de rooms
     * @param window 
     * @param width 
     * @param height 
     */
//    public Room(int width, int height)
//    {
//        this(width);
//        this.roomHeight = height;
//    }//
    
    
    //este debe de tener el view y el offset
    
    /**
     * inicia el thread del juego
     * 
     * start the thread of the game
     */
    public final synchronized void gameStart() 
    {
            //nivel a cargar por default, este se establece en el constructor 
            //loadLvl( firsLvlToLoad );
            running = true;  
            gameThread = new Thread( this );
            gameThread.start();       
    }//start
    
    
    /**
     * detiene y termina el thread del juego
     * 
     * stop the thread of the game
     * wachout with this method
     */
    public final synchronized void GameStop()
    {
        try
        {
            running = false;
            gameThread.join( );
        }
        catch(Exception e)
        {}
    }//stop
     
    
    /**
     * this method calls all update process of levels, 
     * i mean, inside this method the update of the logic
     * of the game will be taken, at the same time updates
     * for logic and render of the game will be taken
     */
    public final synchronized void update()
    {   
        frameCount++;
        if( frameCount == fps )frameCount = 0;
 
       //aqui se hace el update del level
       currentLevel.update();
    }//
    
    
    /**
     * funcion que renderiza todo el game play, esta es la funcion mas importante,
     * pues es la que tiene la interaccion del usuario con el juego
     * 
     * this function call render methods of al levels
     * 
     */
    public final synchronized void render()
    {
        //si el room es de un servidor, no se muestra interfaz grafica
        if(serverApplication) return;
           
        
        if( this.getGraphicsBufferStrategy() == null )
            return;
        
                //se obtienen los graficos
                //Graphics 
                g = this.getGraphicsBufferStrategy();

           
                if(g != null)
                {
                   try
                   {
                      currentLevel.render( (Graphics2D) g );  
                   }
                   catch(Exception e)
                   {  
                   e.printStackTrace();
                   }

                this.closeGraphicsBufferStrategy( (Graphics2D) g, this.getBufferStrategy());

                }//if !=null
        
    }//renderGameState

//    public void renderPause()
//    {
//    
//        Graphics g = this.getGraphicsBufferStrategy();
//        
//        if(g != null)
//        {
//        g.fillRect(0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
//        
////        
//        g.setColor(Color.white);
////        g.drawRoundRect(10, 10, Config.SCREEN_WIDTH-20, Config.SCREEN_HEIGHT - 20, 10, 10);
////        
////        g.setColor(Color.BLUE);
////        g.fillRoundRect(10, 10, Config.SCREEN_WIDTH-20, Config.SCREEN_HEIGHT - 20, 10, 10);
////        
//        
////        g.setColor(Util.getRandomColor());
//        g.drawString(" PAUSE ", Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT / 2);
//        }
//        
//        this.closeGraphicsBufferStrategy(g, this.getBufferStrategy());
//        
//    }//render pause

//    public GameState getGameState() {
//        return gameState;
//    }
//
//    public void setGameState(GameState gameState) {
//        this.gameState = gameState;
//    }
    
    
    
    /**
     * funcion que renderiza lo que sucede en las pantallas de cargado de 
     * recursos (LOAD SCREEN)
     */
//    public void renderLoadState()
//    {
//    Graphics g = this.getGraphicsBufferStrategy();
//    
//    this.drawBgColor((Graphics2D)g, Color.black);
//    g.setColor(Color.white);
//    g.drawString("Cargando..." , roomWidth/2, roomHeight/2);
//    
//        this.closeGraphicsBufferStrategy(g, this.getBufferStrategy());
//    }//
//    
    
/**
 * here is where the main process of the threas is taken
 */
    @Override
    public final void run() 
    {
     
    long lastTime= System.nanoTime();
    double amountOfTicks=60.0;
    double ns= 1000000000 / amountOfTicks;
    double delta=0;
    long timer= System.currentTimeMillis();
//    int frames=0;
    while(running)
    {
    
        if(!pause)
        {
        
                long now= System.nanoTime();
                delta+=(now - lastTime)/ns;
                lastTime = now;
                while(delta >= 1)
                {
                    
                update();
                //render();
                delta--;
                
                }
                
                if(running)
                    render();
                
                //codigo para checar los frames
                frames++;
                if(System.currentTimeMillis() - timer > 1000)
                {
                timer +=1000;
                //System.out.println("FPS: "+frames);
                frames=0; 
                }
             
                
        }//pause
//        else
//        {
//        renderPause();
//        }
               
    }//uail
        
        
    }//run

   
    /**
     * funcion que muestra los frames por segundo del juego
     * 
     * an auxiliar function to show some data of FPS that are
     * processed
     */
    public final void showFPS(Graphics2D g2)
    {
        
//    g2.setColor(Color.yellow);
//    g2.drawString("FPS: "+frames, cam.getOffsetX() + 10, 10);
//    g2.drawString("offsetX: "+cam.getOffsetX(), cam.getOffsetX() + 10, 30);
//    g2.drawString("offsetX+WIDTH: "+(cam.getOffsetX()+cam.getViewXPort()), cam.getOffsetX() + 10, 50);
//    
//    g2.drawString("offsetX+213: "+(cam.offsetX+213), cam.getOffsetX() + 10, 70);
//    g2.drawString("offsetX+426: "+(cam.offsetX+426), cam.getOffsetX() + 10, 90);
//    g2.drawString("playerX: "+(player.getX()), cam.getOffsetX() + 10, 110);
//    g2.drawString("playerY: "+(player.getY()), cam.getOffsetX() + 10, 130);
    
    }
    
    /**
     * funcion auxiliar para poner en los render de los estados de juego,
     * regresa el objeto graphics
     * @return 
     */
    protected final Graphics getGraphicsBufferStrategy()
    {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null)
        {
            this.createBufferStrategy(BUFFER_NUMBER);
            return null;
        }
         Graphics g = bs.getDrawGraphics();   
         return g;
         
    }//
    
    /**
     * funcion que cierra y libera los recursos de los objetos Graphics y
     * BufferStrategy, que se usan en los render
     * @param g
     * @param bs 
     */
    protected final void closeGraphicsBufferStrategy(Graphics g, BufferStrategy bs)
    {
        g.dispose();
        bs.show();
    }

//    @Override
//    public void keyTyped(KeyEvent e) 
//    {
//        
//    }//

//    @Override
//    public void keyPressed(KeyEvent e) {
        
//        int key=e.getKeyCode();
        
//        mapeo de controles
//        if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A)
//        {moveLeft=true;}
//        else if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D)
//        {moveRight=true;}
//        else if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W)
//        {moveUp=true;}
//        else if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S)
//        {moveDown=true;}
//        
//         if(key == KeyEvent.VK_X || key == KeyEvent.VK_K)
//        {
//          jumpBtn=true;    
//        }
    
//    }//

//    @Override
//    public void keyReleased(KeyEvent e) {
       
//        int key=e.getKeyCode();
//        
////        mapeo de controles
//        if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A)
//        {moveLeft=false;}
//        else if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D)
//        {moveRight=false;}
//        else if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W)
//        {moveUp=false;}
//        else if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S)
//        {moveDown=false;}
//        else if(key == KeyEvent.VK_P)
//        {pause= !pause;}
//    
//        
//        if(key == KeyEvent.VK_X || key == KeyEvent.VK_K)
//        {
//          jumpBtn=false;    
//        }
//        
//        if(key == KeyEvent.VK_O )
//        {
////            if(transition != null)
////            {
////                transition.setOn( !transition.isOn() );
////                if(transition.getCurrentState() == Transition.STATE_HIDE)
////                {transition.setCurrentState(Transition.STATE_SHOWING);}
////            }
//          
////            changeLvl();
//        }
        
        
//    }

   
    
    
      /**
       * metodo que sirve para cambiar el nivel o la pantalla, cambia el fondo
       * y vuelve a crear el pool de enemigos, se pueden setear valores al jugador
       * o del puntaje o HUD
       * 
       * this functions is very important, is used when a player want to go
       * from certain level to another, then the current level is then eraded
       * from memory and the listeners attached to it, too
       * 
       * @param levelToLoad
       * @return 
       * @deprecated used until examples are done with new method
       */
      public synchronized boolean loadLvl( String levelToLoad )
      {
          //first we check if the level to load is already loaded
//          if( currentLevel.equals( levelStack.get( levelToLoad )  )  )
//          {
//              System.out.println(" ::: regreso false, el nivel ya esta cargado ");
//              return false;
//          
//          }
          
//          if( null == levelStack.get( levelToLoad ) )
//          {
//          levelStack.get( levelToLoad ) = new MazeXample( 480, 320, 480, 320, null);
//          }
//          
          
          //savig current level in previous level
          GameLevel previousLevel = currentLevel;
          
          //se establece el nuevo nivel actual
          //current level now has the new reference
          currentLevel =  levelStack.get( levelToLoad );
          
          //se establece el keyListener
          //ading keylisterner to curlevel
          currentLevel.addKeyListener( this );
          
          //ading mouselisterner to curlevel
          currentLevel.addMouseListener( this );
          
          //currentLevel now has this room
          currentLevel.setRoom( this );
          
          if( fullScreen )
              setFullScreen();
          
          
            if(previousLevel != null)
          {
          //si hay nivel se elimina y se pone a null para liberar recursos 
          //if there is level we delete it and remove the listeners to free
          //resources
//          previousLevel.disposeLevel();
          previousLevel.removeKeyListener( this );
          previousLevel.removeMouseListener( this );
          
          //se quitan los sonidos de fondo del nivel
          //currentLevel.getMp3Player().pause();
          //currentLevel.getMp3Player().removeAll();
          
//          currentLevel = null;
          }// !=null
          
          try
          {
              //if level is not marked as persistent
              //then it will init all again ( start over again the level )
              if( !currentLevel.isPersistent() )
              {
                   currentLevel.init();
              }
//              gameState = GameState.PLAYING;
              return true;
          }
          catch(Exception e)
          {
              return false;
          }
          
      }//load level
      
      
      /**
       * this function is used to load a new level
       * @param gamelevel
       * @return 
       */
       public synchronized boolean loadLvl( GameLevel gamelevel )
      {
         
          //save in another place the level that gonna change
         //and remove listeners
         GameLevel previousLevel = currentLevel;
         
          
          /**
           * if is the first time to load the game level, then
           * this current level will be null
           */
          if( null != currentLevel )
          {
            //if current level != null means this is not
            //the first level to load
            currentLevel.setGameState( GameState.LOADING );
            
            
            //if current level != null means this is not the 
            //the first level to load, so it can remove listeners
            previousLevel.removeKeyListener( this );
            previousLevel.removeMouseListener( this );
            
          }//
          
         
          
        //current level now has the level must show
        currentLevel = gamelevel;
        currentLevel.addKeyListener( this );
        currentLevel.addMouseListener( this );
        
        //currentLevel now has this room
        currentLevel.setRoom( this );
          
        if( fullScreen )
           setFullScreen();
        
        
        
        //if not persisten init the level again
        if( !currentLevel.isPersistent() )
        {
            //this automatically changes gameState
            //the state to PLAYING
            currentLevel.init();
            
        }//
        
        previousLevel = null;
        
          return true;
      }//loadlevel2
      
       
       /**
        * THIS IS USED TO IMPLEMENT YOUR OWN WAY TO CHANGE FROM
        * ONE LEVEL TO ANOTHER...
        * this method help to game manager set the required level, 
        * but with special implementacion,by if the level to load
        * have diferent settings, etc
        * @param levelChanger
        * @return 
        */
       public synchronized boolean loadLvl( LevelChanger levelChanger )
       {
       return levelChanger.setLvl();
       }
       
       
      /**
       * funcion que inicia el servidor del juego, este es un socket
       * 
       * inits this gamemanager as a server for an online game
       * 
       * @param port 
       */
      public void setGameServer(int port)
      {
        try {
            gameServer = new Server(port);
        } catch (IOException ex) {
            Logger.getLogger(GameManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
      }//
      
      
      public Server getGameServer()
      {
          return gameServer;
      }
      
      /**
       * funcion que establece o instancia el socket cliente que se conecta al
       * servidor
       * 
       * init the game as a client in case this game is online
       * 
       * @param port
       * @param ip 
       */
      public void setGameClient(int port, String ip)
      {
        try {
            gameClient = new ClientSocket(new Socket(ip, port));
        }
        catch(ConnectException conex)
        {
        //excepcion cuando se corre el cliente sin que este prendido el servidor
            
        } 
        catch (IOException ex) {
            Logger.getLogger(GameManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
      }//
      
      
      public ClientSocket getGameClient()
      {
          return gameClient;
      }//getgameclient
      
      
       public Map<String, GameLevel> getLevelStack() {
        return levelStack;
    }

    public void setLevelStack(Map<String, GameLevel> levelStack) {
        this.levelStack = levelStack;
    }

    /**
     * establece los FPS a los que corre el juego
     * @return 
     */
    public int getFps() {
        return fps;
    }

    /**
     * regresa el valor de los FPS a los que corre el juego
     * @param fps
     */
    public void setFps(int fps) {
        this.fps = fps;
    }

    public boolean isServerApplication() {
        return serverApplication;
    }

    /**
     * si true, indica que esta es una aplicacion de servidor, por enede
     * no se muestra la interfaz grafica
     * @param serverApplication 
     */
    public void setServerApplication(boolean serverApplication) {
        this.serverApplication = serverApplication;
    }

   
    
    /**
     * this function make the game fit the screen device, actualy is gonna 
     * scale the game keeping the aspect ratio, for example:
     * game view 480 x 320 (aspect ratio 1.5) 
     * its gonna be scaled to 1350 x 900 ( aspect ratio 1.777)
     * the xScale = 2.8125 and for yScale = 2.8125
     * NOTE: this function have to be called 
     */
    private void setFullScreen()
    {
    
        Dimension windowSize = getScaledWindowSize();
        
    //finally we set the scale to the level
    currentLevel.setGraphicScale(  
            windowSize.getWidth() / currentLevel.getViewWidth(), 
            windowSize.getHeight() / currentLevel.getViewHeight() );
    
    }//seFullScreen

    /**
    * this method reset xScale and yScale to 1, so the 
    * game will be shown with the normal size
    */    
    private void setNormalScreenMode()
    {
    xScale = 1;
    yScale = 1;
    }//

    public void setFullScreen(boolean atFullScreen) 
    {
        this.fullScreen = atFullScreen;
    }//
    
    
    
    
    
    /**
     * this method return a dimension object with the width and heigth
     * of the game at full screen.
     * NOTE: use this function afther set atFullScreen to true
     * @return 
     */
    public Dimension getScaledWindowSize()
    {
    
    //get Screen size to scale the game
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    
    /**
     * get screen size if fullscreen is true
     */
    if( fullScreen )
    {
    
        //we get current device width and heigth
        double deviceWidth = screenSize.getWidth();
        double deviceHeight = screenSize.getHeight();

        //System.out.println( "valores del device: "+deviceWidth +" - "+deviceHeight );

        //then we get the new width that must have the game,
        //keeping the aspect ratio
        float aspectRatio = (float)currentLevel.getViewWidth() / (float)currentLevel.getViewHeight();

        double fixedWidth = deviceHeight * ( aspectRatio  );

        screenSize.setSize( fixedWidth, deviceHeight );
        
    }//
    else
    {
        screenSize.setSize( this.getWidth() , this.getHeight() );
    }
  
    
    return screenSize;
    }//
    
    
    /**
     * this method return the view port width of the level
     * ( width of the portion of the screen that must be show )
     * @return 
     */
    @Override
    public int getWidth()
    {
    return currentLevel.getViewWidth();   
    }
    
    
    /**
     * this method return the view port heigth of the level
     * ( heigth of the portion of the screen that must be show ) 
     * @return 
     */
    @Override
    public int getHeight()
    {
    return currentLevel.getViewHeight();
    }
    
    
}//class

