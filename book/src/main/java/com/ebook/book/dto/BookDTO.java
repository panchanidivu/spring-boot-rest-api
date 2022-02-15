package com.ebook.book.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.ebook.book.validation.LevelOneValidation;
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
    private Long BookId;

    @ApiModelProperty(position = 1)
    @NotBlank(message = "Book name is required", groups = LevelOneValidation.class)
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Only alphanumeric characters are allowed", groups = LevelTwoValidation.class)
    private String bookName;

    @ApiModelProperty(position = 2)
    @NotBlank(message = "Book author name is required", groups = LevelOneValidation.class)
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Only alphanumeric characters are allowed", groups = LevelTwoValidation.class)
    private String bookauthorName;

    @ApiModelProperty(position = 3)
    @NotNull(message = "Book total pages is required", groups = LevelOneValidation.class)
    private Long bookTotalPages;

    @ApiModelProperty(position = 4)
    @NotNull(message = "Book price is required", groups = LevelOneValidation.class)
    private Double bookPrice;
}
