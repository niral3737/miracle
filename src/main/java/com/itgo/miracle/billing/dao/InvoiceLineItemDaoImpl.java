package com.itgo.miracle.billing.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.itgo.miracle.billing.entities.InvoiceLineItem;
import com.itgo.miracle.billing.filters.InvoiceLineItemFilter;
import com.itgo.miracle.global.dao.GenericDaoImpl;

public class InvoiceLineItemDaoImpl extends GenericDaoImpl<InvoiceLineItem, InvoiceLineItemFilter>
      implements InvoiceLineItemDao
{
   private static InvoiceLineItemDaoImpl instance;

   private InvoiceLineItemDaoImpl()
   {
   }

   static InvoiceLineItemDaoImpl getInstance()
   {
      if (instance == null)
         instance = new InvoiceLineItemDaoImpl();
      return instance;
   }

   @Override
   protected List<Predicate> getFilterConditions(CriteriaQuery<InvoiceLineItem> query, Root<InvoiceLineItem> root,
         CriteriaBuilder builder, InvoiceLineItemFilter filter)
   {
      return null;
   }

}
