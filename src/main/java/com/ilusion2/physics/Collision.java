/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilusion2.physics;

import com.ilusion2.config.Config;
import com.ilusion2.sprite.Sprite;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.audio.AudioPlayer;
import com.ilusion2.tile.Tile;

/**
 * clase que tiene los metodos para checar colisiones entre los diferentes sprites
 * 
 * this class have few methos to check collisions between sprites, or tiles,
 * or shapes
 * 
 * @author pavulzavala
 */
public class Collision 
{
    
    private static Collision instance = null;
    
    private Collision()
    {}
    
    /**
     * this create a collision instance if is null
     * @return collision
     */
    public static Collision getInstance()
    {
    
        if( instance == null )
            return new Collision();
        else
            return instance;
    }//
    
    /**
         * this function return the total distance from one point to another
         * @param coor1 X or Y cordinate of point 1
         * @param val1 Width or Heigth of point 1
         * @param coor2 X or Y coordinate of point 2
         * @param val2 Width or Heigth of point 1
         * example 
         * float vx = getDistance( 100, 32, 200, 32 ); //on X
         * float vy = getDistance( 145, 32, 300, 32); //on Y
         * 
         * @return 
         * 
         */
           public float getDistance(float coor1, float val1, float coor2, float val2 )
        {
            
            float v = (coor1 + val1 / 2) - (coor2 + val2 / 2 );

            return Math.abs( v );
            
        }
           
           /**
            * this function is used to combine the half parts from
            * 2 rects, circles or other shapes as well 2 sprites
            * @param half1
            * @param half2
            * this will process something like:
            * float combinedHalfHeight = h / 2 + h2 / 2; 
            * @return 
            */
           public float getCombineHalf( float half1, float half2 )
           {
           return (half1 / 2) + (half2 / 2);
           }
        
           
           
    
     /**
      * metodo que checa si hay colision circular entre 2 sprites
      * 
      * check if there is collision between two sprites
      * this collission is circular
     *
     * @param s1
     * @param s2
	 
	 * @return true if there is a colision and false if not
	 * @example:   colision.circleColision(player, enemy);
         */
	public boolean circleColision(Sprite s1,  Sprite s2)
	{
		float cx=s1.getCenterX()-s2.getCenterX();
		float cy=s1.getCenterY()-s2.getCenterY();
		
		float magnitude=(float)Math.sqrt((cx*cx)+(cy*cy));
		
		float totalRadii=s1.getHalfWidth()-s2.getHalfWidth();
		
		boolean hit=magnitude<totalRadii;
		
		return hit;
		
	}//funcion para checar colision circular
        
        
        /**
	 * Example: colision.blockCircle(player,enemy,true);
	 * checks if the sprite is in colision with another sprite 
	 * the colision is with a circle shape
     * @param s1
     * @param s2
     * @param bounce
	 * 
	 * */
        public void blockCircle(Sprite s1,Sprite s2, boolean bounce)
	{
		
		float cx=s1.getCenterX()-s2.getCenterX();
		float cy=s1.getCenterY()-s2.getCenterY();
		
		float magnitude=(float)Math.sqrt((cx*cx)+(cy*cy));
		
		float totalRadii=s1.getHalfWidth() - s2.getHalfWidth();
		
		if(magnitude < totalRadii)
		{
			
			float overlap=totalRadii-magnitude;
			
			float dx=cx/magnitude;
			float dy=cy/magnitude;
			
                        
			s1.setX(s1.getX() + overlap*dx);  
			s1.setY(s1.getY() + overlap*dy);
		
			if(bounce)
			{
				//codigo para rebotar el sprite en caso de
			}
			
		}//if
		
	}//funcion que hace colision con un circulo pero lo hace solido, 
    
    
        
        
        
     
        
        /**
         * funcion que checa si hay una colision rectangular entre 2 sprites
	 * Example:  sprite.rectangleColision(enemy);
         * 
         * checks if there is rectangle collision  between 2 sprites
         * 
         * @param s1
         * @param s2
	 * @return true if there is a colision and false if not
	 * */
	public  boolean rectangleColision(Sprite s1, Sprite s2)
	{
//            boolean hit = false;
//            float vx=s1.getCenterX() - s2.getCenterX();
//            float vy=s1.getCenterY() - s2.getCenterY();
//
//            float combinedHalfWidth=s1.getHalfWidth()+s2.getHalfWidth();
//            float combinedHalfHeight=s1.getHalfHeight()+s2.getHalfHeight();
//
//            if(Math.abs(vx)<combinedHalfWidth)
//            {
//                return ( (Math.abs(vy) < combinedHalfHeight) );
//            }
//            return hit;
            
            return rectangleColision(s1.getX(),s1.getY(), s1.getW(), s1.getH(),
                    s2.getX(),s2.getY(), s2.getW(), s2.getH());
		
	}//colision rectangular
        
        
        
        /**
         * * funcion que checa si hay una colision rectangular entre 2 
         * rectangulos
         * 
         * checks if there is rectangle collision  between 2 rectalgles
         * @param x
         * @param y
         * @param w
         * @param h
         * @param x2
         * @param y2
         * @param w2
         * @param h2
         * @return 
         */
        public  boolean rectangleColision(
                float x, float y, float w, float h,
                float x2, float y2, float w2, float h2)
	{            

            float combinedHalfWidth = /*getCombineHalf( w, w2 );*/  w/2 +w2/2; //  s1.getHalfWidth()+s2.getHalfWidth();
            float combinedHalfHeight = /*getCombineHalf( h, h2 );*/   h/2 +h2/2; //  s1.getHalfHeight()+s2.getHalfHeight();

            
            if( getDistance( x, w , x2, w2 ) < combinedHalfWidth )
            {
                
//                System.out.println( "d: "+getDistance( y, h, y2, h2 ) +"  "+combinedHalfHeight +" res: "+(getDistance(y, h, y2, h2) < combinedHalfHeight) );
            
                return ( getDistance(y, h, y2, h2) < combinedHalfHeight );
            }
            return false;
		
	}//colision rectangular
        
     
    /**
     * funcion que checa si hay una colision entre sprite1 y sprite2 e indica si se debe de mover
     * el sprite que se empuja o si se debe de revotar
     * 
     * checks if there is a collision between two sprites and if the sprite 1
     * is pushing sprite 2
     *
     * Example:  sprite.blockRectangle(enemy);
     * @param s1
     * @param s2
	 * @param move, if this is set to true, the sprite will push the colision sprite
	 * @param bouncing, true if you want to make that the sprite bounce when colide with colision sprite
	 * checks if the sprite is in colision with another sprite 
	 * the colision is with a rectangle shape
	 * @return true if there is a colision and false if not
	 * 
	 * */
	public String blockRectangle(Sprite s1,Sprite s2,boolean move ,boolean bouncing)
	{
		String side = Config.COLISION_NONE;
		float vx = getDistance( s1.getX(), s1.getW(), s2.getX(), s2.getW() );//   s1.getCenterX()-s2.getCenterX();
////		float vy = s1.getCenterY()-s2.getCenterY();
		float vy = getDistance( s1.getY(), s1.getH(), s2.getY(), s2.getH() );
		
//		float combinedHalfWidth=s1.getHalfWidth()+s2.getHalfWidth();
//		float combinedHalfHeight=s1.getHalfHeight()+s2.getHalfHeight();
		float combinedHalfWidth = getCombineHalf( s1.getW() , s2.getW() );// s1.getHalfWidth()+s2.getHalfWidth();
		float combinedHalfHeight = getCombineHalf( s1.getY() , s2.getY() );// s1.getHalfHeight()+s2.getHalfHeight();
		
//		if(Math.abs(vx)<combinedHalfWidth)
		if( getDistance( s1.getX(), s1.getW(), s2.getX(), s2.getW() ) < combinedHalfWidth )
		{
//			if(Math.abs(vy)<combinedHalfHeight)
			if( getDistance( s1.getY(), s1.getH(), s2.getY(), s2.getH() ) < combinedHalfHeight )
			{
				float overlapX=combinedHalfWidth-Math.abs(vx);
				float overlapY=combinedHalfHeight-Math.abs(vy);
				
				if(overlapX>=overlapY)
				{
					if(vy>0)
					{
						side = Config.COLISION_TOP;
						if(move)s2.setY(s2.getY()-1);
						s1.setY(s1.getY()+overlapY);
                                         }
					else
					{
						side = Config.COLISION_BOTTOM;
						if(move)s2.setY(s2.getY()+1);
                                                s1.setY(s1.getY()-overlapY);
                                                
					}
					if(bouncing)s1.setSpeedY(s1.getSpeedY()*-1);
				}
				else
				{
					
					if( vx > 0 )
					{
						side= Config.COLISION_LEFT;
						if(move)s2.setX(s2.getX()-1);
						s1.setX(s1.getX()+overlapX);
					}
					else
					{
						side = Config.COLISION_RIGHT;
						if(move)s2.setX(s2.getX()+1);
						s1.setX(s1.getX()-overlapX);
					}
					if(bouncing)s1.setSpeedX(s1.getSpeedX()*-1);
		         }//
		
                        }//height
			
		}//width
		
		return side;
		
	}//colision con rectangulos, pero     
        
        
         /**
     * funcion que checa si hay una colision entre sprite1 y coordenadas X e Y  y ancho y alto de 
     * la figura, esta es una manera abstracta de colision para figuras en ves de sprites dados
     * NOTA: esta funcon solo hace una colicion y reestablece las posiciones del sprite que se solapan
     * para que no se vuelva a solapar
     * 
      * check whether there are collision between sprite and some figure defined by X e Y coordinates
      * as well as Width and Height, 
      * NOTE: this function is just checking collisions and return overlaps betwen the sprite and 
      * the sprite ,this doesnt push the other sprite..
      * 
      * this can be used to stablish solid blocks there the sprite must no overlaps
      * solid tiles
     * 
     * @param s1
     * @param x
     * @param y
     * @param width
     * @param height
	 * @return NONE if there is not a colision and  TOP, BOTTOM, RIGHT< LEFT if there are
	 * 
	 * */
	public String blockRectangle( Sprite s1, int x, int y, int width, int height )
	{
            int halfWidth = width / 2;
            int halfHeigth = height / 2;
            int centerX = x + halfWidth;
            int centerY = y + halfHeigth;
            
		String side = Config.COLISION_NONE;        
                
                //get distance vectors
		float vx = s1.getCenterX() - centerX;
		float vy = s1.getCenterY() - centerY;
                
                
                //float v = coor1 + val1 / 2 - coor2 + val2 / 2;
//                float vx = getDistance( s1.getX(), s1.getW(), x, width );//  s1.getCenterX()-centerX;
//                float vy = getDistance( s1.getY(), s1.getH(), y, height ); //s1.getCenterY()-centerY;
//                /.,
                
//		float combinedHalfWidth = getCombineHalf( s1.getW(), width );//   s1.getHalfWidth()+halfWidth;
//		float combinedHalfHeight = getCombineHalf( s1.getH(), height );//  s1.getHalfHeight()+halfHeigth;
            
                float combinedHalfWidth = s1.getHalfWidth()+halfWidth;
		float combinedHalfHeight = s1.getHalfHeight()+halfHeigth;
            
                
                //collision on x axis
                if( Math.abs( vx ) < combinedHalfWidth )
		{
                    //collision on y axis
			if( Math.abs( vy ) < combinedHalfHeight)
			{
                            
                            //collision made 
                            
				float overlapX=combinedHalfWidth-Math.abs(vx);
				float overlapY=combinedHalfHeight-Math.abs(vy);
		
                            if( overlapX < overlapY )
                                    {
					
					if( vx > 0 )
					{
                                            s1.setX( s1.getX()+overlapX );
                                            return Config.COLISION_LEFT;
					}
					else
					{
                                            s1.setX( s1.getX()-overlapX );
                                            return Config.COLISION_RIGHT;
					}

                                    }//
                                else
   
				{

                                    if( vy > 0 )
					{
                                            s1.setY( s1.getY()+overlapY );
                                            return Config.COLISION_TOP;
                                        }
                                    else 
					{
                                           s1.setY( s1.getY()  - overlapY );
                                            return Config.COLISION_BOTTOM;
					}

				}

			}//height
        
		}//width
                
		return side;
		
	}//colision con rectangulos, pero     
        
        
        /**
         * funcion que checa la colision del sprite dado, con todos los tiles que hay,
         * como algunos tiles solo son para mostrar fondo, solo son pocos los que
         * se pudieran tomar como solidos, la funcion checa si el sprite hace una
         * colision con algun tile que debe de ser solido, estos tiles solidos se
         * establecen con el mapa de colision  int []COLISIONMAP
         * 
         * 
         * check the collisions of the sprite with all tiles who are in 
         * COLISIONMAP array, that means those tiles are solids and are not 
         * just to show them as background
         * 
     * @param spr
     * @param colisionMap
         * @param cols
         * @param rows 
     * @param tileWidth 
     * @param tileHeight 
     * @return  
         */
        public  String checkColsionTile(Sprite spr,
                int[] colisionMap,
                int cols, 
                int rows, 
                int tileWidth, 
                int tileHeight)
        {
            String side= Config.COLISION_NONE;
            
            if(cols <=0 || rows <= 0)
            {
                //arrojar excepcion aqui
            return side;
            }
        
        int mapIndex = 0;
        int totalTiles = cols * rows;
        
        int tilewidth = tileWidth;
        int tileHeigth = tileHeight;
      
      //for de renglones
         for( int i = 0;i < rows; i++ )
            {
                int tiley = i * tileHeigth;
            
                    //for de columnas
                    for( int j = 0 ; j < cols ; j++ )
                    {
                        
                   //@TODO check here if the tile to compute is
                    //near the sprite to collide, to avoid extra process...
                        
                    if( colisionMap[ mapIndex ] == 1)
                     {
                         int tilex = j * tilewidth;
                         
                        side = blockRectangle(spr, tilex, tiley, tilewidth,tileHeigth);
                        if ( side.equals( Config.COLISION_BOTTOM ) )
                        {
                            return Config.COLISION_BOTTOM;
                        }
                      
                     }//if validacion si se checa colision
                 
                      if( mapIndex < totalTiles )
                      mapIndex++;
                      
                      
                    }//for cols
            }//for rows
     
          return side;
        }//checkcolisiontile
        
        
        
        /**
         * method that check collision with tile objects
         * @param spr
         * @param tileList
         * @return 
         */
        public  String checkColsionTile(
                Sprite spr,
                List<Tile> tileList)
        {
            String side= Config.COLISION_NONE;
            
//            if(cols <=0 || rows <= 0)
//            {
//                //arrojar excepcion aqui
//            return side;
//            }
//        
//        int mapIndex = 0;
//        int totalTiles = cols * rows;
        
//        int tilewidth = tileWidth;
//        int tileHeigth = tileHeight;
//      
//      //for de renglones
//         for( int i = 0;i < rows; i++ )
//            {
//                int tiley = i * tileHeigth;
//            
//                    //for de columnas
//                    for( int j = 0 ; j < cols ; j++ )
//                    {
//                        
//                   //@TODO check here if the tile to compute is
//                    //near the sprite to collide, to avoid extra process...
//                        
//                    if( colisionMap[ mapIndex ] == 1)
//                     {
//                         int tilex = j * tilewidth;
//                         
//                        side = blockRectangle(spr, tilex, tiley, tilewidth,tileHeigth);
//                        if ( side.equals( Config.COLISION_BOTTOM ) )
//                        {
//                            return Config.COLISION_BOTTOM;
//                        }
//                      
//                     }//if validacion si se checa colision
//                 
//                      if( mapIndex < totalTiles )
//                      mapIndex++;
//                      
//                      
//                    }//for cols
//            }//for rows
     

//        tileList.forEach( tile -> 
//        {
//          if (  blockRectangle( spr, tile.getX(),tile.getY(), tile.getW(), tile.getH() )
//          .equals(Config.COLISION_BOTTOM ) 
//                  )
//          {
//              
//              return null;
////              side = Config.COLISION_BOTTOM;
//          }
//            
//        });


//            for( Tile tile : tileList)
//            {
//
//                if( blockRectangle( spr, tile.getX(),tile.getY(), tile.getW(), tile.getH() )
//                                            .equals(Config.COLISION_BOTTOM ) )
//                {
//                return Config.COLISION_BOTTOM;
//                }
//
//            }//

//@TODO check if the tile is inside of port view
        Tile t = tileList.stream().filter
                (                      
                    tile -> 
                    {
                        switch( tile.getValue() )
                        {
                            case 0:
                                break;
                            case 1:
                            return ( blockRectangle( spr, tile.getX(),tile.getY(), tile.getW(), tile.getH() )
                            .equals(Config.COLISION_BOTTOM ) && spr.getJumpValue() > 0 );
                            case 2:
                            case 3:
                            //collision with slope   
//                                System.err.println( "value: "+tile.getValue() );
//                                System.err.println( spr.getAnchor().getX()+" "+spr.getY() 
//                                        +"  tile: "+ tile.getX()+" "+
//                                        tile.getY());
                                
                                
                                
                                
                                if( rectangleColision(
                                        spr.getX(),
                                        spr.getY(),
                                        spr.getW(),
                                        spr.getH(),
                                        tile.getX(),
                                        tile.getY(),
                                        tile.getW(),
                                        tile.getH() )
                                        //means that if jumpvalue is negative, then
                                        //the sprite is jumping, there is no point to
                                        //check collision on slope if sprite is jumping
                                        //only when falling
                                        && spr.getJumpValue() > 0
                                        )
                                {
                                
                                     //check the x positon of anchor
                               //and iterate slope array
                               int colpos = spr.getAnchor().x - tile.getX();
//                                       Math.abs( spr.getAnchor().x - tile.getX() );
                                       
//                            System.err.println("anchor: "+ ( spr.getAnchor().x ) );
//                            System.err.println("tilex: "+tile.getX() );
//                            System.err.println("colpos: "+( spr.getAnchor().x - tile.getX() ) );

                            /**
                             * if colpos is greater than 0 and its less that tile width mean
                             * the anchor is inside that tile, hence we have to calculate
                             * the correct value for the y position on the slope
                             */
                                   if( colpos  > 0 && colpos <= tile.getW() )
                                   {
                                       
                                       int yval = colpos;
                                        
                                       if( tile.getValue() == 3 )
                                       { yval = tile.getW() - colpos; }

//                               System.err.println("Y: " + (tile.getY()+tile.getH())+" : "+yval );

                               spr.setY( ( tile.getY() - spr.getH() ) + ( tile.getH() - yval ) );

//                               System.out.println("yval = "+yval);
                                       
                                       return true;
                                   }//
                                    
                                }//if there is any collision
//                                else
//                                {System.err.println("non col");}
                                   
                        }//suich
                        
                    return false;
                    }
                            
                ).findFirst().orElse( null );
        
                if( null != t )side = Config.COLISION_BOTTOM;

          return side;
        }//checkcolisiontile
        
        
        
        /**
     * @param slopeArray *  @NEED MORE TESTING to check is working as expected
         * this will check a colisionTile map with slopes included
         * @param spr
         * @param colisionMap
         * @param cols
         * @param rows
         * @param tileWidth
         * @param tileHeight
         * @return 
         */
//        public  String checkColsionTileWithSlope(Sprite spr,
//                int[ ] colisionMap,
//                int[ ] slopeArray,
//                int cols,
//                int rows,
//                int tileWidth,
//                int tileHeight)
//        {
//            String side= Config.COLISION_NONE;
//            
//            if(cols <= 0 || rows <= 0)
//            {
//                //arrojar excepcion aqui
//            return side;
//            }
//        
//        int mapIndex = 0;
//        int totalTiles = cols * rows;
//        
//        int tilewidth = tileWidth;
//        int tileHeigth = tileHeight;
//      
//        
//        //@TODO: check if the tiles to check colision are inside port view, 
//        //to avoid to process the ones that are outlise the port view
//        
//        
//        //for de renglones
//         for( int i = 0;i < rows; i++ )
//            {
//                int tiley = i * tileHeigth;
//            
//                    //for de columnas
//                    for( int j = 0 ; j < cols ; j++ )
//                    {
//                        
//                        if( mapIndex < totalTiles )
//                             mapIndex++;
//                        
//                        
//                        //it will check mapIndex - 1 because, when it just enter
//                        //this loop ( above code ) mapindex is increased
//                        switch( colisionMap[ mapIndex - 1 ] )
//                        {
//                        
//                            case 0: //empty
//                                continue;
//                            case 1: //solid tile
//                                int tilex = j * tilewidth;
//                                //collision check is made here, inside if
//                                if( blockRectangle(spr, tilex, tiley, tilewidth,tileHeigth )
//                                        .equals( Config.COLISION_BOTTOM ) ) 
//                                side = Config.COLISION_BOTTOM;
//                                break;
//                            case 2: //maybe slope
//                                int tilx = j * tilewidth;
//                                
//                                //if there are some kind of collision
//                                   if( !blockRectangleWithSlope( 
//                                           spr, 
//                                           tilx, 
//                                           tiley, 
//                                           tilewidth,
//                                           tileHeigth )
//                                        .equals( Config.COLISION_NONE ) ) 
////                                   if( blockRectang   (spr, tilx, tiley, tilewidth,tileHeigth )
////                                        .equals( Config.COLISION_BOTTOM ) ) 
//                                   {
//                                   side = Config.COLISION_BOTTOM;
//                                   
//                                       //check the x positon of anchor
//                                       //and iterate slope array
//                                       
//                                       System.out.println("X ="+spr.getX() +" anch= "+spr.getAnchor()+" tilx="+tilx );
//                                       
//                                       int xpos = ( ( int )spr.getX() + spr.getAnchor().x ) - tilx ;
//                                       int ypos = 0;
//                                       if( xpos > 0 && xpos <= tileWidth )
//                                       {
//                                           int px = 0;
//                                           //this will check
//                                           for( ypos = 0; ypos <= slopeArray.length - 1; ypos += tileWidth )
//                                           {
//
//                                               System.out.println("::: "+ ( (xpos - 1 ) + ypos)  +" - " + px );
//
//                                               if( slopeArray[ xpos -1 + ypos ] == 0 )
//                                                   px++;
//                                               else 
//                                                   break;
//                                           }
//
////                                           try {
////                                               Thread.sleep( 1000 );
////                                           } catch (InterruptedException ex) {
////                                               Logger.getLogger(Collision.class.getName()).log(Level.SEVERE, null, ex);
////                                           }
//                                           
//                                           
//                                           System.out.println("------------------------------");
//                                           System.out.println("xpos = "+xpos+"  -  ypos "+ypos+"  -  PX " + px);
//                                           
//                                           System.out.println("------------------------------");
//                                           spr.setY( ( int )( spr.getY() - px ) );
//                                       
//                                       }
//                                       
////                            try {
////                                Thread.sleep( 1000 );
////                            } catch (InterruptedException ex) {
////                                Logger.getLogger(Collision.class.getName()).log(Level.SEVERE, null, ex);
////                            }
//                              
//                                   }//
//                                
//                                
//                                break;
//                        }//
//                        
//                    }//for cols
//            }//for rows
//      
////          System.out.println("-checkColsionTile se regreso SIDE: "+side);
//         
//          return side;
//        }//checkcolisiontileWithSlope
//        
        
        
        
//        
//        public String blockRectangleWithSlope(
//                Sprite s1,
//                int x,
//                int y,
//                int width,
//                int height )
//	{       
//            int halfWidth = width / 2;
//            int halfHeigth = height / 2;
//            int centerX = x + halfWidth;
//            int centerY = y + halfHeigth;
//            
//		String side = Config.COLISION_NONE;  
//                
//		float vx = s1.getCenterX() - centerX;
//		float vy = s1.getCenterY() - centerY;
//		
//		float combinedHalfWidth = s1.getHalfWidth() + halfWidth;
//		float combinedHalfHeight = s1.getHalfHeight() + halfHeigth;
//		
//		if( Math.abs( vx ) < combinedHalfWidth )
//		{
//			if( Math.abs( vy ) < combinedHalfHeight )
//			{
//				float overlapX = combinedHalfWidth-Math.abs( vx );
//				float overlapY = combinedHalfHeight-Math.abs( vy );
//				
//				 if( overlapX < overlapY )
//                                    {
//					
//					if( vx > 0 )
//					{
////                                            s1.setX( s1.getX()+overlapX );
//                                            return Config.COLISION_LEFT;
//					}
//					else
//					{
////                                            s1.setX( s1.getX()-overlapX );
//                                            return Config.COLISION_RIGHT;
//					}
//
//                                    }//
//                                else
//   
//				{
//
//                                    if( vy > 0 )
//					{
////                                            s1.setY( s1.getY()+overlapY );
//                                            return Config.COLISION_TOP;
//                                        }
//                                    else 
//					{
////                                           s1.setY( s1.getY()  - overlapY );
//                                            return Config.COLISION_BOTTOM;
//					}
//
//				}
//                        }
//		}//width
//		return side;
//		
//	}//colision con rectangulos, pero     
//        
//        
//        
//         public String blockRectangleWithSlope2(
//                int x1,
//                int y1,
//                int width1,
//                int height1,
//                int x2,
//                int y2,
//                int width2,
//                int height2 )
//	{   
//            
//            int halfWidth1 = width1 / 2;
//            int halfHeigth1 = height1 / 2;
//            int centerX1 = x1 + halfWidth1;
//            int centerY1 = y1 + halfHeigth1;
//            
//            int halfWidth2 = width2 / 2;
//            int halfHeigth2 = height2 / 2;
//            int centerX2 = x2 + halfWidth2;
//            int centerY2 = y2 + halfHeigth2;
//            
//		String side = Config.COLISION_NONE;  
//                
//		float vx = centerX1 - centerX2;
//		float vy = centerY1 - centerY2;
//		
//		float combinedHalfWidth = halfWidth1 + halfWidth2;
//		float combinedHalfHeight = halfHeigth1 + halfHeigth2;
//		
//		if( Math.abs( vx ) < combinedHalfWidth )
//		{
//			if( Math.abs( vy ) < combinedHalfHeight )
//			{
//				float overlapX=combinedHalfWidth-Math.abs(vx);
//				float overlapY=combinedHalfHeight-Math.abs(vy);
//				
//				 if( overlapX < overlapY )
//                                    {
//					
//					if( vx > 0 )
//					{
////                                            s1.setX( s1.getX()+overlapX );
//                                            return Config.COLISION_LEFT;
//					}
//					else
//					{
////                                            s1.setX( s1.getX()-overlapX );
//                                            return Config.COLISION_RIGHT;
//					}
//
//                                    }//
//                                else
//   
//				{
//
//                                        if( vy > 0 )
//					{
////                                            s1.setY( s1.getY()+overlapY );
//                                            return Config.COLISION_TOP;
//                                        }
//                                        else 
//					{
////                                           s1.setY( s1.getY()  - overlapY );
//                                            return Config.COLISION_BOTTOM;
//					}
//
//				}
//                        }
//		}//width
//		return side;
//		
//	}//colision con rectangulos, pero     
//        
//        
//        
//        
        
        
/** @ TODO IMPTOBR THIS METHOD TO MAKE IT CHECK FOR A TILELIST AND
 * TO SPECIFY THE SIDE OF THE FREE COLLISION UP, DOWN, LEFT OR RIGHT
 * method that checks if there is a collision in the bottom, 
 * this is supposed to check a fake collision in th
         * @param s
         * @param fakeY
         * @param colisionMap
         * @param cols
         * @param rows
         * @param tileWidth
         * @param tileHeight
         * @return 
         */
          public  boolean checkColsionFree( 
                    Sprite s,
                    int fakeY,
                    int[] colisionMap,
                    int cols, 
                    int rows,
                    int tileWidth,
                    int tileHeight)
        {
         
            if(cols <=0 || rows <= 0)
            {
            //arrojar excepcion aqui
//            return "NONE";
            return false;
            }
        
        int mapIndex = 0;
        int totalTiles = cols * rows;
        
        int tilewidth = tileWidth;
        int tileHeigth = tileHeight;
      
      //for de renglones
         for( int i = 0;i < rows; i++ )
            {
                int tiley = i * tileHeigth;
            
                    //for de columnas
                    for( int j = 0 ; j < cols ; j++ )
                    {
                        
                    if( colisionMap[ mapIndex ] == 1 )
                     {
                         int tilex = j * tilewidth;
                   
            int halfWidth = tileWidth/2;
            int halfHeigth = tileHeight/2;
            int centerX = tilex +  halfWidth;
            int centerY = tiley +  halfHeigth;
            
//		String side = Config.COLISION_NONE;        
                
                //get distance vectors
		float vx = s.getCenterX() - centerX;
		float vy = s.getCenterY() + fakeY - centerY;
                
		float combinedHalfWidth = s.getHalfWidth() + halfWidth;
		float combinedHalfHeight = s.getHalfHeight() + halfHeigth;
            
                
                //collision on x axis
                if( Math.abs(vx) < combinedHalfWidth )
		{
                    //collision on y axis
			if(Math.abs(vy) < combinedHalfHeight)
			{
                            
                            //collision made 
                            
				float overlapX=combinedHalfWidth-Math.abs(vx);
				float overlapY=combinedHalfHeight-Math.abs(vy);
		
//                            if( overlapX < overlapY )
//                                    {
//					
//					if( vx > 0 )
//					{
//                                            s1.setX( s1.getX()+overlapX );
//                                            return Config.COLISION_LEFT;
//					}
//					else
//					{
//                                            s1.setX( s1.getX()-overlapX );
//                                            return Config.COLISION_RIGHT;
//					}
//
//                                    }//
//                                else
                               if( overlapX > overlapY )
				{

                                    if( vy > 0 )
					{
//                                            s1.setY( s1.getY()+overlapY );
//                                            return Config.COLISION_TOP;
                                        }
                                    else 
					{
//                                           s1.setY( s1.getY()  - overlapY );
//                                            return Config.COLISION_BOTTOM;
                                            return true;
					}

				}

			}//height
        
		}//width

                   
                   
                   
                   
//                 return  rectangleColision(
//                         ( int )s.getX(), ( int )s.getY() + fakeY, ( int )s.getW(), ( int )s.getH(),
//                         tilex, tiley, tileWidth, tileHeight);
//                int x, int y, int w, int h,
//                int x2, int y2, int w2, int h2);
//                   
                         //return true if there is a collision
//                         return  rectangleColision(  x, y, w, h, 
//                                 tilex, tiley, tileWidth, tileHeight );
//                         

//                        String str = blockRectangleWithSlope2( 
//                                ( int ) s.getCenterX()
//                                ,
//                                ( int ) s.getCenterY() + fakeY,
//                                ( int ) s.getW(), 
//                                ( int ) s.getH(),
//                                tilex, 
//                                tiley,
//                                tileWidth, 
//                                tileHeight );
//                         
//                        if( str.equals( "BOTTOM" ) )
//                        {
//                            
//                            System.out.println("x1 y1 "+s.getX()+" - "+s.getY()+" -->"+s.getY()+fakeY );
//                            System.out.println("w1 h1 "+s.getW()+" - "+s.getH() );
//                            System.out.println("x2 y2 "+tilex+" - "+tiley );
//                            System.out.println("w2 h2 "+tileWidth+" - "+tileHeight );
//
//                            
//                            System.out.println( "--> "+mapIndex+" - "+str );
//                        }
//                         
//                         return str;
                     }//if validacion si se checa colision
                 
                      if( mapIndex < totalTiles )
                      mapIndex++;
                      
                      
                    }//for cols
            }//for rows
      
          return false;
        }//checkcolisiontile

          
      /**
     * funtion that check colision along y axis and returns TOP and BOTTOM collision
     * NOTE: this function should be used on update method for a game that implement
     * gravity
     * @param s
     * @param x
     * @param y
     * @param width
     * @param height
           */
          public void blockYAxisRectangles( Sprite s, float x, float y, float width, float height )
          {
            float halfWidth = width / 2;
            float halfHeigth = height / 2;
            float centerX = x + halfWidth;
            float centerY = y + halfHeigth;
            
		String side = Config.COLISION_NONE;        
                
                //get distance vectors
		float vx = s.getCenterX() - centerX;
		float vy = s.getCenterY() - centerY;
            
            
              float combinedHalfWidth = s.getHalfWidth()+halfWidth;
	      float combinedHalfHeight = s.getHalfHeight()+halfHeigth;
            
            if( Math.abs( vx ) < combinedHalfWidth )
            {
            
                if( Math.abs( vy ) < combinedHalfHeight )
                {
                //there must be a colision here
                    
                    float overlapX = combinedHalfWidth - Math.abs( vx );
		    float overlapY = combinedHalfHeight - Math.abs( vy );
                    
                                if(overlapX >= overlapY)
				{
                                 
                                    if(vy > 0)
					{
                                             System.out.println("-> ");
//						side = Config.COLISION_TOP;
//						if(move)s2.setY(s2.getY()-1);
						s.setY( s.getY() + overlapY );
                                         }
					else
					{
//						side = Config.COLISION_BOTTOM;
//						if(move)s2.setY(s2.getY()+1);
                                            System.out.println("-> "+s.getY()+" - "+overlapY+" "+(s.getY()-overlapY));
                                                s.setY( s.getY() - overlapY );
                                                
					}
				}//
                                
                }//heig
                
            }//
          
          }//
          
          
          
          /**
           * funtion that check colision along X axis and returns TOP and BOTTOM collision
           * NOTE: this function should be used on update method
           * @param s
           * @param x
           * @param y
           * @param width
           * @param height 
           */
          public void blockXAxisRectangles( Sprite s, float x, float y, float width, float height )
          {
              
               float halfWidth = width / 2;
            float halfHeigth = height / 2;
            float centerX = x + halfWidth;
            float centerY = y + halfHeigth;
            
		String side = Config.COLISION_NONE;        
                
                //get distance vectors
		float vx = s.getCenterX() - centerX;
		float vy = s.getCenterY() - centerY;
            
                float combinedHalfWidth = s.getHalfWidth()+halfWidth;
                float combinedHalfHeight = s.getHalfHeight()+halfHeigth;
            
            if( Math.abs( vx ) < combinedHalfWidth )
            {
            
                if( Math.abs( vy ) < combinedHalfHeight )
                {
                //there must be a colision here
                    
                    float overlapX = combinedHalfWidth - Math.abs( vx );
		    float overlapY = combinedHalfHeight - Math.abs( vy );
                    
		                
                                if( overlapX < overlapY )
                                {
                                    System.out.println("COL ON X");            
                                    if( vx > 0 )
					{
//						side= Config.COLISION_LEFT;
//						if(move)s2.setX(s2.getX()-1);
						s.setX(s.getX()+overlapX);
					}
					else
					{
//						side = Config.COLISION_RIGHT;
//						if(move)s2.setX(s2.getX()+1);
						s.setX(s.getX()-overlapX);
					}
                                    
                                }//
                                
                    
                }//heig
                
            }//
              
          }//
          
          
          
          public boolean checkSlopeCollision( int tileValue, Sprite spr, Tile tile )
          {

              switch( tileValue )
              {
                case 1:
                    return blockRectangle( spr, tile.getX(),tile.getY(), tile.getW(), tile.getH() )
                            .equals(Config.COLISION_BOTTOM );
                
                  case 2:
                  case 3:
                      
                      if( rectangleColision(
                        spr.getX(),
                        spr.getY(),
                        spr.getW(),
                        spr.getH(),
                        tile.getX(),
                        tile.getY(),
                        tile.getW(),
                        tile.getH() ) )
                        {
                                
                               //check the x positon of anchor
                               //and iterate slope array
                               int colpos = spr.getAnchor().x - tile.getX();

                             /**
                              * if colpos is greater than 0 and its less that tile width mean
                              * the anchor is inside that tile, hence we have to calculate
                              * the correct value for the y position on the slope
                              */
                               if( colpos  > 0 && colpos <= tile.getW() )
                               {

                                   int yval = colpos;

                                   if( tile.getValue() == 3 )
                                   { yval = tile.getW() - colpos; }

                                   spr.setY( ( tile.getY() - spr.getH() ) + ( tile.getH() - yval ) );

                                   return true;
                               }//
                                    
                        }//if there is any collision
                      
                      break;
                  case 4:
                  case 5:
                      break;
              } 
              return false;
          }//
          
          
}//collision
