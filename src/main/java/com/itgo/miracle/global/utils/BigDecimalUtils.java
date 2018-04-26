package com.itgo.miracle.global.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class BigDecimalUtils
{
   private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

   public static BigDecimal getOneHundred()
   {
      return getDecimal4(100.0);
   }

   public static BigDecimal getDecimal4(double trouble)
   {
      BigDecimal result = getBigDecimal(trouble, 12, 4);
      return result;
   }

   public static BigDecimal getDecimalQty(double trouble)
   {
      BigDecimal result = getBigDecimal(trouble, 8, 2);
      return result;
   }

   public static BigDecimal getDecimalWhole(double trouble)
   {
      BigDecimal result = getBigDecimal(trouble, 8, 0);
      return result;
   }

   public static BigDecimal getDecimalVat(double trouble)
   {
      BigDecimal result = getBigDecimal(trouble, 5, 2);
      return result;
   }

   public static BigDecimal round2(BigDecimal value)
   {
      return value.setScale(2, ROUNDING_MODE);
   }

   public static BigDecimal getBigDecimal(double trouble, int precision, int scale)
   {
      MathContext mc = new MathContext(precision, ROUNDING_MODE);
      BigDecimal result = new BigDecimal(String.valueOf(trouble), mc);
      result = result.setScale(scale, ROUNDING_MODE);
      return result;
   }

   /**
    * Testet ob der Ãœbergebene Wert 0 ist.
    */
   public static boolean isZero(BigDecimal value)
   {
      if (value == null)
         return false;

      return BigDecimal.ZERO.compareTo(value) == 0;
   }

   /**
    * Testet ob der Ãœbergebene Wert negativ (kleiner 0) ist.
    */
   public static boolean isNegative(BigDecimal value)
   {
      if (value == null)
         return false;

      return BigDecimal.ZERO.compareTo(value) > 0;
   }

   /**
    * Vergleicht zwei BigDecimal-Werte ohne Beachtung der Stellen-Anzahl (2.0 ==
    * 2.00).
    * 
    * @param value1
    * @param value2
    * @return
    */
   public static boolean equals(BigDecimal value1, BigDecimal value2)
   {
      if (value1 == null)
         return value2 == null;

      if (value2 == null)
         return value1 == null;

      return value1.compareTo(value2) == 0;
   }

   /**
    * Dividiert das erste durch das zweite Argument unter BerÃ¼cksichtigung
    * einer Standardrundung.
    * 
    * @param divide
    * @param by
    * @return
    */
   public static BigDecimal divide(BigDecimal divide, BigDecimal by)
   {
      return divide.divide(by, ROUNDING_MODE);
   }
}
