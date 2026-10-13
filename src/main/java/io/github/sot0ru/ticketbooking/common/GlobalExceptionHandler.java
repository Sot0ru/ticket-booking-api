package io.github.sot0ru.ticketbooking.common;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Turns exceptions into RFC 9457 problem responses ({@code ProblemDetail}).
 * Validation errors (400) are already handled by the parent class.
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	// TODO (week 2): map a lost optimistic-locking race to 409 Conflict,
	//  and "seat not found" / "event not found" to 404 Not Found.

}
