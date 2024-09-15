package com.example.taco.web;

import com.example.taco.Ingredient;
import com.example.taco.Taco;
import com.example.taco.TacoOrder;
import com.example.taco.TacoTypes;
import com.example.taco.data.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;

@Autowired
    public DesignTacoController(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

//    @ModelAttribute
//    public void addIngredientsToModel(Model model) {
//        Iterable<Ingredient> ingredients = ingredientRepo.findAll();
//        Ingredient.Type[] types = Ingredient.Type.values();
//        Arrays.stream(types).parallel()
//                .forEach(type -> {
//                    model.addAttribute(type.toString().toLowerCase(),
//                            filterByType(ingredients, type));
//
//                });
//    }
    @ModelAttribute
public void addIngredientsToModel(Model model) {
    Iterable<Ingredient> ingredients = ingredientRepo.findAll();
        TacoTypes[] types = TacoTypes.values();
    for (TacoTypes type : types) {
        model.addAttribute(type.toString().toLowerCase(),
                filterByType(ingredients, type));
    }
}
// ...


    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping()
    public String showDesignForm() {
        return "design";
    }


    @PostMapping
    public String showDesignFormPost(@Validated Taco taco, Errors error, @ModelAttribute TacoOrder tacoOrder) {
        if (!error.hasErrors()) {

            tacoOrder.addTaco(taco);
            log.info("Processing taco: {}", taco);
            return "orderForm";
        } else {
            return "/design";
        }
    }

    private Iterable<Ingredient> filterByType(
            Iterable<Ingredient> ingredients, TacoTypes type) {
        return StreamSupport.stream(ingredients.spliterator(), false)
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

}