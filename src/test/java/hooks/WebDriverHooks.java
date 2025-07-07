package hooks;

import java.net.MalformedURLException;
import java.net.URL;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import helper.ConfigManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.github.cdimascio.dotenv.Dotenv;

public class WebDriverHooks {
    public static WebDriver driver;
    private static final Dotenv dotenv = Dotenv.configure().load();

    @Before
    public void setUp() throws MalformedURLException{
        // Setup WebDriver
        ChromeOptions options = new ChromeOptions();

        if (dotenv.get("ENVIRONMENT").equals("local")) {
            // Local environment setup
            // options.addArguments("--headless=new");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--no-sandbox");
            driver = new ChromeDriver(options);
        }else{ 
            String remoteUrl = System.getenv("SELENIUM_REMOTE_URL");
            if (remoteUrl == null || remoteUrl.isEmpty()) {
                throw new IllegalArgumentException("Environment variable SELENIUM_REMOTE_URL is not set.");
            }
            // options.addArguments("--headless=new");
            driver = new RemoteWebDriver(new URL(remoteUrl), options);
        }
        driver.get("https://rahulshettyacademy.com/client");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
    }

    @After
    public void tearDown() {
        // Close the browser after the test
        if (driver != null) {
            driver.quit();
        }
    }
}
