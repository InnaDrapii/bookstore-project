package dto;

import lombok.Data;

import java.util.List;

@Data
public class PostBooksRequestDto {
    private String userId;
    private List<IsbnDto> collectionOfIsbns;
}
