package definitions;

import clients.RestResponse;
import clients.UserClient;
import dto.CreateUserRequestDto;
import dto.CreateUserResponseDto;
import hooks.Context;
import hooks.ScenarioContext;
import io.cucumber.java.en.Given;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MyStepdefs {
    private ScenarioContext context;
    private final UserClient userClient;

    @Given("^Create user with username '(.*)' and password '(.*)'$")
    public void createUser(String username, String password) {
        CreateUserRequestDto request = new CreateUserRequestDto();
        request.setUserName(username);
        request.setPassword(password);
        RestResponse response = userClient.postCreateUser(request);
        String resp = response.as(CreateUserResponseDto.class).toString();
        context.setContext(Context.RESPONSE, response);
    }
}
