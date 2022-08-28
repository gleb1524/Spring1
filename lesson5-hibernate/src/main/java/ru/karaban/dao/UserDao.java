package ru.karaban.dao;

import ru.karaban.controller.FactoryController;
import ru.karaban.model.User;

import java.util.Optional;

public class UserDao {
    FactoryController factoryController = new FactoryController();

    public void init(){
        factoryController.init(new User("Mikk"));
        factoryController.init(new User("Vova"));
        factoryController.init(new User("Lada"));
    }

    public void delete(long id){
        factoryController.deleteUser(id);
    }

    public void update(User user){
        factoryController.update(user);
    }

    public Optional<User> findById(Long id){
       return factoryController.findUserById(id);
    }
//
//    public List<Product> getAll(){}
}
