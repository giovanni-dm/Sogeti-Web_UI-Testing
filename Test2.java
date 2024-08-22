package test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;
import java.util.UUID;


public class Test2 {

    public static void main(String[] args) {
        // Set path to the WebDriver, e.g., ChromeDriver
    	System.setProperty("webdriver.chrome.driver", "C:\\Users\\Giovanni-Outlook\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        Actions action = new Actions(driver);
        
        // Generate random data
        String randomUserFirstName = "First Name " + UUID.randomUUID().toString().substring(0, 8);
        String randomUserFamilyName = "Family Name " + UUID.randomUUID().toString().substring(0, 8);
        String randomEmail = "user" + new Random().nextInt(1000) + "@example.com";
        String randomPhoneNumber = String.format("%011d", new Random().nextInt(1000000000));
        
     


        try {
            // 1. Navigate to the URL
            driver.get("https://www.sogeti.com/");

            // Maximise window and decline cookies
            driver.manage().window().maximize();
            WebElement decline_cookies = driver.findElement(By.className("declineCookie"));
	        decline_cookies.click();
            
            long time = 2000;
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
            
            

            // 2. Hover over Services Link and Click Automation link

            WebElement servicesLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"main-menu\"]/ul/li[3]/div[1]")));
	        action.moveToElement(servicesLink).perform();

	        // Click on 'Automation' link
	        WebElement automationLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Automation']")));
	        action.moveToElement(automationLink).click().perform();
	    
            
            


            // 3. Scroll to the Contact us Form
            WebElement contactForm = driver.findElement(By.name("__field_123927"));
            
            action.moveToElement(contactForm).perform();

            // 4. Fill the form with random data
            
            
            driver.findElement(By.xpath("//*[@id=\"4ff2ed4d-4861-4914-86eb-87dfa65876d8\"]")).sendKeys(randomUserFirstName);
            driver.findElement(By.id("11ce8b49-5298-491a-aebe-d0900d6f49a7")).sendKeys(randomUserFamilyName);
            driver.findElement(By.id("056d8435-4d06-44f3-896a-d7b0bf4d37b2")).sendKeys(randomEmail);
            driver.findElement(By.id("755aa064-7be2-432b-b8a2-805b5f4f9384")).sendKeys(randomPhoneNumber);
            driver.findElement(By.id("88459d00-b812-459a-99e4-5dc6eff2aa19")).sendKeys("This is a test message.");

            // 5. Check the I agree checkbox
            WebElement agreeCheckbox = driver.findElement(By.id("__field_1239350"));
            
            Boolean isEnabled = agreeCheckbox.isEnabled();
            
            System.out.println("Checkbox is enabled: "+isEnabled);

            // 6. Click SUBMIT button
            WebElement submitButton = driver.findElement(By.id("b35711ee-b569-48b4-8ec4-6476dbf61ef8"));
            submitButton.click();

            // 7. Assert Thank you message
            WebElement thankYouMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("thank-you-message")));
            assert thankYouMessage.getText().contains("Thank you");

            System.out.println("Test completed successfully."); 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }

	
}
