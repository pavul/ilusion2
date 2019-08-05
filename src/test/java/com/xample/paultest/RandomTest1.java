
package com.xample.paultest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * this is the first class to start teaching how Junit Test Works
 * @author PavulZavala
 */
public class RandomTest1 
{
   
    @Before
   public void setUp()
   {
       System.err.println(" BEFORE CALLED ");
   }
    
   @Test
   public void firstTest()
   {
   
      Assert.assertTrue( true );
   
   }
    

}//
