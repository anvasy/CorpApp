package com.anvasy.rest;

import com.anvasy.model.Article;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class RestConnector {
    private static String GET_ARTICLES_URL = "http://localhost:4848/corp-server/article/";
    private static String ARTICLE_URL = "http://localhost:4848/corp-server/article/%s";

    public List<Article> getArticleList() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        ResponseEntity<Article[]> response = restTemplate.getForEntity(GET_ARTICLES_URL, Article[].class);

        return Arrays.asList(response.getBody());
    }

    public Article getArticle(int id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        return restTemplate.getForObject(String.format(ARTICLE_URL, id), Article.class);
    }

    public void removeArticle(Article article) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(String.format(ARTICLE_URL, article.getId()));
    }

    public void updateArticle(Article article) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        restTemplate.put(String.format(ARTICLE_URL, article.getId()), article, Article.class);
    }
}
