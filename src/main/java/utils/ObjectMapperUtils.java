package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class ObjectMapperUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @SneakyThrows
    public static String objectToJson(Object object) {
        return MAPPER.writeValueAsString(object);
    }

}
