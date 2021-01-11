package ru.jm.crudApp.dao;

import ru.jm.crudApp.models.Role;

import java.util.List;


public interface RoleDAO {

    public List<Role> getRoles();

    public Role getRoleByName(String roleName);
}
