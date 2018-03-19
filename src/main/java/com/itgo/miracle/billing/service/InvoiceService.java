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
import com.itgo.miracle.billing.dao.InvoiceDao;
import com.itgo.miracle.billing.entities.Invoice;
import com.itgo.miracle.billing.filters.InvoiceFilter;

@Path("/invoice")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InvoiceService
{
   private InvoiceDao getDao()
   {
      return DaoFactory.getInvoiceDao();
   }

   @POST
   @Path("/set")
   public Response set(Invoice invoice)
   {
      //validate user
      getDao().store(invoice);
      return Response.status(Status.OK).entity(invoice.getId()).build();
   }

   @POST
   @Path("/get")
   public Response get(InvoiceFilter filter)
   {
      List<Invoice> invoice = getDao().loadByFilter(filter);

      return Response.status(Status.OK).entity(invoice).build();
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
