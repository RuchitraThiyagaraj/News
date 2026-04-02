package com.example.news.service;

import com.example.news.exception.ResourceNotFoundException;
import com.example.news.model.Source;
import com.example.news.repo.SourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SourceService {

    private final SourceRepository repo;

    public SourceService(SourceRepository repo) {
        this.repo = repo;
    }

    public Source addSource(Source source) {
        return repo.save(source);
    }

    public List<Source> getAllSources() {
        return repo.findAll();
    }

    public Source getSourceById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Source not found: " + id));
    }

    public Source getSourceByName(String name) {
        return repo.findByNameIgnoreCase(name)
                .orElseThrow(() -> new ResourceNotFoundException("Source not found: " + name));
    }

    public void deleteSource(int id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Source not found: " + id);
        }
        repo.deleteById(id);
    }
}