package com.ebook.book.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookResponseDTO {
    @ApiModelProperty(position = 0)
    private Long BookId;

    @ApiModelProperty(position = 1)
    private String bookName;

    @ApiModelProperty(position = 2)
    private String bookauthorName;

    @ApiModelProperty(position = 3)
    private Long bookTotalPages;

    @ApiModelProperty(position = 4)
    private Double bookPrice;
}
