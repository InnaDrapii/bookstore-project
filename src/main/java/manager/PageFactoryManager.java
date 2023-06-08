package manager;

import org.openqa.selenium.WebDriver;
import pageobject.HomePage;
import pageobject.LoginPage;
import pageobject.ProfilePage;

public class PageFactoryManager {
    WebDriver driver;

    public PageFactoryManager(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage getHomePage() {
        return new HomePage(driver);
    }

    public LoginPage getLoginPage() {
        return new LoginPage(driver);
    }

    public ProfilePage getProfilePage() {
        return new ProfilePage(driver);
    }
}
