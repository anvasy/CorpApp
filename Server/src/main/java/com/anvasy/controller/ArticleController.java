package com.anvasy.controller;

import com.anvasy.model.Article;
import com.anvasy.services.ArticleService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController {

    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ArticleController.class);

    @RequestMapping(value = "/article", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Article> getArticleList() {
        ArticleService articleService = new ArticleService();
        List<Article> articles = articleService.getAll();
        logger.info(articles.get(0).getTopic());
        return articles;
    }

    @RequestMapping(value = "/article/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Article getArticle(@PathVariable("id") String id) {
        ArticleService articleService = new ArticleService();
        return articleService.get(Integer.valueOf(id));
    }

    @DeleteMapping("/article/{id}")
    public ResponseEntity<?> removeArticle(@PathVariable("id") String id) {
        ArticleService articleService = new ArticleService();
        articleService.delete(Integer.valueOf(id));
        articleService.close();
        return ResponseEntity.ok("");
    }

    @PutMapping("/article/{id}")
    public ResponseEntity<?> updateArticle(@PathVariable("id") String id, @RequestBody Article article) {
        ArticleService articleService = new ArticleService();
        if(id.equals("0"))
            articleService.save(article);
        else
            articleService.update(article);
        articleService.close();
        return ResponseEntity.ok("");
    }
}
