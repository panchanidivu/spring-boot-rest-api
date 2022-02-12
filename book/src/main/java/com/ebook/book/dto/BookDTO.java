package com.ebook.book.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDTO {
    
    @ApiModelProperty(position = 0)
    private Long bookId;

    @ApiModelProperty(position = 1)
    private String bookname;

    @ApiModelProperty(position = 2)
    private String bookauthorName;

    @ApiModelProperty(position = 3)
    private Long bookTotalPages;

    @ApiModelProperty(position = 4)
    private Double bookPrice;
}
