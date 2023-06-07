package dto;

import lombok.Data;

@Data
public class CreateUserRequestDto {
    private String userName;
    private String password;
}
