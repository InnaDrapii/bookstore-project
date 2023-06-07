package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ObjectMapperUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @SneakyThrows
    public static String objectToJson(Object object) {
        return MAPPER.writeValueAsString(object);
    }

}
