package com.itgo.miracle.api.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.itgo.miracle.global.exceptions.ValidationException;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException>
{

   @Override
   public Response toResponse(ValidationException exception)
   {
      return Response.status(Status.BAD_REQUEST).entity(exception.getViolations()).build();
   }

}
