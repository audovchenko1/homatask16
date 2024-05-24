import PageObjects.LoginPage;
import PageObjects.ProfilePage;
import PageObjects.RegistrationPage;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import static org.testng.Assert.*;
import static org.assertj.core.api.Assertions.*;

public class AuthTest {

    private WebDriver driver;

    private Faker faker;

    @BeforeClass(alwaysRun = true)
    public void init()
    {
        driver = WebDriverFactory.createWebDriver("chrome");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        faker = new Faker();
    }

    @Test(priority = 1)
    public void testRegistration()
    {
        driver.get("https://www.xda-developers.com/register/");
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.setEmail(
                faker.internet().emailAddress()
        );
        registrationPage.setPassword(
                faker.internet().password()
        );
        registrationPage.clickSubmit();
        String actualErrorText = registrationPage.getCaptchaErrorText();
        String expectedErrorText = "Captcha is required";

        assertEquals(expectedErrorText, actualErrorText);
    }

    @Test(priority = 2)
    public void testAuthWithWrongCredentials()
    {
        driver.get("https://www.xda-developers.com/login/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setEmail("test@example.com");
        loginPage.setPassword("testpass");
        loginPage.clickLoginButton();
        String actualWrongCredentialsErrorMessage = loginPage.getErrorMessageText();
        String expectedWrongCredentialsErrorMessage = "Your login credentials are incorrect.";

        assertEquals(expectedWrongCredentialsErrorMessage, actualWrongCredentialsErrorMessage);
    }

    @Test(priority = 3)
    public void testAuthWithCorrectCredentials()
    {
        driver.get("https://www.xda-developers.com/login/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setEmail(LoginPage.email);
        loginPage.setPassword(LoginPage.password);
        loginPage.clickLoginButton();

        ProfilePage profilePage = new ProfilePage(driver);
        String currentUrlPath = profilePage.getCurrentUrlPath();
        String expectedUrlPath = "https://www.xda-developers.com/profile/";

        assertEquals(currentUrlPath, expectedUrlPath);
        assertEquals(LoginPage.email, profilePage.getEmailLabelText());
    }

    @Test(priority = 4)
    public void testLogout()
    {
        driver.get("https://www.xda-developers.com/profile/");
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.hoverProfileButton();
        profilePage.clickLogoutButton();

        String currentUrlPath = driver.getCurrentUrl();
        String expectedUrlPath = "https://www.xda-developers.com/login/";

        assertThat(currentUrlPath)
                .contains("/login/")
                .startsWith("https://")
                .isEqualTo(expectedUrlPath);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown()
    {
        if (driver != null) {
            driver.quit();
        }
    }
}
