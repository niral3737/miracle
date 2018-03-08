package com.itgo.miracle.users.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.itgo.miracle.global.dao.GenericDaoImpl;
import com.itgo.miracle.users.entities.User;
import com.itgo.miracle.users.filters.UserFilter;

class UserDaoImpl extends GenericDaoImpl<User, UserFilter> implements UserDao
{
   private static UserDaoImpl instance;

   private UserDaoImpl()
   {
   }

   static UserDaoImpl getInstance()
   {
      if (instance == null)
         instance = new UserDaoImpl();
      return instance;
   }

   @Override
   protected List<Predicate> getFilterConditions(CriteriaQuery<User> query, Root<User> root, CriteriaBuilder builder,
         UserFilter filter)
   {
      List<Predicate> conditions = new ArrayList<>();

      if (filter.name != null)
         conditions.add(builder.equal(root.get("name"), filter.name));

      return conditions;
   }

}
