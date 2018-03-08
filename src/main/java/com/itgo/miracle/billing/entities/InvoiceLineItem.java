package com.itgo.miracle.billing.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;

import com.itgo.miracle.global.entities.BaseObject;

@SuppressWarnings("serial")
@Entity
public class InvoiceLineItem extends BaseObject
{
   private int position;
   private long invoiceId;
   private long productId;
   private BigDecimal discount;
   private BigDecimal netAmount;
   private BigDecimal sgst;
   private BigDecimal cgst;
   private int quantity;
   private String hSNCode;

   public int getPosition()
   {
      return position;
   }

   public void setPosition(int position)
   {
      this.position = position;
   }

   public long getInvoiceId()
   {
      return invoiceId;
   }

   public void setInvoiceId(long invoiceId)
   {
      this.invoiceId = invoiceId;
   }

   public long getProductId()
   {
      return productId;
   }

   public void setProductId(long productId)
   {
      this.productId = productId;
   }

   public BigDecimal getDiscount()
   {
      return discount;
   }

   public void setDiscount(BigDecimal discount)
   {
      this.discount = discount;
   }

   public BigDecimal getNetAmount()
   {
      return netAmount;
   }

   public void setNetAmount(BigDecimal netAmount)
   {
      this.netAmount = netAmount;
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

   public int getQuantity()
   {
      return quantity;
   }

   public void setQuantity(int quantity)
   {
      this.quantity = quantity;
   }

   public String gethSNCode()
   {
      return hSNCode;
   }

   public void sethSNCode(String hSNCode)
   {
      this.hSNCode = hSNCode;
   }

   @Override
   public String toString()
   {
      StringBuilder builder = new StringBuilder();
      builder.append("position=").append(position).append(", invoiceId=").append(invoiceId).append(", productId=")
            .append(productId).append(", discount=").append(discount).append(", netAmount=").append(netAmount)
            .append(", sgst=").append(sgst).append(", cgst=").append(cgst).append(", quantity=").append(quantity)
            .append(", hSNCode=").append(hSNCode).append(super.toString());
      return builder.toString();
   }

}
