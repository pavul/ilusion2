/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilusion2.tile;

/**
 * this class it will be used to handle tiles, not common ones
 * like array tiles, but if there is animation for tiles, or 
 * you want to separate solid tiles to not to have a huge grid 
 * with empty values this will come handy
 * @author nigthmare
 */
public class Tile 
{
   
    /**
     * x position of the tile
     */
    private int x; 
    
    /**
     * y position of the tile
     */
    private int y;
    
    /**
     * width of the tile
     */
    private int w;
    
    /**
     * heigth of the tile
     */
    private int h;
           
    /**
     * possibly name of the tile
     */
    private String label;
    
    /**
     * to store here the value of this tile in the array
     */
    private int value;

    
    /**
     * constructor to use getters and setters to cofigure
     * this tile
     */
    public Tile()
    {}
    
    
    /**
     * constructor that defines a new tile with
     * x, y, w & h
     * @param x
     * @param y
     * @param w
     * @param h
     */
    public Tile( int x, int y, int w, int h)
    {
    this.x =x;
    this.y = y;
    this.w = w;
    this.h = h;
    }

    public Tile( int x, int y, int w, int h, int value)
    {
        this( x, y, w, h);
        this.value = value;
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
    
}//
