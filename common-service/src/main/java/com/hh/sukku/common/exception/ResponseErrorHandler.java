package com.hh.sukku.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hh.sukku.common.beans.Response;
import com.hh.sukku.common.util.ErrorCodes;

/**
 * 
 * @author arun.sudhakaran
 *
 * 04-Apr-2023 10:03:34 pm
 */

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ResponseErrorHandler extends ResponseEntityExceptionHandler {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		if (log.isInfoEnabled())
			log.info(" Validation Error : {}", ex.getBindingResult().toString());

		return new ResponseEntity<Object>(new Response(ErrorCodes.COMMON_ERROR,
				ex.getBindingResult() != null && ex.getBindingResult().getFieldError() != null
						? ex.getBindingResult().getFieldError().getDefaultMessage()
						: " Invalid Message Format"),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler	({ CommonException.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Response> commonException(CommonException ex) {
		
		String statusCode = (ex.getStatusCode() != null) ? ex.getStatusCode() : ErrorCodes.COMMON_ERROR;
		String message = (ex != null) ? ex.getMessage() : "System error";

		String classname = (ex.getStackTrace() == null) ? "" : ex.getStackTrace()[0].getClassName();
		int line = (ex.getStackTrace() == null) ? 0 : ex.getStackTrace()[0].getLineNumber();

		log.error("CommonException : RESULTCODE {} MESSAGE {} CLASS {}, LINE {} ", statusCode, message, classname, line);

		Response response = new Response(statusCode, message);

		return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
