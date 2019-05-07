package com.anvasy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "topic")
    private String topic;

    @Column(name = "summary")
    private String summary;

    @Column(name = "content")
    private String content;

    @Column(name = "rate")
    private float rate;

    @Column(name = "rate_number")
    private int rateNumber;

    @ManyToMany
    @JoinTable(name = "rated_articles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "article_id"))
    @JsonIgnore
    private List<User> users = new ArrayList<>();

    public Article() {
        id = 0;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setContent(String text) {
        this.content = text;
    }

    public int getId() {
        return id;
    }
}
