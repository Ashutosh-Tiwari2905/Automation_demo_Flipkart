package day23;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ShopNowAutomation {

    public static void main(String[] args) throws InterruptedException {
        // Choose browser: "chrome" or "firefox"
        String browser = "chrome";
        WebDriver driver = initializeDriver(browser);
        try {
            searchAndAddToCart(driver);
            proceedToCheckout(driver);
            //userAuthentication(driver);
           // enterShippingInformation(driver);
            //choosePaymentMethod(driver);
            //verifyOrderSummary(driver);
        } finally {
           driver.quit();
        }
    }

    // Initialize WebDriver based on the chosen browser
    public static WebDriver initializeDriver(String browser) {
        WebDriver driver;
        if (browser.equals("chrome")) {
        	WebDriverManager.chromedriver().setup();
         driver = new ChromeDriver();

        } else if (browser.equals("firefox")) {
        	WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser");
        }
        return driver;
    }

    // Search for a laptop and add it to the cart
    public static void searchAndAddToCart(WebDriver driver) throws InterruptedException {
        driver.get("https://www.flipkart.com");
        
        driver.manage().window().maximize();
        // Verify that the homepage loads successfully
        assert "Flipkart".equals(driver.getTitle());
        

        // Search for a laptop
        WebElement searchInput = driver.findElement(By.name("q"));
        searchInput.sendKeys("laptop");
        searchInput.sendKeys(Keys.RETURN);


      
        // Click on the first search result
        WebElement firstResult = driver.findElement(By.xpath("(//div[@class='_1AtVbE col-12-12'])[3]/div/div/div/a"));
        firstResult.click();
        
       String homepageurl= driver.getCurrentUrl();
        //switch to new tab
        Set<String> window= driver.getWindowHandles();
        for(String w: window) {
        	if(w!=homepageurl)
        	driver.switchTo().window(w);
        }
// Add the laptop to the cart
        driver.switchTo().defaultContent();
        WebElement addToCartButton = driver.findElement(By.xpath("//div[@class='_1p3MFP dTTu2M']/ul/li"));
        addToCartButton.click();

    }

    // Proceed to checkout
    public static void proceedToCheckout(WebDriver driver) throws InterruptedException {
        // User automatically navigates to the cart on clicking 'Add to cart' button
        

        // Verify that the correct item is in the cart
        assert driver.getPageSource().contains("laptop");
        // Proceed to checkout
        driver.switchTo().defaultContent();
        
        Thread.sleep(5000);
        WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//span[text()='Place Order']"));
        proceedToCheckoutButton.click();
    }

    /* Perform user authentication
    public static void userAuthentication(WebDriver driver) throws InterruptedException {
        // Enter valid credentials
    	driver.switchTo().defaultContent();
    	 Thread.sleep(5000);
        WebElement usernameInput = driver.findElement(By.xpath("//span[text()='Enter Email/Mobile number']"));
        usernameInput.sendKeys("tiwariashutosh126@gmail.com");
        Thread.sleep(2000);
        usernameInput.click();
        
        Thread.sleep(5000);

        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("your_password");

        // Log in
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));
        loginButton.click();

        // Wait for the checkout page to load after successful login
       // WebDriverWait wait = new WebDriverWait(driver, 10);
        //wait.until(ExpectedConditions.urlContains("checkout"));
    }

    // Enter shipping information
    public static void enterShippingInformation(WebDriver driver) {
        // Enter shipping information
        WebElement addressInput = driver.findElement(By.name("address"));
        addressInput.sendKeys("123 Main Street");

        WebElement cityInput = driver.findElement(By.name("city"));
        cityInput.sendKeys("City");

        WebElement stateInput = driver.findElement(By.name("state"));
        stateInput.sendKeys("State");

        WebElement zipInput = driver.findElement(By.name("zip"));
        zipInput.sendKeys("12345");

        // Proceed to the next step
        WebElement nextButton = driver.findElement(By.xpath("//button[contains(text(),'Next')]"));
        nextButton.click();
    }

    // Choose a payment method
    public static void choosePaymentMethod(WebDriver driver) {
        // Choose payment method
        WebElement paymentMethod = driver.findElement(By.xpath("//input[@value='credit_card']"));
        paymentMethod.click();
    }

    // Verify order summary
    public static void verifyOrderSummary(WebDriver driver) {
        // Verify order summary
        WebElement orderSummaryText = driver.findElement(By.xpath("//h2[contains(text(),'Order Summary')]"));
        assert orderSummaryText.getText().contains("Order Summary");
    }*/
}


