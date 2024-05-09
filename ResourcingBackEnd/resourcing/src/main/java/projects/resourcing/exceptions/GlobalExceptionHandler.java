package projects.resourcing.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(), NotFoundException.getStatusCode());
	}
	
	@ExceptionHandler(ServiceValidationException.class)
	public ResponseEntity<String> handleServiceValidationException (ServiceValidationException ex) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String json = objectMapper.writeValueAsString(ex.getErrors());
			return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return new ResponseEntity<>("Something went wront", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<String> dataIntegrityHandler(DataIntegrityViolationException ex) {
		String message = ex.getMessage();
		return new ResponseEntity<>(message , HttpStatus.BAD_REQUEST);
	}
}
