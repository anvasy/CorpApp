package com.anvasy.rest;

import com.anvasy.model.Article;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class RestConnector {
    private static String GET_ARTICLES_URL = "http://an_vasy:8080/corp-server/articles/%s";
    private static String ARTICLE_URL = "http://an_vasy:8080/corp-server/article/%s";

    public Map<String, Object> getArticleList(String page) {
        return new RestTemplate().exchange(String.format(GET_ARTICLES_URL, page),
                HttpMethod.GET, new HttpEntity<>(new Article()), new ParameterizedTypeReference<HashMap<String, Object>>() {}).getBody();
    }

    public Article getArticle(int id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        return restTemplate.getForObject(String.format(ARTICLE_URL, id), Article.class);
    }

    public void removeArticle(int id) {
        new RestTemplate().delete(String.format(ARTICLE_URL, id));
    }

    public void updateArticle(Article article) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        restTemplate.put(String.format(ARTICLE_URL, article.getId()), article, Article.class);
    }
}
