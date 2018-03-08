package com.itgo.miracle.users.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.itgo.miracle.global.dao.GenericDaoImpl;
import com.itgo.miracle.users.entities.Address;
import com.itgo.miracle.users.filters.AddressFilter;

public class AddressDaoImpl extends GenericDaoImpl<Address, AddressFilter> implements AddressDao
{
   private static AddressDaoImpl instance;

   private AddressDaoImpl()
   {
   }

   static AddressDaoImpl getInstance()
   {
      if (instance == null)
         instance = new AddressDaoImpl();
      return instance;
   }

   @Override
   protected List<Predicate> getFilterConditions(CriteriaQuery<Address> query, Root<Address> root,
         CriteriaBuilder builder, AddressFilter filter)
   {
      return null;
   }

}
