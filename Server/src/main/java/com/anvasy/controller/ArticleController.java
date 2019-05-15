package com.anvasy.controller;

import com.anvasy.model.Article;
import com.anvasy.services.ArticleService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ArticleController {

    @RequestMapping(value = "/articles/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Map<String, Object> getArticleList(@PathVariable("page") String page) {
        ArticleService articleService = new ArticleService();
        return articleService.getAll(Integer.valueOf(page));
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
