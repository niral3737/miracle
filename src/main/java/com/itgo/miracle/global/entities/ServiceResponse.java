package com.itgo.miracle.global.entities;

@SuppressWarnings("serial")
public class ServiceResponse extends SuperObject
{
   private boolean isOk;
   private Object payload;

   public ServiceResponse(
         boolean isOk,
         Object payload)
   {
      super();
      this.isOk = isOk;
      this.payload = payload;
   }

   public ServiceResponse(
         boolean isOk)
   {
      this(isOk, null);
   }

   public boolean isOk()
   {
      return isOk;
   }

   public void setOk(boolean isOk)
   {
      this.isOk = isOk;
   }

   public Object getPayload()
   {
      return payload;
   }

   public void setPayload(Object payload)
   {
      this.payload = payload;
   }

}
