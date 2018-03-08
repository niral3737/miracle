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
import com.itgo.miracle.users.dao.UserDao;
import com.itgo.miracle.users.entities.User;
import com.itgo.miracle.users.filters.UserFilter;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserService
{
   private UserDao getDao()
   {
      return DaoFactory.getUserDao();
   }

   @POST
   @Path("/set")
   public Response set(User user)
   {
      //validate user
      getDao().store(user);
      return Response.status(Status.OK).entity(user.getId()).build();
   }

   @POST
   @Path("/get")
   public Response get(UserFilter filter)
   {
      List<User> users = getDao().loadByFilter(filter);

      return Response.status(Status.OK).entity(users).build();
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
