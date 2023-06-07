package definitions;

import clients.UserClient;
import hooks.Context;
import hooks.ScenarioContext;
import io.cucumber.java.After;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Hooks {
    private final ScenarioContext context;
    private final UserClient userClient;

    @After
    public void deleteCreatedUser() {
        userClient.deleteUser(context.getContext(Context.USER_ID), context.getContext(Context.TOKEN));
    }


}
