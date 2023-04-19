package com.cp.salon.service;

import com.cp.salon.entity.CategoryEntity;
import com.cp.salon.model.Category;
import com.cp.salon.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategotyService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAll(){
        List<CategoryEntity> categoriesEntity = categoryRepository.findAll();
        List<Category> categories = new ArrayList<Category>();
        for(CategoryEntity cat: categoriesEntity){
            categories.add(Category.toModel(cat));
        }
        return categories;
    }

    public void add(CategoryEntity category){
        categoryRepository.save(category);
    }

}
