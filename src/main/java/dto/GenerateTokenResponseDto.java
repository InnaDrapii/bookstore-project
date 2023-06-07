package dto;

import lombok.Data;

@Data
public class GenerateTokenResponseDto {
    private String token;
    private String expires;
    private String status;
    private String result;
}
