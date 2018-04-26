package com.itgo.miracle.billing_configuration.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.itgo.miracle.billing_configuration.entities.Product;
import com.itgo.miracle.billing_configuration.filters.ProductFilter;
import com.itgo.miracle.global.dao.GenericDaoImpl;
import com.itgo.miracle.global.filters.BaseFilter.OrderByMode;

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
      List<Predicate> restrictions = new ArrayList<Predicate>();

      if (filter.userId != 0)
         restrictions.add(builder.equal(root.<Long> get("userId"), filter.userId));

      if (filter.pagingOffsetProductId != null)
      {
         if (filter.pagingOffsetProductId != 0)
            restrictions.add(builder.lt(root.<Long> get("id"), filter.pagingOffsetProductId));
         filter.addOrderBy("id", OrderByMode.DESC);
      }

      addFilterRestrictionsForSearchString(root, builder, filter, restrictions,
            Arrays.asList("name", "productCode", "barcode"));
      return restrictions;
   }

}
