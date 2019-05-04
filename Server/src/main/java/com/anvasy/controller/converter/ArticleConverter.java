package com.anvasy.controller.converter;

import com.anvasy.model.Article;
import com.anvasy.model.data.ArticleData;
import org.springframework.stereotype.Component;

@Component
public class ArticleConverter {
    public Article toModel(ArticleData articleData) {
        return new Article();
    }
}
