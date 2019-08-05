
package com.xample.paultest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author PavulZavala
 */
public class SeleniumTest1 
{

    
    
public static void main( String... args )
{

    System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
 
    WebDriver driver = new ChromeDriver();

    driver.navigate().to( "https://www.google.com" );

    String appTitle = driver.getTitle();

    System.out.println("Application title is : "+appTitle);

    //driver.quit();
    
}//main
    
    
}//
