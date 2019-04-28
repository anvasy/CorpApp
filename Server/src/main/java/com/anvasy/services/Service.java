package com.anvasy.services;

import com.anvasy.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public abstract class Service<T> {

    private Session session;

    Service() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        session = sessionFactory.openSession();
    }

    public void save(T t) {
        session.save(t);
        session.flush();
    }

    public void delete(int id){
        T t = get(id);
        session.delete(t);
        session.flush();
    }

    public void update(T t) {
        session.update(t);
        session.flush();
    }

    public void close() {
        session.close();
    }

    abstract T get(int id);

    abstract List<T> getAll();

    abstract Session getCurrentSession();
}
