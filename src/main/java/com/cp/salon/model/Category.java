package com.cp.salon.model;

import com.cp.salon.entity.CategoryEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {
    private Long id;
    private String name;

    public static Category toModel(CategoryEntity category){
        Category model = new Category();
        model.setId(category.getId());
        model.setName(category.getName());
        return model;
    }
}
