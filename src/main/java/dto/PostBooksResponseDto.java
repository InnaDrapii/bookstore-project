package dto;

import lombok.Data;

import java.util.List;

@Data
public class PostBooksResponseDto {
    private List<IsbnDto> books;
}
