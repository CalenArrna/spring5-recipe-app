package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping("recipe")
@Controller
public class RecipeController {

    RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"/show/{recipe_id}", "/{recipe_id}"})
    public String getRecipeView(@PathVariable Long recipe_id, Model model) {
        Recipe recipe = recipeService.getRecipeById(recipe_id);
        model.addAttribute("recipe", recipe);
        return "recipe/show";
    }
}
