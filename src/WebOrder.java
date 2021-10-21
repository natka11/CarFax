import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.testng.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.Random;
import java.util.concurrent.TimeUnit;


public class WebOrder {
    public static void main(String[] args) throws InterruptedException {

//1.Launch Chrome browser.

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\magda\\Desktop\\Selenium\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
// 2. Navigate to http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx

        driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
//3. Login using username Tester and password test

        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
        driver.findElement(By.name("ctl00$MainContent$password")).sendKeys("test");
        driver.findElement(By.name("ctl00$MainContent$login_button")).click();

//4. Click on Order link

        driver.findElement(By.linkText("Order")).click();

// 5. Enter a random product quantity between 1 and 100

        int random = (1 + (int) (Math.random() * 100));
        String str = new String(String.valueOf(random));
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).sendKeys(str);
        driver.findElement(By.className("btn_dark")).click();

//6.  Click on Calculate and verify that the Total value is correct.

        if ( random < 10 ) {
            int totalValue = random * 100;
            String str1 = new String(String.valueOf(totalValue));
            driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtTotal")).equals(str1);

        } else {
            int totalValue = (int) (random * 100 * 0.08);
            String str1 = new String(String.valueOf(totalValue));
            driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtTotal")).equals(str1);
        }

// 7. Generate and enter random first name and last name.
        String[] firstName = new String[]{"Adam", "Alex", "Aaron", "Ben", " Paul", "Anna", "Betty", "Emilia", "Natalia", "Carlina"};
        String[] lastName = new String[]{"Smith", "Novak", "Rudy", "Vo", "Nixon", "Obama", "Clinton", "Trump", "Walesa", "Biden"};
        String firstName1 = firstName[new Random().nextInt(firstName.length)];
        String lastName1 = lastName[new Random().nextInt(lastName.length)];
        String fullName = firstName1 + " " + lastName1;

        driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtName")).sendKeys(fullName);

// 8. Generate and Enter random street address

        String num1 = "0123456789";
        Random random1 = new Random();
        String str2 = "";
        for (int i = 0; i < 4; i++) {
            int randomIndex1 = random1.nextInt(num1.length());
            str2 += (num1.charAt(randomIndex1));
        }

        String[] street = new String[]{"New York Ave", "Dayspring", "18th Street NW", "14th Street", "U street", "Beach Drive", "H Street", "Minnesota Ave", "Salem Street", "Memorial Drive"};
        String streetName = street[new Random().nextInt(lastName.length)];
        String address = str2 + " " + streetName;
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox2")).sendKeys(address);

//9. Generate and Enter random city

        String[] city = new String[]{"Washington DC", "Los Angeles", "New York", "Boston", "Newark", "Chicago", "Denver", "Houston", "Seattle", "Detroit"};
        String cityName = city[new Random().nextInt(lastName.length)];
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox3")).sendKeys(cityName);

//10. Generate and Enter random state

        String[] state = new String[]{"DC", "VA", "NY", "MA", "NJ", "IL", "CA", "CO", "AZ", "TX"};
        String stateName = state[new Random().nextInt(lastName.length)];
        driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox4")).sendKeys(stateName);

//11.Generate and Enter a random 5 digit zip code
        String num = "0123456789";
        Random random3 = new Random();
        String str4 = "";
        for (int i = 0; i < 5; i++) {
            int randomIndex1 = random3.nextInt(num.length());
            str4 += (num.charAt(randomIndex1));
        }
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox5")).sendKeys(str4);

//12.Select the card type randomly. On each run your script should select a random type.

        List<WebElement> radioButtons = driver.findElements(By.cssSelector("input[type='radio']"));
        int randomNo = (int) (Math.random() * radioButtons.size());
        radioButtons.get(randomNo).click();

// 13. Generate and enter the random card number
        String num2 = "0123456789";
        String card = "";
        String cardName ="";
        if ( randomNo == 0 ) {
            for (int i = 0; i < 16; i++) {
                Random random5 = new Random();
                int randomIndex1 = random5.nextInt(num.length());
                card += (num.charAt(randomIndex1));
                cardName = "Visa";
            }
            String cardVisa = "4" + card;
            driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys(cardVisa);
        } else if ( randomNo == 1 ) {
            for (int i = 0; i < 16; i++) {
                Random random6 = new Random();
                int randomIndex1 = random6.nextInt(num.length());
                card += (num.charAt(randomIndex1));
                cardName = "MaterCard";
            }
            String cardMaterCard = "5" + card;
            driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys(cardMaterCard);
        } else if ( randomNo == 2 ) {
            for (int i = 0; i < 14; i++) {
                Random random7 = new Random();
                int randomIndex1 = random7.nextInt(num.length());
                card += (num.charAt(randomIndex1));
                cardName = "American Express";
            }
            String cardAMEX = "3" + card;
            driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys(cardAMEX);
        }

// 14. Enter a valid expiration date (newer than the current date)

        String expirationDate = "11/22";
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox1")).sendKeys(expirationDate);

//15.Click on Process

        driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();

//16.Verify that “New order has been successfully added” message appeared on the page.

        String expectedText = "New order has been successfully added";

        String pageSource = driver.getPageSource();

        assertTrue(pageSource.contains(expectedText));

        //17. Click on View All Orders link

        driver.findElement(By.linkText("View all orders")).click();

//18. The placed order details appears on the first row of the orders table.
// Verify that the entire information contained on the row (Name, Product, Quantity, etc)
// matches the previously entered information in previous steps.


//        Select default = new Select(driver.findElement(By.name("ctl00$MainContent$fmwOrder$ddlProduct")));
//        default.selectByIndex(1);
//        System.out.println(default);
        LocalDate date = LocalDate.now();
        String currentDate = date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        List<WebElement> firstRow = driver.findElements(By.xpath("//table[@id]//tbody//tr[2]//td"));
        List<String> actualList = getText(firstRow);
        List<String> expectedList = new ArrayList<>
                (Arrays.asList(fullName, "MyMoney", str, currentDate, address, cityName, stateName,str4, cardName, card, expirationDate));

        assertEquals(actualList, expectedList);
        System.out.println(Arrays.toString(new List[]{actualList}));
        System.out.println(Arrays.toString(new List[]{expectedList}));



//19. Log out of the application

      driver.findElement(By.id("ctl00_logout")).click();

      driver.quit();

    }

    public static List<String> getText(List<WebElement> list) {

        List<String> acutal = new ArrayList<>();

        for (WebElement element : list) {
            String text = element.getText();
            if ( !text.isEmpty() ) {
                acutal.add(text);
            }
        }
        return acutal;
    }
}







