package com.anvasy.controller;

import com.anvasy.controller.converter.ArticleConverter;
import com.anvasy.model.Article;
import com.anvasy.model.data.ArticleData;
import com.anvasy.services.ArticleService;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController {

    private Logger logger = org.apache.log4j.Logger.getLogger(ArticleController.class);
    private ArticleConverter articleConverter = new ArticleConverter();
    private ArticleService articleService = new ArticleService();

    @RequestMapping(value = "/article", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Article> getArticleList() {
        return articleService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getArticle(@PathVariable("id") String id) {
        Article article = articleService.get(Integer.valueOf(id));
        return ResponseEntity.ok(article);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeArticle(@PathVariable("id") String id) {
        articleService.delete(Integer.valueOf(id));
        return ResponseEntity.ok("");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateArticle(@PathVariable("id") String id, @RequestBody ArticleData article) {
        if(id.equals("0"))
            articleService.save(articleConverter.toModel(article));
        else
            articleService.update(articleConverter.toModel(article));
        return ResponseEntity.ok("");
    }
}
