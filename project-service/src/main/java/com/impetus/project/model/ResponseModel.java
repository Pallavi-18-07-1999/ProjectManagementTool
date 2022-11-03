package com.impetus.project.model;

import lombok.*;

@Getter
@Setter
public class ResponseModel<T> {
	private T response;
	private String exception;
	private boolean success;
}
