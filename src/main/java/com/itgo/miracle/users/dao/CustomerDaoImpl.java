package com.itgo.miracle.users.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.itgo.miracle.global.dao.GenericDaoImpl;
import com.itgo.miracle.global.filters.BaseFilter.OrderByMode;
import com.itgo.miracle.users.entities.Customer;
import com.itgo.miracle.users.filters.CustomerFilter;

public class CustomerDaoImpl extends GenericDaoImpl<Customer, CustomerFilter> implements CustomerDao
{
   private static CustomerDaoImpl instance;

   private CustomerDaoImpl()
   {
   }

   static CustomerDaoImpl getInstance()
   {
      if (instance == null)
         instance = new CustomerDaoImpl();
      return instance;
   }

   @Override
   protected List<Predicate> getFilterConditions(CriteriaQuery<Customer> query, Root<Customer> root,
         CriteriaBuilder builder, CustomerFilter filter)
   {
      List<Predicate> restrictions = new ArrayList<Predicate>();

      if (filter.userId != 0)
         restrictions.add(builder.equal(root.<Long> get("userId"), filter.userId));

      if (filter.pagingOffsetCustomerId != null)
      {
         if (filter.pagingOffsetCustomerId != 0)
            restrictions.add(builder.lt(root.<Long> get("id"), filter.pagingOffsetCustomerId));
         filter.addOrderBy("id", OrderByMode.DESC);
      }

      addFilterRestrictionsForSearchString(root, builder, filter, restrictions, Arrays.asList("name", "address"));
      return restrictions;
   }

}
