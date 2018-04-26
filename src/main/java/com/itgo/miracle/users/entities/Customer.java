package com.itgo.miracle.users.entities;

import javax.persistence.Entity;

import com.itgo.miracle.global.entities.BaseObject;

@SuppressWarnings("serial")
@Entity
public class Customer extends BaseObject
{
   private String name;
   private String phoneNumber;
   private String firmName;
   private long addressId;
   private long userId;

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getPhoneNumber()
   {
      return phoneNumber;
   }

   public void setPhoneNumber(String phoneNumber)
   {
      this.phoneNumber = phoneNumber;
   }

   public String getFirmName()
   {
      return firmName;
   }

   public void setFirmName(String firmName)
   {
      this.firmName = firmName;
   }

   public long getAddressId()
   {
      return addressId;
   }

   public void setAddressId(long addressId)
   {
      this.addressId = addressId;
   }

   public long getUserId()
   {
      return userId;
   }

   public void setUserId(long userId)
   {
      this.userId = userId;
   }

   @Override
   public String toString()
   {
      StringBuilder builder = new StringBuilder();
      builder.append("name=").append(name).append(", phoneNumber=").append(phoneNumber).append(", firmName=")
            .append(firmName).append(", addressId=").append(addressId).append(", userId=").append(userId)
            .append(super.toString());
      return builder.toString();
   }

}
