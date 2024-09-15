package com.example.taco;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Taco {

    public Taco() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date createdDate = new Date();

    @NonNull
    @Size(min = 5, max = 100, message = "نام انتحاب شده باید بین 5 الی 100 کاراکتر باشد.")
    private String name;
    @NonNull
    @Size(min = 1, message = "حد اقل یکی از موارد را انتخاب نمایید")
    @ManyToMany
    private List<Ingredient> ingredients = new ArrayList<>();

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);

    }

}
