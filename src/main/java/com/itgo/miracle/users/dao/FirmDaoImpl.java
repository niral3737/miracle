package com.itgo.miracle.users.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.itgo.miracle.global.dao.GenericDaoImpl;
import com.itgo.miracle.users.entities.Firm;
import com.itgo.miracle.users.filters.FirmFilter;

public class FirmDaoImpl extends GenericDaoImpl<Firm, FirmFilter> implements FirmDao
{
   private static FirmDaoImpl instance;

   private FirmDaoImpl()
   {
   }

   static FirmDaoImpl getInstance()
   {
      if (instance == null)
         instance = new FirmDaoImpl();
      return instance;
   }

   @Override
   protected List<Predicate> getFilterConditions(CriteriaQuery<Firm> query, Root<Firm> root, CriteriaBuilder builder,
         FirmFilter filter)
   {
      List<Predicate> conditions = new ArrayList<>();

      return conditions;
   }

}
