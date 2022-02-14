package com.ebook.book.dto;

import javax.validation.constraints.Pattern;

import com.ebook.book.validation.LevelTwoValidation;
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
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Only alphanumeric characters are allowed",groups =LevelTwoValidation.class)
    private String bookName;

    @ApiModelProperty(position = 2)
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Only alphanumeric characters are allowed",groups =LevelTwoValidation.class)
    private String bookauthorName;

    @ApiModelProperty(position = 3)
    private Long bookTotalPages;

    @ApiModelProperty(position = 4)
    private Double bookPrice;
}
