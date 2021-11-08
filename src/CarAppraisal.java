import com.sun.scenario.effect.ImageData;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.testng.Assert.*;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;
import java.util.concurrent.TimeUnit;



public class CarAppraisal {

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\magda\\Desktop\\Selenium\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://www.carmax.com/");
        driver.manage().deleteAllCookies();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1800)", "");

        driver.findElement(By.id("button-VIN")).click();
        driver.findElement(By.id("ico-form-vin")).sendKeys("4T1BE46K67U162207");
        driver.findElement(By.id("ico-form-zipcode")).sendKeys("22182");
        driver.findElement(By.xpath("//button[@class='submit-button kmx-button--primary kmx-button']")).click();
//
//     Thread.sleep(2000);
//     WebElement element = driver.findElement(By.id("//button[@class='submit-button kmx-button--primary kmx-button']"));
//     js.executeScript("arguments[0].click();",element);

        WebElement dropdownBoxForStyle = driver.findElement(By.id("select-ico-features-style"));
        Select select = new Select(dropdownBoxForStyle);
        select.selectByVisibleText("LE 4D Sedan 2.4L");

        Thread.sleep(1000);
        WebElement dropdownBoxForDrive = driver.findElement(By.xpath("//select[@id='select-ico-features-drive']"));
        Select selectDrive = new Select(dropdownBoxForDrive);
        selectDrive.selectByVisibleText("4 Wheel Drive/All Wheel Drive");

        Thread.sleep(1000);
        WebElement dropdownBoxForTransmission = driver.findElement(By.xpath("//select[@id='select-ico-features-transmission']"));
        Select selectTransmission = new Select(dropdownBoxForTransmission);
        selectTransmission.selectByVisibleText("Automatic");

//        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
//        for (WebElement checkbox : checkboxes) {
//            checkbox.click();


//        for (WebElement checkbox : checkboxes) {
//                 checkbox.click();
//                 Thread.sleep(1000);
//            WebElement d = driver.findElement(By.xpath("//label[@for='checkbox-ico-cb-feature-3040']"));
//            String m = d.getText();
//            if ( m.equals("Leather Seats")) {
//                   continue;

        driver.findElement(By.id("ico-step-Mileage_and_Condition-btn")).click();
        driver.findElement(By.xpath("//input[@name='currentMileage']")).sendKeys("60,000");


        List<WebElement> radioButtonList = driver.findElements(By.xpath("//input[@type='radio'][@class='mdc-radio__native-control'][@value='1']"));
        for (WebElement radioButton : radioButtonList) {

            new Actions(driver).sendKeys(Keys.PAGE_DOWN).perform();
            Thread.sleep(100);
            radioButton.click();
        }

        driver.findElement(By.xpath("//input[@class='kmx-text-field__input mdc-text-field__input'][@inputmode='email']")).sendKeys("test@test.com");


//        String expected = "2007 Toyota Camry";
//        String firstHeader = driver.findElement(By.xpath("//p[@id='vehicleInfo-YMM']")).getText().replace("\"","");
        //assertTrue(firstHeader.contains(expected));
        //js.executeScript("window.scrollBy(0,-1800)", "");


        driver.findElement(By.id("ico-continue-button")).click();

        String amount = driver.findElement(By.xpath("//h1[@data-qa='offer-amount']")).getText().substring(1, 6);
        assertEquals(amount, "6,400");

        driver.findElement(By.id("ico-set-my-appointment")).click();

        String parentWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if ( !parentWindow.contentEquals(windowHandle) ) {
                driver.switchTo().window(windowHandle);
                break;
            }

            WebElement location = driver.findElement(By.xpath("//select[@class='mdc-select__native-control']"));
            int ramdom = (1 + (int) (Math.random() * 16));
            Select appLocation = new Select(location);
            appLocation.selectByIndex(ramdom);


            /// date and time

            driver.findElement(By.xpath("//button[@class='kmx-button--primary kmx-button']")).click();

            driver.findElement(By.xpath("//a [@href='https://www.carmax.com/privacy-policy']")).click();

            Set<String> windowHandles = driver.getWindowHandles();
            for (String windowHandle1 : windowHandles) {
                driver.switchTo().window(windowHandle1);
                if ( driver.getTitle().equals("Privacy Policy | CarMax") ) {
                    break;
                }
            }
            driver.close();
            driver.switchTo().window(parentWindow);


            driver.quit();
        }

    }
}

