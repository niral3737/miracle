package com.itgo.miracle.users.entities;

import javax.persistence.Entity;

import com.itgo.miracle.global.entities.BaseObject;

@SuppressWarnings("serial")
@Entity
public class Address extends BaseObject
{
   private String line1 = EMPTY_STRING;
   private String line2 = EMPTY_STRING;
   private String line3 = EMPTY_STRING;
   private String pincode = EMPTY_STRING;
   private String city = EMPTY_STRING;
   private String state = EMPTY_STRING;
   private String country = EMPTY_STRING;

   public String getLine1()
   {
      return line1;
   }

   public void setLine1(String line1)
   {
      this.line1 = line1;
   }

   public String getLine2()
   {
      return line2;
   }

   public void setLine2(String line2)
   {
      this.line2 = line2;
   }

   public String getLine3()
   {
      return line3;
   }

   public void setLine3(String line3)
   {
      this.line3 = line3;
   }

   public String getPincode()
   {
      return pincode;
   }

   public void setPincode(String pincode)
   {
      this.pincode = pincode;
   }

   public String getCity()
   {
      return city;
   }

   public void setCity(String city)
   {
      this.city = city;
   }

   public String getState()
   {
      return state;
   }

   public void setState(String state)
   {
      this.state = state;
   }

   public String getCountry()
   {
      return country;
   }

   public void setCountry(String country)
   {
      this.country = country;
   }

   @Override
   public String toString()
   {
      StringBuilder builder = new StringBuilder();
      builder.append("line1=").append(line1).append(", line2=").append(line2).append(", line3=").append(line3)
            .append(", pincode=").append(pincode).append(", city=").append(city).append(", state=").append(state)
            .append(", country=").append(country).append(super.toString());
      return builder.toString();
   }

}
