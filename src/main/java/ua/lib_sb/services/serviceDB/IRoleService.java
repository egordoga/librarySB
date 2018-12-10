package ua.lib_sb.services.serviceDB;

import ua.lib_sb.entity.Role;

public interface IRoleService {
    Role findRoleByName(String name);
}
