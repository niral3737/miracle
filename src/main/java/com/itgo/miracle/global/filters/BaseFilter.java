package com.itgo.miracle.global.filters;

import java.util.LinkedHashMap;

public class BaseFilter extends SuperFilter
{
   public long id = 0;
   public boolean activeOnly = true;
   public String searchString = "";
   public int limit = 0;

   public enum OrderByMode
   {
      ASC, DESC
   }

   protected LinkedHashMap<String, OrderByMode> orderByFields = null;

   public void addOrderBy(String fieldName, OrderByMode mode)
   {
      if (orderByFields == null)
         orderByFields = new LinkedHashMap<String, BaseFilter.OrderByMode>();

      orderByFields.put(fieldName, mode);
   }

   public boolean hasOrderByFields()
   {
      return orderByFields != null;
   }

   public LinkedHashMap<String, OrderByMode> getOrderByFields()
   {
      return orderByFields;
   }
}
