package com.itgo.miracle.users.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.itgo.miracle.global.entities.BaseObject;

@SuppressWarnings("serial")
@Entity
public class User extends BaseObject
{
   private String username = EMPTY_STRING;
   private String createdBy = EMPTY_STRING;
   private String name = EMPTY_STRING;

   @Temporal(TemporalType.DATE)
   private Date createdDate = new Date();

   public String getUsername()
   {
      return username;
   }

   public void setUsername(String username)
   {
      this.username = username;
   }

   public String getCreatedBy()
   {
      return createdBy;
   }

   public void setCreatedBy(String createdBy)
   {
      this.createdBy = createdBy;
   }

   public Date getCreatedDate()
   {
      return createdDate;
   }

   public void setCreatedDate(Date createdDate)
   {
      this.createdDate = createdDate;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   @Override
   public String toString()
   {
      StringBuilder builder = new StringBuilder();
      builder.append("username=").append(username).append(", createdBy=").append(createdBy).append(", name=")
            .append(name).append(", createdDate=").append(createdDate).append(", ").append(super.toString());
      return builder.toString();
   }

}
