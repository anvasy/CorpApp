package com.anvasy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Article {
    private int id;
    private String topic;
    private String summary;
    private String content;
    private float rate;
    private int rateNumber;

    public Article() {
        id = 0;
    }

    public Article(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTopic() { return topic; }

    public String getSummary() { return summary; }

    public String getContent() {
        return content;
    }

    public float getRate() { return rate; }

}
