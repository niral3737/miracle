package com.itgo.miracle.billing.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;

import com.itgo.miracle.global.entities.BaseObject;

@SuppressWarnings("serial")
@Entity
public class Invoice extends BaseObject
{
   private long userId;
   private long customerId;
   private String title;
   private String invoiceNumber;
   private long firmId;
   private Date invoiceDate = new Date();
   private BigDecimal netAmount;
   private BigDecimal discount;
   private BigDecimal sgst;
   private BigDecimal cgst;
   private BigDecimal totalAmount;

   public long getUserId()
   {
      return userId;
   }

   public void setUserId(long userId)
   {
      this.userId = userId;
   }

   public long getCustomerId()
   {
      return customerId;
   }

   public void setCustomerId(long customerId)
   {
      this.customerId = customerId;
   }

   public String getInvoiceNumber()
   {
      return invoiceNumber;
   }

   public void setInvoiceNumber(String invoiceNumber)
   {
      this.invoiceNumber = invoiceNumber;
   }

   public long getFirmId()
   {
      return firmId;
   }

   public void setFirmId(long firmId)
   {
      this.firmId = firmId;
   }

   public Date getInvoiceDate()
   {
      return invoiceDate;
   }

   public void setInvoiceDate(Date invoiceDate)
   {
      this.invoiceDate = invoiceDate;
   }

   public BigDecimal getNetAmount()
   {
      return netAmount;
   }

   public void setNetAmount(BigDecimal netAmount)
   {
      this.netAmount = netAmount;
   }

   public BigDecimal getDiscount()
   {
      return discount;
   }

   public void setDiscount(BigDecimal discount)
   {
      this.discount = discount;
   }

   public BigDecimal getSgst()
   {
      return sgst;
   }

   public void setSgst(BigDecimal sgst)
   {
      this.sgst = sgst;
   }

   public BigDecimal getCgst()
   {
      return cgst;
   }

   public void setCgst(BigDecimal cgst)
   {
      this.cgst = cgst;
   }

   public BigDecimal getTotalAmount()
   {
      return totalAmount;
   }

   public void setTotalAmount(BigDecimal totalAmount)
   {
      this.totalAmount = totalAmount;
   }

   public String getTitle()
   {
      return title;
   }

   public void setTitle(String title)
   {
      this.title = title;
   }

   @Override
   public String toString()
   {
      StringBuilder builder = new StringBuilder();
      builder.append("userId=").append(userId).append(", customerId=").append(customerId).append(", title=")
            .append(title).append(", invoiceNumber=").append(invoiceNumber).append(", firmId=").append(firmId)
            .append(", invoiceDate=").append(invoiceDate).append(", netAmount=").append(netAmount).append(", discount=")
            .append(discount).append(", sgst=").append(sgst).append(", cgst=").append(cgst).append(", totalAmount=")
            .append(totalAmount).append(super.toString());
      return builder.toString();
   }

}
