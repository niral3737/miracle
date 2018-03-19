package com.itgo.miracle.billing_configuration.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.itgo.miracle.billing_configuration.entities.Product;
import com.itgo.miracle.billing_configuration.filters.ProductFilter;
import com.itgo.miracle.global.dao.GenericDaoImpl;

public class ProductDaoImpl extends GenericDaoImpl<Product, ProductFilter> implements ProductDao
{
   private static ProductDaoImpl instance;

   private ProductDaoImpl()
   {
   }

   static ProductDaoImpl getInstance()
   {
      if (instance == null)
         instance = new ProductDaoImpl();
      return instance;
   }

   @Override
   protected List<Predicate> getFilterConditions(CriteriaQuery<Product> query, Root<Product> root,
         CriteriaBuilder builder, ProductFilter filter)
   {
      return null;
   }

}
