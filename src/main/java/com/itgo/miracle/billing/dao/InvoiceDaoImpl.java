package com.itgo.miracle.billing.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.itgo.miracle.billing.entities.Invoice;
import com.itgo.miracle.billing.filters.InvoiceFilter;
import com.itgo.miracle.global.dao.GenericDaoImpl;

public class InvoiceDaoImpl extends GenericDaoImpl<Invoice, InvoiceFilter> implements InvoiceDao
{
   private static InvoiceDaoImpl instance;

   private InvoiceDaoImpl()
   {
   }

   static InvoiceDaoImpl getInstance()
   {
      if (instance == null)
         instance = new InvoiceDaoImpl();
      return instance;
   }

   @Override
   protected List<Predicate> getFilterConditions(CriteriaQuery<Invoice> query, Root<Invoice> root,
         CriteriaBuilder builder, InvoiceFilter filter)
   {
      List<Predicate> conditions = new ArrayList<>();

      if (filter.userId != 0)
         conditions.add(builder.equal(root.get("userId"), filter.userId));

      return conditions;
   }

}
