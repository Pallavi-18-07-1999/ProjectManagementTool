package com.impetus.section.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseModel<T> {
	private T response;
	private boolean success;
	private String exception;
}
