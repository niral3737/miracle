package com.itgo.miracle.users.dao;

public class DaoFactory
{
   public static UserDao getUserDao()
   {
      return UserDaoImpl.getInstance();
   }

   public static FirmDao getFirmDao()
   {
      return FirmDaoImpl.getInstance();
   }

   public static AddressDao getAddressDao()
   {
      return AddressDaoImpl.getInstance();
   }

   public static CustomerDao getCustomerDao()
   {
      return CustomerDaoImpl.getInstance();
   }
}
