package com.anvasy.services;

import com.anvasy.model.Article;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService extends com.anvasy.services.Service<Article> {

    @Override
    public Article get(int id) {
        return new Article();
    }

    @Override
    public List<Article> getAll() {
        return new ArrayList<>();
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
