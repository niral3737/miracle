package com.itgo.miracle.billing_configuration.dao;

public class DaoFactory
{
   public static ProductDao getProductDao()
   {
      return ProductDaoImpl.getInstance();
   }
}
