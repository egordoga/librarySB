package ua.lib_sb.services.serviceDB;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import ua.lib_sb.dao.IUserRepository;
import ua.lib_sb.entity.User;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceTest {


    private UserService userService = new UserService();
    private User user = new User();

    @Mock
    private IUserRepository mockUserRepository;

    @Before
    public void setup() {
        initMocks(this);
        userService.setUserRepository(mockUserRepository);
        user.setId(1L);
        user.setEmail("test@test.com");
        user.setActivationCode("111");

        when(mockUserRepository.save(any())).thenReturn(user);
        when(mockUserRepository.findByEmail(anyString())).thenReturn(user);
        when(mockUserRepository.findByActivationCode(anyString())).thenReturn(user);
    }

    @Test
    public void findUserByEmail() {
        final String email = "test@test.com";
        final User result = userService.findUserByEmail(email);
        assertEquals(email, result.getEmail());
    }

    @Test
    public void saveUser() {
        final User result = userService.saveUser(user);
        assertEquals("test@test.com", result.getEmail());
    }

    @Test
    public void findUserByActivationCode() {
        final User result = userService.findUserByActivationCode("111");
        assertEquals("111", result.getActivationCode());
    }
}