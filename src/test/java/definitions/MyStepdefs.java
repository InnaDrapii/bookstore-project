package definitions;

import clients.BooksClient;
import clients.RestResponse;
import clients.TokenClient;
import clients.UserClient;
import dto.*;
import hooks.Context;
import hooks.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.AllArgsConstructor;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class MyStepdefs {
    private final ScenarioContext context;
    private final UserClient userClient;
    private final TokenClient tokenClient;
    private final BooksClient booksClient;

    @Given("^create user with username '(.*)' and password '(.*)'$")
    public void createUser(String username, String password) {
        CreateUserRequestDto request = new CreateUserRequestDto();
        request.setUserName(username);
        request.setPassword(password);
        RestResponse response = userClient.postCreateUser(request);
        CreateUserResponseDto responseDto = response.as(CreateUserResponseDto.class);
        context.setContext(Context.USER_ID, responseDto.getUserID());
        context.setContext(Context.USERNAME, username);
        context.setContext(Context.PASSWORD, password);
    }

    @When("generate token for created user")
    public void generateTokenForCreatedUser() {
        RestResponse response = tokenClient.postToken(getCreatedRequestDto());
        context.setContext(Context.RESPONSE, response);
        context.setContext(Context.TOKEN, response.as(GenerateTokenResponseDto.class).getToken());
    }

    @When("^get books with author '(.*)'$")
    public void getBooksWithAuthorAxelRauschmayer(String author) {
        RestResponse response = booksClient.getBooks();
        List<BookDto> books = response.as(GetBooksResponseDto.class).getBooks();
        List<BookDto> booksOfAuthor = books.stream().filter(bookDto -> bookDto.getAuthor().equals(author)).collect(Collectors.toList());
        context.setContext(Context.BOOKS, booksOfAuthor);
    }

    @When("add selected books to user")
    public void addSelectedBooksToUser() {
        PostBooksRequestDto requestDto = new PostBooksRequestDto();
        requestDto.setUserId(context.getContext(Context.USER_ID));
        List<BookDto> books = context.getContext(Context.BOOKS);
        List<IsbnDto> collectionOfIsbn = new ArrayList<>();
        books.forEach(bookDto -> {
            IsbnDto isbnDto = new IsbnDto();
            isbnDto.setIsbn(bookDto.getIsbn());
            collectionOfIsbn.add(isbnDto);
        });
        requestDto.setCollectionOfIsbns(collectionOfIsbn);
        context.setContext(Context.ISBN_LIST, collectionOfIsbn);
        RestResponse response = booksClient.postBooksToUser(requestDto, context.getContext(Context.TOKEN));
        context.setContext(Context.RESPONSE, response);
    }

    @Then("^response status code is '(.*)'$")
    public void responseStatusCodeIs(String code) {
        RestResponse response = context.getContext(Context.RESPONSE);
        Assert.assertEquals(code, String.valueOf(response.getStatusCode()));
    }

    @Then("response contains added books")
    public void responseContainsAddedBooks() {
        RestResponse response = context.getContext(Context.RESPONSE);
        PostBooksResponseDto postBooksresponseDto = response.as(PostBooksResponseDto.class);
        List<IsbnDto> collectionOfIsbn = postBooksresponseDto.getBooks();

        Assert.assertEquals(context.getContext(Context.ISBN_LIST), collectionOfIsbn);
    }

    private CreateUserRequestDto getCreatedRequestDto(){
        CreateUserRequestDto request = new CreateUserRequestDto();
        request.setUserName(context.getContext(Context.USERNAME).toString());
        request.setPassword(context.getContext(Context.PASSWORD).toString());
        return request;
    }
}


