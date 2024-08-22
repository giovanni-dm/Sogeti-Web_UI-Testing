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
        Actions actions = new Actions(driver);
        
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
            WebElement servicesLink = driver.findElement(By.xpath("//*[@id=\\\"main-menu\\\"]/ul/li[3]/div[1]"));
            actions.moveToElement(servicesLink).perform();
            WebElement automationLink = driver.findElement(By.xpath("//a[text()='Automation']"));
            wait.until(ExpectedConditions.visibilityOf(automationLink));
            automationLink.click();
            
            

            // 3. Scroll to the Contact us Form
            WebElement contactForm = driver.findElement(By.id("99a12a58-3899-4fe1-a5c7-b9065fe635b0"));
            actions.moveToElement(contactForm).perform();

            // 4. Fill the form with random data
            contactForm.findElement(By.id("4ff2ed4d-4861-4914-86eb-87dfa65876d8")).sendKeys(randomUserFirstName);
            contactForm.findElement(By.id("11ce8b49-5298-491a-aebe-d0900d6f49a7")).sendKeys(randomUserFamilyName);
            contactForm.findElement(By.id("056d8435-4d06-44f3-896a-d7b0bf4d37b2")).sendKeys(randomEmail);
            contactForm.findElement(By.id("755aa064-7be2-432b-b8a2-805b5f4f9384")).sendKeys(randomPhoneNumber);
            contactForm.findElement(By.id("message")).sendKeys("This is a test message.");

            // 5. Check the I agree checkbox
            WebElement agreeCheckbox = contactForm.findElement(By.xpath("//input[@type='checkbox']"));
            agreeCheckbox.click();

            // 6. Click SUBMIT button
            WebElement submitButton = contactForm.findElement(By.xpath("//button[@type='submit']"));
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
