package com.anvasy.controller;

import com.anvasy.controller.converter.ArticleConverter;
import com.anvasy.model.Article;
import com.anvasy.model.data.ArticleData;
import com.anvasy.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleConverter articleConverter;

    @Autowired
    private ArticleService articleService;

    @GetMapping("/")
    public ResponseEntity<?> getArticleList() {
        List<Article> articleList = articleService.getAll();
        return ResponseEntity.ok(articleList);
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
