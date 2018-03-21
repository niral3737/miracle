package com.itgo.miracle.global.exceptions;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

public class ValidationUtils
{
   public static void validate(Validator validator, Object object) throws ValidationException
   {
      throwValidationFailedExceptionIfFailed(validator.validate(object));
   }

   private static void throwValidationFailedExceptionIfFailed(Set<ConstraintViolation<Object>> violations)
         throws ValidationException
   {
      if (!violations.isEmpty())
         throw getValidationExceptionFromConstraintViolations(violations);
   }

   private static ValidationException getValidationExceptionFromConstraintViolations(
         Set<ConstraintViolation<Object>> violations)
   {
      ValidationException exception = new ValidationException();
      for (ConstraintViolation<Object> violation : violations)
         exception.addViolation(getViolationFromConstraintViolation(violation));
      return exception;
   }

   private static Violation getViolationFromConstraintViolation(ConstraintViolation<Object> constraintViolation)
   {
      String dataFieldName = constraintViolation.getPropertyPath().toString();
      Violation violation = new Violation(dataFieldName, constraintViolation.getMessage());
      return violation;
   }
}
