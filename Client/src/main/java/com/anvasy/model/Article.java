package com.anvasy.model;

import javax.persistence.Entity;

@Entity
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

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String text) {
        this.content = text;
    }

    public int getRateNumber() {
        return rateNumber;
    }

    public float getRate() {
        return rate;
    }

    public void setRateNumber(int rateNumber) {
        this.rateNumber = rateNumber;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
