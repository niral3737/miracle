package com.itgo.miracle.billing_configuration.service;

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

import com.itgo.miracle.billing_configuration.dao.DaoFactory;
import com.itgo.miracle.billing_configuration.dao.ProductDao;
import com.itgo.miracle.billing_configuration.entities.Product;
import com.itgo.miracle.billing_configuration.filters.ProductFilter;
import com.itgo.miracle.global.entities.ServiceResponse;
import com.itgo.miracle.global.exceptions.ServerValidation;
import com.itgo.miracle.global.exceptions.ValidationException;

@Path("/product")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductService
{
   private ProductDao getDao()
   {
      return DaoFactory.getProductDao();
   }

   @POST
   @Path("/set")
   public Response set(Product product) throws ValidationException
   {
      ServerValidation.validate(product);

      //validate product
      getDao().store(product);
      return Response.status(Status.OK).entity(new ServiceResponse(true, product.getId())).build();
   }

   @POST
   @Path("/get")
   public Response get(ProductFilter filter)
   {
      List<Product> products = getDao().loadByFilter(filter);

      return Response.status(Status.OK).entity(new ServiceResponse(true, products)).build();
   }

   @DELETE
   @Path("/remove/{id}")
   public Response remove(@PathParam("id") long id) throws ValidationException
   {
      getDao().delete(id);

      return Response.status(Status.OK).entity(new ServiceResponse(true)).build();
   }
}
