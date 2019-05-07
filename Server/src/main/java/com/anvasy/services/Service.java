package com.anvasy.services;

import com.anvasy.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

@org.springframework.stereotype.Service
public abstract class Service<T> {

    protected Session session;

    Service() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        session = sessionFactory.openSession();
    }

    public void save(T t) {
        session.save(t);
        Transaction tx = session.beginTransaction();
        session.flush();
        tx.commit();
    }

    public void delete(int id){
        T t = get(id);
        session.delete(t);
        Transaction tx = session.beginTransaction();
        session.flush();
        tx.commit();
    }

    public void update(T t) {
        session.update(t);
        Transaction tx = session.beginTransaction();
        session.flush();
        tx.commit();
    }

    public void close() {
        session.close();
    }

    abstract T get(int id);

    abstract List<T> getAll();

    abstract Session getCurrentSession();

    public Session getSession()  {
        return session;
    }
}
