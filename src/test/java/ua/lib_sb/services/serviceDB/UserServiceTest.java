package ua.lib_sb.services.serviceDB;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ua.lib_sb.dao.IUserRepository;
import ua.lib_sb.entity.User;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @MockBean
    private IUserRepository userRepository;

    private User user;


    @Before
    public void setUp() {
        user = new User();
    }

    @Test
    public void findUserByEmail() {
        when(userRepository.findByEmail("aa")).thenReturn(user);
        User result = userService.findUserByEmail("aa");
        assertEquals(user, result);
    }

    @Test
    public void saveUser() {
        userService.saveUser(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void findUserByActivationCode() {
        when(userRepository.findByActivationCode("aa")).thenReturn(user);
        User result = userService.findUserByActivationCode("aa");
        assertEquals(user, result);
    }
}