/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilusion2.gamemanager;

import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *clase que tiene una imagen con coordenadas X e Y
 * 
 * this class can have an image at certain coordinate X and Y
 * 
 */
public class ImageBackground 
{
   
   private Image img;
   private int x;
   private int y;
   private int w;
   private int h;
    
    
    public ImageBackground(BufferedImage img, int x, int y)
    {
    this.img=img;
    this.x=x;
    this.y=y;
    this.w = img.getWidth();
    this.h=img.getHeight();
    }//

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }
    
    
    /**
     * estanlece la posicion X e Y de la imagen de fondo
     * 
     * set initial posicion where image will be ( on X and Y axis )
     * 
     * @param x
     * @param y 
     */
    public void setPosition(int x, int y)
    {
    this.x=x;
    this.y=y;
    }
    
    
    
}//class
