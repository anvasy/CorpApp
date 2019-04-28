package com.anvasy.services;

import com.anvasy.model.User;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends com.anvasy.services.Service<User> {

    @Override
    User get(int id) {
        return null;
    }

    @Override
    List<User> getAll() {
        return null;
    }

    @Override
    Session getCurrentSession() {
        return null;
    }
}
