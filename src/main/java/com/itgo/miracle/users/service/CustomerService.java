package com.itgo.miracle.users.service;

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

import com.itgo.miracle.users.dao.CustomerDao;
import com.itgo.miracle.users.dao.DaoFactory;
import com.itgo.miracle.users.entities.Customer;
import com.itgo.miracle.users.filters.CustomerFilter;

@Path("/customer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerService
{
   private CustomerDao getDao()
   {
      return DaoFactory.getCustomerDao();
   }

   @POST
   @Path("/set")
   public Response set(Customer customer)
   {
      //validate user
      getDao().store(customer);
      return Response.status(Status.OK).entity(customer.getId()).build();
   }

   @POST
   @Path("/get")
   public Response get(CustomerFilter filter)
   {
      List<Customer> cutomers = getDao().loadByFilter(filter);

      return Response.status(Status.OK).entity(cutomers).build();
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
