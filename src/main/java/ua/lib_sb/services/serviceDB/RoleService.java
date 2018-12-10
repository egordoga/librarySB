package ua.lib_sb.services.serviceDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lib_sb.dao.IRoleRepository;
import ua.lib_sb.entity.Role;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findByName(name);
    }
}
