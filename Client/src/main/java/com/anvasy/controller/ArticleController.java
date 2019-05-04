package com.anvasy.controller;

import com.anvasy.model.Article;
import com.anvasy.rest.RestConnector;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ArticleController {

    private Logger logger = org.apache.log4j.Logger.getLogger(ArticleController.class);

    @Autowired
    private RestConnector restConnector;

    @RequestMapping(value = {"/home", "/"}, method = RequestMethod.GET)
    public ModelAndView getArticleList() {
        ModelAndView modelAndView = new ModelAndView("/home");
        List<Article> articles = restConnector.getArticleList();
        logger.info(articles.size() + " " + articles.get(0).getTopic() + " " + articles.get(0).getId());
        modelAndView.addObject("articles", articles);
        return modelAndView;
    }

    @RequestMapping(value = "/article", method = RequestMethod.GET)
    public ModelAndView getArticle(@RequestParam String id) {
        ModelAndView modelAndView = new ModelAndView("/article");
        Article article = restConnector.getArticle(Integer.valueOf(id));
        modelAndView.addObject("article", article);
        return modelAndView;
    }

    @RequestMapping(value = "/addedit", method = RequestMethod.POST)
    public ModelAndView updateArticle(@ModelAttribute("article")Article article) {
        restConnector.updateArticle(article);
        return getArticleList();
    }

    @RequestMapping(value = "/article", method = RequestMethod.POST)
    public ModelAndView removeArticle(@ModelAttribute("article")Article article) {
        restConnector.removeArticle(article);
        return getArticleList();
    }
}
