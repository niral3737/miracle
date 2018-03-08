package com.itgo.miracle.users.entities;

import javax.persistence.Entity;

import com.itgo.miracle.global.entities.BaseObject;

@SuppressWarnings("serial")
@Entity
public class Firm extends BaseObject
{
   private long userId;
   private String name = EMPTY_STRING;
   private String phoneNumber = EMPTY_STRING;
   private String gSTNumber = EMPTY_STRING;
   private String registrationNumber = EMPTY_STRING;
   private long addressId;

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

   public String getgSTNumber()
   {
      return gSTNumber;
   }

   public void setgSTNumber(String gSTNumber)
   {
      this.gSTNumber = gSTNumber;
   }

   public String getRegistrationNumber()
   {
      return registrationNumber;
   }

   public void setRegistrationNumber(String registrationNumber)
   {
      this.registrationNumber = registrationNumber;
   }

   @Override
   public String toString()
   {
      StringBuilder builder = new StringBuilder();
      builder.append("userId=").append(userId).append(", name=").append(name).append(", phoneNumber=")
            .append(phoneNumber).append(", gSTNumber=").append(gSTNumber).append(", registrationNumber=")
            .append(registrationNumber).append(", addressId=").append(addressId).append(super.toString());
      return builder.toString();
   }

}
