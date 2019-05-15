package com.anvasy.services;

import com.anvasy.model.Article;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @SuppressWarnings("unchecked")
    public  Map<String, Object> getAll(int page) {
        List<Article> articles =  session.createQuery("from Article").list();
        Map<String, Object> map = new HashMap<>();
        map.put("articles", page * 10 + 10 < articles.size() ? articles.subList(page * 10, page * 10 + 10) :  articles.subList(page * 10, articles.size() - 1));
        map.put("max", articles.size());
        map.put("page", page);
        return map;
    }

    @Override
    Session getCurrentSession() {
        return session;
    }
}
