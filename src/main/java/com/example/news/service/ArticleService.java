package com.example.news.service;

import com.example.news.exception.ResourceNotFoundException;
import com.example.news.model.Article;
import com.example.news.repo.ArticleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository repo;

    public ArticleService(ArticleRepository repo) {
        this.repo = repo;
    }

    public List<Article> getLatestNews() {
        return repo.findAll()
                .stream()
                .sorted(Comparator.comparing(Article::getPublishedAt).reversed())
                .toList();
    }

    public List<Article> searchNews(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return List.of();
        }

        return repo.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(keyword, keyword);
    }

    public List<Article> getByCategory(String category) {
        return repo.findByCategoryIgnoreCase(category);
    }

    public List<Article> getByDate(LocalDate date) {
        return repo.findByPublishedAt(date);
    }

    public Article getById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found: " + id));
    }

    public Article addArticle(Article article) {

        if (repo.existsByTitleIgnoreCase(article.getTitle())) {
            throw new RuntimeException("Duplicate article: " + article.getTitle());
        }

        return repo.save(article);
    }

    public void deleteArticle(int id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Article not found: " + id);
        }
        repo.deleteById(id);
    }
}