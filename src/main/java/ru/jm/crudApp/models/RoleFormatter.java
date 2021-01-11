package ru.jm.crudApp.models;

import org.springframework.format.Formatter;

import javax.persistence.EntityManager;
import java.text.ParseException;
import java.util.Locale;

public class RoleFormatter implements Formatter<Role> {

    private final EntityManager entityManager;

    public RoleFormatter(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Role parse(String s, Locale locale) throws ParseException {
        return entityManager.find(Role.class, Long.parseLong(s));
    }

    @Override
    public String print(Role role, Locale locale) {
        return role.getId().toString();
    }
}
