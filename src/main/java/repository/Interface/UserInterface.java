package repository.Interface;

import models.User;

import java.util.List;

public interface UserInterface {
    User getUserByUsernameAndPassword(String username, String password);

    List<User> getAllUsers();

    boolean signup(String username, String name, String phone, String address, String password, String gender);
}
