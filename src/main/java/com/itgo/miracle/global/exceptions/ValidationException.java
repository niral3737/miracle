package com.itgo.miracle.global.exceptions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ValidationException extends Exception
{
   private static final long serialVersionUID = 9181377444219180180L;

   private List<Violation> violations = new ArrayList<Violation>();

   public ValidationException()
   {

   }

   public ValidationException(
         String message)
   {
      super(message);
   }

   public ValidationException(
         Violation singleViolation)
   {
      addViolation(singleViolation);
   }

   public List<Violation> getViolations()
   {
      return violations;
   }

   public void addViolation(Violation violation)
   {
      violations.add(violation);
   }

   public boolean hasViolations()
   {
      return !getViolations().isEmpty();
   }

   @Override
   public String getMessage()
   {
      if (hasViolations())
      {
         StringBuilder sb = new StringBuilder();
         Iterator<Violation> iV = getViolations().iterator();
         while (iV.hasNext())
         {
            Violation violation = iV.next();
            if (violation.getDataFieldName() != null && violation.getDataFieldName().trim().length() != 0)
               sb.append(violation.getDataFieldName()).append(": ");
            sb.append(violation.getErrorMessage());
            if (iV.hasNext())
               sb.append(", ");
         }
         return sb.toString();
      }
      return super.getMessage();
   }
}
