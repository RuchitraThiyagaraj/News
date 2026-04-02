package com.example.news.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String content;
    private String category;
    private LocalDate publishedAt;

    public Article() {
    }

    public Article(String title, String content, String category, LocalDate publishedAt) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.publishedAt = publishedAt;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCategory() {
        return category;
    }

    public LocalDate getPublishedAt() {
        return publishedAt;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPublishedAt(LocalDate publishedAt) {
        this.publishedAt = publishedAt;
    }
}