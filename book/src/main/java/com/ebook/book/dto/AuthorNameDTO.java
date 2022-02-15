package com.ebook.book.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthorNameDTO {

    @ApiModelProperty(position = 0)
    private String bookName;

    @ApiModelProperty(position = 1)
    private int bookCount;

    
}
