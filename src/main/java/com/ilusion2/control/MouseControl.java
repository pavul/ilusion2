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
import java.time.Clock;

/**
 * this interface provides all methods necesary for
 * mouse control
 * @author nigthmare
 */
public class MouseControl implements MouseListener 
{

    public static final int SWIPE_DOWN = 0;
    public static final int SWIPE_UP = 1;
    public static final int SWIPE_LEFT = 2;
    public static final int SWIPE_RIGTH = 3;
    
    
    private Point pointPressed, pointReleased;
    //private int xPressed, yPressed, xReleased, yReleased;
    
    
    private boolean isPressed = false, isReleased = false;
//    private boolean isSwipe = false;
    
    
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
    
//        System.out.println(" clicked ");
        pointReleased.setLocation( e.getX(), e.getY() );
    }//

    
    /**
     * when user keep pressed the mouse
     * @param e 
     */
    @Override
    public void mousePressed(MouseEvent e)
    {
        
//           System.out.println("PRESSED at "+e.getX()+" - "+e.getY() );
           pointPressed.setLocation( e.getX(), e.getY() );
           isPressed = true;
           isReleased =  false;
           
    }//

    /**
     * when user release mouse button
     * @param e 
     */
    @Override
    public void mouseReleased(MouseEvent e) 
    {
       //    System.out.println("released atL "+e.getX()+" - "+e.getY() );
           pointReleased.setLocation( e.getX(), e.getY() );
           
           isReleased = true;
           isPressed = false;
       
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
    
        
        return provideTouchedArea( pointPressed, 
                (int)sprite.getX( ),
                (int)sprite.getY(),
                (int)sprite.getW(),
                (int)sprite.getH()
                                 ) && isPressed;
        
//        return ( pointPressed.getX() >= sprite.getX()
//         && pointPressed.getX() <= ( sprite.getX() + sprite.getW() )
//         && pointPressed.getY() >= sprite.getY()
//         && pointPressed.getY() >= ( sprite.getY() + sprite.getH() )
//          );
                
                
        //check sprite area
        
//        return false;
    }//
    
    /**
     this function will return true when member is
     * pressed the specified sprite
     * @param sprite
     * @return 
     */
    public boolean spriteReleased( Sprite sprite )
    {
    return provideTouchedArea( pointReleased, 
                (int)sprite.getX( ),
                (int)sprite.getY(),
                (int)sprite.getW(),
                (int)sprite.getH()
                                 ) && isReleased;
    }// 
    
    
    /**
     * to check if the area is pressed
     * @param x
     * @param y
     * @param w
     * @param h
     * @return 
     */
    public boolean areaPresed( int x, int y, int w, int h )
    {
    return provideTouchedArea( pointPressed, 
                x,
                y,
                w,
                h
                             ) && isPressed;
    }// 
    
    
    /**
     * to check if the area is Released
     * @param x
     * @param y
     * @param w
     * @param h
     * @return 
     */
    public boolean areaReleased( int x, int y, int w, int h)
    {
     return provideTouchedArea( pointPressed, 
                x,
                y,
                w,
                h ) && isReleased;
    }// 
    
    
    /**
     * this function check if the swipe was done along in X axis
     * @param minDistance minimum distance to make swipe work
     * @return 
     */
    public int swipeXAxis( int minDistance )
    {
        
//        if( !isPressed ) return -1;
//    System.out.println(" swipe axis ");
        
        int res = -1;
      
//        int dx = Math.abs ( (int)( pointReleased.getX() - pointPressed.getX() ) );
        
        int dx = (int)getDistance( (float)pointReleased.getX() , (float)pointPressed.getX() );
        
//        System.out.println( " --> " + dx+" - "+minDistance );
        
        if( dx >= minDistance )
        {
            //swipe on X created
        
            //check if was for rigth or left
            if( pointReleased.getX() > pointPressed.getX() )
            {//System.out.println( " RIGTH"  );
                //rigth
                res = SWIPE_RIGTH;
            }
            else if( pointReleased.getX() < pointPressed.getX() )
            { //System.out.println( " LEFT " +pointReleased.getX() +" - "+ pointPressed.getX()  );
                //left
                res = SWIPE_LEFT;
            }
            
        }
        
        
//        pointReleased.x = 0;//  setLocation( 0,0 );
//        pointPressed.x =0;//  setLocation( 0,0 );
        
//        isSwipe = false;
//        pointPressed.setLocation(0, 0);
//        pointReleased.setLocation(0, 0);

//        System.out.println( " return res "+res  );
        return res;
    }//
    
    
    /**
     * this function check if the swipe was done along in Y axis
     * @param minDistance minimum distance to make swipe work
     * @return 
     */
    public int swipeYAxis( int minDistance )
    {
        
//        if( !isPressed ) return -1;
        
        int res = -1;
        
//        int dy = Math.abs ( (int)( pointReleased.getY() - pointPressed.getY() ) );        
        int dy = (int)getDistance( (float)pointReleased.getX() , (float)pointPressed.getX() ); 
        
        if( dy >= minDistance )
        {
            //swipe on X created
        
            //check if was for rigth or left
            if( pointReleased.getY() > pointPressed.getY() )
            {
                //down
                res = SWIPE_DOWN;
            }
            else if( pointReleased.getY() < pointPressed.getY() )
            {
                //up
                res = SWIPE_UP;
            }
            
        }///
        
        
        
//        pointReleased.y = 0;//  setLocation( 0,0 );
//        pointPressed.y =0;// 
        
//        isSwipe = false;
//        pointPressed.setLocation(0, 0);
//        pointReleased.setLocation(0, 0);
        return res;
    }//
    
    
    
    /**
     * true if the swipe was done to right
     * @param miDistance
     * @return 
     */
    public boolean swipeRigth( int miDistance )
    {
//        System.out.println(" swipe rigth igual a : " +swipeXAxis( miDistance));
        
        boolean b = swipeXAxis( miDistance) == SWIPE_RIGTH; 
//        pointPressed.x = 0;
//        pointReleased.x = 0;
//        return swipeXAxis( miDistance) == SWIPE_RIGTH;
        return b;
    }//
    
    
    /**
     * true if the swipe was done to left
     * @param miDistance
     * @return 
     */
    public boolean swipeLeft( int miDistance )
    {
        
        
        boolean b = swipeXAxis( miDistance) == SWIPE_LEFT; 
//        pointPressed.x = 0;
//        pointReleased.x = 0;
//        int ii = swipeXAxis( miDistance) ;
//        
//        if( ii == SWIPE_LEFT )
//        {
//            System.out.println( "entrando al LEFT true" );
//            return true;
//        }
//        
//        return false;
//        System.out.println(" swipe left igual a : " +swipeXAxis( miDistance));
//        return swipeXAxis( miDistance) == SWIPE_LEFT;
        return b;
    }//
    
    /**
     * true if the swipe was done to up
     * @param miDistance
     * @return 
     */
    public boolean swipeUp( int miDistance )
    {
        return swipeYAxis( miDistance) == SWIPE_UP;
    }//
    
    
    /**
     * true if the swipe was done to down
     * @param miDistance
     * @return 
     */
    public boolean swipeDown( int miDistance )
    {
        return swipeYAxis( miDistance) == SWIPE_DOWN;
    }//
    
    
    /**
     * this function check if there was swipe down, the force is going to divide
     * the distance of the swipe to get a value that can be used to define
     * if the swipe was long or no.
     * NOTE: minDistance =100, force =10, 
     * if the swipe distance was 200, then ir greater that minDistance, then
     * 200/10 = 20, that value can be used to set bigger speed or force, 
     * because it means the player did a big swipe ( twice the minDistance permitted )
     * @return 
     */
    public float swipeForceDown( int minDistance, float force )
    {
    
        //check if the swipe is down
        if( swipeDown( minDistance ) )
        {
            //if is down then the distance will be dividel by the force
            return getDistance( (float)pointReleased.getX() , (float)pointPressed.getX() ) / force;
        }
        
        return -1f;
    }//
    
     /**
     * this function check if there was swipe down, the force is going to divide
     * the distance of the swipe to get a value that can be used to define
     * if the swipe was long or no.
     * NOTE: minDistance =100, force =10, 
     * if the swipe distance was 200, then ir greater that minDistance, then
     * 200/10 = 20, that value can be used to set bigger speed or force, 
     * because it means the player did a big swipe ( twice the minDistance permitted )
     * @return 
     */
    public float swipeForceUp( int minDistance, float force )
    {
    
        //check if the swipe is down
        if( swipeUp( minDistance ) )
        {
            //if is down then the distance will be dividel by the force
            return getDistance( (float)pointReleased.getX() , (float)pointPressed.getX() ) / force;
        }
        
        return -1f;
    }//
    
    
    
     /**
     * this function check if there was swipe down, the force is going to divide
     * the distance of the swipe to get a value that can be used to define
     * if the swipe was long or no.
     * NOTE: minDistance =100, force =10, 
     * if the swipe distance was 200, then ir greater that minDistance, then
     * 200/10 = 20, that value can be used to set bigger speed or force, 
     * because it means the player did a big swipe ( twice the minDistance permitted )
     * @return 
     */
    public float swipeForceRigth( int minDistance, float force )
    {
    
        //check if the swipe is down
        if( swipeRigth(minDistance ) )
        {
            //if is down then the distance will be dividel by the force
            return getDistance( (float)pointReleased.getX() , (float)pointPressed.getX() ) / force;
        }
        
        return -1f;
    }//
    
    
     /**
     * this function check if there was swipe down, the force is going to divide
     * the distance of the swipe to get a value that can be used to define
     * if the swipe was long or no.
     * NOTE: minDistance =100, force =10, 
     * if the swipe distance was 200, then ir greater that minDistance, then
     * 200/10 = 20, that value can be used to set bigger speed or force, 
     * because it means the player did a big swipe ( twice the minDistance permitted )
     * @return 
     */
    public float swipeForceLeft( int minDistance, float force )
    {
    
        //check if the swipe is down
        if( swipeLeft( minDistance ) )
        {
            //if is down then the distance will be dividel by the force
            return getDistance( (float)pointReleased.getX() , (float)pointPressed.getX() ) / force;
        }
        
        return -1f;
    }//
    
    /**
     * this method process all regarding spriteReleased
     * @param x
     * @param y
     * @return 
     */
    private boolean provideTouchedArea( Point point, int x, int y, int w, int h )
    {
        return point.getX() >= x
         && point.getX() <= ( x + w )
         && point.getY() >= y
         && point.getY() <= ( y + h );
        
    }//
    
    
    
    private float getDistance( float  point1, float point2 )
    {
    
       return Math.abs ( ( point2 - point1 ) );
//        int dy = Math.abs ( (int)( pointReleased.getY() - pointPressed.getY() ) );
    }

    public boolean isPressed() {
        return isPressed;
    }

    public boolean isReleased() {
        return isReleased;
    }

    public void setPressed(boolean isPressed) {
        this.isPressed = isPressed;
    }

    public void setReleased(boolean isReleased) {
        this.isReleased = isReleased;
    }
    
    
    
    
    
}//class
