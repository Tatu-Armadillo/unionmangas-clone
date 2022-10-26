package br.com.clone.unionmangas.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.clone.unionmangas.exception.NegocioException;
import br.com.clone.unionmangas.model.Category;
import br.com.clone.unionmangas.repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    
    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category findByName(String name) {
        var respose = this.categoryRepository.findByName(name);
        if (respose == null) {
            throw new NegocioException("Category Not Found");
        }
        return respose;
    }

    public Page<Category> findAllByName(Pageable pageable, String name) {
        var respose = this.categoryRepository.findAllByName(pageable, name);
        return respose;
    }

    public Set<Category> checkCategories(Set<Category> categories) {
        if (categories.isEmpty()) {
            throw new NegocioException("Unselected Categories");
        }
        Set<Category> response = new HashSet<>();
        categories.forEach(g -> {
            final var category = this.findByName(g.getName());
            response.add(category);
        });
        return response;
    }

}
