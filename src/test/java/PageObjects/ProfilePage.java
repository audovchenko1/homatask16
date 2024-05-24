package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    private WebDriver driver;

    private WebDriverWait wait;

    @FindBy(xpath = "//div[@id='user_profile_view']/div[4]/span")
    private WebElement emailLabel;

    @FindBy(xpath = "//li[@class='nav-elements']/a[contains(@class, 'nav-link') and contains(@class, 'i-user-active') and contains(@class, 'js-subscription-link')]")
    private WebElement headerProfileMenuButton;

    @FindBy(xpath = "//li[@class='nav-elements']/a[@href='/logout/' and contains(@class, 'nav-link')]")
    private WebElement logoutButton;

    public ProfilePage(WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public String getEmailLabelText()
    {
        return this.emailLabel.getText();
    }

    public String getCurrentUrlPath()
    {
        return this.driver.getCurrentUrl();
    }

    public void hoverProfileButton()
    {
        Actions hoverProfileButtonAction = new Actions(driver);
        hoverProfileButtonAction.moveToElement(headerProfileMenuButton).perform();
        this.wait.until(ExpectedConditions.visibilityOf(logoutButton));
    }

    public void clickLogoutButton()
    {
        this.logoutButton.click();
    }
}
