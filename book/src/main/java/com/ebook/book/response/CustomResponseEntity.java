package com.ebook.book.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomResponseEntity{

	private int code;
	
	private int status;
	
	private String message;
	
	private Object data;
}
