package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private SessionFactory sessionFactory;
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUser(String model, int series) {
        String HQL = "from Car car LEFT OUTER JOIN FETCH car.user WHERE car.model =:CarModel AND car.series =:CarSeries";
        Car car = entityManager.createQuery(HQL, Car.class).setParameter("CarModel", model).setParameter("CarSeries", series).getSingleResult();
        return car.getUser();
    }

    @Override
    public void addCar(Car car) {
        entityManager.persist(car);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = entityManager.createQuery("from User", User.class);
        return query.getResultList();
    }
}
