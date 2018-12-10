package ua.lib_sb.config.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.lib_sb.entity.User;
import ua.lib_sb.services.serviceDB.UserService;

@Component
public class AuthValidator implements Validator {

    private final UserService userService;

    @Autowired
    public AuthValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        if (userService.findUserByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "error.user", "Someone already has that email.");
        }

    }
}
