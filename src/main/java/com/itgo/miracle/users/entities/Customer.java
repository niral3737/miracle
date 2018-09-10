package com.itgo.miracle.users.entities;

import javax.persistence.Entity;

import com.itgo.miracle.global.entities.BaseObject;

@SuppressWarnings("serial")
@Entity
public class Customer extends BaseObject
{
   private String name;
   private String phoneNumber;
   private String address;
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

   public long getUserId()
   {
      return userId;
   }

   public void setUserId(long userId)
   {
      this.userId = userId;
   }

   public String getAddress()
   {
      return address;
   }

   public void setAddress(String address)
   {
      this.address = address;
   }

   @Override
   public String toString()
   {
      StringBuilder builder = new StringBuilder();
      builder.append("Customer [name=").append(name).append(", phoneNumber=").append(phoneNumber).append(", address=")
            .append(address).append(", userId=").append(userId).append(super.toString());
      return builder.toString();
   }

}
