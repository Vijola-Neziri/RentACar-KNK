package repository.Interface;

import models.Admin;

import java.util.List;

public interface AdminInterface {
    Admin getAdminByUsernameAndPassword(String username, String password);

    List<Admin> getAllAdmins();

    boolean signup(String adminUsername, String adminName, String adminPhone, String adminAddress,
                   String adminPassword);
}
