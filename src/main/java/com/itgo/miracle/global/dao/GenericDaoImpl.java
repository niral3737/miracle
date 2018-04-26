package com.itgo.miracle.global.dao;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.itgo.miracle.global.entities.BaseObject;
import com.itgo.miracle.global.exceptions.DaoException;
import com.itgo.miracle.global.filters.BaseFilter;
import com.itgo.miracle.global.filters.BaseFilter.OrderByMode;
import com.itgo.miracle.global.utils.StringUtils;
import com.itgo.miracle.utils.HibernateUtil;

public abstract class GenericDaoImpl<T extends BaseObject, F extends BaseFilter> implements GenericDao<T, F>
{

   private F filterInstance;

   private void createFilterInstanceForLoadAll()
   {
      try
      {
         filterInstance = getFilterClass().newInstance();
      }
      catch (InstantiationException | IllegalAccessException e)
      {
         e.printStackTrace();
      }
   }

   protected GenericDaoImpl()
   {
      createFilterInstanceForLoadAll();
   }

   @Override
   public void store(T obj)
   {
      Session session = null;
      try
      {
         session = HibernateUtil.getSessionFactory().openSession();

         session.beginTransaction();

         if (obj.isNew())
         {
            session.save(obj);
         }
         else
         {
            session.update(obj);
         }

         session.getTransaction().commit();
      }
      catch (Exception exc)
      {
         session.getTransaction().rollback();
         throw new DaoException(exc.getMessage());
      }
      finally
      {
         if (session != null)
            session.close();
      }
   }

   @Override
   public List<T> loadByFilter(F filter)
   {
      List<T> result = new ArrayList<>();

      if (canLoadById(filter))
      {
         T loaded = load(filter.id);
         if (loaded != null && loaded.isActive())
            result.add(loaded);

      }
      else
      {
         Session session = null;
         try
         {
            session = HibernateUtil.getSessionFactory().openSession();
            Class<T> persistentClass = getPersistentClass();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<T> query = builder.createQuery(persistentClass);
            Root<T> root = query.from(persistentClass);

            //SELECT
            query.select(root);

            //WHERE
            List<Predicate> conditions = new ArrayList<Predicate>();

            //active=1
            if (filter.activeOnly)
               addRestrictionsForActiveState(builder, root, conditions);

            List<Predicate> filterConditions = getFilterConditions(query, root, builder, filter);
            if (filterConditions != null && !filterConditions.isEmpty())
               conditions.addAll(filterConditions);

            if (!conditions.isEmpty())
               query.where(conditions.toArray(new Predicate[] {}));

            // ORDER BY ...
            if (filter.hasOrderByFields())
            {
               List<Order> orderByExpressions = getOrderByFields(filter, builder, root);
               query.orderBy(orderByExpressions);
            }

            TypedQuery<T> typedQuery = session.createQuery(query);

            // LIMIT ...
            if (filter.limit > 0)
               typedQuery.setMaxResults(filter.limit);

            //EXECUTE
            result = typedQuery.getResultList();
         }
         catch (Exception e)
         {
            if (session != null)
               session.getTransaction().rollback();

            throw new DaoException(e.getMessage());
         }
         finally
         {
            if (session != null)
               session.close();
         }
      }
      return result;
   }

   protected abstract List<Predicate> getFilterConditions(CriteriaQuery<T> query, Root<T> root, CriteriaBuilder builder,
         F filter);

   private void addRestrictionsForActiveState(CriteriaBuilder builder, Root<T> root, List<Predicate> conditions)
   {
      conditions.add(builder.equal(root.get("active"), 1));
   }

   private List<Order> getOrderByFields(BaseFilter filter, CriteriaBuilder builder, Root<?> path)
   {
      ArrayList<Order> orders = new ArrayList<Order>();

      if (filter.hasOrderByFields())
      {
         LinkedHashMap<String, OrderByMode> orderByFields = filter.getOrderByFields();
         for (String field : orderByFields.keySet())
         {
            OrderByMode mode = orderByFields.get(field);
            if (mode == OrderByMode.ASC)
               orders.add(builder.asc(path.get(field)));
            else
               orders.add(builder.desc(path.get(field)));
         }
      }

      return orders;
   }

   private T load(long id)
   {
      Session session = null;
      try
      {
         session = HibernateUtil.getSessionFactory().openSession();
         T loaded = session.find(getPersistentClass(), id);

         return loaded;
      }
      catch (Exception exc)
      {
         throw new DaoException(exc.getMessage());
      }
      finally
      {
         if (session != null)
            session.close();
      }
   }

   protected void addFilterRestrictionsForSearchString(Root<T> root, CriteriaBuilder builder, BaseFilter filter,
         List<Predicate> restrictions)
   {
      addFilterRestrictionsForSearchString(root, builder, filter, restrictions, "name");
   }

   protected void addFilterRestrictionsForSearchString(Root<T> root, CriteriaBuilder builder, BaseFilter filter,
         List<Predicate> restrictions, String attributeName)
   {
      if (StringUtils.isNotNullOrEmpty(filter.searchString))
         restrictions.add(builder.like(root.<String> get(attributeName), "%" + filter.searchString + "%"));
   }

   protected void addFilterRestrictionsForSearchString(Root<T> root, CriteriaBuilder builder, BaseFilter filter,
         List<Predicate> restrictions, List<String> attributeNames)
   {
      if (StringUtils.isNotNullOrEmpty(filter.searchString))
      {
         Predicate searchStringRestrictions[] = new Predicate[attributeNames.size()];
         for (int i = 0; i < attributeNames.size(); i++)
            searchStringRestrictions[i] = builder.like(root.<String> get(attributeNames.get(i)),
                  "%" + filter.searchString + "%");
         restrictions.add(builder.or(searchStringRestrictions));
      }
   }

   @SuppressWarnings("unchecked")
   private Class<T> getPersistentClass()
   {
      return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
   }

   @SuppressWarnings("unchecked")
   protected Class<F> getFilterClass()
   {
      return (Class<F>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
   }

   private boolean canLoadById(F filter)
   {
      return filter.id > 0;
   }

   @Override
   public List<T> loadAll()
   {
      return loadByFilter(filterInstance);
   }

   @Override
   public void delete(long id)
   {
      Session session = null;
      try
      {
         session = HibernateUtil.getSessionFactory().openSession();
         T loaded = session.find(getPersistentClass(), id);

         if (loaded != null)
         {
            loaded.setActive(false);
            store(loaded);
         }
      }
      catch (Exception exc)
      {
         throw new DaoException(exc.getMessage());
      }
      finally
      {
         if (session != null)
            session.close();
      }

   }

   @Override
   public void deleteAll()
   {
      Session session = null;
      Class<T> persistentClass = null;
      try
      {
         session = HibernateUtil.getSessionFactory().openSession();
         persistentClass = getPersistentClass();
         Query query = session.createQuery("delete from " + persistentClass.getSimpleName());
         session.getTransaction().begin();

         query.executeUpdate();

         session.getTransaction().commit();
      }
      catch (Throwable e)
      {
         e.printStackTrace();
         throw new DaoException(e.getMessage());
      }
      finally
      {
         if (session != null)
            session.close();
      }
   }
}
