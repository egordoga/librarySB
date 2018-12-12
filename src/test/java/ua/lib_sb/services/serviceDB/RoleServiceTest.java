package ua.lib_sb.services.serviceDB;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ua.lib_sb.dao.IRoleRepository;
import ua.lib_sb.entity.Role;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RoleServiceTest {

    @Autowired
    private RoleService roleService;
    @MockBean
    private IRoleRepository roleRepository;


    @Test
    public void findRoleByName() {
        Role role = new Role();
        when(roleRepository.findByName("aa")).thenReturn(role);
        Role result = roleService.findRoleByName("aa");
        assertEquals(role, result);
    }
}