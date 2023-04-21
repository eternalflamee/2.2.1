package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      Car bmw = new Car("BMW", 1);
      Car rr = new Car("Rolls-Royce", 2);
      Car cadillac = new Car("Cadillac", 3);
      Car lamborghini = new Car("Lamborghini", 4);

      carService.add(bmw);
      carService.add(rr);
      carService.add(cadillac);
      carService.add(lamborghini);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", bmw));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", rr));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", cadillac));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", lamborghini));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }

      context.close();
   }
}
