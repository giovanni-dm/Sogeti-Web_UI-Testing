package test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Test1 {
	
	
	    private WebDriver driver;
	    private WebDriverWait wait;
	    private Actions action;

	    @BeforeClass
	    public void setUp() {
	        // Set the property for the ChromeDriver
	        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Giovanni-Outlook\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	        action = new Actions(driver);
	        driver.get("https://www.sogeti.com/");
	        WebElement decline_cookies = driver.findElement(By.className("declineCookie"));
	        decline_cookies.click();
	    }

	    @Test
	    public void testAutomationPageNavigation() {
	        // Hover over 'Services' link
	        WebElement servicesLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"main-menu\"]/ul/li[3]/div[1]")));
	        action.moveToElement(servicesLink).perform();

	        // Click on 'Automation' link
	        WebElement automationLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Automation']")));
	        action.moveToElement(automationLink).click().perform();

	        // Verify 'Automation' page is displayed
	        WebElement bodyText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
	        Assert.assertTrue(bodyText.getText().contains("Automation"), "'Automation' text is not visible on the page");

	        // Hover again over 'Services' link and verify selections
	        action.moveToElement(servicesLink).perform();
	        
	        // Check if "Automation" is selected
	        
	        WebElement is_selected = driver.findElement(By.className("selected current expanded"));
	        boolean isDisplayed = is_selected.isDisplayed();
	            
	        Assert.assertTrue(isDisplayed, "The element Automation is not displayed.");
	    
	    
	    }

	    @AfterClass
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	


