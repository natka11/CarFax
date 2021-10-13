import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.testng.Assert.*;
import org.openqa.selenium.By;

import java.security.SecureRandom;
import java.util.Random;


public class AutomationProject {

    public static void main(String[] args) {
//1. Navigate to http://duotifyapp.us-east-2.elasticbeanstalk.com/register.php
       System.setProperty("webdriver.chrome.driver","C:\\Users\\magda\\Desktop\\Selenium\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://duotifyapp.us-east-2.elasticbeanstalk.com/register.php");

// 2. Verify the the title is "Welcome to Duotify!"

        String expectedTitle = "Welcome to Duotify!";
        String actualTitle = driver.getTitle();
        assertEquals(actualTitle,expectedTitle);

// 3. Click on Signup here
        driver.findElement(By.id("hideLogin")).click();

//4.  Fill out the form with the required info
     String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
     Random random = new Random();
     String str = "";
     for (int i = 8; i < 25 ; i++) {
      int randomIndex1 = random.nextInt(chars.length());
      str += (chars.charAt(randomIndex1));
     } String username = str;

     String ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
     Random random1 = new Random();
     String str1 = "";
     for (int i = 0; i < 10; i++) {
      int randomIndex = random1.nextInt(ch.length());
      str1 += (ch.charAt(randomIndex)) ;
     } String email =  str1 + "@gmail.com";

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("firstName")).sendKeys("Betty");
        driver.findElement(By.id("lastName")).sendKeys("Smith");
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("email2")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys("Betty1234");
        driver.findElement(By.id("password2")).sendKeys("Betty1234");
//5. Click on Sign up
        driver.findElement(By.name("registerButton")).click();

//6. Once logged in to the application, verify that the URL is:
//http://duotifyapp.us-east-2.elasticbeanstalk.com/browse.php?
        assertEquals(driver.getCurrentUrl(),
                "http://duotifyapp.us-east-2.elasticbeanstalk.com/browse.php?");

 // 7. In the left navigation bar,verify that your first and last name matches
// the first and last name that you used when signing up.

        String firstAndLastName = "Betty Smith";
        String pageSource = driver.getCurrentUrl();
        assertTrue(pageSource.contains(firstAndLastName));
//8.Click on the username on the left navigation bar and verify
// the username on the main window is correct and then click logout.

        driver.findElement(By.id("nameFirstAndLast")).click();
        WebElement element = driver.findElement(By.className("userInfo"));
        assertEquals(element,"Betty Smith");
        driver.findElement(By.id("rafael")).click();

 //9.  Verify that you are logged out by verifying the URL is:
        //http://duotifyapp.us-east-2.elasticbeanstalk.com/register.php

        assertEquals(driver.getCurrentUrl(),
                "http://duotifyapp.us-east-2.elasticbeanstalk.com/register.php");
//10. Login using the same username and password when you signed up.
        driver.findElement(By.id("loginUsername")).sendKeys(username);
        driver.findElement(By.id("loginPassword")).sendKeys("Betty1234");
        driver.findElement(By.name("loginButton")).click();

//11. Verify successful login by verifying that the home page contains
// the text "You Might Also Like".

        String text = "You Might Also Like";
        String pageSource1 = driver.getCurrentUrl();
        assertTrue(pageSource1.contains(text));

//12. Log out once again and verify that you are logged out.

        driver.findElement(By.id("rafael")).click();

        assertEquals(driver.getCurrentUrl(),
                "http://duotifyapp.us-east-2.elasticbeanstalk.com/register.php");
    }
}
