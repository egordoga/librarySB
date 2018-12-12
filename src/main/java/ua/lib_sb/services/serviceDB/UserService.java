package ua.lib_sb.services.serviceDB;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lib_sb.dao.IUserRepository;
import ua.lib_sb.entity.User;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User findUserByActivationCode(String activationCode) {
        return userRepository.findByActivationCode(activationCode);
    }
}
