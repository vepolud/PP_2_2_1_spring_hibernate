package hiber.dao;

import hiber.model.Car;
import hiber.model.User;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public User getUser(String model, int series) {
//      TypedQuery<User> query = sessionFactory.getCurrentSession().createSQLQuery("SELECT users.id, name, last_name, email, model, series FROM cars join users on users.id = cars.id where model = :model and series = :series");
//      query.setParameter("model", model);
//      query.setParameter("series", series);
//      return query.getSingleResult();
      String HQL="FROM Car car LEFT OUTER JOIN FETCH car.user WHERE car.id =:CarId";
      Car car = sessionFactory.getCurrentSession().createQuery(HQL, Car.class).setParameter("CarId", 1l).uniqueResult();
      System.out.println(car);
      return car.getUser();
   }

   @Override
   public void addCar(Car car){
      sessionFactory.getCurrentSession().save(car);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

}
