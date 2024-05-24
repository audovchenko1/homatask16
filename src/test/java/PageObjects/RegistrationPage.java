package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {

    private WebDriver driver;

    private WebDriverWait wait;

    @FindBy(id = "register_websiteUser_email")
    private WebElement emailField;

    @FindBy(id = "register_password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[contains(@class, 'custom-button') and contains(@class, 'submit') and @type='send']")
    private WebElement submitButton;

    @FindBy(id = "f-validation")
    private WebElement captchaError;

    public RegistrationPage(WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    public void setEmail(String email)
    {
        this.emailField.sendKeys(email);
    }

    public void setPassword(String password)
    {
        this.passwordField.sendKeys(password);
    }

    public void clickSubmit()
    {
        this.submitButton.click();
    }

    public String getCaptchaErrorText()
    {
        wait.until(ExpectedConditions.visibilityOf(captchaError));
        return captchaError.getText();
    }
}
