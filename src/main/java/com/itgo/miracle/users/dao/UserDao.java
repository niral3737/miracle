package com.itgo.miracle.users.dao;

import com.itgo.miracle.global.dao.GenericDao;
import com.itgo.miracle.users.entities.User;
import com.itgo.miracle.users.filters.UserFilter;

public interface UserDao extends GenericDao<User, UserFilter>
{
}
