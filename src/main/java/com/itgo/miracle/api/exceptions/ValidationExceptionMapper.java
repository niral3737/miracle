package com.itgo.miracle.api.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.itgo.miracle.global.entities.ServiceResponse;
import com.itgo.miracle.global.exceptions.ValidationException;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException>
{

   @Override
   public Response toResponse(ValidationException exception)
   {
      return Response.status(Status.OK).entity(new ServiceResponse(false, exception.getViolations())).build();
   }

}
