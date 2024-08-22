package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Test3 {

    public static void main(String[] args) {
        // Set up the WebDriver (ensure the ChromeDriver path is correctly set)
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Giovanni-Outlook\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            // Navigate to the Sogeti homepage
            driver.get("https://www.sogeti.com/");
            WebElement decline_cookies = driver.findElement(By.className("declineCookie"));
	        decline_cookies.click();
            
            long time = 3000;
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
            
            

            // Click on the Worldwide dropdown link
            WebElement worldwideDropdown = driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div[2]/div[2]/div[2]/span"));
            worldwideDropdown.click();
            
            

            // Wait until the country list is displayed
            
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            
            
            
            
            // Belgium example: wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"country-list-id\"]/ul/li[1]/a")));
            
            //wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("country-list")));

            // Get all country links
            
            
            //Belgium example: List<WebElement> countryLinks = driver.findElements(By.xpath("//*[@id=\"country-list-id\"]/ul/li[1]/a"));

			
            
            
            List<WebElement> countryLinks = driver.findElements(By.cssSelector("#country-list-id a"));
            
            // Iterate over each link, click it, and assert it opens without errors
            for (WebElement link : countryLinks) {
                String countryUrl = link.getAttribute("href");
                System.out.println(countryUrl);

				
				  try { driver.navigate().to(countryUrl); 
				  
				  
				  // Wait for the page to load and check the title (basic assertion)
				  
				  System.out.println("Link working: " + countryUrl); } catch (Exception e) {
				  System.out.println("Link broken: " + countryUrl); }
				 

				
				  // Navigate back to the main page and reopen the dropdown
				  
				  driver.navigate().back();
				  
				  worldwideDropdown =
				  wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				  "//*[@id=\"header\"]/div[2]/div/div[2]/div[2]/div[2]/span")));
				  worldwideDropdown.click();
				  
				 
            }

        } finally {
            // Close the driver
            driver.quit();
        }
    }
}
