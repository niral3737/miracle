package com.itgo.miracle.billing_configuration.enums;

import java.math.BigDecimal;

import com.itgo.miracle.global.utils.BigDecimalUtils;

public enum GSTSlab
{
   GST5(5), GST12(12), GST18(18), GST28(28), GST3(3), GSTNILL(0), NONGST(0);

   private int rate;

   private GSTSlab(
         int rate)
   {
      this.rate = rate;
   }

   public int getRate()
   {
      return rate;
   }

   public BigDecimal getGSTRate()
   {
      return BigDecimalUtils.getDecimal4(getRate());
   }

}
