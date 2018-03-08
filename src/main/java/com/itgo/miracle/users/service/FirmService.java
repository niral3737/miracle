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

import com.itgo.miracle.users.dao.DaoFactory;
import com.itgo.miracle.users.dao.FirmDao;
import com.itgo.miracle.users.entities.Firm;
import com.itgo.miracle.users.filters.FirmFilter;

@Path("/firm")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FirmService
{
   private FirmDao getDao()
   {
      return DaoFactory.getFirmDao();
   }

   @POST
   @Path("/set")
   public Response set(Firm firm)
   {
      //validate user
      getDao().store(firm);
      return Response.status(Status.OK).entity(firm.getId()).build();
   }

   @POST
   @Path("/get")
   public Response get(FirmFilter filter)
   {
      List<Firm> firms = getDao().loadByFilter(filter);

      return Response.status(Status.OK).entity(firms).build();
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
