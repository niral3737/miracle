package com.itgo.miracle.global.exceptions;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ServerValidation
{
   private static Validator validator;

   public static Validator getValidator()
   {
      if (validator == null)
      {
         ValidatorFactory factory = Validation.byDefaultProvider().configure().buildValidatorFactory();
         validator = factory.getValidator();
      }
      return validator;
   }

   public static void validate(Object obj) throws ValidationException
   {
      ValidationUtils.validate(getValidator(), obj);
   }
}
