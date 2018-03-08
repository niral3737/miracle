package com.itgo.miracle.global.dao;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.itgo.miracle.global.entities.BaseObject;
import com.itgo.miracle.global.exceptions.DaoException;
import com.itgo.miracle.global.filters.BaseFilter;
import com.itgo.miracle.utils.HibernateUtil;

public abstract class GenericDaoImpl<T extends BaseObject, F extends BaseFilter> implements GenericDao<T, F>
{
   @Override
   public void store(T obj)
   {
      Session session = null;
      try
      {
         session = HibernateUtil.getSessionFactory().openSession();

         session.beginTransaction();

         session.save(obj);

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

            query.where(conditions.toArray(new Predicate[] {}));

            TypedQuery<T> typedQuery = session.createQuery(query);

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
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public void delete(long id)
   {
      // TODO Auto-generated method stub

   }

   @Override
   public void deleteAll()
   {
      // TODO Auto-generated method stub

   }
}
