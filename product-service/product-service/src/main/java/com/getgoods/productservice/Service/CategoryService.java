package com.getgoods.productservice.Service;

import com.getgoods.productservice.Repository.CategoryRepository;
import com.getgoods.productservice.Util.CategoryNotFoundException;
import com.getgoods.productservice.entity.Category;
import com.getgoods.productservice.entity.Product;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        Optional<Category> obj = categoryRepository.findById(id);
        if (obj.isPresent()) {
            return obj.get();
        } else {
            throw new CategoryNotFoundException("Category with id " + id + " not found");
        }
    }

    public Category add(Category category) {
        if (category.getProducts() != null) {
            for (Product p : category.getProducts()) {
                p.setCategory(category);
            }
        }
        return categoryRepository.save(category);
    }


    public Category update(Long id, Category category) {
        Category existing = findById(id);
        existing.setName(category.getName());
        return categoryRepository.save(existing);
    }

    public String deleteById(Long id) {
        Optional<Category> obj = categoryRepository.findById(id);
        if (obj.isPresent()) {
            categoryRepository.deleteById(id);
            return "Deleted Successfully";
        } else {
            throw new CategoryNotFoundException("Category with id " + id + " not found");
        }
    }

    public Category findByName(String name) {
        Optional<Category> cat = categoryRepository.findByName(name);
        if(cat.isPresent()){
            return cat.get();
        }
        else{
            throw new CategoryNotFoundException("Category with name " + name + " not found");
        }

    }
}
