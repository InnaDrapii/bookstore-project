package dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateUserResponseDto {
    private String userID;
    private String username;
    private List<BookDto> books;
}
