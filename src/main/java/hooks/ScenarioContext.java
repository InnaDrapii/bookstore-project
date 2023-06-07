package hooks;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ScenarioContext {
    private final Map<String, Object> context;

    public ScenarioContext(){
        context = new HashMap<>();
    }

    public void setContext(Context key, Object value) {
        context.put(key.toString(), value);
    }

    public <T> T getContext(Context key) {
        return (T) Optional.ofNullable(context.get(key.name())).orElse(null);
    }
}
