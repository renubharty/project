package com.intcomcorp.intcomcorpApplication.exception;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.intcomcorp.intcomcorpApplication.dto.response.ApiResponse;
import com.intcomcorp.intcomcorpApplication.dto.response.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	private static MessageSource messageSource;

	@Autowired
	private MessageSource msgSource;

	@PostConstruct
	public void init() {
		messageSource = msgSource;
	}

	/**
	 * @param exception
	 * @param request
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ApiResponse> handleAllExceptions(Exception exception, WebRequest request) {
		log.info("",exception);
		return new ResponseEntity<>(new ErrorResponse("something has been wrong"), HttpStatus.BAD_REQUEST);
	}

	/**
	 * @param expiredJwtException
	 * @param request
	 * @return
	 */
	@ExceptionHandler(AccessDeniedException.class)
	public final ResponseEntity<ApiResponse> handleAccessDeniedException(AccessDeniedException expiredJwtException,
			WebRequest request) {
		return new ResponseEntity<>(new ErrorResponse("you are not authorized to view this page"),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MailSendException.class)
	public final ResponseEntity<ApiResponse> handleAccessDeniedException(MailSendException expiredJwtException,
			WebRequest request) {
		log.info("",expiredJwtException);
		return new ResponseEntity<>(new ErrorResponse("you are not authorized to view this page"),
				HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> listmessages = exception.getBindingResult().getFieldErrors().stream()
				.map(x -> x.getDefaultMessage()).collect(Collectors.toList());
		String firstMessage = listmessages.iterator().next();
		return new ResponseEntity<>(new ErrorResponse(messageSource.getMessage(firstMessage, null, Locale.US)),
				HttpStatus.BAD_REQUEST);
	}

}
