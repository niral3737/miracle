package com.itgo.miracle.global.exceptions;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Violation implements Serializable
{
   private String dataFieldName;
   private String errorMessage;

   public Violation(
         String dataFieldName,
         String errorMessage)
   {
      this();
      this.dataFieldName = dataFieldName;
      this.errorMessage = errorMessage;
   }

   private Violation()
   {
   }

   public String getDataFieldName()
   {
      return dataFieldName;
   }

   public void setDataFieldName(String dataFieldName)
   {
      this.dataFieldName = dataFieldName;
   }

   public String getErrorMessage()
   {
      return errorMessage;
   }

   @Override
   public String toString()
   {
      if (dataFieldName == null || dataFieldName.trim().length() == 0)
         return errorMessage;

      return dataFieldName + ": " + errorMessage;
   }
}
