package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends WebPage{

    @FindBy(css = "#userName")
    private WebElement usernameInput;
    @FindBy(css = "#password")
    private WebElement passwordInput;
    @FindBy(css = "#login")
    private WebElement loginSubmitButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getPasswordInput() {
        return passwordInput;
    }

    public void inputPassword(String password) {
        getPasswordInput().sendKeys(password);
    }

    public WebElement getUsernameInput() {
        return usernameInput;
    }

    public void inputUsername(String username) {
        getUsernameInput().sendKeys(username);
    }

    public void clickOnLoginSubmitButton() {
        loginSubmitButton.click();
    }

}
