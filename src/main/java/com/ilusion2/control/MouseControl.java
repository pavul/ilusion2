/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilusion2.control;

import com.ilusion2.sprite.Sprite;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * this interface provides all methods necesary for
 * mouse control
 * @author nigthmare
 */
public class MouseControl implements MouseListener 
{

    private Point pointPressed, pointReleased;
//    private int xPressed, yPressed, xReleased, yReleased;
    
    
    /**
     * 
     */
    public MouseControl()
    {
        //init two points 
        pointPressed = new Point();
        pointReleased = new Point();
    
    }//
    
    /**
     * when there is a click, when user press and release click button
     * @param e 
     */
    @Override
    public void mouseClicked(MouseEvent e) 
    {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        pointReleased.setLocation( e.getX(), e.getY() );
    }//

    
    /**
     * when user keep pressed the mouse
     * @param e 
     */
    @Override
    public void mousePressed(MouseEvent e)
    {
           System.out.println("PRESSED at "+e.getX()+" - "+e.getY() );
           pointPressed.setLocation( e.getX(), e.getY() );
    }//

    /**
     * when user release mouse button
     * @param e 
     */
    @Override
    public void mouseReleased(MouseEvent e) 
    {
           System.out.println("released atL "+e.getX()+" - "+e.getY() );
           pointReleased.setLocation( e.getX(), e.getY() );
    }//

    
       
    /**
     * when mouse enter the component
     * @param e 
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }//

    
    /**
     * when mouse leaves the component
     * @param e 
     */
    @Override
    public void mouseExited(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }//

    
    /**
     * get the last point where the click is pressed
     * @return 
     */
    public Point getPointPressed() {
        return pointPressed;
    }

//    public void setPointPressed(Point pointPressed) {
//        this.pointPressed = pointPressed;
//    }

    /**
     * get the last point where the click was released
     * @return 
     */
    public Point getPointReleased() {
        return pointReleased;
    }

//    public void setPointReleased(Point pointReleased) {
//        this.pointReleased = pointReleased;
//    }
    
    
    /**
     * this function will return true when mouse button is 
     * pressed inside the area of specified sprite
     * @param sprite
     * @return 
     */
    public boolean spritePressed( Sprite sprite )
    {
    
        //check sprite area
        
        return false;
    }//
    
    /**
     this function will return true when member is
     * pressed the specified sprite
     * @param sprite
     * @return 
     */
    public boolean spriteReleased( Sprite sprite )
    {
    //check the sprite area when click is released
        
        
        return false;
    }// 
    
    
    
    
    
    
    
}//class
