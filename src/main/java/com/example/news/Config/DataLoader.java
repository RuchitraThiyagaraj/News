package com.example.news.config;

import com.example.news.model.Article;
import com.example.news.repo.ArticleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DataLoader {
    private final ArticleRepository repo;
    public DataLoader(ArticleRepository repo) {
        this.repo = repo;
    }
    @PostConstruct
    public void init() {
        if (repo.count() > 0) return;
        List<Article> list = new ArrayList<>();
        Random random = new Random();
        String[] categories = {"study", "sports", "business"};
        for (int i = 1; i <= 50; i++) {
            Article a = new Article(
                    "Title " + i,
                    "Content " + i,
                    categories[random.nextInt(4)],
                    LocalDate.now().minusDays(random.nextInt(10))
            );
            list.add(a);
        }
        repo.saveAll(list);
    }
}