package clients;

import utils.ObjectMapperUtils;

public class BooksClient extends RestService {
    private final String BOOKS = "BookStore/v1/Books";

    public <T> RestResponse getBooks() {
        return get(BOOKS);
    }

    public <T> RestResponse postBooksToUser(T body, String token) {
        return postWithToken(BOOKS, ObjectMapperUtils.objectToJson(body), token);
    }
}
