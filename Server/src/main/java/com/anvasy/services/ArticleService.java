package com.anvasy.services;

import com.anvasy.model.Article;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService extends com.anvasy.services.Service<Article> {

    private Logger logger = org.apache.log4j.Logger.getLogger(ArticleService.class);

    @Override
    public Article get(int id) {
        return new Article();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Article> getAll() {
       return  (List<Article>) session.createQuery("from Article").list();
    }

    @Override
    Session getCurrentSession() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void save(Article article) {

    }

    @Override
    public void update(Article article) {

    }
}
