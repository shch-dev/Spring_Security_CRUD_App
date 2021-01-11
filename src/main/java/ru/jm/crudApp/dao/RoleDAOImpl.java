package ru.jm.crudApp.dao;

import org.springframework.stereotype.Repository;
import ru.jm.crudApp.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {

@PersistenceContext
private EntityManager entityManager;

    @Override
    public List<Role> getRoles() {
        Query query = entityManager.createQuery("from Role");
        List<Role> result = query.getResultList();
        return result;
    }

    @Override
    public Role getRoleByName(String roleName) {
        Query query = entityManager.createQuery("from Role where name = :name", Role.class);
        query.setParameter("name", roleName);
        return (Role) query.getSingleResult();
    }
}
