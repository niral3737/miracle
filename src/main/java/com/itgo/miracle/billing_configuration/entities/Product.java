package com.itgo.miracle.billing_configuration.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.itgo.miracle.billing_configuration.enums.GSTSlab;
import com.itgo.miracle.global.entities.BaseObject;

@SuppressWarnings("serial")
@Entity
@Table(indexes = {@Index(name = "name", columnList = "active, name"),
      @Index(name = "productCode", columnList = "active, productCode"),
      @Index(name = "barcode", columnList = "active, barcode"), @Index(name = "userId", columnList = "active, userId")})
public class Product extends BaseObject
{
   private String name = EMPTY_STRING;
   private String productCode = EMPTY_STRING;
   private String barcode = EMPTY_STRING;
   private BigDecimal purchasePrice;
   private BigDecimal salesPrice;
   private GSTSlab gstSlab = GSTSlab.GSTNILL;
   private int quantity;
   private String location = EMPTY_STRING;
   private long userId;

   @Size(min = 1)
   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getBarcode()
   {
      return barcode;
   }

   public void setBarcode(String barcode)
   {
      this.barcode = barcode;
   }

   @Min(0)
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

   public long getUserId()
   {
      return userId;
   }

   public void setUserId(long userId)
   {
      this.userId = userId;
   }

   @Size(min = 1)
   public String getProductCode()
   {
      return productCode;
   }

   public void setProductCode(String productCode)
   {
      this.productCode = productCode;
   }

   @Min(0)
   public BigDecimal getPurchasePrice()
   {
      return purchasePrice;
   }

   public void setPurchasePrice(BigDecimal purchasePrice)
   {
      this.purchasePrice = purchasePrice;
   }

   @Min(0)
   public BigDecimal getSalesPrice()
   {
      return salesPrice;
   }

   public void setSalesPrice(BigDecimal salesPrice)
   {
      this.salesPrice = salesPrice;
   }

   public GSTSlab getGstSlab()
   {
      return gstSlab;
   }

   public void setGstSlab(GSTSlab gstSlab)
   {
      this.gstSlab = gstSlab;
   }

   @Override
   public String toString()
   {
      StringBuilder builder = new StringBuilder();
      builder.append("name=").append(name).append(", productCode=").append(productCode).append(", barcode=")
            .append(barcode).append(", purchasePrice=").append(purchasePrice).append(", salesPrice=").append(salesPrice)
            .append(", gstSlab=").append(gstSlab).append(", quantity=").append(quantity).append(", location=")
            .append(location).append(", userId=").append(userId).append(super.toString());
      return builder.toString();
   }

}
