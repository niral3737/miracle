package com.itgo.miracle.billing.dao;

public class DaoFactory
{
   public static InvoiceDao getInvoiceDao()
   {
      return InvoiceDaoImpl.getInstance();
   }

   public static InvoiceLineItemDao getInvoiceLineItemDao()
   {
      return InvoiceLineItemDaoImpl.getInstance();
   }
}
