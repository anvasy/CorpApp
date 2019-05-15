package com.anvasy.controller;

import com.anvasy.model.Article;
import com.anvasy.rest.RestConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class ArticleController {

    @Autowired
    private RestConnector restConnector;

    @RequestMapping(value = {"/home", "/"}, method = RequestMethod.GET)
    public ModelAndView getArticleList(@RequestParam(defaultValue = "0", required = false) String page) {
        ModelAndView modelAndView = new ModelAndView("/home");
        Map<String, Object> articles = restConnector.getArticleList(page);
        modelAndView.addObject("articles", articles.get("articles"));
        modelAndView.addObject("page", articles.get("page"));
        int max = (int)articles.get("max");
        modelAndView.addObject("max", (int)Math.ceil(max/10.0));
        return modelAndView;
    }

    @RequestMapping(value = "/article", method = RequestMethod.GET)
    public ModelAndView getArticle(@RequestParam String id) {
        ModelAndView modelAndView = new ModelAndView("/article");
        modelAndView.addObject("article",  restConnector.getArticle(Integer.valueOf(id)));
        return modelAndView;
    }

    @RequestMapping(value = "/addedit", method = RequestMethod.GET)
    public ModelAndView goToEdit(@RequestParam String id) {
        ModelAndView modelAndView = new ModelAndView("/addedit");
        if (id.equals("0"))
            modelAndView.addObject("article", new Article());
        else
            modelAndView.addObject("article", restConnector.getArticle(Integer.valueOf(id)));
        return modelAndView;
    }

    @RequestMapping(value = "/addedit", method = RequestMethod.POST)
    public String updateArticle(@ModelAttribute("article")Article article) {
        restConnector.updateArticle(article);
        return "redirect:/home";
    }

    @RequestMapping(value = "/article", method = RequestMethod.POST)
    public String removeArticle(@RequestParam String id) {
        restConnector.removeArticle(Integer.valueOf(id));
        return"redirect:/home";
    }
}
