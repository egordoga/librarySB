package ua.lib_sb.services.serviceDB;

import ua.lib_sb.entity.User;

public interface IUserService {
    User findUserByEmail(String email);

    void saveUser(User user);

    User findUserByActivationCode(String activationCode);
}
