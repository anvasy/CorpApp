package com.anvasy.controller.converter;

import com.anvasy.model.Article;
import com.anvasy.model.data.ArticleData;

public class ArticleConverter {

    public Article toModel(ArticleData articleData) {
        return new Article();
    }
}
