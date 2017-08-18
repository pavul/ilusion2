/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilusion2.physics;

import com.ilusion2.config.Config;
import com.ilusion2.sprite.Sprite;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            
            boolean hit=false;
            float vx=s1.getCenterX()-s2.getCenterX();
            float vy=s1.getCenterY()-s2.getCenterY();

            float combinedHalfWidth=s1.getHalfWidth()+s2.getHalfWidth();
            float combinedHalfHeight=s1.getHalfHeight()+s2.getHalfHeight();

            if(Math.abs(vx)<combinedHalfWidth)
            {
                return ( (Math.abs(vy) < combinedHalfHeight) );
            }
            return hit;
		
	}//colision rectangular
        
        
        public  boolean rectangleColision(
                int x, int y, int w, int h,
                int x2, int y2, int w2, int h2)
	{
            
            boolean hit=false;
            float vx = x + w / 2 - x2 + w2 / 2;//  s1.getCenterX() - s2.getCenterX();
            float vy = y + h / 2 - y2 + h2 / 2;//   s1.getCenterY() - s2.getCenterY();

            
            //@TODO i can improve this more, creating proper variables
            float combinedHalfWidth = w/2 +w2/2; //  s1.getHalfWidth()+s2.getHalfWidth();
            float combinedHalfHeight = h/2 +h2/2; //  s1.getHalfHeight()+s2.getHalfHeight();

            if(Math.abs(vx)<combinedHalfWidth)
            {
                return ( (Math.abs(vy) < combinedHalfHeight) );
            }
            return hit;
		
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
		String side="";
		float vx=s1.getCenterX()-s2.getCenterX();
		float vy=s1.getCenterY()-s2.getCenterY();
		
		float combinedHalfWidth=s1.getHalfWidth()+s2.getHalfWidth();
		float combinedHalfHeight=s1.getHalfHeight()+s2.getHalfHeight();
		
		if(Math.abs(vx)<combinedHalfWidth)
		{
			if(Math.abs(vy)<combinedHalfHeight)
			{
				float overlapX=combinedHalfWidth-Math.abs(vx);
				float overlapY=combinedHalfHeight-Math.abs(vy);
				
				if(overlapX>=overlapY)
				{
					if(vy>0)
					{
						side="TOP";
						if(move)s2.setY(s2.getY()-1);
						s1.setY(s1.getY()+overlapY);
                                         }
					else
					{
						side="BOTTOM";
						if(move)s2.setY(s2.getY()+1);
                                                s1.setY(s1.getY()-overlapY);
                                                
					}
					if(bouncing)s1.setSpeedY(s1.getSpeedY()*-1);
				}
				else
				{
					
					if(vx>0)
					{
						side="LEFT";
						if(move)s2.setX(s2.getX()-1);
						s1.setX(s1.getX()+overlapX);
					}
					else
					{
						side="RIGHT";
						if(move)s2.setX(s2.getX()+1);
						s1.setX(s1.getX()-overlapX);
					}
					if(bouncing)s1.setSpeedX(s1.getSpeedX()*-1);
		         }//
			}//height
			else
			{
				side="";
//				side="NONE";
			}
			
		}//width
		else
		{
			side="";
//			side="NONE";
		}
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
	public String blockRectangle(Sprite s1,int x, int y, int width,int height)
	{
            int halfWidth = width / 2;
            int halfHeigth = height / 2;
            int centerX = x + halfWidth;
            int centerY = y + halfHeigth;
            
		String side = Config.COLISION_NONE;        
                
                //get distance vectors
		float vx=s1.getCenterX()-centerX;
		float vy=s1.getCenterY()-centerY;
		
                
		float combinedHalfWidth = s1.getHalfWidth()+halfWidth;
		float combinedHalfHeight = s1.getHalfHeight()+halfHeigth;
            
                
                //collision on x axis
                if( Math.abs(vx) < combinedHalfWidth )
		{
                    //collision on y axis
			if(Math.abs(vy) < combinedHalfHeight)
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
            String aux="";
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
                        if (side.equals(Config.COLISION_BOTTOM))
                        {
                        aux=Config.COLISION_BOTTOM;
                        }
                      
                     }//if validacion si se checa colision
                 
                      if( mapIndex < totalTiles )
                      mapIndex++;
                      
                      
                    }//for cols
            }//for rows
      
//          System.out.println("-checkColsionTile se regreso SIDE: "+side);
        
          if(!aux.equals(""))
          {
          side=aux;
          }
          
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
//            if(cols <=0 || rows <= 0)
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
//                                    
//                                break;
//                            case 2: //maybe slope
//                                int tilx = j * tilewidth;
//                                
//                                //if there are some kind of collision
//                                   if( !blockRectangleWithSlope(spr, tilx, tiley, tilewidth,tileHeigth )
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
                        
                    if( colisionMap[ mapIndex ] == 1)
                     {
                         int tilex = j * tilewidth;
//                   /.,      
                   
                 return  rectangleColision(
                         ( int )s.getX(), ( int )s.getY() + fakeY, ( int )s.getW(), ( int )s.getH(),
                         tilex, tiley, tileWidth, tileHeight);
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
      
//          return "NONE";
          return false;
        }//checkcolisiontile
//        
//        
        
//        public boolean checkCollionFree()
//        {
//            
//            
//        }
//        
        
        
}//collision
