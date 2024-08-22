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

	            // Set a timeout
	            long time = 3;
	            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));

	            // Create a WebDriverWait instance
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	            // Click on the Worldwide dropdown link
	            WebElement worldwideDropdown = driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div[2]/div[2]/div[2]/span"));
	            worldwideDropdown.click();

	            // Iterate over each country link
	            List<WebElement> countryLinks = driver.findElements(By.cssSelector("#country-list-id a"));
	            for (int i = 0; i < countryLinks.size(); i++) {
	                // Re-fetch the list of country links after each navigation back
	                countryLinks = driver.findElements(By.cssSelector("#country-list-id a"));

	                // Get the current link
	                WebElement link = countryLinks.get(i);
	                String countryUrl = link.getAttribute("href");
	                System.out.println(countryUrl);

	                try {
	                    // Navigate to the country page
	                    driver.navigate().to(countryUrl);

	                    // Optionally, handle cookies on the new page if necessary
	                    try {
	                        WebElement decline_cookies_2 = driver.findElement(By.className("declineCookie"));
	                        decline_cookies_2.click();
	                    } catch (Exception e) {
	                        // Handle if no cookie banner appears
	                    }

	                    // Simple validation
	                    System.out.println("Link working: " + countryUrl);
	                } catch (Exception e) {
	                    System.out.println("Link broken: " + countryUrl);
	                }

	                // Navigate back to the main page
	                driver.navigate().back();

	                // Reopen the dropdown
	                worldwideDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"header\"]/div[2]/div/div[2]/div[2]/div[2]/span")));
	                worldwideDropdown.click();
	            }

	        } finally {
	            // Close the driver
	            driver.quit();
	        }
	    }
	}
