package com.example.news.repo;

import com.example.news.model.Source;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SourceRepository extends JpaRepository<Source, Integer> {

    List<Source> findByNameIgnoreCase(String name);
}