/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilusion2.level;

import java.awt.Font;
import java.awt.Graphics2D;

/**
 * this interface has the methods to render stuff in a level
 * @author pavul
 */
public interface Renderable 
{
   
    /**
     * the main render method, this method when called
     * will render:
     * background layer
     * foreground layer
     * HUD layer
     * @param g2 
     */
    public default void render( Graphics2D g2 )
    {
        renderBackground( g2 );
        renderForeground( g2 );
        renderHUD( g2 );
    }//
    
    /**
     * to draw all background or the first layer 
     * this is implemented in render method
     * @param g2 
     */
    public void renderBackground( Graphics2D g2 );
    
    /**
     * to draw all sprites or middle layer
     * this is implemented in render method
     * @param g 
     */
    public void renderForeground(Graphics2D g);
    
    /**
     * to draw all Stats or HUD in the front layer
     * this is implemented in render method
     * @param g2 
     */
    public void renderHUD( Graphics2D g2 );
    
}//
