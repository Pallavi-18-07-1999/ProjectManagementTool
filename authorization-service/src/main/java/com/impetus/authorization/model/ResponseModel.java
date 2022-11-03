package com.impetus.authorization.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseModel<T> {
	private boolean success;
	private T response;
	private String exception;
}
