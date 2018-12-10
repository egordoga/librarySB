package ua.lib_sb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.lib_sb.config.util.AuthValidator;
import ua.lib_sb.entity.Role;
import ua.lib_sb.entity.User;
import ua.lib_sb.model.UserForm;
import ua.lib_sb.services.MailSender;
import ua.lib_sb.services.serviceDB.RoleService;
import ua.lib_sb.services.serviceDB.UserService;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Controller
public class AuthController {
    private static final Byte ACTIVE_FALSE = 0;
    private static final Byte ACTIVE_TRUE = 1;

    @Autowired
    private AuthValidator authValidator;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private MailSender mailSender;

    @GetMapping("/registration")
    public ModelAndView viewRegistration(Model model) {
        return new ModelAndView("registration", "userForm", new UserForm());
    }

    @PostMapping("/registration")
    public ModelAndView doRegistration(Model model, @ModelAttribute UserForm userForm, BindingResult bindingResult) {
       // model.addAttribute("user", userForm);

        User user = new User();
        user.setEmail(userForm.getEmail());
        user.setPass(bCryptPasswordEncoder.encode(userForm.getPass()));

        authValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return new ModelAndView("registration", "error", true);
        }
        Set<Role> roles = new HashSet<>();
        if ("admin".equals(userForm.getPass())) {
            roles.add(roleService.findRoleByName("admin"));
            roles.add(roleService.findRoleByName("user"));
            user.setRoles(roles);
        } else {
            roles.add(roleService.findRoleByName("user"));
            user.setRoles(roles);
        }
        user.setActive(false);

        String activationCode = UUID.randomUUID().toString();
        user.setActivationCode(activationCode);

        String message = String.format("Добрый день, %s! \n" +
                        "Для завершения регистрации перейдите по ссылке: http://localhost:8080/activate/%s",
                user.getEmail(), activationCode);
        mailSender.sendMail(user.getEmail(), "Activation code", message);

        userService.saveUser(user);
        return new ModelAndView("registration", "mess", "Для завершения регистрации проверьте почту");
    }

    @GetMapping("activate/{code}")
    public String doActivation(@PathVariable("code") String code, Model model) {
        User user = userService.findUserByActivationCode(code);
        if (user != null) {
            model.addAttribute("message", "Вы успешно прошли активацию");
            user.setActive(true);
            user.setActivationCode(null);
            userService.saveUser(user);
            return "activation";
        } else {
            model.addAttribute("message", "Код активации не найден");
            return "registration";
        }
    }

    @GetMapping("/login")
    public ModelAndView viewLogin() {
        return new ModelAndView("login");
    }

   /* @PostMapping("/login")
    public String doLogin(@ModelAttribute("userForm") UserForm userForm) {

    }*/
}
