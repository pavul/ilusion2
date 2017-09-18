/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilusion2.particle;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

/**
 * this class is a single particle can be a 
 * point, oval, rect 
 * @author pavulzavala
 */
public class Particle 
{


    public static final int DRAW_FILLEDRECT = 1;
    public static final int DRAW_FILLEDOVAL = 2;
    public static final int DRAW_RECT = 3;
    public static final int DRAW_OVAL = 4;
    public static final int DRAW_IMAGE = 5;
    
    
    private float x; //coor x
    private float y; //coor y
    private float w; //width size 
    private float h; //heigth size
    private float sx; //speed x
    private float sy; //speed y
    private int life; //tiempo de vida
    private Color color;
    
    
    /**
     * this is the value of te gravity that will
     * be applied to gravityValue
     */
    private float gravity;
    
    /**
     * 
     */
    private float gravityValue;
   
    /**
     * this is the max value that gravityValue
     * will reach
     */
    private float gravityLimit;
    
    
    private Image img;
    private int currentDraw;
    
    
    /**
     * this constructor set the particle without data, is intented to use 
     * setters, by default this constructor set currentDraw to DRAW_FILLEDRECT
     * and color to WHITE
     */
    public Particle()
    {
    currentDraw = DRAW_FILLEDRECT;
    this.color = Color.white;
    gravity = 0.37f;
    gravityValue = 5f * -1;
    gravityLimit = 5f;
    }//
    
    /**
     * constructor que define todos los parametros de la particula
     * @param x
     * @param y
     * @param w
     * @param h
     * @param sx
     * @param sy
     * @param life
     * @param c 
     */
    public Particle(float x, float y, float w, float h, float sx, float sy, int life, Color c)
    {
        this();
        
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.sx = sx;
        this.sy = sy;
        this.life = life;
        this.color = c;
        
        
    }//
    
  /**
   * this function will substract one point of life
   * every step, when it reaches zero will return true
   * to notify that this particle has died
   * @return 
   */
    public boolean decreaseLife()
    {
      life--;
        
        if( life <= 0 )life = 0;
        
      return life <= 0;
    }
    
    /**
     * this method update the position of the particle and it decrease
     * life every thick depending on speedX ( SX ) and speedY ( SY )
     * @return true if life is cero
     */
    public boolean update()
    {
        x += sx;
        y += sy;
     
        return decreaseLife();
    }//
    
    
    /**
     * this function update X e Y position of the particle
     * but gravity force is applied to make it fall
     * @return 
     */
    public boolean updateWithGravity()
    {
        x += sx;
        
        System.err.println("gV: "+gravityValue+" g: "+gravity);
           gravityValue += gravity; 
            
                if( gravityValue >= gravityLimit )
                {
                    gravityValue = gravityLimit;
                }
                System.err.println( "proc gV: "+gravityValue );
            y += gravityValue;
        
            return decreaseLife();
    }//
    
    
    /**
     * metodo que renderiza la particula por pantalla
     * @param g 
     */
    public void draw(Graphics2D g)
    {
        
        g.setColor(color);


        switch( currentDraw )
        {
            case DRAW_FILLEDRECT:
                g.fillRect( (int)x, (int)y, (int)w, (int)h );
                break;
            case DRAW_FILLEDOVAL:
                g.fillOval( (int)x, (int)y, (int)w, (int)h );
                break;
            case DRAW_RECT:
                g.drawRect((int)x, (int)y, (int)w, (int)h);
                break;
            case DRAW_OVAL:
                g.drawOval( (int)x, (int)y, (int)w, (int)h );
                break;
            case DRAW_IMAGE:
                g.drawImage(img, (int)x, (int)y, null);
                break;
        }///
        
    }//

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

    public float getSx() {
        return sx;
    }

    public void setSx(float sx) {
        this.sx = sx;
    }

    public float getSy() {
        return sy;
    }

    public void setSy(float sy) {
        this.sy = sy;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public int getCurrentDraw() {
        return currentDraw;
    }

    public void setCurrentDraw(int currentDraw) {
        this.currentDraw = currentDraw;
    }

    public float getGravity() {
        return gravity;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public float getGravityValue() {
        return gravityValue;
    }

    public void setGravityValue(float gravityValue) {
        this.gravityValue = gravityValue;
    }

    public float getGravityLimit() {
        return gravityLimit;
    }

    public void setGravityLimit(float gravityLimit) {
        this.gravityLimit = gravityLimit;
    }
    
    
    
    
    /**
     * getther and setters
     */
    
    
    
}//class
