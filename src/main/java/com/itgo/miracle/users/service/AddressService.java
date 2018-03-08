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

import com.itgo.miracle.users.dao.AddressDao;
import com.itgo.miracle.users.dao.DaoFactory;
import com.itgo.miracle.users.entities.Address;
import com.itgo.miracle.users.filters.AddressFilter;

@Path("/address")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AddressService
{
   private AddressDao getDao()
   {
      return DaoFactory.getAddressDao();
   }

   @POST
   @Path("/set")
   public Response set(Address address)
   {
      //validate user
      getDao().store(address);
      return Response.status(Status.OK).entity(address.getId()).build();
   }

   @POST
   @Path("/get")
   public Response get(AddressFilter filter)
   {
      List<Address> addresses = getDao().loadByFilter(filter);

      return Response.status(Status.OK).entity(addresses).build();
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
