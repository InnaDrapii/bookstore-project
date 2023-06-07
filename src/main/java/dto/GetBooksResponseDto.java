package dto;

import lombok.Data;

import java.util.List;

@Data
public class GetBooksResponseDto {
    private List<BookDto> books;
}
