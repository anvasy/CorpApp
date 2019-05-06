package com.anvasy.services;

import com.anvasy.model.Article;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ArticleService extends com.anvasy.services.Service<Article> {

    @Override
    public Article get(int id) {
        return session.get(Article.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Article> getAll() {
       return  (List<Article>) session.createQuery("from Article").list();
    }

    @Override
    Session getCurrentSession() {
        return session;
    }
}
