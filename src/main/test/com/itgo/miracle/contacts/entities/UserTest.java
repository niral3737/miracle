package com.itgo.miracle.contacts.entities;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.itgo.miracle.users.dao.DaoFactory;
import com.itgo.miracle.users.entities.User;
import com.itgo.miracle.users.filters.UserFilter;

public class UserTest
{
   @Test
   public void testCreate()
   {
      User user = new User();
      user.setName("Dhaval");
      user.setUsername("dsp");

      DaoFactory.getUserDao().store(user);
      assertTrue(user.getId() > 0);

      UserFilter filter = new UserFilter();
      filter.name = "Dhaval";

      List<User> loaded = DaoFactory.getUserDao().loadByFilter(filter);
      System.out.println(loaded);
   }
}
