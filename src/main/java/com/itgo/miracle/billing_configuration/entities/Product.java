package com.itgo.miracle.billing_configuration.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;

import com.itgo.miracle.global.entities.BaseObject;

@SuppressWarnings("serial")
@Entity
public class Product extends BaseObject
{
   private String name;
   private String hsnCode;
   private String barcode;
   private BigDecimal price;
   private int quantity;
   private String location;

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getHsnCode()
   {
      return hsnCode;
   }

   public void setHsnCode(String hsnCode)
   {
      this.hsnCode = hsnCode;
   }

   public String getBarcode()
   {
      return barcode;
   }

   public void setBarcode(String barcode)
   {
      this.barcode = barcode;
   }

   public BigDecimal getPrice()
   {
      return price;
   }

   public void setPrice(BigDecimal price)
   {
      this.price = price;
   }

   public int getQuantity()
   {
      return quantity;
   }

   public void setQuantity(int quantity)
   {
      this.quantity = quantity;
   }

   public String getLocation()
   {
      return location;
   }

   public void setLocation(String location)
   {
      this.location = location;
   }

   @Override
   public String toString()
   {
      StringBuilder builder = new StringBuilder();
      builder.append("name=").append(name).append(", hsnCode=").append(hsnCode).append(", barcode=").append(barcode)
            .append(", price=").append(price).append(", quantity=").append(quantity).append(", location=")
            .append(location).append(super.toString());
      return builder.toString();
   }

}
