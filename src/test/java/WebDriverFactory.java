import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

/**
 * This class represent factory for creating different WebDrivers depends on browser type string
 */
public class WebDriverFactory {

    /**
     * This method generate new WebDriver object based on browserType param.
     *
     * @param browserType String that represent browser type. Must be one of "chrome", "firefox", "safari".
     * @return new WebDriver object based on your browser type, with maximized window set by default.
     * @throws IllegalArgumentException if unsupported browser type is given to this function
     */
    public static WebDriver createWebDriver(String browserType) throws IllegalArgumentException {
        WebDriver driver;

        switch (browserType.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser type detected: " + browserType.toLowerCase());
        }

        driver.manage().window().maximize();

        return driver;
    }
}
