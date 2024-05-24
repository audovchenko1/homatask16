package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * LoginPage class represent login page on website with ability to interact with page elements
 */
public class LoginPage {

    private WebDriver driver;

    private WebDriverWait wait;

    @FindBy(id = "sign_in_websiteUser_email")
    private WebElement emailField;
    @FindBy(id = "sign_in_password")
    private WebElement passwordField;
    @FindBy(xpath = "//button[contains(@class, 'custom-button') and contains(@class, 'submit') and @type='send']")
    private WebElement loginButton;
    @FindBy(xpath = "//div[@class='alert alert-error']")
    private WebElement validationErrorMessage;

    /**
     * Valid email for auth
     */
    public static final String email = "jojiyo6778@cgbird.com";

    /**
     * Valid password for auth
     */
    public static final String password = "A12345678";

    /**
     *
     * @param driver WebDriver for finding elements on page
     */
    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    /**
     * This method allow you to set any string in email field
     *
     * @param email String that contain an email address that will be filled in email field
     */
    public void setEmail(String email)
    {
        emailField.sendKeys(email);
    }

    /**
     * This method allow you to set any string in password field
     *
     * @param password String that contain a password that will be filled in password field
     */
    public void setPassword(String password)
    {
        passwordField.sendKeys(password);
    }

    /**
     * This method allows you to trigger button click for login button
     */
    public void clickLoginButton()
    {
        loginButton.click();
    }

    /**
     * This method returns a string from error block after unsuccessful auth attempt
     *
     * @return String with error message of incorrect login/password data
     */
    public String getErrorMessageText()
    {
        wait.until(ExpectedConditions.visibilityOf(validationErrorMessage));
        return validationErrorMessage.getText();
    }
}

