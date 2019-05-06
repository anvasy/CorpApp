package com.anvasy.rest;

import com.anvasy.model.Article;
import org.apache.log4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

//-Dmaven.tomcat.port=8000 tomcat7:run-war

@Service
public class RestConnector {
    private static String GET_ARTICLES_URL = "http://localhost:8000/corp-server/article";
    private static String ARTICLE_URL = "http://localhost:8000/corp-server/article/%s";

    private Logger logger = org.apache.log4j.Logger.getLogger(RestConnector.class);

    public List<Article> getArticleList() {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Article> request = new HttpEntity<>(new Article());
        ResponseEntity<List<Article>> articleResponse =
                restTemplate.exchange(GET_ARTICLES_URL,
                        HttpMethod.GET, request, new ParameterizedTypeReference<List<Article>>() {
                        });
        List<Article> articles = articleResponse.getBody();
        return articles;
    }

    public Article getArticle(int id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        return restTemplate.getForObject(String.format(ARTICLE_URL, id), Article.class);
    }

    public void removeArticle(int id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(String.format(ARTICLE_URL, id));
    }

    public void updateArticle(Article article) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        restTemplate.put(String.format(ARTICLE_URL, article.getId()), article, Article.class);
    }
}
