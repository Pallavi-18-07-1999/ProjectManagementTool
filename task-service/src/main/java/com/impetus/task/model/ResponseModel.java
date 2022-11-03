package com.impetus.task.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseModel<T> {
	private String exception;
	private boolean success;
	private T response;
}
