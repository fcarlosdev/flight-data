package com.flightdata.exception;

import com.flightdata.dto.APIResponse;
import com.flightdata.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@RestControllerAdvice
public class FlightDataExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIResponse<?> handleMethodArgumentException(MethodArgumentNotValidException exception) {
        APIResponse<?> apiResponse = new APIResponse<>();
        List<ErrorDTO> errors = getErrors(exception);
        apiResponse.setStatus("FAILED");
        apiResponse.setErrors(errors);
        return apiResponse;
    }

    @ExceptionHandler(FlightNotFoundException.class)
    public APIResponse<?> handleServiceException(FlightNotFoundException exception) {
       APIResponse<?> apiResponse = new APIResponse<>();
       apiResponse.setStatus("FAILED");
       apiResponse.getErrors().add(ErrorDTO.builder()
               .errorMessage(exception.getMessage())
               .build());
       return apiResponse;
    }

    @ExceptionHandler(BusinessException.class)
    public APIResponse<?> handleBusinessException(BusinessException exception) {
        APIResponse<?> apiResponse = new APIResponse<>();
        apiResponse.setStatus("FAILED");
        apiResponse.getErrors().add(ErrorDTO.builder()
                .errorMessage(exception.getMessage())
                .build());
        return apiResponse;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public APIResponse<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        APIResponse<?> apiResponse = new APIResponse<>();
        apiResponse.setStatus("FAILED");
        apiResponse.getErrors().add(ErrorDTO.builder()
                .errorMessage(exception.getMessage())
                .build());
        return apiResponse;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public APIResponse<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        APIResponse<?> apiResponse = new APIResponse<>();
        apiResponse.setStatus("FAILED");
        apiResponse.getErrors().add(ErrorDTO.builder()
                .errorMessage(exception.getMessage())
                .build());
        return apiResponse;
    }


    @ExceptionHandler(Exception.class)
    public APIResponse<?> handleException(Exception exception) {
        APIResponse<?> apiResponse = new APIResponse<>();
        apiResponse.setStatus("FAILED");
        apiResponse.getErrors().add(ErrorDTO.builder()
                .errorMessage(exception.getMessage())
                .build());
        return apiResponse;
    }

    private static List<ErrorDTO> getErrors(MethodArgumentNotValidException exception) {
        return exception.getBindingResult().getFieldErrors().stream()
                .map(error -> ErrorDTO.builder()
                        .field(error.getField())
                        .errorMessage(error.getDefaultMessage())
                        .build())
                .toList();
    }
}
