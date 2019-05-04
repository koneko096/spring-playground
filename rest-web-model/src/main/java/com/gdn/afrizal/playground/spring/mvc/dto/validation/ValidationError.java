package com.gdn.afrizal.playground.spring.mvc.dto.validation;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

public class ValidationError {
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private List<String> errors = new ArrayList<>();

  private final String errorMessage;

  public ValidationError(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public void addValidationError(String error) {
    errors.add(error);
  }

  public List<String> getErrors() {
    return errors;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public static ValidationError fromBindingErrors(Errors errors) {
    ValidationError error = new ValidationError("Validation failed. " + errors.getErrorCount() + " error(s)");
      for (FieldError fieldError : errors.getFieldErrors()) {
          error.addValidationError(fieldError.getField() + " : " + fieldError.getDefaultMessage());
    }
    return error;
  }
}
