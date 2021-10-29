import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.testng.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.util.*;
import java.util.concurrent.TimeUnit;


public class CarFax {

    public static void main(String[] args) throws InterruptedException {

// 1   Navigate  to carfax.com

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\magda\\Desktop\\Selenium\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.carfax.com/");

//2.	Click on Find a Used Car

        driver.findElement(By.xpath(" //a[ contains ( text(), 'Find a Used Car' )]")).click();

//3.	Verify the page title contains the word “Used Cars”;

        String expectedText = "Used Cars";
        String pageSource = driver.getPageSource();
        assertTrue(pageSource.contains(expectedText));

//4.	Choose “Tesla” for  Make.

        WebElement dropdownBoxForType = driver.findElement(By.cssSelector("select[class='form-control search-make search-make--lp']"));
        Select select = new Select(dropdownBoxForType);
        select.selectByValue("Tesla");

        Thread.sleep(2000);


//5.	Verify that the Select Model dropdown box contains 4 current Tesla models - (Model 3, Model S, Model X, Model Y).
//
        List<String> listOfModel = new ArrayList<>();
        List <WebElement> options = driver.findElements(By.xpath("//optgroup[@class='current-models']//option[@class='model-option']"));
        for (WebElement option : options) {
            listOfModel.add(option.getText().trim());
        }
        //System.out.println(listOfModel);

        List<String> expectedModels = new ArrayList<>
                (Arrays.asList("Model 3", "Model S", "Model X", "Model Y"));
        assertEquals(listOfModel,expectedModels);


//6.	Choose “Model S” for Model.

        Select model = new Select(driver.findElement(By.name("model")));
        model.selectByValue("Model S");

//7.	Enter the zip code 22182 and click Next

        driver.findElement(By.name("zip")).sendKeys("22182");
        driver.findElement(By.id("make-model-form-submit")).click();

////8.	Verify that the page contains the text “Step 2 – Show me cars with”

        String expectedText2 = "Step 2 – Show me cars with";
        String pageSource1 = driver.getPageSource();
        assertTrue(pageSource.contains(expectedText));

////9.	Check all 4 checkboxes.


        driver.findElement(By.xpath("//span[@aria-label = 'Toggle noAccidents']")).click();
        driver.findElement(By.xpath("//span[@aria-label = 'Toggle oneOwner']")).click();
        driver.findElement(By.xpath("//span[@aria-label = 'Toggle personalUse']")).click();
        driver.findElement(By.xpath("//span[@aria-label = 'Toggle serviceRecords']")).click();


////10.	Save the count of results from “Show me X Results” button. In this case it is 9

        String countOfResults = driver.findElement(By.xpath("//div//span[@class ='totalRecordsText']")).getText();

////11.	Click on “Show me x Results” button.

         driver.findElement(By.xpath("//span[@class ='totalRecordsText']")).click();

////12.	Verify the results count by getting the actual number of results displayed in the page by getting the count of WebElements that represent each result


        int countNumber = Integer.parseInt(countOfResults);

        List<String> resultsNumber = new ArrayList<>();
        List <WebElement> number = driver.findElements(By.xpath("//div[@class ='srp-list-container  srp-list-container--srp']//article"));
        int numberListSize = number.size();

        assertEquals(numberListSize,countNumber);


////13.	Verify that each result header contains “Tesla Model S”.

     String expected = "Tesla Model S";

     String firstHeader = driver.findElement(By.xpath("//article[@id='listing_0']//h4")).getText();
     assertTrue(firstHeader.contains(expected));

     String secondHeader = driver.findElement(By.xpath("//article[@id='listing_1']//h4")).getText();
     assertTrue(secondHeader.contains(expected));

     String thirdHeader = driver.findElement(By.xpath("//article[@id='listing_2']//h4")).getText();
     assertTrue(thirdHeader.contains(expected));

     String fourthHeader = driver.findElement(By.xpath("//article[@id='listing_3']//h4")).getText();
     assertTrue(fourthHeader.contains(expected));

     String fiveHeader = driver.findElement(By.xpath("//article[@id='listing_4']//h4")).getText();
     assertTrue(fiveHeader.contains(expected));

     String sixHeader = driver.findElement(By.xpath("//article[@id='listing_5']//h4")).getText();
     assertTrue(sixHeader.contains(expected));

     String sevenHeader = driver.findElement(By.xpath("//article[@id='listing_6']//h4")).getText();
     assertTrue(sevenHeader.contains(expected));

     String eightHeader = driver.findElement(By.xpath("//article[@id='listing_7']//h4")).getText();
     assertTrue(eightHeader.contains(expected));

      String nineHeader = driver.findElement(By.xpath("//article[@id='listing_8']//h4")).getText();
      assertTrue(nineHeader.contains(expected));


////14.	Get the price of each result and save them into a List in the order of their appearance. (You can exclude “Call for price” options)

      List<String> listOfPrices= new ArrayList<>();
      List<WebElement> prices  = driver.findElements(By.xpath("//div[@class='srp-list-container  srp-list-container--srp']//span[@class='srp-list-item-price']"));

        for (WebElement price : prices) {
            listOfPrices.add(price.getText().substring(8).trim().replace(",",""));
        }


////15.	Choose “Price - High to Low” option from the Sort By menu

    Select priceHighToLow = new Select(driver.findElement(By.xpath("//select[@aria-label= 'SelectSort']")));
    priceHighToLow.selectByValue("PRICE_DESC");

    Thread.sleep(2000);


//////16.	Verify that the results are displayed from high to low price.

        Collections.sort(listOfPrices,Collections.reverseOrder());
        System.out.println(listOfPrices);

        List<String> highToLow= new ArrayList<>();
        List<WebElement> pricesHighToLow  = driver.findElements
                (By.xpath("//div[@class='srp-list-container  srp-list-container--srp']//span[@class='srp-list-item-price']"));
        for (WebElement priceHTL : pricesHighToLow) {
            highToLow.add(priceHTL.getText().substring(8).replace(",",""));

        }
        System.out.println(highToLow);




//////17.	Choose “Mileage - Low to High” option from Sort By menu
//

      Select mileageLowToHigh = new Select(driver.findElement(By.xpath("//select[@aria-label= 'SelectSort']")));
      mileageLowToHigh.selectByValue("MILEAGE_ASC");

      Thread.sleep(3000);

//////18.	Verify that the results are displayed from low to high mileage.

        List<String> mileages = new ArrayList<>();
        List<WebElement> mileageResults  = driver.findElements(By.xpath("//div[@class='srp-list-container  srp-list-container--srp']//article//strong[contains(text(), 'Mileage')]/parent::span"));
        for (WebElement mileages1: mileageResults) {
            mileages.add(mileages1.getText().substring(9,16).trim().replace(",",""));
        } Collections.sort(mileages);
        System.out.println(mileages);

       // List<WebElement> mileageResults1  = driver.findElements(By.xpath("//div[@class='srp-list-container  srp-list-container--srp']//div[@class='srp-list-item-basic-info srp-list-item-special-features']"));

//////19.	Choose “Year - New to Old” option from Sort By menu
//
        Select yearNewToOld = new Select(driver.findElement(By.xpath("//select[@aria-label= 'SelectSort']")));
        yearNewToOld.selectByValue("YEAR_ASC");

////20.	Verify that the results are displayed from new to old year.

        List<String> years = new ArrayList<>();
        List<WebElement> yearsCollection  = driver.findElements(By.xpath("//div[@class='srp-list-container  srp-list-container--srp']//h4[@class='srp-list-item-basic-info-model']"));
        for (WebElement year: yearsCollection) {
            years.add(year.getText().substring(0,4).trim());
        } Collections.sort(years,Collections.reverseOrder());
        System.out.println(years);

        }
    }




