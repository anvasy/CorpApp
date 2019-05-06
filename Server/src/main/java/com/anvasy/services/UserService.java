package com.anvasy.services;

import com.anvasy.model.User;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends com.anvasy.services.Service<User> {

    @Override
    public User get(int id) {
        return session.get(User.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAll() {
        return (List<User>) session.createQuery("from User").list();
    }

    @Override
    public Session getCurrentSession() {
        return session;
    }
}
