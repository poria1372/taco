package com.example.taco.web;

import com.example.taco.Ingredient;
import com.example.taco.data.IngredientRepository;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class IngredientByIdConverter implements Converter<Long, Ingredient> {

 private IngredientRepository ingredientRepo;

    @Autowired
    public IngredientByIdConverter(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public Ingredient convert(Long id) {
        return ingredientRepo.findById(id).orElse(null);
    }
}
