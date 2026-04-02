package com.example.news.repo;

import com.example.news.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

    List<Article> findByCategoryIgnoreCase(String category);

    List<Article> findByPublishedAt(LocalDate date);

    List<Article> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String t1, String t2);

    boolean existsByTitleIgnoreCase(String title);
}