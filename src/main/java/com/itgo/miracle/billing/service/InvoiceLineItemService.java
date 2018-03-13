package com.itgo.miracle.billing.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.itgo.miracle.billing.dao.DaoFactory;
import com.itgo.miracle.billing.dao.InvoiceLineItemDao;
import com.itgo.miracle.billing.entities.InvoiceLineItem;
import com.itgo.miracle.billing.filters.InvoiceLineItemFilter;

@Path("/InvoiceLineItem")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InvoiceLineItemService
{
   private InvoiceLineItemDao getDao()
   {
      return DaoFactory.getInvoiceLineItemDao();
   }

   @POST
   @Path("/set")
   public Response set(InvoiceLineItem invoiceLineItem)
   {
      //validate user
      getDao().store(invoiceLineItem);
      return Response.status(Status.OK).entity(invoiceLineItem.getId()).build();
   }

   @POST
   @Path("/get")
   public Response get(InvoiceLineItemFilter filter)
   {
      List<InvoiceLineItem> invoiceLineItem = getDao().loadByFilter(filter);

      return Response.status(Status.OK).entity(invoiceLineItem).build();
   }

   @DELETE
   @Path("/remove/{id}")
   public Response remove(@PathParam("id") long id)
   {
      System.out.println(id);
      getDao().delete(id);

      return Response.status(Status.OK).build();
   }

}
