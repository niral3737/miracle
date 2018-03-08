package com.itgo.miracle.users.dao;

import com.itgo.miracle.global.dao.GenericDao;
import com.itgo.miracle.users.entities.Customer;
import com.itgo.miracle.users.filters.CustomerFilter;

public interface CustomerDao extends GenericDao<Customer, CustomerFilter>
{

}
