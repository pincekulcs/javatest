package com.hg.medicalevolution.javatest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Field not exists")
public class NotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
}
