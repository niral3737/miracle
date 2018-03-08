package com.itgo.miracle.global.exceptions;

@SuppressWarnings("serial")
public class DaoException extends RuntimeException
{
   public DaoException(
         String message)
   {
      super(message);
   }
}
