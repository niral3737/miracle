package com.itgo.miracle.global;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.itgo.miracle.users.dao.DaoFactory;
import com.itgo.miracle.users.entities.User;

public class SetUp
{
   public static void main(String[] args)
   {
      FileReader reader;
      try
      {
         reader = new FileReader("src/main/resources/environment.properties");
         Properties p = new Properties();
         p.load(reader);
         if (p.getProperty("env").equals("test"))
         {
            User user = new User();
            user.setName("TestName");
            user.setUsername("TestUsername");
            DaoFactory.getUserDao().store(user);
            Environment.TEST_USER_ID = user.getId();
         }
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }

   }
}
