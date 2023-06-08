package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProfilePage extends WebPage{

    @FindBy(css = "a[href*='book']")
    private List<WebElement> booksTitles;

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getBooksTitles(){
        return booksTitles;
    }

    public List<String> getBooksIsbnList(){
        List<String> isbnList = new ArrayList<>();
        getBooksTitles().forEach(book -> {
            String href = book.getAttribute("href");
            String isbn = Arrays.asList(href.split("=")).get(1);
            isbnList.add(isbn);
        } );
        return isbnList;
    }
}
