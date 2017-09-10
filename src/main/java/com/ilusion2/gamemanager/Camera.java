/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilusion2.gamemanager;

/**
 * clase que crea una camara que renderiza el room a lo largo de una ventana de puerto
 * portview
 * 
 * this class create a camera who render certain part of the level, if the level
 * is bigger than portview
 * 
 * @author pavulzavala
 */
public class Camera implements CameraMovement
{
    
    /**
     * NOTA: para ver que se mueve la escena a la derecha, se debe de mover la camara en X --
     * para ver que se mueva la escena a la izquierda, se debe de mover la camara en Y
     */
    
    /**
     * x axis position of cammera
     */
    int camx; //posicion x en que se encuentra la camara
    
    /**
     * y axis position of cammera
     */
    int camy; //posicion y en que se encuentra la camara
    
    /**
     * minimum value alowed for camera on X axis
     * expected value 0
     */
    int camxMin; //posicion minima que tiene la camara, por lo general 0 en eje x
    
    /**
     * maximum value alowed for camera on X axis
     * expected value roomWidth
     */
    int camxMax; //posicion maxima para mover la camara -roomWidth + viewxport
    
    /**
     * minimum value alowed for camera on Y axis
     * expected value 0
     */
    int camyMin; //posicion minima que tiene la camara, por lo general 0 en eje y
    
    /**
     * maximum value alowed for camera on Y axis
     * value expected roomHeigth
     */
    int camyMax; //posicion maxima para mover la camara -roomHeigth +viewyport
    
    /**
     * a room can be huge, but we arent show everything, to show a part of the 
     * room there will be viewport
     */
    int roomWidth; //el ancho del room del nivel este puede ser de muchos pixeles de ancho
    int roomHeigth; //el alto del room del nivel este puede ser de muchos pizeles de alto
    
    /**
     * this is the width of port view ( viewWidth )
     */
    float viewXPort; //el ancho de porcion de pantalla que se vera del nivel
    
    /**
     * this is the heigth of the port view ( viewHeigth )
     */
    float viewYPort; //el alto de porcion de pantalla que se vera del nivel
    
    /**
     * this is a value that will be in the same place always on the viewport, 
     * if some level is implementing camera and want to show HUD, that
     * hud must take offsetX and offsetY to show that HUD always in the same place
     */
    int offsetX; //posicion estatica que siempre esta en el view de la ventana
    // es decir, si se pone un texto en x = offsetX, siempre se vera en esa posicion
   
    /**
     * this is a value that will be in the same place always on the viewport, 
     * if some level is implementing camera and want to show HUD, that
     * hud must take offsetX and offsetY to show that HUD always in the same place
     */
    int offsetY;//lo mismo que offsetX, pero para el eje Y
    
    //margenes para que cuando pasa el personaje se muevan la camara de la pantalla y
    //muestre mas espacio del room, por default estan inactivos con el valor -1
    
    /**
     * left margin to check if the player is reaching that to move the camera to left
     */
    float marginLeft= -1; 
    
    /**
     * right margin to check if the player is reaching that to move the camera to right
     */
    float marginRight= -1;
    
    /**
     * top margin to check if the player is reaching that to move the camera to top
     */
    float marginTop= -1;
    
    /**
     * bottom margin to check if the player is reaching that to move the camera to bottom
     */
    float marginBottom= -1;
            
    
    /**
     * constructor 1, 
     * creates a camera defining the width and heigth values of the room ( these values 
     * are the boundaries of the camera ),
     * and the portion that has to show in the screen ( viewXPort, viewYPort )
     * 
     * create a camera object definig the size for the room and the size for 
     * the view port to show in the screen
     * 
     * @param roomWidth
     * @param roomHeight
     * @param viewXPort
     * @param viewYPort
     */
    public Camera(int roomWidth, int roomHeight, int viewXPort,int viewYPort)
    {
        
        this.roomWidth = roomWidth;
        this.roomHeigth = roomHeight;
        this.viewXPort = viewXPort;
        this.viewYPort = viewYPort;
        
        this.camxMin = 0;
        this.camxMax = -roomWidth + viewXPort;
        this.offsetX = camx;
        this.camyMin = 0;
        this.camyMax = -roomHeight + viewYPort;
        this.offsetY = camy;
        
    }//const1

    public int getCamx() {
        return camx;
    }

    public void setCamx(int camx) {
        this.camx = camx;
    }

    public int getCamy() {
        return camy;
    }

    public void setCamy(int camy) {
        this.camy = camy;
    }

    public int getCamxMin() {
        return camxMin;
    }

    public void setCamxMin(int camxMin) {
        this.camxMin = camxMin;
    }

    public int getCamxMax() {
        return camxMax;
    }

    public void setCamxMax(int camxMax) {
        this.camxMax = camxMax;
    }

    public int getCamyMin() {
        return camyMin;
    }

    public void setCamyMin(int camyMin) {
        this.camyMin = camyMin;
    }

    public int getCamyMax() {
        return camyMax;
    }

    public void setCamyMax(int camyMax) {
        this.camyMax = camyMax;
    }

    public int getRoomWidth() {
        return roomWidth;
    }

    public void setRoomWidth(int roomWidth) {
        this.roomWidth = roomWidth;
    }

    public int getRoomHeigth() {
        return roomHeigth;
    }

    public void setRoomHeigth(int roomHeigth) {
        this.roomHeigth = roomHeigth;
    }

    public float getViewXPort() {
        return viewXPort;
    }

    public void setViewXPort(float viewXPort) {
        this.viewXPort = viewXPort;
    }

    public float getViewYPort() {
        return viewYPort;
    }

    public void setViewYPort(float viewYPort) {
        this.viewYPort = viewYPort;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public float getMarginLeft() {
        return marginLeft;
    }

    /**
     * this method specifies the left boundary that the camera
     * has to check when a sprite si approaching to the left edge
     * @param marginLeft 
     */
    public void setMarginLeft(float marginLeft) {
        this.marginLeft = marginLeft;
    }

    
    
    public float getMarginRight() {
        return marginRight;
    }

    /**
     * * this method specifies the right boundary that the camera
     * has to check when a sprite si approaching to the right edge
     * @param marginRight
     */
    public void setMarginRight( float marginRight ) {
        this.marginRight = marginRight;
    }

    public float getMarginTop() {
        return marginTop;
    }

    /**
     * * this method specifies the top boundary that the camera
     * has to check when a sprite is approaching to the top edge
     * @param marginTop
     */
    public void setMarginTop( float marginTop ) {
        this.marginTop = marginTop;
    }

    public float getMarginBottom() {
        return marginBottom;
    }

    /**
     * * this method specifies the bottom boundary that the camera
     * has to check when a sprite is approaching to the bottom edge
     * @param marginBottom
     */
    public void setMarginBottom( float marginBottom ) {
        this.marginBottom = marginBottom;
    }
    
    
    
    
    /**
     * metodo que mueve la camara sobre el eje x
     * @param x 
     */
    @Override
    public void moveX( int x )
    {
        
         this.camx += x;
        
        //establece que se muestre el room a cero
        if( this.camx >= camxMin )
        {
            this.camx = camxMin;
            this.offsetX=camxMin;
        }
        else if( this.camx <= camxMax )
        {
            //establece que se muestre el room a  -roomwidth + viewXwidth
            this.camx = camxMax;
            this.offsetX = camxMax * -1;
        }
        else
        {    
            this.offsetX-=x;
        }
                    
        
    }//
    
    
    //@TODO hacer el bound para los margenes de la posicion Y
    /**
     * metodo que mueve la camara sobre el eje y
     * 
     * moves camera among y axis
     * 
     * @param y 
     */
    @Override
    public void moveY( int y )
    {
        this.camy += y; 
        
        //establece que se muestre el room a cero
        if(this.camy>=camyMin)
        {
            this.camy = camyMin;
            this.offsetY=camyMin;
        }
        else if(this.camy<=camyMax)
        {
            //establece que se muestre el room a  -roomHeigth + viewXheigth
            this.camy = camyMax;
            this.offsetY = camyMax * -1;
        }
        else
        {    
            this.offsetY-=y;
        }
        
    }//moveY

    
    
      /**
     * metodo que mueve la camara sobre el eje X e Y
     * 
     * moves camera among X and Y axis
     * 
     * @param x 
     * @param y 
     */
    @Override
    public void moveXY(int x, int y) {
        moveX(x);
        moveY(y);
}//

    
    /**
     * this is only for testing purposes, must not be ued on
     * production
     * @return 
     */
    @Override
    public String toString() 
    {
        return "Camera{" + "camx=" + camx + ", camy=" + camy + ", camxMin=" + camxMin + ", camxMax=" + camxMax + ", camyMin=" + camyMin + ", camyMax=" + camyMax + ", roomWidth=" + roomWidth + ", roomHeigth=" + roomHeigth + ", viewXPort=" + viewXPort + ", viewYPort=" + viewYPort + ", offsetX=" + offsetX + ", offsetY=" + offsetY + ", marginLeft=" + marginLeft + ", marginRight=" + marginRight + ", marginTop=" + marginTop + ", marginBottom=" + marginBottom + '}';
    }
    
    
    
    
    
    
    
    
}//class
