package com.anvasy.services;

import com.anvasy.model.User;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
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

    public void changeRole(String username, String role) {
        if(role.equals("admin"))
            role = "user";
        else
            role = "admin";
        Query query = session.createQuery("update User set role=? where username=?");
        query.setParameter(0, role);
        query.setParameter(1, username);
        Transaction tx = session.beginTransaction();
        query.executeUpdate();
        tx.commit();
    }

    public User get(String name, String password) {
        Query query = session.createQuery("from User where username=? AND password=?");

        query.setParameter(0, name);
        query.setParameter(1, password);

        User user = (User) query.uniqueResult();
        return user;
    }

    public User register(User user) {
        try {
           session.save(user);
            Transaction tx = session.beginTransaction();
            session.flush();
            tx.commit();
        } catch (Exception ex) {
            return null;
        }
        close();
        return user;
    }
}
