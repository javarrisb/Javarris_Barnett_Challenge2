package com.company.challenge2.controller;

import com.company.challenge2.models.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<List<CustomErrorResponse>> handleValidationFailures(MethodArgumentNotValidException ex) {
        // BindingResult holds the validation errors
        BindingResult result = ex.getBindingResult();
        // Validation errors are stored in FieldError objects
        List<FieldError> fieldErrors = result.getFieldErrors();

        // Translate the FieldErrors to CustomErrorResponse
        List<CustomErrorResponse> errorResponseList = new ArrayList<>();

        HttpStatus responseStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        for (FieldError fieldError : fieldErrors) {
            CustomErrorResponse errorResponse = new CustomErrorResponse(responseStatus, fieldError.getDefaultMessage());
            errorResponseList.add(errorResponse);
        }

        // Create and return the ResponseEntity
        ResponseEntity<List<CustomErrorResponse>> responseEntity = new ResponseEntity<>(errorResponseList, responseStatus);
        return responseEntity;
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<CustomErrorResponse> handleOutOfRangeException(IllegalArgumentException ex) {
        HttpStatus returnHttpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        CustomErrorResponse error = new CustomErrorResponse(returnHttpStatus, ex.getMessage());
        ResponseEntity<CustomErrorResponse> returnVal = new ResponseEntity<>(error, returnHttpStatus);

        return returnVal;
    }

  /*  @ExceptionHandler(value = {ArgumentIsNotANumberException.class})
    public ResponseEntity<CustomErrorResponse> handleNotANumberException(ArgumentIsNotANumberException ex) {
        HttpStatus returnHttpStatus = HttpStatus.I_AM_A_TEAPOT;
        CustomErrorResponse error = new CustomErrorResponse(returnHttpStatus, ex.getMessage());
        ResponseEntity<CustomErrorResponse> returnVal = new ResponseEntity<>(error, returnHttpStatus);

        return returnVal;

   */

}
