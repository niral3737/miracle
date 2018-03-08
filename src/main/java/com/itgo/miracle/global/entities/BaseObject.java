package com.itgo.miracle.global.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Min;

@SuppressWarnings("serial")
@MappedSuperclass
public class BaseObject extends SuperObject
{
   public static final String EMPTY_STRING = "";
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private long id;
   private boolean active = true;

   @Min(0)
   public long getId()
   {
      return id;
   }

   public void setId(long id)
   {
      this.id = id;
   }

   public boolean isActive()
   {
      return active;
   }

   public void setActive(boolean active)
   {
      this.active = active;
   }

   public boolean isNew()
   {
      return getId() == 0;
   }

   public static Set<Long> getObjectIdSet(List<? extends BaseObject> objects)
   {
      Set<Long> set = new HashSet<Long>();
      for (BaseObject tmo : objects)
         set.add(tmo.getId());
      return set;
   }

   public static List<Long> getObjectIdList(List<? extends BaseObject> objects)
   {
      List<Long> list = new ArrayList<Long>();
      for (BaseObject tmo : objects)
         list.add(tmo.getId());
      return list;
   }

   public static BaseObject getObjectFromListById(List<? extends BaseObject> objects, long id)
   {
      for (BaseObject tmo : objects)
      {
         if (tmo.getId() == id)
            return tmo;
      }
      return null;
   }

   @Override
   public String toString()
   {
      StringBuilder builder = new StringBuilder();
      builder.append("id=").append(id).append(", active=").append(active);
      return builder.toString();
   }

}
