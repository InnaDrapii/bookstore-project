package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends WebPage {

    @FindBy(id = "login")
    private WebElement loginButton;

    @FindBy(xpath = "//li[span[text()='Profile']]")
    private WebElement profileMenuItem;

    @FindBy(css = "#userName-value")
    private WebElement userNameValue;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public WebElement getUserNameValue() {
        return userNameValue;
    }

    public WebElement getProfileMenuItem() {
        return profileMenuItem;
    }

    public void openHomePage(String url) {
        driver.get(url);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void clickOnProfileMenuItem(){
        getProfileMenuItem().click();
    }
}
