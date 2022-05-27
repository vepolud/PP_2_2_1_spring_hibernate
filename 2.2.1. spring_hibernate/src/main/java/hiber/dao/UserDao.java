package hiber.dao;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserDao {
   void add(User user);

   User getUser(String model, int series);
   List<User> listUsers();
   public void addCar(Car car);
}
