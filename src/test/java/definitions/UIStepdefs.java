package definitions;

import dto.IsbnDto;
import hooks.Context;
import hooks.ScenarioContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import manager.PageFactoryManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobject.HomePage;
import pageobject.LoginPage;
import pageobject.ProfilePage;

import java.util.ArrayList;
import java.util.List;

public class UIStepdefs {
    private final ScenarioContext context;
    private static final long DEFAULT_TIMEOUT = 60;
    private WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    ProfilePage profilePage;
    PageFactoryManager pageFactoryManager;

    public UIStepdefs(ScenarioContext context){
        this.context = context;
    }

    @Before
    public void testsSetUp() {
        //no remote webdriver run during this tests
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
    }

    @After(order=1)
    public void tearDown() {
        driver.quit();
    }

    @When("^user opens '(.*)' page$")
    public void openHomePage(final String url) {
        homePage = pageFactoryManager.getHomePage();
        homePage.openHomePage(url);
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
    }

    @When("user goes to Login page")
    public void userGoesToLoginPage() {
        homePage.clickLoginButton();
    }

    @When("user logs in as created user")
    public void logInAsCreatedUser(){
        loginPage = pageFactoryManager.getLoginPage();
        loginPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, loginPage.getUsernameInput());
        loginPage.inputUsername(context.getContext(Context.USERNAME));
        loginPage.inputPassword(context.getContext(Context.PASSWORD));
        loginPage.clickOnLoginSubmitButton();
    }

    @When("user goes to Profile page")
    public void goToProfile(){
        homePage = pageFactoryManager.getHomePage();
        homePage.waitVisibilityOfElement(DEFAULT_TIMEOUT, homePage.getUserNameValue());
        homePage.scrollDown();
        homePage.clickOnProfileMenuItem();
    }

    @Then("the list of user's books contains added books")
    public void theListOfUserBooksContainsAddedBooks() {
        profilePage = pageFactoryManager.getProfilePage();
        profilePage.waitVisibilityOfElements(DEFAULT_TIMEOUT, profilePage.getBooksTitles());
        List<String> actualIsbnList = profilePage.getBooksIsbnList();
        List<IsbnDto> isbnDtoList = context.getContext(Context.ISBN_LIST);
        List<String> expectedIsbnList = new ArrayList<>();
        isbnDtoList.forEach(isbnDto -> expectedIsbnList.add(isbnDto.getIsbn()));
        Assert.assertEquals(expectedIsbnList.size(), actualIsbnList.size());
        Assert.assertTrue(expectedIsbnList.containsAll(actualIsbnList));
    }

}
